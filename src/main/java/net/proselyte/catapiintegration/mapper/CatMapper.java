package net.proselyte.catapiintegration.mapper;

import net.proselyte.catapiintegration.dto.CatDto;
import net.proselyte.catapiintegration.model.Cat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMapper {
    CatDto toDto(Cat cat);
    List<CatDto> toDto(List<Cat> cats);
}
