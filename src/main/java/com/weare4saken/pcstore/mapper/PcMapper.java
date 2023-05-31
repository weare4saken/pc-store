package com.weare4saken.pcstore.mapper;

import com.weare4saken.pcstore.dto.PcDto;
import com.weare4saken.pcstore.model.Pc;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PcMapper {

    PcMapper INSTANCE = Mappers.getMapper(PcMapper.class);

    PcDto toDto(Pc pc);
    Pc toEntity(PcDto pcDto);

}
