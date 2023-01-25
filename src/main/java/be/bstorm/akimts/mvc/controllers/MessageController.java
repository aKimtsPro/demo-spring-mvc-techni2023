package be.bstorm.akimts.mvc.controllers;

import be.bstorm.akimts.mvc.models.form.CalculatriceForm;
import be.bstorm.akimts.mvc.models.form.PersonneForm;
import be.bstorm.akimts.mvc.patterns.Personne;
import be.bstorm.akimts.mvc.services.CalculatriceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class MessageController {

    private final CalculatriceService calculatriceService;

    public MessageController(CalculatriceService calculatriceService) {
        this.calculatriceService = calculatriceService;
    }

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

    @GetMapping("/calc")
    public String displayCalc(Model model){
        model.addAttribute("form", new CalculatriceForm());
        return "calc/form";
    }

    @PostMapping("/calc")
    public String processCalc(Model model, CalculatriceForm form){
        model.addAttribute("rslt", calculatriceService.calc(form));
        return "calc/result";
    }

//    @PostMapping("/calc")
//    public String showParams(@RequestParam Map<String,String> params){
//        for (String s : params.keySet()) {
//            System.out.println(s + " : " + params.get(s));
//        }
//        return "index";
//    }
//
//    @PostMapping("/calc")
//    public String showParams(@RequestParam("operator") char op){
//        System.out.println( op );
//        return "index";
//    }

    @GetMapping("/pers/add")
    public String personneForm(@ModelAttribute("pers")PersonneForm f){
        return "personne/add";
    }

    @PostMapping("/pers/add")
    public String personneForm(@ModelAttribute("pers") @Valid PersonneForm f, BindingResult bindingResult){
        if( bindingResult.hasErrors() )
            return "personne/add";

        System.out.println( f.getNom() );
        System.out.println( f.getPrenom() );
        System.out.println( f.getDateNaiss() );

        return "personne/personne-view";
    }

}
