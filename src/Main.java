import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Grid map = new Grid(30, new Coordinate(5,5), new Coordinate(25,25));

        ArrayList<Coordinate> houseLocations = map.randomizeGrid(40);

        System.out.println(houseLocations.toString());

        map.printCurrentGrid();

        new Generations(25, 400, 15, houseLocations);

    }
}
