package com.example.kokeroulis.retrofit_bug.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class Github implements JsonSerializer<Github>, JsonDeserializer<Github> {
    public int id;
    public String name;
    public Owner owner;
    public String url;
    public int size;
    public Organization organization;
    public String foo;

    @Override
    public JsonElement serialize(Github src, Type typeOfSrc, JsonSerializationContext context) {
        // it never calls this one....
        src.foo = "this is foo";
        return context.serialize(src);
    }

    @Override
    public Github deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // this one gets called.
        Github github = new Gson().fromJson(json, Github.class);
        Log.i("info", "test that the deserializer works!");
        return github;
    }
}
