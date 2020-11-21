package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k;
    
    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 7));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "salaatti", 4));

        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);          
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));   
    }
    
    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeinKunOstetaanKaksiTuotetta() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta nro 2 eli leipää
        k.tilimaksu("matti", "127");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto(eq("matti"), eq(42), eq("127"), eq("33333-44455"),eq(12));   
    }
    
    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeinKunOstetaanKaksiSamaaTuotetta() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("sini", "155");
        
        verify(pankki).tilisiirto(eq("sini"), eq(42), eq("155"), eq("33333-44455"), eq(10));
    }
    
    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikeinKunOstetaanTuoteJotaOnJaTuoteJokaOnLoppu() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("leena", "172");
        
        verify(pankki).tilisiirto(eq("leena"), eq(42), eq("172"), eq("33333-44455"), eq(5));
    }
}

