package generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandLineMain {
    ArrayList<CountryMarkov> countryMarkovArray = new ArrayList<>();
    public boolean updated = false;
    public CountryMarkov mainCountryMarkov = null;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
//        CountryMarkov markov = (CountryMarkov) Global.importJson(CountryMarkov.class,"markovData/DE.json");
//        CountryMarkov markov2 = (CountryMarkov) Global.importJson(CountryMarkov.class,"markovData/FR.json");
//        CountryMarkov markov3 = (CountryMarkov) Global.importJson(CountryMarkov.class,"markovData/ES.json");
//        markov.merge(new CountryMarkov[]{markov2, markov3});
//        for(int i = 0; i < 10; i++) {
//            System.out.println(markov.genMarkovTextUnique());
//        }
        CommandLineMain main = new CommandLineMain();
        main.mainLoop();
    }
    public void mainLoop() throws IOException {
        char inputChar;
        this.displayMenu();
        do {
            inputChar = this.getCharOption("select an option (a/d/c/p/q/x)");
            switch(inputChar) {
                case 'a':
                    this.addCountry();
                    break;
                case 'd':
                    this.removeCountry();
                    break;
                case 'c':
                    this.viewCountries();
                    break;
                case 'p':
                    this.generateCity();
                    break;
                case 'q':
                    this.generateCities();
                    break;
                case 'x':
                    System.out.println("Exiting application!");
                    break;
                case '?':
                    displayMenu();
                    break;
                default:
                    System.out.println("input not recognized! type \'?\' to see the options");
                    break;
            }
        }
        while(inputChar != 'x');
    }
    public void displayMenu() {
        System.out.println("City Name Generator");
        System.out.println("a - add country");
        System.out.println("d - remove country");
        System.out.println("c - view countries added");
        System.out.println("p - generate and print a city");
        System.out.println("q - generate and print multiple cities");
        System.out.println("x - exit");
    }
    public char getCharOption(String prompt) {
        System.out.print(prompt + ": ");
        String strInput = scanner.next().toLowerCase();
        if(strInput.length()>0) {
            return strInput.charAt(0);
        }
        else {
            return '\n';
        }
    }
    public String getStringOption(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.next();
    }
    public int getIntOption(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextInt();
    }
    public void addCountry() throws IOException {
        String country = this.getStringOption("enter country code / country");
        String countryName = null;
        String countryCode = null;
        if(CountryMarkov.isValidCountryCode(country)) {
            countryCode = country;
            countryName = CountryMarkov.getCountryCodeHash().get(country);
        }
        else if(CountryMarkov.isValidCountryName(country)) {
            countryName = country;
            for(String key : CountryMarkov.getCountryCodeHash().keySet()) {
                if(CountryMarkov.getCountryCodeHash().get(key).equals(country)) {
                    countryCode = key;
                    break;
                }
            }
        }
        else {
            System.out.println("Invalid input!");
            return;
        }
        String fileName = String.format("markovData/%s.json",countryCode);
        CountryMarkov markov = (CountryMarkov) Global.importJson(CountryMarkov.class,fileName);
        this.countryMarkovArray.add(markov);
        System.out.println(String.format("%s added!",countryName));
        updated = false;
    }
    public void removeCountry() throws IOException {
        String country = this.getStringOption("enter country code / country");
        String countryName = null;
        String countryCode = null;
        if(CountryMarkov.isValidCountryCode(country)) {
            countryCode = country;
            countryName = CountryMarkov.getCountryCodeHash().get(country);
        }
        else if(CountryMarkov.isValidCountryName(country)) {
            countryName = country;
            for(String key : CountryMarkov.getCountryCodeHash().keySet()) {
                if(CountryMarkov.getCountryCodeHash().get(key).equals(country)) {
                    countryCode = key;
                    break;
                }
            }
        }
        else {
            System.out.println("Invalid input!");
            return;
        }
        for (CountryMarkov countryMarkov : this.countryMarkovArray) {
            if(countryMarkov.getCountryCode().equals(countryCode)) {
                this.countryMarkovArray.remove(countryMarkov);
                System.out.println(String.format("%s removed!", countryName));
                updated = false;
                return;
            }
        }
        System.out.println("Country is not in the country list");
    }
    public void viewCountries() {
        System.out.println("---country list---");
        int i = 0;
        for (CountryMarkov countryMarkov : this.countryMarkovArray) {
            i += 1;
            String entry = String.format("(%d) : %s (%d data points)",i,countryMarkov.getCountryName(),countryMarkov.getDataArray().size());
            System.out.println(entry);
        }
    }
    public void generateCity() throws IOException {
        if(!updated) {
            this.updateMainCountryMarkov();
            updated = true;
        }
        System.out.println(this.mainCountryMarkov.genMarkovTextUnique());
    }
    public void generateCities() throws IOException {
        if(!updated) {
            this.updateMainCountryMarkov();
            updated = true;
        }
        int cityCount = this.getIntOption("number of cities to generate");
        for(int i = 0; i < cityCount; i++) {
            System.out.println(this.mainCountryMarkov.genMarkovTextUnique());
        }

    }
    public void updateMainCountryMarkov() throws IOException {
        System.out.println("change in countries detected! updating markov chain!");
        this.mainCountryMarkov = new CountryMarkov(this.countryMarkovArray);
    }
}
