package com.weare4saken.pcstore.dto;

import com.weare4saken.pcstore.enums.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LaptopDto extends ProductDto {

    private Size size;

}
