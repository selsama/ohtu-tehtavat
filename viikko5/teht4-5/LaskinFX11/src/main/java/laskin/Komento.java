/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 *
 * @author salmison
 */
public abstract class Komento {
    
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;
    protected int edellinenArvo;
    
    public Komento(TextField tulos, TextField syote, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tulos;
        this.syotekentta = syote;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }
    
    public abstract void suorita();
    
    public void sailoEdellinen() {
        edellinenArvo = sovellus.tulos();
    }
    
    public void peru() {
        sovellus.setTulos(edellinenArvo);
        this.naytaTulos();
    }
    
    public void naytaTulos() {
        tuloskentta.setText(sovellus.tulos() + "");
    }
    
    public int getSyote() {
        try {
            return Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            return 0;
        }
    }
}
