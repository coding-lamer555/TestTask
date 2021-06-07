package me.codinglamer.testtask.core;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class BaseListTypeConverter<T> {

    private final Gson gson = new Gson();

    public BaseListTypeConverter() {

    }

    @TypeConverter
    public List<T> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<T>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String listToString(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }

        return gson.toJson(list);
    }
}
