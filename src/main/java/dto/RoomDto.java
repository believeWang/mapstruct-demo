package dto;

import java.util.List;
import lombok.Data;

@Data
public class RoomDto {

  private String roomNo;

  private List<String> students;

}
