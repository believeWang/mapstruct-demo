package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {

  private String roomNo;

  private Student student;

}
