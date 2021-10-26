package src;

import java.util.ArrayList;
import java.util.Collections;

public class Chromosome {
    int chromosomeLength;
    Chromosome child1, child2;
    ArrayList<Integer> genes = new ArrayList<>();

    Chromosome(int length) {
        chromosomeLength = length;
        for (int i = 1; i <= length; i++) {
            genes.add(i);
        }
        Collections.shuffle(genes);
    }

    Chromosome(int value, int length) {
        chromosomeLength = length;
        for (int i = 1; i <= length; i++) {
            genes.add(value);
        }
    }

    public double fitness() {
        double score = 0;
        for (int i = 1; i < chromosomeLength; i++) {
            score = score + ((genes.get(i) - genes.get(i - 1)) * (genes.get(i) - genes.get(i - 1))) + 2 * (Math.sqrt(genes.get(i) * genes.get(i - 1)));
        }
        return score;
    }


    public Chromosome mutation() {
        int random = (int) (Math.random() * chromosomeLength);
        if (random == chromosomeLength - 1) {
            random--;
        }
        int valueAtOne = (genes.get(random));
        int valueAtTwo = (genes.get(random + 1));
        Chromosome child1 = new Chromosome(0, chromosomeLength);
        child1.genes = genes;
        child1.genes.set(random, valueAtTwo);
        child1.genes.set(random + 1, valueAtOne);
        return child1;
    }

    public void crossover(Chromosome parent2) {
        while (true) {

            child1 = new Chromosome(0, chromosomeLength);
            child2 = new Chromosome(0, chromosomeLength);
            int random1 = (int) (Math.random() * chromosomeLength);
            int random2 = (int) (Math.random() * chromosomeLength);
            int start, end;
            if (random1 > random2) {
                start = random2;
                end = random1;
            } else {
                start = random1;
                end = random2;
            }

            for (int i = 0; i < start; i++) {
                int index1 = (parent2.genes.indexOf(genes.get(i)));
                int swappingValue1 = genes.get(index1);
                int index2 = (genes.indexOf(parent2.genes.get(i)));
                int swappingValue2 = parent2.genes.get(index2);

                if (index1 >= start && index1 <= end) {

                    child1.genes.set(i, swappingValue1);
                } else {
                    child1.genes.set(i, genes.get(i));
                }
                if (index2 >= start && index2 <= end) {
                    child2.genes.set(i, swappingValue2);
                } else {
                    child2.genes.set(i, parent2.genes.get(i));
                }
            }
            for (int i = start; i <= end; i++) {
                child1.genes.set(i, parent2.genes.get(i));
                child2.genes.set(i, genes.get(i));
            }
            for (int i = end + 1; i < chromosomeLength; i++) {
                int index1 = (parent2.genes.indexOf(genes.get(i)));
                int swappingValue1 = genes.get(index1);
                int index2 = (genes.indexOf(parent2.genes.get(i)));
                int swappingValue2 = parent2.genes.get(index2);

                if (index1 >= start && index1 <= end) {
                    child1.genes.set(i, swappingValue1);
                } else {
                    child1.genes.set(i, genes.get(i));
                }
                if (index2 >= start && index2 <= end) {
                    child2.genes.set(i, swappingValue2);
                } else {
                    child2.genes.set(i, parent2.genes.get(i));
                }
            }
            if (child1.validate() && child2.validate()) {
                break;
            }
        }
    }

    public boolean validate() {
        int desiredSum = 0, originalSum = 0;
        for (int i = 0; i < chromosomeLength; i++) {
            desiredSum += i;
            originalSum += genes.get(i);
        }
        desiredSum = desiredSum + chromosomeLength;
        if (desiredSum == originalSum) {
            return true;
        } else {
            return false;
        }
    }
}
