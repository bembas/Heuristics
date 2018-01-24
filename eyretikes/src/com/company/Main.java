package com.company;


import java.io.*;
import java.util.Arrays;



public class Main {


    public static void main(String[] args) throws IOException {

        // For a problem with random Data.
        //GENERATOR WITH GREEDY Dexetai akeraio gia thn timh ton antikeimenon tou problimatos
        //Parsing.KnapsackGenerator(100).greedy();

        //GENERATOR WITH GENETIC ALGORITHM
        Parsing gen = new Parsing();
        //O protos akeraios einai gia ton arithmo ton antikeimenon kai o deuteros gia tis genies
        //Einai proteimomeno na einai kontina ta duo noume pou tha kataxorithoun gia na min uparxnoun polles idies luseis
        //gen.KnapsackGenerator2(10).Genetic(20);


        //data are from   https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html
        //Uparxoun dedomena me noumero 6,7,8
        Integer[] weights = Parsing.data("p07_w.txt");
        Integer[] profits = Parsing.data("p07_p.txt");
        int capacity = Parsing.capacity("p07_c.txt");

        System.out.println("Weights =" + Arrays.toString(weights));
        System.out.println("Profits =" + Arrays.toString(profits));
        System.out.println("Capacity =" + (capacity));

        Genetic lo = new Genetic(weights, profits, capacity);
        //GENETIC ALGORITHM
        lo.Genetic(10);// i parametros einai gia poses genies tha treksei to problima mou


        Greedy test = new Greedy(weights, profits, capacity);
        // GREEDY ALGORITHM
        //test.greedy();


    }
}
