package ru.bananus.mmarcane.common.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bananus.mmarcane.client.gui.TradeScreen;
import ru.bananus.mmarcane.init.ItemRegistry;
import ru.bananus.mmarcane.init.NetworkRegistry;
import ru.bananus.mmarcane.network.S2CShowTradeScreenPacket;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WizardEntity extends PathfinderMob implements GeoEntity, Merchant {

    public MerchantOffers offers = new MerchantOffers();

    public WizardEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        offers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 16), new ItemStack(ItemRegistry.MAGIC_WAND.get(), 1), 1, 1, 1));
    }

    public Player player;

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "animation", state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenPlay("story.npc.walk"));
            }
            return state.setAndContinue(RawAnimation.begin().thenPlay("story.npc.idle"));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        this.player = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return player;
    }

    @Override
    public @NotNull MerchantOffers getOffers() {
        return offers;
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand interactionHand) {
        /*
        if (!player.level().isClientSide && interactionHand == InteractionHand.MAIN_HAND) {
            if (offers != null && Screen.hasShiftDown()) {
                NetworkRegistry.CHANNEL.sendToPlayer((ServerPlayer) player, new S2CShowTradeScreenPacket());
            }
        }
        if (player.level().isClientSide && interactionHand == InteractionHand.MAIN_HAND) {
            if (offers != null && Screen.hasShiftDown()) {
                Minecraft mc = Minecraft.getInstance();
                mc.setScreen(new TradeScreen(offers));
            }
        }
         */
        if (!player.level().isClientSide && interactionHand == InteractionHand.MAIN_HAND) {
            if (offers != null && Screen.hasShiftDown()) {
                setTradingPlayer(player);
                openTradingScreen(player, Component.translatable("entity.mmarcane.wizard"), 0);
            }
        }
        return super.interactAt(player, vec3, interactionHand);
    }

    @Override
    public void overrideOffers(MerchantOffers merchantOffers) {

    }

    @Override
    public void notifyTrade(MerchantOffer merchantOffer) {
        merchantOffer.increaseUses();
        Level level = player.level();
        if (!level.isClientSide()) {
            ExperienceOrb.award((ServerLevel) level, player.position(), merchantOffer.getXp());
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack itemStack) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int i) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.VILLAGER_YES;
    }

    @Override
    public boolean isClientSide() {
        if (player != null) {
            return player.level().isClientSide();
        } else {
            return false;
        }
    }
}
