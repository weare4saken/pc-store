package com.weare4saken.pcstore.model;

import com.weare4saken.pcstore.enums.FormFactor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pc extends Product {

    @Enumerated(EnumType.STRING)
    private FormFactor formFactor;

}
