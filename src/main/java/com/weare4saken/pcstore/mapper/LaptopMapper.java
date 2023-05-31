package com.weare4saken.pcstore.mapper;

import com.weare4saken.pcstore.dto.LaptopDto;
import com.weare4saken.pcstore.model.Laptop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LaptopMapper {

    LaptopMapper INSTANCE = Mappers.getMapper(LaptopMapper.class);

    LaptopDto toDto(Laptop laptop);
    Laptop toEntity(LaptopDto laptopDto);

}
