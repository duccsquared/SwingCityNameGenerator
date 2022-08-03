package main;

public class Country {
    private String countryCode;
    private String countryName;
    App app;
    public Country(App app, String countryCode,String countryName) {
        this.app = app;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }
    public String getCountryCode() {return countryCode;}
    public String getCountryName() {return countryName;}
    public void setCountryCode(String countryCode) {this.countryCode = countryCode;}
    public void setCountryName(String countryName) {this.countryName = countryName;}

    public String toString() {
        return String.format("%s|%s",countryCode,countryName);
    }
}
