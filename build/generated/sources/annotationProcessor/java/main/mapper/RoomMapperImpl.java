package mapper;

import dto.RoomDto;
import entity.Room;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-10T14:21:24+0800",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.1.1.jar, environment: Java 11.0.7 (AdoptOpenJDK)"
)
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDto roomToRoomDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setStudents( locationToLocationDto( room ) );
        roomDto.setRoomNo( room.getRoomNo() );

        return roomDto;
    }
}
