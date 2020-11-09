package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class Viitegeneraattori implements Numeroiva {

    private int viite;
    
    public Viitegeneraattori() {
        this.viite = 1;
    }
    
    @Override
    public int uusi(){
        return viite++;
    }
}
