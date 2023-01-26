package be.bstorm.akimts.mvc.utils;

import be.bstorm.akimts.mvc.models.entity.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
public class DataInit implements InitializingBean {

    private final EntityManager manager;

    public DataInit(EMFSharer emfSharer) {
        this.manager = emfSharer.createEntityManager();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        manager.getTransaction().begin();

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstname("luc");
        employee.setLastname("dubois");
        employee.setAddress("une adresse");
        employee.setEmail("@.com");
        employee.setPhone("000");

        employee = manager.merge(employee);

        Hotel hotel = manager.find(Hotel.class,1L);
        hotel.setReceptionist(employee);

        Room room = new Room();
        room.setId(2L);
        room.setSize(10);
        room.setHotel(hotel);
        room.setAvailable(true);
        room.setDoubleBeds(1);
        room.setNumero(101);
        room.setFloor(1);
        room.setType(RoomType.BASIC);
        room.setView(RoomView.AVERAGE);

        manager.merge( room );

        User user = new User();

        user.setId(2L);
        user.setUsername("user");
        user.setPassword("pass");
        user.setRole("customer");
        user.setAddress("adresse");
        user.setEmail("email");
        user.setPhone("phone");
        user.setFirstname("luc");
        user.setLastname("dubois");

        manager.merge(user);

        Reservation reservation = new Reservation();

        reservation.setId(1L);
        reservation.setStart(LocalDateTime.of(2023, 10, 29,12,0,0));
        reservation.setEnd(LocalDateTime.of(2023, 11, 02,12,0,0));
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setPrice(100);
        reservation.setBreakfastIncluded(true);
        reservation.setCreatedAt(LocalDateTime.now());

        manager.merge(reservation);

        reservation = new Reservation();

        reservation.setId(2L);
        reservation.setStart(LocalDateTime.of(2023, 9, 30,12,0,0));
        reservation.setEnd(LocalDateTime.of(2023, 10, 3,12,0,0));
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setPrice(100);
        reservation.setBreakfastIncluded(true);
        reservation.setCreatedAt(LocalDateTime.now());

        manager.merge(reservation);


        reservation = new Reservation();

        reservation.setId(3L);
        reservation.setStart(LocalDateTime.of(2023, 10, 3,12,0,0));
        reservation.setEnd(LocalDateTime.of(2023, 10, 30,12,0,0));
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setPrice(100);
        reservation.setBreakfastIncluded(false);
        reservation.setCreatedAt(LocalDateTime.now());

        manager.merge(reservation);

        manager.getTransaction().commit();

    }
}
