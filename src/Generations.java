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
    ArrayList<Double> fittnessList = new ArrayList<>();


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
            generationFitnessSum += currentGenChromosomes[i].calculateFitness();
        }

        // Run operation on chromosomes
        for (int i = 0; i < numbersOfCycles; i++) {
            performOperations();
            fittnessList.add(fittestChromosome(currentGenChromosomes));
            System.out.print("Average Fitness for cycle: " + i + " is: " + fittestChromosome(currentGenChromosomes) + "\n");
            for (int j = 0; j < numOfChromosomes; j++) {
                currentGenChromosomes[j].genes = nextGenChromosomes[j].genes;
            }
        }
        Double average = fittnessList.stream().mapToDouble(val -> val).average().orElse(0.0);
        System.out.println("\nStarting Fitness: " + fittnessList.get(0));
        System.out.println("Overall Average: " + average);
    }

    public int generationFitness(Chromosome[] generation) {
        int fitness = 0;
        for (int i = 0; i < numOfChromosomes; i++) {
            fitness += generation[i].calculateFitness();
        }
        return (fitness / numOfChromosomes);
    }

    public double fittestChromosome(Chromosome[] generation) {
        int fitness = 100000000;
        for (int i = 0; i < numOfChromosomes; i++) {
            if (fitness > generation[i].calculateFitness()) ;
            {
                fitness = (int) generation[i].calculateFitness();
            }
        }
        return fitness;
    }

    public void filterBest() {
        double[] copy = new double[childCounter];
        for (int i = 0; i < childCounter; i++) {
            copy[i] = intermediateGenChromosomes[i].calculateFitness();
        }
        Arrays.sort(copy);
        for (int i = 0; i < numOfChromosomes; i++) {
            for (int j = 0; j < childCounter; j++) {
                if (intermediateGenChromosomes[j].calculateFitness() == copy[i]) {
                    nextGenChromosomes[i] = intermediateGenChromosomes[j];
                }
            }
        }
        childCounter = 0;
    }

    public void performOperations() {
        double r = Math.random();
        int firstRoulette, secondRoulette;
        while (childCounter < numOfChromosomes * 2) {
            if (r > 0.5) {
                int x = (int) (Math.random() * numOfChromosomes);
                intermediateGenChromosomes[childCounter] = currentGenChromosomes[x].mutate();
                childCounter++;
                r = Math.random();
            } else {
                if (childCounter == (numOfChromosomes * 2) - 1) {
                    break;
                }
                while (true) {
                    firstRoulette = rouletteWheel();
                    secondRoulette = rouletteWheel();
                    if (firstRoulette != secondRoulette) {
                        break;
                    }
                }
                r = Math.random();
                currentGenChromosomes[firstRoulette].crossover(currentGenChromosomes[secondRoulette]);
                intermediateGenChromosomes[childCounter] = currentGenChromosomes[firstRoulette].firstChild;
                intermediateGenChromosomes[childCounter + 1] = currentGenChromosomes[firstRoulette].secondChild;
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
            probabilities[i] = (generationFitnessSum / currentGenChromosomes[i].calculateFitness());
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
