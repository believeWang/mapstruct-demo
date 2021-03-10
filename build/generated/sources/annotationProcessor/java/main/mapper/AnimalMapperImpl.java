package mapper;

import dto.AnimalDto;
import entity.Animal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-10T14:21:24+0800",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.1.1.jar, environment: Java 11.0.7 (AdoptOpenJDK)"
)
public class AnimalMapperImpl implements AnimalMapper {

    @Override
    public AnimalDto animalToAnimalDto(Animal animal) {
        if ( animal == null ) {
            return null;
        }

        AnimalDto animalDto = new AnimalDto();

        animalDto.setHeight( animal.getHeight() );
        animalDto.setWeight( animal.getWeight() );

        return animalDto;
    }
}
