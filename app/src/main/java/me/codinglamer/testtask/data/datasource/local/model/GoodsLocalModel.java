package me.codinglamer.testtask.data.datasource.local.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.codinglamer.testtask.data.datasource.local.converter.StringListTypeConverter;

@AllArgsConstructor
@Builder
@Entity(tableName = "goods")
public class GoodsLocalModel {

    @NonNull
    @PrimaryKey
    public Integer id;

    public String name;

    public String description;

    public String preview;

    @TypeConverters(StringListTypeConverter.class)
    public List<String> gallery;

    public Double price;

    public Double rating;

    public Double weight;

    public Integer countInCart;
}
