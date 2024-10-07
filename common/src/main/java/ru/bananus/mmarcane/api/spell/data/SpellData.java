package ru.bananus.mmarcane.api.spell.data;

public class SpellData {
    private String spellId;
    private int wandLevel;
    private String chatTrigger;

    public SpellData(String spellId, int wandLevel) {
        this.spellId = spellId;
        this.wandLevel = wandLevel;
        this.chatTrigger = null;
    }

    public SpellData(String spellId, int wandLevel, String chatTrigger) {
        this.spellId = spellId;
        this.wandLevel = wandLevel;
        this.chatTrigger = chatTrigger;
    }

    public String getSpellId() {
        return spellId;
    }

    public int getWandLevel() {
        return wandLevel;
    }

    public String getChatTrigger() {
        return chatTrigger;
    }

    public static class Builder {
        private String spellId;
        private int wandLevel;
        private String chatTrigger;

        public Builder spellId(String spellId) {
            this.spellId = spellId;
            return this;
        }

        public Builder chatTrigger(String chatTrigger) {
            this.chatTrigger = chatTrigger;
            return this;
        }

        public Builder wandLevel(int wandLevel) {
            this.wandLevel = wandLevel;
            return this;
        }

        public SpellData build() {
            return new SpellData(spellId, wandLevel, chatTrigger);
        }
    }
}
