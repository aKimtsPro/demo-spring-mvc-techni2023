package be.bstorm.akimts.mvc.services;

import be.bstorm.akimts.mvc.models.Room;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final List<Room> rooms = new ArrayList<>();

    public RoomService() {
        rooms.add( new Room(1, 1, 0, 20, null) );
        rooms.add( new Room(2, 1, 0, 20, null) );
        rooms.add( new Room(3, 2, 0, 25, null) );
        rooms.add( new Room(4, 2, 0, 25, null) );
        rooms.add( new Room(5, 2, 0, 25, null) );
        rooms.add( new Room(6, 0, 1, 30, null) );
        rooms.add( new Room(7, 0, 1, 30, null) );
        rooms.add( new Room(10, 1, 1, 30, null) );
        rooms.add( new Room(15, 1, 1, 30, null) );
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

    public void insert(Room room){
        if(
            room.getNumRoom() <= 0 ||
            room.getNbrSimpleBed() < 0 ||
            room.getNbrDoubleBed() < 0 ||
            room.getSize() < 0
        ) throw new RuntimeException("valeur invalide");

        rooms.add(room);
    }

    public void update(int num, Room room){
        if( room.getNbrSimpleBed() < 0 || room.getNbrDoubleBed() < 0 || room.getSize() < 0 )
            throw new RuntimeException("valeur invalide");

        Room toUpdate = getOne(num);

        toUpdate.setNbrSimpleBed( room.getNbrSimpleBed() );
        toUpdate.setNbrDoubleBed( room.getNbrDoubleBed() );
        toUpdate.setSize( room.getSize() );
    }
}
