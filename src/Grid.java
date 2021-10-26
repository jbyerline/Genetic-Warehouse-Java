import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Grid class serves as a template for both the actual map and the "Car's Memory Map".
 * This class is used by the Main and the Car classes
 *
 * @author  Jacob Byerine
 * @version 1.0
 * @since   2021-09-08
 */
public class Grid {
    public int[][] gridArray;
    public int size;
    public Coordinate warehouseA_Coord;
    public Coordinate warehouseB_Coord;

    public Grid(int size, Coordinate warehouseA_Coord, Coordinate warehouseB_Coord) {
        gridArray = new int[size][size];
        this.size = size;
        this.warehouseA_Coord = warehouseA_Coord;
        this.warehouseB_Coord = warehouseB_Coord;
    }

    public ArrayList<Coordinate> randomizeGrid(int numberOfHouses) {
        // Calculate number of spaces to block based on percentage
        int spacesToFill = numberOfHouses;

        ArrayList<Coordinate> houseLocations = new ArrayList<>();

        // Fill ArrayList with that many blockades(1's)
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < (size * size); i++) {
            if (i < spacesToFill) {
                list.add(4);
            } else {
                list.add(0);
            }
        }

        // Shuffle the list
        Collections.shuffle(list);

        // Fill the game grid with the randomized list
        int counter = 0;
        for (int i = 0; i < gridArray.length; i++) {
            for (int j = 0; j < gridArray[i].length; j++) {
                gridArray[i][j] = list.get(counter);
                counter++;
            }
        }

        for (int i = 0; i < gridArray.length; i++) {
            for (int j = 0; j < gridArray[i].length; j++) {
                if (gridArray[i][j] == 4) {
                    houseLocations.add(new Coordinate(i, j));
                }
            }
        }

        // Set Car's starting position and ending position
        gridArray[warehouseA_Coord.x][warehouseA_Coord.y] = 8;
        gridArray[warehouseB_Coord.x][warehouseB_Coord.y] = 9;

        return houseLocations;
    }

    /**
     * This method prints out the current contents of the given Grid
     */
    public void printCurrentGrid() {
        // Print out what the car currently sees.
        for (int x = 0; x < gridArray.length; x++) {
            for (int y = 0; y < gridArray.length; y++) {
                if(gridArray[x][y] == 0) {
                    System.out.print("*  ");
                } else {
                    System.out.print(gridArray[x][y] + "  ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
