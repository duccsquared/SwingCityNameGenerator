package generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CountryMarkov extends MarkovChain {
    private static HashMap<String,String> countryCodeHash = null;
    private String countryCode;
    private String countryName;
    private int cityCount;
    public static void confirmCountryCodeHash() throws IOException {
        if(CountryMarkov.countryCodeHash == null) {
            CountryMarkov.countryCodeHash = Global.importCountryCodeHash();
        }
    }
    public static boolean isValidCountryCode(String countryCode) throws IOException {
        CountryMarkov.confirmCountryCodeHash();
        return CountryMarkov.countryCodeHash.containsKey(countryCode);
    }
    public static boolean isValidCountryName(String countryName) throws IOException {
        CountryMarkov.confirmCountryCodeHash();
        return CountryMarkov.countryCodeHash.containsValue(countryName);
    }
    public CountryMarkov(String countryCode) throws IOException {
        super(new ArrayList<String>(),3,true);
        CountryMarkov.confirmCountryCodeHash();
        this.setCountryCode(countryCode);
        this.setCountryName(this.getCountryCodeHash().get(countryCode));
        ArrayList<String> dataArray = Global.importFile(String.format("countryData/%s.txt",countryCode));
        this.setCityCount(dataArray.size());
        this.setDataArray(dataArray);
        this.genMarkovDict();
    }
    public CountryMarkov(ArrayList<CountryMarkov> countryMarkovList) throws IOException {
        super(new ArrayList<String>(),3,true);
        CountryMarkov.confirmCountryCodeHash();
        this.setCountryCode("");
        this.setCountryName("");
        ArrayList<String> dataArray = new ArrayList<>();
        this.setCityCount(dataArray.size());
        this.setDataArray(dataArray);
        this.genMarkovDict();
        for(CountryMarkov countryMarkov : countryMarkovList) {
            this.merge(countryMarkov);
        }
    }
    public void merge(CountryMarkov[] countryMarkovList) {
        for(CountryMarkov countryMarkov : countryMarkovList) {
            this.merge(countryMarkov);
        }
    }
    public void merge(CountryMarkov countryMarkov) {
        this.setCountryCode(this.getCountryCode()+"|"+countryMarkov.getCountryCode());
        this.setCountryName(this.getCountryName()+"|"+countryMarkov.getCountryName());
        this.setCityCount(this.getCityCount()+countryMarkov.getCityCount());
        for(String key : countryMarkov.getMarkovHash().keySet()) {
            if(this.getMarkovHash().get(key)==null) {
                this.getMarkovHash().put(key,new HashMap<String,Double>());
            }
            for(String subKey : countryMarkov.getMarkovHash().get(key).keySet()) {
                if(this.getMarkovHash().get(key).get(subKey)==null) {
                    this.getMarkovHash().get(key).put(subKey,
                            countryMarkov.getMarkovHash().get(key).get(subKey));
                }
                else {
                    this.getMarkovHash().get(key).put(subKey,
                            this.getMarkovHash().get(key).get(subKey) +
                            countryMarkov.getMarkovHash().get(key).get(subKey));
                }
            }
        }
    }
    // accessors
    public static HashMap<String, String> getCountryCodeHash() {return countryCodeHash;}
    public String getCountryCode() {return countryCode;}
    public String getCountryName() {return countryName;}
    public int getCityCount() {
        return cityCount;
    }

    // mutators
    public static void setCountryCodeHash(HashMap<String, String> countryCodeHash) {CountryMarkov.countryCodeHash = countryCodeHash;}
    public void setCountryCode(String countryCode) {this.countryCode = countryCode;}
    public void setCountryName(String countryName) {this.countryName = countryName;}
    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }
}
