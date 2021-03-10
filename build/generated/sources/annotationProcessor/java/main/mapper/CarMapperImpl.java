package mapper;

import dto.CarDto;
import entity.Car;
import entity.CarPk;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-10T14:21:24+0800",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.1.1.jar, environment: Java 11.0.7 (AdoptOpenJDK)"
)
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto carToCarDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        if ( car.getCreateDate() != null ) {
            carDto.setCreateDate( DateTimeFormatter.ofPattern( "yyyyMMdd" ).format( car.getCreateDate() ) );
        }
        carDto.setSeatCount( car.getNumberOfSeats() );
        carDto.setNo( carIdNo( car ) );
        carDto.setCompanyId( carIdCompanyId( car ) );
        carDto.setName( car.getName() );
        if ( car.getCarType() != null ) {
            carDto.setCarType( car.getCarType().name() );
        }

        return carDto;
    }

    @Override
    public List<CarDto> catListToCatDtoList(List<Car> cars) {
        if ( cars == null ) {
            return null;
        }

        List<CarDto> list = new ArrayList<CarDto>( cars.size() );
        for ( Car car : cars ) {
            list.add( carToCarDto( car ) );
        }

        return list;
    }

    private String carIdNo(Car car) {
        if ( car == null ) {
            return null;
        }
        CarPk id = car.getId();
        if ( id == null ) {
            return null;
        }
        String no = id.getNo();
        if ( no == null ) {
            return null;
        }
        return no;
    }

    private String carIdCompanyId(Car car) {
        if ( car == null ) {
            return null;
        }
        CarPk id = car.getId();
        if ( id == null ) {
            return null;
        }
        String companyId = id.getCompanyId();
        if ( companyId == null ) {
            return null;
        }
        return companyId;
    }
}
