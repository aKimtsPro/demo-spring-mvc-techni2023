package be.bstorm.akimts.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

    // GET - http://localhost:8080/message
    @GetMapping("/message")
    public String displayMessage(Model model){
        String message = "mon message depuis le controller";

        model.addAttribute("message", message);

        return "message-view";
    }

    // GET - http://localhost:8080/redirect
    @GetMapping("/redirect")
    public String redirect(){
        return "redirect:message";
    }

    // GET - http://localhost:8080/forward
    @GetMapping("/forward")
    public String forward(){
        System.out.println("on passe par ici!!");
        return "forward:message";
    }

}
