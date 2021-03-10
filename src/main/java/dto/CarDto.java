package dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CarDto {

  private String no;
  private String companyId;
  private String name;
  private String carType;
  private String createDate;
  private Integer seatCount;
}
