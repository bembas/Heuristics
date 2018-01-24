package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Genetic {

    private Integer[] weights;
    private Integer[] profits;
    private int capacity;
    private ArrayList<Boolean[]> solutions = new ArrayList<Boolean[]>();
    private ArrayList<Integer> values = new ArrayList<Integer>();

    //constructor
    public Genetic(Integer[] weights, Integer[] profits, int capacity) {

        this.weights = weights;
        this.profits = profits;
        this.capacity = capacity;
    }


    public Boolean[] RandomWalk() {


        Random rand = new Random();
        double cap = 0;
        double prof = 0;


        Boolean[] solution = new Boolean[weights.length];

        double max = 0;
        //brisko to max ton weights giati tha to xriasto sth sunthikh termatismou tis random lushs
        for (double cur : weights)
            max = Math.max(max, cur);

        //kataxoro thn timh false se oles tis theseis tou pinaka o opoios tha einai h lush.
        for (int i = 0; i < weights.length; i++)
            solution[i] = false;

        do {
            int r = rand.nextInt(weights.length);//brisko ena tuxaio arithmo apo to ena mexri kai ton  arithmo olon ton antikeimenon

            if (!solution[r]) {//h sunthikh mou einai gia na briskei thn thesh tou pinaka solution oste na kataxoriso to neo antikeimeno
                cap = cap + weights[r];//h metablith cap einai gia na metrao to capacity ths random lushs mou
                prof = prof + profits[r];// h metablith prog einai gia na metrao to profit ths random lushs mou
                solution[r] = true;//dexomai to tuxaio antikeimeno sthn  lush mou
            }
        } while (capacity - cap >= max);
        //h sunthikh termatismou mou tha einai oti i diafora tou capacity tou problimatos me to capacity ths lushs tha einai
        // panta megaluterh apo to megalutero baros etsi den tha bgo pote ekso apo to capacity tou problimatos


        value(solution); //Brisko to profit ths tuxaias lushs mou
        //System.out.println("The Value of the random solution is " + val);
        //System.out.println(Arrays.toString(solution));

        return solution;
    }


    //Genetikos algorithmos me epilogh apo ton kanona tis rouletas  ,1- point crossover, Mutation , kai elitist strategy

    public void Genetic(int n) {

        int k = 0;
        //tuxaies arxikes luseis gia na ftiaxtoun oi goneis
        Boolean[] random1, random5;
        Boolean[] random2, random6;
        Boolean[] random3, random7;
        Boolean[] random4, random8;

        //anathesh ton tuxaion luseon
        random1 = RandomWalk();
        random2 = RandomWalk();
        random3 = RandomWalk();
        random4 = RandomWalk();
        random5 = RandomWalk();
        random6 = RandomWalk();
        random7 = RandomWalk();
        random8 = RandomWalk();

        //kanoume evaluate kathe lush gia na broume to kerdos ths
        int val1 = value(random1);
        int val2 = value(random2);
        int val3 = value(random3);

        int val5 = value(random5);
        int val6 = value(random6);
        int val7 = value(random7);

        // 2 metablites oi opoies prosthetoun ta kerdoi apo 4 luseis i kathemia
        int sumval = value(random1) + value(random2) + value(random3) + value(random4);
        int sumval2 = value(random5) + value(random6) + value(random7) + value(random8);

        //2 tuxaioi arithmoi apo to 0 eos to athrisma ton kerdon ton tessaron luseon
        Random rand = new Random();
        int r = rand.nextInt(sumval);
        int t = rand.nextInt(sumval2);

        //Goneis
        Boolean[] parent1;
        Boolean[] parent2;

        //2 Pinakes luseon gia ton neon pluthismo (Tha xriastoun parakato)
        Boolean[] newpopulation = new Boolean[weights.length];
        Boolean[] newpopulation2 = new Boolean[weights.length];

        while (k < n) {//oi genies

            if (k > 0)//o neos pluthismos tha xriastei meta thn deuterh genia
                parent1 = newpopulation;
                //Efarmogh o kanonas tis rouletas
            else if (r <= val1)//
                parent1 = random1;
            else if (val1 < r && r <= val1 + val2)
                parent1 = random2;
            else if (val2 + val1 < r && r <= val1 + val2 + val3)
                parent1 = random3;
            else
                parent1 = random4;

            if (k > 0)
                parent2 = newpopulation2;
            else if (t <= val5)
                parent2 = random5;
            else if (val5 < t && t <= val5 + val6)
                parent2 = random6;
            else if (val5 + val6 < t && t <= val5 + val6 + val7)
                parent2 = random7;
            else
                parent2 = random8;

            //Elegxo ama oi goneis exoun idia lush tote antikathisto ton enan me tuxaia kainourgia lush gia na apofugo na bretho se idious apogonous(me tous goneis)
            if (value(parent1) == value(parent2)) {
                parent1 = RandomWalk();
            }

            //EKTUPOSH ton gonion
            System.out.println("Parent 1 = " + Arrays.toString(parent1));
            System.out.println("Parent 2 = " + Arrays.toString(parent2));
            System.out.println("The value of the first parent is " + value(parent1) + " and his capacity is " + cap(parent1));
            System.out.println("The value of the second parent is " + value(parent2) + " and his capacity is " + cap(parent2));

            //Anathesh ton paidion()
            Boolean[] kid1 = new Boolean[weights.length];
            Boolean[] kid2 = new Boolean[weights.length];

            //enas tuxaios arithmos apo 1 eos megethous-1 ton antikeimenon tou knapsack problimatos
            //o arithmos autos tha kanei to crossover ton gonion opote dn pairno to 0 h to megethos ton antikeimenon
            int w = rand.nextInt(weights.length - 1) + 1;

            //efarmozo to crossover
            for (int i = 0; i < w; i++) {
                for (int j = w; j < weights.length; j++) {
                    kid1[i] = parent1[i];
                    kid1[j] = parent2[j];
                    kid2[j] = parent1[j];
                    kid2[i] = parent2[i];

                }

            }

            //Dialego ena apo ta 2 paidia(auto me tin kaluterh timh) gia ton neo pluthismo ths epomenhs genias kai an einai kai ta 2 invalid logo
            //xoritikothtas tote perno ton arxiko patera auta ginontai sth methodo Newpopulation
            newpopulation = Newpopulation(kid1, kid2, parent1);

            //Ektuposh
            System.out.println("Apogonos 1=" + Arrays.toString(kid1));
            System.out.println("Apogonos 2=" + Arrays.toString(kid2));
            System.out.println("The value of the first kid is " + value(kid1) + " and his capacity is " + cap(kid1));
            System.out.println("The value of the second kid is " + value(kid2) + " and his capacity is " + cap(kid2));


            //mutation

            //2 tuxaioi arithmoi  gia thn metalaksh ton paidion
            int m = rand.nextInt(weights.length);
            int l = rand.nextInt(weights.length);

            //Ta metalagmena paidia
            Boolean[] Mutkid1 = new Boolean[weights.length];
            Boolean[] Mutkid2 = new Boolean[weights.length];

            //anatheto tin lush ton paidion sta metalagmena gia na ksekinhsei i metallaksh
            for (int i = 0; i < weights.length; i++) {
                Mutkid1[i] = kid1[i];
                Mutkid2[i] = kid2[i];
            }
            //allazo ena tuxaio xromosoma(index) apo true se false
            for (int i = 0; i < weights.length; i++) {
                if (Mutkid1[m] = true) {
                    Mutkid1[m] = false;
                } else {
                    Mutkid1[m] = true;
                }

                if (Mutkid2[l] = true) {
                    Mutkid2[l] = false;
                } else {
                    Mutkid2[l] = true;
                }
            }

            //Ektuposh ton metalagmenon
            System.out.println("Mutant 1=" + Arrays.toString(Mutkid1));
            System.out.println("Mutant 2=" + Arrays.toString(Mutkid2));
            System.out.println("The mutant 1 has value: " + value(Mutkid1) + " and the capacity is " + cap(Mutkid1));
            System.out.println("The mutant 2 has value: " + value(Mutkid2) + " and the capacity is " + cap(Mutkid2));

            //Dialego to deutero atomo gia thn nea genia apo ta metalagmena paidia (auto me thn kaluterh timh)
            newpopulation2 = Newpopulation(Mutkid1, Mutkid2, parent2);
            //Ektuposei tou neou pluthismou
            System.out.println("Newpopulation 1=" + Arrays.toString(newpopulation));
            System.out.println("Newpopulation 2=" + Arrays.toString(newpopulation2));
            System.out.println("The newpopulation 1 has value: " + value(newpopulation) + " and the capacity is " + cap(newpopulation));
            System.out.println("The newpopulation 2 has value: " + value(newpopulation2) + " and the capacity is " + cap(newpopulation2));


            //2 listes  gia oles tis times oste na kratithei h kaluterh lush me thn kaluterh timh ths
            solutions.add(newpopulation);
            solutions.add(newpopulation2);
            values.add(value(newpopulation));
            values.add(value(newpopulation2));

            k++;//auksano ton akeraio pou xrisimopoieitai gia na metraei tis genies

        }
        findBestvalue(values, solutions);
    }

    //h klash auth ftiaxnei ton neo pluthismo
    public Boolean[] Newpopulation(Boolean[] pop1, Boolean[] pop2, Boolean[] parent) {

        Boolean[] newpopulation;

        //Brisko thn kaluterh timh apo tous duo upopsifious gia ton neo pluthismo
        int max = Math.max(value(pop1), value(pop2));

        //elegxo pios exei thn kaluterh timh kai an mporei na xoresei sto sakidio
        //an den xoraei kaneis apo tous upopsifious tote thn thesh tha tin parei o arxikos pateras
        if (max == value(pop1)) {
            if (cap(pop1) <= capacity)
                newpopulation = pop1;
            else if (cap(pop2) <= capacity)
                newpopulation = pop2;
            else
                newpopulation = parent;
        } else {
            if (cap(pop2) <= capacity)
                newpopulation = pop2;
            else if (cap(pop1) <= capacity)
                newpopulation = pop1;
            else
                newpopulation = parent;
        }
        return newpopulation;
    }

    //Methodo pou kanei evaluate ta kerdh tis dexomenhs lushs
    public int value(Boolean[] bool) {
        int val = 0;
        for (int i = 0; i < weights.length; i++) {
            if (bool[i]) {
                val += profits[i];
            }
        }
        return val;
    }

    //Methodo pou metraei to capacity tis dexomenhs lushs
    public int cap(Boolean[] bool) {
        int cap = 0;
        for (int i = 0; i < weights.length; i++) {
            if (bool[i]) {
                cap += weights[i];
            }
        }
        return cap;
    }

    //Getter gia thn lista ton luseon olon ton genion
    public ArrayList<Boolean[]> getbestsolution() {
        return solutions;
    }

    //Getter gia thn lista ton timon olon ton genion
    public ArrayList<Integer> getbestvalue() {

        return values;
    }

    //Methodo pou briskei thn kaluterh timh olon ton genion kai antistoixa oles tis luseis me authn tin timh kai poses einai
    public void findBestvalue(ArrayList<Integer> values, ArrayList<Boolean[]> solutions) {
        Boolean[] sol = new Boolean[weights.length];
        int max = Collections.max(values);
        System.out.println("THE GENETIC ALGORITHM GIVES :");
        System.out.println("\nThe best value of the best solution is " + Collections.max(values));
        int solcounter = 0;
        System.out.println("And The best solutions are");
        for (int i = 0; i < values.size(); i++) {
            if (max == values.get(i)) {
                System.out.println(Arrays.toString(solutions.get(i)));
                solcounter++;
            }
        }
        System.out.println("The number of all the same best solutions is " + solcounter);


    }


}
