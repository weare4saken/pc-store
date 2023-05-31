package com.weare4saken.pcstore.dto;

import com.weare4saken.pcstore.enums.FormFactor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PcDto extends ProductDto {

    private FormFactor formFactor;

}
