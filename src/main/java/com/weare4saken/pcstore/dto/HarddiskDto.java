package com.weare4saken.pcstore.dto;

import com.weare4saken.pcstore.enums.Capacity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HarddiskDto extends ProductDto{

    private Capacity capacity;

}
