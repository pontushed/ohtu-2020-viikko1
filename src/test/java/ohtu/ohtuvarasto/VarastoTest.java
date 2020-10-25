package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiYlitaKapasiteettia() {
        varasto.lisaaVarastoon(10);
        varasto.lisaaVarastoon(2);

        // varastossa pitaisi olla saldo 10.
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaMaaraansaEnempaa() {
        varasto.lisaaVarastoon(2);
        double saatuMaara = varasto.otaVarastosta(4);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranLisaaminen() {
        varasto.lisaaVarastoon(-2);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranOttaminen() {
        varasto.lisaaVarastoon(2);
        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void virheellinenKonstruktori1() {
        Varasto v = new Varasto(0);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenKonstruktori2() {
        Varasto v = new Varasto(-2);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenKonstruktori3() {
        Varasto v = new Varasto(-2, 0);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenKonstruktori4() {
        Varasto v = new Varasto(2, -1);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldoOikein() {
        Varasto v = new Varasto(2, 1);
        assertEquals(2, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringOnOikein() {
        String saatu = varasto.toString();
        String odotettu = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        assertEquals(odotettu, saatu);
    }

}