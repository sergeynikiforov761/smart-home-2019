package ru.sbt.mipt.oop.readers;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.homeelements.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReaderSmartHome implements Reader<SmartHome> {
    private String path;

    // считываем состояние дома из файла
    @Override
    public SmartHome read() {
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException error) {
            System.err.println(error);
        }
        return null;
    }
}
