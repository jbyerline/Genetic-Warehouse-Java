# CS 657 Intelligent Systems

### Assignment 3 – Genetic System

### Author: Jacob Byerline

# Program:

## Problem Description:
The problem at hand is to develop a genetic system that can determine a close to optimal route to deliver appliances. We have two warehouses and 40 houses to deliver two. These are all represented on a 30km-by-30km grid. The 40 houses are randomly generated with every iteration of the program. Each chromosome in the genetic algorithm represents a possible sequence of deliveries or route. For this program we start off with generating 15 random chromosomes.


## My Approach:
I decided to create this program in Java. At a high level, I broke this program down into 3 distinct classes. They are as follows:

1.	Main
2.	Grid
3.	Coordinate
4.	Chromosome
5.	Generations

Starting from the top of the class list, the Main class serves as an entry point of this program. It instantiates a grid of size 30-by-30 and defines the warehouse locations of (5,5) and (25,25). It then tells the grid to randomly populate 40 houses and print the grid. Finally, it triggers the Generation class to run 400 cycles with 15 chromosomes of length 25.

The next class is the Grid class. This class allows us to visualize the task at hand better. It contains the grid data structure which is a 2D array. It also contains the logic for randomly populating and filling the grid.

Next, we have the Coordinate class. This class serves as a template for (x,y) coordinates and allows us to easily refer to and pass around coordinate sets.

The Chromosome class contains several methods specific to chromosome objects. These are mutation, crossover, and validation.

Finally, the Generations class performs the operations on the chromosomes. It checks for paths and calls mutations to happen. It then determines the fittest chromosome in each generation and returns the fitness.

## Trials:
After running 5 simulation trials where each goes through 400 cycles. I was surprised by the fluctuation in my results. While the average overall fitness is near the median of the overall dataset for each trial, they still fluctuate by as much as 400. I suppose this is to be expected when our results are based on random home locations. The advantage of using a genetic algorithm like this one over a brute force alternative is that will produce. A result much sooner for a large data set. However, the downside is that the genetic algorithm will most probably not produce the exact best path possible. This is because the genetic algorithm will not check all possible combinations. So, there is still potential for a faster alternative route.


## Graphs:
As I mentioned above, these results fluctuate more than I would have expected but I believe that this is due to the random nature of the problem.

![Graph 1](https://github.com/jbyerline/Genetic-Warehouse-Java/blob/master/Graph1.png?raw=true)
![Graph 2](https://github.com/jbyerline/Genetic-Warehouse-Java/blob/master/Graph2.png?raw=true)

## Notable Additions:
The program prints out a map of the home and warehouse locations in the console. The 4’s represent homes to deliver to. The 8 is warehouse 1, and the 9 is warehouse 2. The *’s represent all of the other houses in this perfect grid city.

## Running Instructions:
This program was written with Java 17.0.1. The executable jar file can be run using the command java -jar Program3.jar on MacOS. Please keep in mind, to execute a jar file, you need to have the same version of Java installed on your system. Otherwise, you can compile the source code using your local java by opening the code in and IDE and executing the main method.

Once the program has started it will run based on the hard coded program parameters. It will print out the map of coordinates, a list of home coordinates, the fitness per each of the 400 generations, and finally, the average fitness of those 400 generations.

Source code can be found on my GitHub at: https://github.com/jbyerline/Genetic-Warehouse-Java

### Contact
For questions, please reach out to [Jacob Byerline](mailto:jbyerline@gmail.com)