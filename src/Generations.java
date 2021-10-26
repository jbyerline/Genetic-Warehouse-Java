package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Generations {
    Chromosome[] currentGenChromosomes;
    Chromosome[] intermediateGenChromosomes;
    Chromosome[] nextGenChromosomes;
    int generationFitnessSum = 0;
    int childCounter = 0;
    int numOfChromosomes;
    ArrayList<Coordinate> houseCoordinates;

    Generations(int chromosomeLength, int numbersOfCycles, int numOfChromosomes, ArrayList<Coordinate> houseCoordinates) {
        // Instantiate src.Chromosome Arrays
        this.houseCoordinates = houseCoordinates;
        this.numOfChromosomes = numOfChromosomes;
        currentGenChromosomes = new Chromosome[numOfChromosomes];
        intermediateGenChromosomes = new Chromosome[numOfChromosomes * 2];
        nextGenChromosomes = new Chromosome[numOfChromosomes];

        // Fill chromosome arrays
        for (int i = 0; i < numOfChromosomes; i++) {
            currentGenChromosomes[i] = new Chromosome(chromosomeLength);
        }
        for (int i = 0; i < numOfChromosomes; i++) {
            generationFitnessSum += currentGenChromosomes[i].fitness();
        }

        // Run operation on chromosomes
        for (int i = 0; i < numbersOfCycles; i++) {
            performOperations();
            System.out.print("Average Fitness for cycle: ");
            System.out.print(i);
            System.out.print(" is :");
            System.out.print(fittestChromosome(currentGenChromosomes));
            System.out.println();
            for (int j = 0; j < numOfChromosomes; j++) {
                currentGenChromosomes[j].genes = nextGenChromosomes[j].genes;
            }
        }
    }

    public int generationFitness(Chromosome[] generation) {
        int fitness = 0;
        for (int i = 0; i < numOfChromosomes; i++) {
            fitness += generation[i].fitness();
        }
        return (fitness / numOfChromosomes);
    }

    public double fittestChromosome(Chromosome[] generation) {
        int fitness = 99999999;
        for (int i = 0; i < numOfChromosomes; i++) {
            if (fitness > generation[i].fitness()) ;
            {
                fitness = (int) generation[i].fitness();
            }
        }
        return fitness;
    }

    public void filterBest() {
        double[] copy = new double[childCounter];
        for (int i = 0; i < childCounter; i++) {
            copy[i] = intermediateGenChromosomes[i].fitness();
        }
        Arrays.sort(copy);
        for (int i = 0; i < numOfChromosomes; i++) {
            for (int j = 0; j < childCounter; j++) {
                if (intermediateGenChromosomes[j].fitness() == copy[i]) {
                    nextGenChromosomes[i] = intermediateGenChromosomes[j];
                }
            }
        }
        childCounter = 0;
    }

    public void performOperations() {
        double r = Math.random();
        int random1, random2;
        while (childCounter < numOfChromosomes * 2) {
            if (r > 0.5) {
                int x = (int) (Math.random() * numOfChromosomes);
                intermediateGenChromosomes[childCounter] = currentGenChromosomes[x].mutation();
                childCounter++;
                r = Math.random();
            } else {
                if (childCounter == (numOfChromosomes * 2) - 1) {
                    break;
                }
                while (true) {
                    random1 = rouletteWheel();
                    random2 = rouletteWheel();
                    if (random1 != random2) {
                        break;
                    }
                }
                r = Math.random();
                currentGenChromosomes[random1].crossover(currentGenChromosomes[random2]);
                intermediateGenChromosomes[childCounter] = currentGenChromosomes[random1].child1;
                intermediateGenChromosomes[childCounter + 1] = currentGenChromosomes[random1].child2;
                childCounter = childCounter + 2;
            }
        }
        filterBest();
    }

    public int rouletteWheel() {
        double[] probabilities = new double[numOfChromosomes];
        double[] finalProbabilities = new double[numOfChromosomes];
        double sumOfProbabilities = 0;
        for (int i = 0; i < numOfChromosomes; i++) {
            probabilities[i] = (generationFitnessSum / currentGenChromosomes[i].fitness());
            sumOfProbabilities += probabilities[i];
        }
        for (int i = 0; i < numOfChromosomes; i++) {
            if (i == 0) {
                finalProbabilities[i] = probabilities[i] / sumOfProbabilities;
            } else {
                finalProbabilities[i] = finalProbabilities[i - 1] + (probabilities[i] / sumOfProbabilities);
            }
        }

        double probability = Math.random();
        int counter = 0;
        while (true) {
            if (probability > finalProbabilities[counter]) {
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }
}
