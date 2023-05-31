package com.weare4saken.pcstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public abstract class Product {

    @Id
    private String serialNumber;
    private String producer;
    private Double price;
    private Integer amount;

}
