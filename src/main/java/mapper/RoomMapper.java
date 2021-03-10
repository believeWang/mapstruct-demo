package mapper;

import dto.RoomDto;
import entity.Room;
import java.util.Arrays;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
  RoomMapper INSTANCE = Mappers.getMapper( RoomMapper.class );

  @Mapping(source = "room", target = "students", qualifiedByName = "studentToString")
  RoomDto roomToRoomDto(Room room);

  @Named("studentToString")
  default List<String> locationToLocationDto(Room room) {
    return Arrays.asList(room.getStudent().getStudentName());
  }

}
