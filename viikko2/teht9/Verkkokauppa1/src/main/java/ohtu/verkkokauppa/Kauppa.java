package ohtu.verkkokauppa;

public class Kauppa {

    private Varastoiva varasto;
    private Maksava pankki;
    private Ostoskori ostoskori;
    private Numeroiva viitegeneraattori;
    private String kaupanTili;
    
    public Kauppa(Varastoiva varasto, Maksava pankki, Numeroiva viite, String tili) {
        this.varasto = varasto;
        this.pankki = pankki;
        viitegeneraattori = viite;
        kaupanTili = tili;
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
