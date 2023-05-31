package com.weare4saken.pcstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private String serialNumber;
    private String producer;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Double price;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Integer amount;

}
