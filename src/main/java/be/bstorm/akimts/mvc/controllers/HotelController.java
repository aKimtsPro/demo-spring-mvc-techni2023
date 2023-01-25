package be.bstorm.akimts.mvc.controllers;

import be.bstorm.akimts.mvc.models.form.HotelForm;
import be.bstorm.akimts.mvc.services.HotelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    // GET - http://localhost:8080/hotel/1
    @GetMapping("/{id:[0-9]+}")
    public String displayOne(Model model, @PathVariable long id){
        model.addAttribute("hotel", hotelService.getById(id));
        return "hotel/one";
    }

    @GetMapping("/add")
    public String insertForm(@ModelAttribute("form") HotelForm form){
        return "hotel/insert";
    }

    @PostMapping("/add")
    public String processInsert(@ModelAttribute("form") @Valid HotelForm form, BindingResult bindingResult){
        if( bindingResult.hasErrors() ) {
            return "hotel/insert";
        }

        hotelService.create(form);
        return "redirect:all";
    }

}
