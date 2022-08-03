package generator;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkovChain {
    private boolean splitByChar;
    private ArrayList<String> dataArray;
    private HashMap<String,Integer> dataHash;
    private HashMap<String,Integer> subDataHash;
    private HashMap<String,HashMap<String,Double>> markovHash;
    private int chainLength;
    public MarkovChain(ArrayList<String> dataArray,int chainLength, boolean splitByChar) {
        this.setDataArray(dataArray);
        this.setSplitByChar(splitByChar);
        this.setChainLength(chainLength);
        this.genMarkovDict();
    }
    public void genMarkovDict() {
        this.setMarkovHash(new HashMap<String,HashMap<String,Double>>());
        this.setDataHash(new HashMap<String,Integer>());
        this.setSubDataHash(new HashMap<String,Integer>());
        String[] subDataArray;
        for(String data : dataArray) {
            if(this.getDataHash().get(data)==null) {
                this.getDataHash().put(data,1);
            }
            else {
                this.getDataHash().put(data,this.getDataHash().get(data)+1);
            }
            if(this.getSplitByChar()) {
                for(int i = 0; i < this.getChainLength(); i++) {
                    data="\n" + data;
                }
                data+="\n";
                subDataArray = data.split("");
            }
            else {
                for(int i = 0; i < this.getChainLength(); i++) {
                    data+="\n ";
                }
                data+=" \n";
                subDataArray = data.split(" ");
            }
            for(String subData: subDataArray) {
                if(!subData.equals("\n")) {
                    if(this.getSubDataHash().get(subData)==null) {
                        this.getSubDataHash().put(subData,1);
                    }
                    else {
                        this.getSubDataHash().put(subData,this.getSubDataHash().get(subData)+1);
                    }
                }
            }
            for(int i = 1; i < subDataArray.length; i++) {
                String segment = "";
                for(int j = Math.max(0,i-this.getChainLength());j<i;j++) {
                    segment += subDataArray[j];
                }
                String subData = subDataArray[i];
                if(this.getMarkovHash().get(segment)==null) {
                    this.getMarkovHash().put(segment,new HashMap<String,Double>());
                }
                HashMap<String,Double> segHash = this.getMarkovHash().get(segment);
                if(segHash.get(subData)==null) {
                    segHash.put(subData,1.0);
                }
                else {
                    segHash.put(subData,segHash.get(subData)+1);
                }
            }
        }
    }
    public String weightedChoice(String[] itemArray, Double[] weightArray) {
        double totalWeight = 0;
        for(Double weight : weightArray) {
            totalWeight += weight;
        }
        double r = Math.random() * totalWeight;
        double weightCount = 0;
        String selected = "REEEEEEEEE";
        for(int i = 0; i < itemArray.length; i++) {
            weightCount += weightArray[i];
            if(weightCount >= r) {
                selected = itemArray[i];
                break;
            }
        }
        return selected;
    }
    public String genMarkovText() {
        String result = "";
        ArrayList<String> resultArray = new ArrayList<String>();
        String nextVal = "\n";
        for(int i = 0; i < this.getChainLength(); i++) {
            resultArray.add("\n");

        }
        while(!nextVal.equals("\n") || resultArray.size()==this.getChainLength()) {
            String segment = "";
            for(int i = resultArray.size()-this.getChainLength(); i < resultArray.size(); i++) {
                segment += resultArray.get(i);
            }
            String[] keyArray = this.getMarkovHash().get(segment).keySet().toArray(new String[0]);
            Double[] valueList = this.getMarkovHash().get(segment).values().toArray(new Double[0]);
            nextVal = this.weightedChoice(keyArray,valueList);
            resultArray.add(nextVal);
        }
        for(String val : resultArray) {
            result += val;
        }
        result = result.replace("\n","");
        return result;
    }
    public String genMarkovTextUnique() {
        boolean isUnique = false;
        String result = null;
        while(!isUnique) {
            result = this.genMarkovText();
            if(!this.contains(result)) {
                isUnique = true;
            }
        }
        return result;
    }
    // print markov
    public void printMarkovHash() {
        String result = "";
        result += "{\n";
        boolean first = true;
        for(String key : this.getMarkovHash().keySet()) {
            if(first) {
                first = false;
            }
            else {
                result += ", \n";
            }
            String subResult = "";
            subResult += "{";
            boolean subFirst = true;
            for(String subKey : this.getMarkovHash().get(key).keySet()) {
                if(subFirst) {
                    subFirst = false;
                }
                else {
                    subResult += ", ";
                }
                subResult += String.format("\"%s\" : %.1f",subKey,this.getMarkovHash().get(key).get(subKey));
            }

            subResult += "}";
            result += String.format("\"%s\" : %s",key,subResult).replace("\n","\\n");

        }
        result += "\n}";
        System.out.println(result);
    }
    // accessors
    public boolean getSplitByChar() {return splitByChar;}
    public ArrayList<String> getDataArray() {return dataArray;}
    public HashMap<String, Integer> getDataHash() {return dataHash;}
    public HashMap<String, Integer> getSubDataHash() {return subDataHash;}
    public HashMap<String,HashMap<String,Double>> getMarkovHash() {return markovHash;}
    public int getChainLength() {return chainLength;}

    // mutators
    public void setSplitByChar(boolean splitByChar) {this.splitByChar = splitByChar;}
    public void setDataArray(ArrayList<String> dataArray) {this.dataArray = dataArray;}
    public void setDataHash(HashMap<String, Integer> dataHash) {this.dataHash = dataHash;}
    public void setSubDataHash(HashMap<String, Integer> subDataHash) {this.subDataHash = subDataHash;}
    public void setMarkovHash(HashMap<String, HashMap<String, Double>> markovHash) {this.markovHash = markovHash;}
    public void setChainLength(int chainLength) {this.chainLength = chainLength;}
    // check duplicate
    public boolean contains(String value) {
        if(this.getDataHash().get(value)==null) {
            return false;
        }
        else {
            return true;
        }
    }
}
