package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] newData = {
                1, 2, 3, 17, 1, 2, 3, 4, 20, 2, 3, 4};//
        //0, 1, 2, 3, 4, 5,  6, 7,  8, 9, 10,11,12,13, 14, 15
        boolean doWeLookForCertainSymbols = true;
        int[] symbols = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<String> db = new ArrayList<>();
        HashMap<String, HashSet<Integer>> patterns = new HashMap<>();
        Main m = new Main();

        m.incommingDataFlow(newData, db, symbols, patterns, doWeLookForCertainSymbols);


    }

    /**
     * method imitates continuously incoming data
     *
     * @param newData List of data that we go through imitating new data flow
     * @param db      database where we save newly received data
     * @param symbols list of symbols that we work with
     */
    public void incommingDataFlow(int[] newData, List<String> db, int[] symbols, HashMap<String, HashSet<Integer>> patterns, boolean doWeLookForCertainSymbols) {

        for (int i = 0; i < newData.length; i++) {
            String currentSymbolString = "" + newData[i];
            System.out.println("gavau nauja skaiciu " + currentSymbolString + ". tai yra " + (i + 1) + "/" + (newData.length) + " simbolis");
            boolean validSymbol = true;
            if (doWeLookForCertainSymbols) validSymbol = isCompatableWithAllowedSymbols(symbols, currentSymbolString);
            addNewDataToDB(db, currentSymbolString);
            if (!validSymbol) {
                System.out.println("blogas simbolis");
                continue;
            }
            addPatternAndCount(patterns, db, currentSymbolString, symbols);
            printAllKnownPatterns(patterns);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printAllKnownPatterns(HashMap<String, HashSet<Integer>> patterns) {
        for (String pattern : patterns.keySet()) {
            if (patterns.get(pattern).size() > 1) {//lyg
                System.out.println("patternas " + pattern + " pasikartojo pozicijose: " + patterns.get(pattern));
            }
        }
    }

    public boolean isCompatableWithAllowedSymbols(int[] symbols, String currentSymbol) {
        boolean bl = false;
        for (int symbol = 0; symbol < symbols.length; symbol++) {
            if (currentSymbol.equals("" + symbols[symbol])) {
                bl = true;
                return bl;
            }
        }
        return bl;
    }

    public void addNewDataToDB(List<String> db, String currentSymbol) {
        db.add(currentSymbol);
    }

    public void addPatternAndCount(HashMap<String, HashSet<Integer>> patterns, List<String> db, String currentSymbolString, int[] symbols) {
        System.out.println("pasileido addPatternAndCount");
        String pattern = "";
        for (int h = db.size(); h-- > 0; ) {
            if (!isCompatableWithAllowedSymbols(symbols, db.get(h))) break;
            pattern = db.get(h) + "," + pattern;
            if (h == db.size() - 1) {
                pattern = db.get(h);
            }

            if (patterns.containsKey(pattern)) {
                patterns.get(pattern).add(h);
            } else {
                HashSet hS = new HashSet();
                hS.add(h);
                patterns.put(pattern, hS);
            }
        }
    }
}