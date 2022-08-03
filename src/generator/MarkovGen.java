package generator;

import java.io.IOException;
import java.util.HashMap;

public class MarkovGen {
    public static void main(String[] args) throws IOException {
        HashMap<String,String> hashCodeMap = Global.importCountryCodeHash();
        for(String countryCode : hashCodeMap.keySet()) {
            Timer timer = new Timer();
            timer.start();
            System.out.println(String.format("Current country: %s",countryCode));
            CountryMarkov testMarkov = new CountryMarkov(countryCode);
            timer.end();
            Global.exportJson(testMarkov,String.format("markovData/%s.json",countryCode));
        }
    }
}
