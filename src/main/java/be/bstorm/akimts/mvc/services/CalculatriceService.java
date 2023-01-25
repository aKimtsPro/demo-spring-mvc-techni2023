package be.bstorm.akimts.mvc.services;

import be.bstorm.akimts.mvc.models.form.CalculatriceForm;
import org.springframework.stereotype.Service;

@Service
public class CalculatriceService {

    public int calc(CalculatriceForm form){
        int rslt;
        switch (form.getOperator()) {
            case '+'    ->  rslt = form.getNbr1() + form.getNbr2();
            case '-'    ->  rslt = form.getNbr1() - form.getNbr2();
            case '*'    ->  rslt = form.getNbr1() * form.getNbr2();
            case '/'    ->  {
                if( form.getNbr2() == 0 )
                    throw new IllegalArgumentException("division par 0 impossible");

                rslt = form.getNbr1() / form.getNbr2();
            }
            case '%'    ->  {
                if( form.getNbr2() == 0 )
                    throw new IllegalArgumentException("modulo par 0 impossible");

                rslt = form.getNbr1() % form.getNbr2();
            }
            default     ->  throw new IllegalStateException("Unexpected value: " + form.getOperator());
        }
        return rslt;
    }

}
