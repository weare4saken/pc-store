package com.weare4saken.pcstore.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String serialNumber;
    private String producer;
    private Double price;
    private Integer amount;

}
