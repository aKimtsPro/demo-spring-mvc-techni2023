package be.bstorm.akimts.mvc.controllers;

import be.bstorm.akimts.mvc.services.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/all")
    public String displayAll(Model model){
        model.addAttribute("hotels", hotelService.getAll());
        return "hotel/all";
    }

    @GetMapping("/{id}")
    public String displayOne(Model model, @PathVariable long id){
        model.addAttribute("hotel", hotelService.getById(id));
        return "hotel/one";
    }

}
