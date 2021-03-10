import consts.CarType;
import dto.AnimalDto;
import dto.CarDto;
import dto.RoomDto;
import entity.Car;
import entity.CarPk;
import entity.Cat;
import entity.Room;
import entity.Student;
import java.time.LocalDate;
import java.util.Arrays;
import mapper.AnimalMapper;
import mapper.CarMapper;
import mapper.RoomMapper;

public class Main {

  public static void main(String[] args) {
    //
    CarMapper mapper = CarMapper.INSTANCE;

    CarPk pk = new CarPk("10", "23");
    Car tesla =
        Car.builder()
            .carType(CarType.SPORTS)
            .name("Tesla")
            .numberOfSeats(4)
            .createDate(LocalDate.now())
            .id(pk)
            .build();

    System.out.println(tesla);
    CarDto carDto = mapper.carToCarDto(tesla);
    System.out.println(carDto);
    System.out.println(mapper.catListToCatDtoList(Arrays.asList(tesla)));

    //
    RoomMapper roomMapper = RoomMapper.INSTANCE;

    Student student = new Student("Jack");
    Room room = Room.builder().roomNo("201").student(student).build();

    System.out.println(room);
    RoomDto roomDto = roomMapper.roomToRoomDto(room);
    System.out.println(roomDto);

    //
    AnimalMapper animalMapper = AnimalMapper.INSTANCE;
    Cat cat = new Cat();
    cat.setHeight(1);
    cat.setWeight(5);

    System.out.println(cat);
    AnimalDto animalDto = animalMapper.animalToAnimalDto(cat);
    System.out.println(animalDto);
  }
}
