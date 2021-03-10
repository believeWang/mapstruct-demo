# 透過 Mapstruct 取代 BeanUtils.copyProperties
## _Java bean mappings, the easy way!_

## 前言

在現在流行的架構中，將對DB的entity與對view的model做分離，但欄位是彼此相關的，所以在過往總是會利用各式套件提供的`copyProperties`來轉換，隨著需求的變動，欄位可能逐漸增加，此時透過`reflection`實作的`copyProperties`效能問題就會越來越明顯。

此時就可以利用`Mapstruct`快速且有效地完成 java bean mapping
>MapStruct is a code generator that greatly simplifies the implementation of mappings between Java bean types based on a convention over configuration approach.
>The generated mapping code uses plain method invocations and thus is fast, type-safe and easy to understand.

## 環境

```
dependencies {
    ...
    implementation 'org.mapstruct:mapstruct:1.4.2.Final'
 
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.4.2.Final'
}
```
[官網](https://mapstruct.org/documentation/installation/)

## 基本運用
範例中會有`entity`代表對應DB實體，`dto`代表對應view物件，`mapper`代表Mapstruct的工具類別。

Car實體，有子物件(embeddedId)、字串、enum、數字，應該能滿足大部分的情境
```
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

```
CarDto，對應view，通常不需要把ID封裝成一個子物件且大部分欄位是字串，也有一個欄位名稱與entity不同。
```
@Data
public class CarDto {

  private String no;
  private String companyId;
  private String name;
  private String carType;
  private String createDate;
  private Integer seatCount;
}
```
若搭配spring framework使用`componentModel="spring"`，即可用`@Autowired`注入，範例使用 default 需透過 INSTANCE取得。
- 時間可透過`dateFormat`來轉換，十分方便
- 若欄位名稱不同，需手動指定對應名稱，若相同則不需特別寫@`Mapping`
- 子物件對應也可以透過`.`來對應
- 陣列一樣也可以轉換，`FOR` 迴圈一個一個轉


```
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
```
自動產生的code如下
```
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
```
## 自訂mapping

有時候會有一些特別的需求，希望可以自定義，這邊假設對應的view物件是需要一個學生姓名的array，需要從`Student` 實體去做轉換。

```
@Data
@Builder
public class Room {

  private String roomNo;
  private Student student;
}
```
```
@Data
public class RoomDto {

  private String roomNo;

  private List<String> students;

}
```
可以透過`qualifiedByName`自訂轉換方式
```
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
```

## 疑難雜症

1. 若VsCode無法使用，可在`build.gradle`內加上這段
```
// Using mapstruct for VSCode
sourceSets {
    main.java.srcDirs += "build/generated/sources/annotationProcessor/java/main"
    generated {
        java {
            srcDirs = ['src/generated/java']
        }
    }
}

```
## 參考資料

[官網](https://mapstruct.org/)
