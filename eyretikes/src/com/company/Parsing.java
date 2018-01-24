package com.company;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parsing {


    //generate a greedy knapsack problem with size n and max profit at most P
    public static Greedy KnapsackGenerator(int n) {

        //capacity distributed  between 100 and 200*n where n is the quantity of the items on the problem
        int c;
        c = (int) (Math.random() * 150*n + 100);
        System.out.println("Capacity of current problem :" + c);
        //weights distributed between 20 and 400
        Integer[] s = new Integer[n];
        for (int i = 0; i < n; i++)
            s[i] = (int) ((Math.random() * 380) + 20);


        System.out.println("The weights of the objects :" + Arrays.toString(s));
        //profits distributed between 10 and 400
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++)
            p[i] = (int) (((Math.random() * 380) + 10));


        System.out.println("The profits of the objects :" + Arrays.toString(p));

        return (new Greedy(s, p, c));

    }//end method

    //generate a knapsack problem with Genetic Algorithm with size n and max profit at most P
    public  Genetic KnapsackGenerator2(int n) {

        //capacity distributed  between 100 and 200*n where n is the quantity of the items on the problem
        int c;
        c = (int) (Math.random() * 200*n + 400);
        System.out.println("Capacity of current problem :" + c);
        //weights distributed between 20 and 400
        Integer[] s = new Integer[n];
        for (int i = 0; i < n; i++)
            s[i] = (int) ((Math.random() * 380) + 20);


        System.out.println("The weights of the objects :" + Arrays.toString(s));
        //profits distributed between 10 and 400
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++)
            p[i] = (int) (((Math.random() * 380) + 10));


        System.out.println("The profits of the objects :" + Arrays.toString(p));

        return (new Genetic(s, p, c));

    }//end method



    // parser for the weights and the profits.
    public static Integer[] data(String string) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(string));
        String line;

        ArrayList<Integer> data = new ArrayList<>();
        while ((line = in.readLine()) != null) {
            line = line.trim(); // remove leading and trailing whitespace
            data.add(Integer.valueOf((line)));
        }
        Integer[] Data = data.toArray(new Integer[0]);

        return Data;
    }

    // parser for capacity of the problem.
    public static int capacity(String string) throws IOException {
        Scanner scanner = new Scanner(new File(string));
        int capacity = 0;

        while (scanner.hasNextInt()) {
            capacity = scanner.nextInt();
        }

        return capacity;
    }


}
