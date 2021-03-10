package mapper;

import dto.CarDto;
import entity.Car;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
@Mapper
public interface CarMapper {
  CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

  @Mapping(source = "createDate", target = "createDate", dateFormat = "yyyyMMdd")
  @Mapping(source = "numberOfSeats", target = "seatCount")
  @Mapping(source = "id.no", target = "no")
  @Mapping(source = "id.companyId", target = "companyId")
  CarDto carToCarDto(Car car);

  List<CarDto> catListToCatDtoList(List<Car> cars);

}
