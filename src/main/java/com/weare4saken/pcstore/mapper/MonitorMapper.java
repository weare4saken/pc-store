package com.weare4saken.pcstore.mapper;

import com.weare4saken.pcstore.dto.MonitorDto;
import com.weare4saken.pcstore.model.Monitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MonitorMapper {

    MonitorMapper INSTANCE = Mappers.getMapper(MonitorMapper.class);

    MonitorDto toDto(Monitor monitor);
    Monitor toEntity(MonitorDto monitorDto);

}
