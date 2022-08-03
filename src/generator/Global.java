package generator;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Global {
    public static ArrayList<String> importFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        ArrayList<String> lineArray = new ArrayList<String>();
        String st = br.readLine();
        while(st!=null) {
            lineArray.add(st);
            st = br.readLine();
        }
        return lineArray;
    }
    public static void exportJson(Object obj, String fileName) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        File file = new File (fileName);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(json);
        out.close();
    }
    public static <T> Object importJson(Class<T> objType, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Object object = new Gson().fromJson(br,objType);
        return object;
    }
    public static HashMap<String,String> importCountryCodeHash() throws IOException {
        ArrayList<String> dataArray = Global.importFile("countryCodeData.txt");
        ArrayList<String[]> countryCodeArray = new ArrayList<String[]>();
        HashMap<String,String> countryCodeHash = new HashMap<String,String>();
        for(String data : dataArray) {
            String key = data.split(" ")[0];
            String value = data.substring(data.indexOf("(")+1,data.indexOf(")"));
            countryCodeHash.put(key,value);
        }
        return countryCodeHash;
    }
}
