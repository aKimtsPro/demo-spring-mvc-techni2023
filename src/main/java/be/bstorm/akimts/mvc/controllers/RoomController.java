package be.bstorm.akimts.mvc.controllers;


import be.bstorm.akimts.mvc.models.Room;
import be.bstorm.akimts.mvc.services.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    // 3 conditions à DI:
    // 1 - je peux injecter une dépendance pour créer un Bean
    // 2 - il existe un Bean qui correspond au type de la dépendance souhaitée
    // 3 - la dépendance est déclarée

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    //GET - /room/all -> voir les chambres
    @GetMapping("/all")
    public String allRooms(Model model){
        List<Room> rooms = roomService.getAll();

        model.addAttribute("rooms", rooms);

        return "room/all";
    }


    //GET - /room/1   -> voir la chambre num 1
    @GetMapping("/{numRoom}")
    public String oneRoom(Model model, @PathVariable int numRoom){
        model.addAttribute("room", roomService.getOne( numRoom ));

        return "room/one";
    }

    @GetMapping("/add")
    public String insertForm(Model model){
        model.addAttribute("room", new Room());
        return "room/insert";
    }

    @PostMapping("/add")
    public String processInsert(Room form){
        roomService.insert( form );
        return "redirect:all";
    }

    @GetMapping("/update/{num}")
    public String updateForm(Model model, @PathVariable int num){
        Room toUpdate = roomService.getOne( num );

        model.addAttribute("room", toUpdate);

        return "room/update";
    }
    
    @PostMapping("/update/{num}")
    public String processUpdate(Room room, @PathVariable int num){
        roomService.update( num, room );

        return "redirect:/room/all";
    }

}


