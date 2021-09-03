package com.weiyzue.juc.c_30_somequestions_from_students;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Compartor {


    public static void main(String[] args) throws Exception {
        int num = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());

        OlympicNation[] nations = new OlympicNation[num];

        for (int i = 0; i < num; i++) {
            String s = br.readLine().trim();
            String[] temp = s.split(",");
            nations[i] = new OlympicNation(temp[0], Integer.parseInt(temp[1]),
                    Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));

        }

        Arrays.sort(nations);


        for (OlympicNation o : nations) {
            System.out.println(o);
        }
    }
}

class OlympicNation implements Comparable<OlympicNation> {

    private String name;
    private int numOfGold, numOfSilver, numOfBronze;

    public OlympicNation(String name, int numOfGold, int numOfSilver, int numOfBronze) {
        this.name = name;
        this.numOfGold = numOfGold;
        this.numOfSilver = numOfSilver;
        this.numOfBronze = numOfBronze;
    }

    @Override
    public String toString() {
        return "OlympicNation{" +
                "name='" + name + '\'' +
                ", numOfGold=" + numOfGold +
                ", numOfSilver=" + numOfSilver +
                ", numOfBronze=" + numOfBronze +
                '}';
    }

    @Override
    public int compareTo(OlympicNation o) {

        if (this.numOfGold < o.numOfGold) {
            return 1;
        } else if (this.numOfGold > o.numOfGold) {
            return -1;
        }

        if (this.numOfSilver < o.numOfSilver) {
            return 1;
        } else if (this.numOfSilver > o.numOfSilver) {
            return -1;
        }

        if (this.numOfBronze < o.numOfBronze) {
            return 1;
        } else if (this.numOfBronze > o.numOfBronze) {
            return -1;
        }

        return this.name.compareTo(o.name);
    }
}