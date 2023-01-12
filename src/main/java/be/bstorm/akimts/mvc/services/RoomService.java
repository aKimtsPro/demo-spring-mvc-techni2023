package be.bstorm.akimts.mvc.services;

import be.bstorm.akimts.mvc.models.Room;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
public class RoomService {

    private final List<Room> rooms = new ArrayList<>();

    public RoomService() {
        rooms.add( new Room(1, 1, 0, 20) );
        rooms.add( new Room(2, 2, 0, 25) );
        rooms.add( new Room(3, 0, 1, 30) );
        rooms.add( new Room(15, 1, 1, 30) );
    }

    public List<Room> getAll(){
        return new ArrayList<>(rooms);
    }

    public Room getOne(int numRoom){
        return rooms.stream()
                .filter( room -> room.getNumRoom() == numRoom )
                .findFirst()
                .orElseThrow( () -> new RuntimeException("Pas de chambre avec ce numéro") );
    }
}
