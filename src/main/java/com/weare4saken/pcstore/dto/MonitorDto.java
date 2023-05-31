package com.weare4saken.pcstore.dto;

import com.weare4saken.pcstore.enums.Diagonal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MonitorDto extends ProductDto {

    private Diagonal diagonal;

}
