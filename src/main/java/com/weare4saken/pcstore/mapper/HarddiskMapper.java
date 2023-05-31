package com.weare4saken.pcstore.mapper;

import com.weare4saken.pcstore.dto.HarddiskDto;
import com.weare4saken.pcstore.model.Harddisk;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HarddiskMapper {

    HarddiskMapper INSTANCE = Mappers.getMapper(HarddiskMapper.class);

    HarddiskDto toDto(Harddisk harddisk);
    Harddisk toEntity(HarddiskDto harddiskDto);

}
