package entity;

import consts.CarType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {

  //embeddedId
  private CarPk id;

  private String name;
  private CarType carType;
  private LocalDate createDate;
  private Integer numberOfSeats;

}
