
package ohtu.intjoukkosovellus;

public class IntJoukko {

    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this.alusta(5, 5);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        this.alusta(kapasiteetti, 5);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }
        this.alusta(kapasiteetti, kasvatuskoko);
    }
    
    private void alusta(int kapasiteetti, int kasvatuskoko) {
        this.kasvatuskoko = kasvatuskoko;
        this.alustaTyhjaJoukko(kapasiteetti);
        this.alkioidenLkm = 0;
    }
    
    private void alustaTyhjaJoukko(int koko) {
        ljono = new int[koko];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm == ljono.length) {
            this.suurennaTaulukkoa();
        }
        return true;
    }

    public boolean kuuluu(int luku) {
        return this.etsiLuku(luku) != -1;
    }
    
    private int etsiLuku(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int kohta = this.etsiLuku(luku);
        if (kohta != -1) {
            this.siirraLuvutVasemmalleAlkaen(kohta);
            alkioidenLkm--;
            return true;
        }
        return false;
    }
    
    private void siirraLuvutVasemmalleAlkaen(int indeksi) {
        for (int j = indeksi; j < alkioidenLkm - 1; j++) {
            ljono[j] = ljono[j + 1];
        }
    }

    private void suurennaTaulukkoa() {
        int[] uusi = new int[alkioidenLkm + kasvatuskoko];
        for (int i = 0; i < alkioidenLkm; i++) {
            uusi[i] = ljono[i];
        }
        ljono = uusi;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            StringBuilder merkkijonoesitys = new StringBuilder("{");
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                merkkijonoesitys.append(ljono[i]).append(", ");
            }
            merkkijonoesitys.append(ljono[alkioidenLkm - 1]).append("}");
            return merkkijonoesitys.toString();
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if(b.kuuluu(aTaulu[i])) {
                leikkaus.lisaa(aTaulu[i]);
            }
        }
        return leikkaus;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }
 
        return erotus;
    }
        
}
