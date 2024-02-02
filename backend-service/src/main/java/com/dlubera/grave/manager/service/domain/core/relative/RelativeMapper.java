package com.dlubera.grave.manager.service.domain.core.relative;

import com.dlubera.grave.manager.service.exception.GraveManagerServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.Base64;

@Mapper
abstract class RelativeMapper {

    static final RelativeMapper INSTANCE = Mappers.getMapper(RelativeMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "age", expression = "java(MappingFunctions.getAge(createRequestDto))")
    @Mapping(target = "image", expression = "java(MappingFunctions.getBytes(createRequestDto))")
    abstract Relative toRelative(RelativeCreateRequestDto createRequestDto);

    abstract Relative toRelative(RelativeEntity relativeEntity);

    @Mapping(target = "graveId", ignore = true)
    abstract RelativeEntity toRelativeEntity(Relative relative);

    @Mapping(target = "imageBase64", expression = "java(MappingFunctions.getBase64(relative))")
    abstract RelativeDto toRelativeDto(Relative relative);

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static final class MappingFunctions {

        static int getAge(RelativeCreateRequestDto createRequestDto) {
            var birthDate = createRequestDto.getDateOfBirth().getYear();
            var deathDate = createRequestDto.getDateOfDeath().getYear();
            return deathDate - birthDate;
        }

        static byte[] getBytes(RelativeCreateRequestDto createRequestDto) {
            try {
                return createRequestDto.getImage().getBytes();
            } catch (IOException e) {
                throw new GraveManagerServiceException("Failed to read bytes from relative image.", e);
            }
        }

        static String getBase64(Relative relative) {
            return Base64.getEncoder().encodeToString(relative.getImage());
        }
    }
}
