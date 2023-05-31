package com.weare4saken.pcstore.model;

import com.weare4saken.pcstore.enums.FormFactor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table
public class Pc extends Product {

    private FormFactor formFactor;

}