package com.dlubera.grave.manager.service.domain.core.relative;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
abstract class RelativeMapper {

    static final RelativeMapper INSTANCE = Mappers.getMapper(RelativeMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "age", expression = "java(MappingFunctions.getAge(createRequestDto))")

    abstract Relative toRelative(RelativeCreateRequestDto createRequestDto);

    abstract Relative toRelative(RelativeEntity relativeEntity);

    @Mapping(target = "graveId", ignore = true)
    abstract RelativeEntity toRelativeEntity(Relative relative);

    abstract RelativeDto toRelativeDto(Relative relative);

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static final class MappingFunctions {

        static int getAge(RelativeCreateRequestDto createRequestDto) {
            var birthDate = createRequestDto.getDateOfBirth().getYear();
            var deathDate = createRequestDto.getDateOfDeath().getYear();
            return deathDate - birthDate;
        }
    }
}
