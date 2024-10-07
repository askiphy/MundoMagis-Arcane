package ru.bananus.mmarcane.api.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JSONSerializer {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static InputStreamReader createInput(File f) throws FileNotFoundException
    { return new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8); }

    public static OutputStreamWriter createOutput(File f) throws FileNotFoundException
    { return new OutputStreamWriter(new FileOutputStream(f),StandardCharsets.UTF_8); }

    public static <T> T toJava(File f, Class<T> classOf) throws InstantiationException, IllegalAccessException {
        try {
            f.createNewFile();
            InputStreamReader reader = createInput(f);
            T data = gson.fromJson(reader,classOf);
            if(data == null) data = classOf.newInstance();
            toJson(f,data);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return classOf.newInstance();
        }
    }

    public static void toJson(File f, Object data) {
        try {
            f.createNewFile();
            OutputStreamWriter writer = createOutput(f);
            gson.toJson(data,writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
