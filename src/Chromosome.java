import java.util.ArrayList;
import java.util.Collections;

public class Chromosome {
    int chromosomeLength;
    Chromosome firstChild, secondChild;
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

    public double calculateFitness() {
        double fitness = 0;
        for (int i = 1; i < chromosomeLength; i++) {
            fitness = fitness + ((genes.get(i) - genes.get(i - 1)) * (genes.get(i) - genes.get(i - 1))) + 2 * (Math.sqrt(genes.get(i) * genes.get(i - 1)));
        }
        return fitness;
    }


    public Chromosome mutate() {
        int random = (int) (Math.random() * chromosomeLength);
        if (random == chromosomeLength - 1) {
            random--;
        }
        int valueAtOne = (genes.get(random));
        int valueAtTwo = (genes.get(random + 1));
        Chromosome child = new Chromosome(0, chromosomeLength);
        child.genes = genes;
        child.genes.set(random, valueAtTwo);
        child.genes.set(random + 1, valueAtOne);
        return child;
    }

    public void crossover(Chromosome parentChromosome) {
        while (true) {
            firstChild = new Chromosome(0, chromosomeLength);
            secondChild = new Chromosome(0, chromosomeLength);
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
                int index1 = (parentChromosome.genes.indexOf(genes.get(i)));
                int swappingValue1 = genes.get(index1);
                int index2 = (genes.indexOf(parentChromosome.genes.get(i)));
                int swappingValue2 = parentChromosome.genes.get(index2);
                if (index1 >= start && index1 <= end) {
                    firstChild.genes.set(i, swappingValue1);
                } else {
                    firstChild.genes.set(i, genes.get(i));
                }
                if (index2 >= start && index2 <= end) {
                    secondChild.genes.set(i, swappingValue2);
                } else {
                    secondChild.genes.set(i, parentChromosome.genes.get(i));
                }
            }
            for (int i = start; i <= end; i++) {
                firstChild.genes.set(i, parentChromosome.genes.get(i));
                secondChild.genes.set(i, genes.get(i));
            }
            for (int i = end + 1; i < chromosomeLength; i++) {
                int index1 = (parentChromosome.genes.indexOf(genes.get(i)));
                int swappingValue1 = genes.get(index1);
                int index2 = (genes.indexOf(parentChromosome.genes.get(i)));
                int swappingValue2 = parentChromosome.genes.get(index2);

                if (index1 >= start && index1 <= end) {
                    firstChild.genes.set(i, swappingValue1);
                } else {
                    firstChild.genes.set(i, genes.get(i));
                }
                if (index2 >= start && index2 <= end) {
                    secondChild.genes.set(i, swappingValue2);
                } else {
                    secondChild.genes.set(i, parentChromosome.genes.get(i));
                }
            }
            if (firstChild.validateChildren() && secondChild.validateChildren()) {
                break;
            }
        }
    }

    public boolean validateChildren() {
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
