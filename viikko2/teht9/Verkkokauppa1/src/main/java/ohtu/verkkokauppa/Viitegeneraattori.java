package ohtu.verkkokauppa;

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
