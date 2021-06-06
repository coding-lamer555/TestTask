package me.codinglamer.testtask.data.datasource.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoodsApiModel {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("preview")
    private String preview;

    @SerializedName("gallery")
    private List<String> gallery;

    @SerializedName("price")
    private Double price;

    @SerializedName("rating")
    private Double rating;

    @SerializedName("weight")
    private Double weight;
}
