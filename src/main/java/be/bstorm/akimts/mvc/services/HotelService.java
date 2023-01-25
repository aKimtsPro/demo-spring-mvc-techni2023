package be.bstorm.akimts.mvc.services;

import be.bstorm.akimts.mvc.models.Hotel;
import be.bstorm.akimts.mvc.models.form.HotelForm;
import be.bstorm.akimts.mvc.models.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    private final List<Hotel> list = new ArrayList<>();

    public HotelService(RoomService roomService) {
        List<Room> rooms = new ArrayList<>();
        rooms.add( roomService.getOne(1) );
        rooms.add( roomService.getOne(4) );
        rooms.add( roomService.getOne(7) );

        list.add(
                Hotel.builder()
                        .id(1)
                        .receptionist("Jack")
                        .stars(3)
                        .name("Overlook")
                        .address("Timberline Lodge, Mt. Hood, Oregon")
                        .rooms( rooms )
                        .build()
        );
        rooms.forEach( r -> r.setHotel( list.get(0) ) );

        rooms = new ArrayList<>();
        rooms.add( roomService.getOne(2) );
        rooms.add( roomService.getOne(5) );
        rooms.add( roomService.getOne(10) );

        list.add(
                Hotel.builder()
                        .id(2)
                        .receptionist("Norma Jennings")
                        .stars(4)
                        .name("The Great Northern")
                        .address("Twin Peaks")
                        .rooms( rooms )
                        .build()
        );
        rooms.forEach( r -> r.setHotel( list.get(1) ) );

        rooms = new ArrayList<>();
        rooms.add( roomService.getOne(3) );
        rooms.add( roomService.getOne(6) );
        rooms.add( roomService.getOne(15) );

        list.add(
                Hotel.builder()
                        .id(3)
                        .receptionist("Mr. Gustave")
                        .stars(5)
                        .name("The Grand Budapest")
                        .address("Gorlitz, Germany")
                        .rooms( rooms )
                        .build()
        );
        rooms.forEach( r -> r.setHotel( list.get(2) ) );
    }

    public List<Hotel> getAll(){
        return new ArrayList<>( list );
    }

    public Hotel getById(long id){
        return list.stream()
                .filter( h -> h.getId() == id )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("hotel introuvable"));
    }

    public void create(HotelForm form){
        list.add(
                Hotel.builder()
                        .id(form.getId())
                        .stars(form.getStars())
                        .receptionist(form.getReceptionist())
                        .address(form.getAddress())
                        .name(form.getName())
                        .build()
        );
    }
}
