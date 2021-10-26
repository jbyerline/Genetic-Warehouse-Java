package src;

import java.util.ArrayList;

public class TravellingSalesman {
    public static void main(String[] args) {
        // 2D array for
        Grid map = new Grid(30, new Coordinate(5,5), new Coordinate(25,25));

        ArrayList<Coordinate> houseLocations = map.randomizeGrid(40);

        System.out.println(houseLocations.toString());

        map.printCurrentGrid();

        new Generations(25, 400, 25, houseLocations);

    }
}
