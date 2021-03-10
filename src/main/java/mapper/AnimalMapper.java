package mapper;

import dto.AnimalDto;
import entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimalMapper {
  AnimalMapper INSTANCE = Mappers.getMapper( AnimalMapper.class );

  AnimalDto animalToAnimalDto(Animal animal);


}
