package me.codinglamer.testtask.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoodsPreviewEntity {

    private Integer id;
    private String preview;
    private String price;
    private String rating;
    private String name;
    private Integer countInCart;
}
