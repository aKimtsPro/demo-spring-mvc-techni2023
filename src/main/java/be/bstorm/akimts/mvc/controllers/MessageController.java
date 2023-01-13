package be.bstorm.akimts.mvc.controllers;

import be.bstorm.akimts.mvc.patterns.Personne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @GetMapping("/pers")
    public String personnes(Model model){
        model.addAttribute("p",
                List.of(
                        Personne.builder("paul-luc")
                                .nom("deschamps")
                                .build()
                )
        );
        return "personne";
    }

    @GetMapping("/text/{nbr:[0-9]+}")
    public String getTexts(Model model, @PathVariable int nbr){

        List<String> mots = new ArrayList<>();
        Random rdm = new Random();

        for (int i = 0; i < nbr; i++) {
            StringBuilder sb = new StringBuilder();
            int taille = rdm.nextInt(5,11);
            for (int j = 0; j < taille; j++) {
                sb.append( (char)rdm.nextInt('a', 'z'+1) );
            }
            mots.add( sb.toString() );
        }

        model.addAttribute("mots", mots);

        return "display-mots";
    }

}
