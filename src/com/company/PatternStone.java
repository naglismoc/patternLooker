package com.company;

import java.util.ArrayList;
import java.util.List;

public class PatternStone {
   private int simbolis;
    private List<Integer> pozicijos = new ArrayList<>();

    public PatternStone(int simbolis, List<Integer> pozicijos) {
        this.simbolis = simbolis;
        this.pozicijos = pozicijos;
    }

    public int getSimbolis() {
        return simbolis;
    }

    public void setSimbolis(int simbolis) {
        this.simbolis = simbolis;
    }

    public List<Integer> getPozicijos() {
        return pozicijos;
    }

    public void setPozicijos(List<Integer> pozicijos) {
        this.pozicijos = pozicijos;
    }

    @Override
    public String toString() {
        return "PatternStone{" +
                "simbolis=" + simbolis +
                ", pozicijos=" + pozicijos +
                '}';
    }
}
