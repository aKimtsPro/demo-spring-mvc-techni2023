package be.bstorm.akimts.mvc.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Hotel {

    private long id;
    private int stars;
    private String receptionist;
    private String name;
    private String address;
    private List<Room> rooms;

}
