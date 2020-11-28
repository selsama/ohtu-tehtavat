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
public class Nollaa extends Komento {
    
    public Nollaa(TextField tulos, TextField syote, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tulos, syote, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        super.sailoEdellinen();
        sovellus.nollaa();
        super.naytaTulos();
    }
       
}
