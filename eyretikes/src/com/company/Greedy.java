package com.company;


import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;


public class Greedy {

    private Integer[] weights;
    private Integer[] profits;
    private int capacity;

    public Greedy(Integer[] weights, Integer[] profits, int capacity) {
        this.weights = weights;
        this.profits = profits;
        this.capacity = capacity;

    }
     //GREEDY ALGORITM
    public Boolean[] greedy() {

        //Pinakes gia na brethei o logos ton antikeimenon kai antistoixa ena pinakas gia thn lush
        Double[] ratio = new Double[weights.length];
        Double[] ratio2 = new Double[weights.length];
        Boolean[] solution = new Boolean[weights.length];
        int cap = capacity;//Metablith i opoia pernei thn timh tou capacity tou knapsack problimatos

        //Brisko ton logo (kerdos)/(varos) kathe antikeimenou tou knapsack
        for (int i = 0; i < weights.length; i++) {
            ratio[i] = (double) profits[i] / weights[i];
            ratio2[i] = ratio[i];//Dummy pinakas pou tou pernao tis analogies gia na ton metatrepso se auksousa seira
        }
        Arrays.sort(ratio2);//Metatroph ton stoixeion apo to mikrotero sto megalutero
        //System.out.println(Arrays.toString(ratio));
        //System.out.println(Arrays.toString(ratio2));

        //Anathesh ths timhs false se ola ta kelia tou pinaka solution
        for (int i = 0; i < weights.length; i++)
            solution[i] = false;

        //2 epanalipsei gia na brisko to index tou paliou pinaka me tis analogies me ton kainourgio o opoios einai se
        //aukousa seira
        for (int i = weights.length - 1; i >= 0; i--) {
            for (int j = 0; j < weights.length; j++) {
                //H sunthikh mou briskei ton kalutero logo kai krataei thn thesh pou eixe ston palio pinaka epishs
                //elegxo an h metablith cap (pou exei thn timh capacity) tha xoraei to antistoixo antikeimeno
                if (ratio2[i] == ratio[j] && cap >= weights[j]) {
                    solution[j] = true;//Dexomai to antoistixo antikeimeno
                    cap = cap - weights[j];//Afairo to Baros tou apo to Geniko capacity
                   //etsi ama ftasei kapoio antikeimeno na min xoraei tha psaksei to epomeno mexri na mn xoraei kanena
                }
            }
        }
        int val = 0;
        int ca=0;
        //Evaluate ta kerdoi kai to capacity ths lushs mou
        for (int i = 0; i < weights.length; i++) {
            if (solution[i]) {
                val = val + profits[i];
                ca=+ca+weights[i];
            }
        }
        System.out.println("THE GREEDY ALGORITHM GIVES :");
        System.out.println("The Value of the solution is " + val+" with capacity "+ca);
        System.out.println("And the solution is :"+Arrays.toString(solution));


        return solution;

    }


}





