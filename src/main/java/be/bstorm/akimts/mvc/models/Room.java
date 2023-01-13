package be.bstorm.akimts.mvc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private int numRoom;
    private int nbrSimpleBed;
    private int nbrDoubleBed;
    private int size;
    private Hotel hotel;

}
