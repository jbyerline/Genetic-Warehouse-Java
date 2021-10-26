package src;

/**
 * The Coordinate class serves as a template for (x,y) coordinates.
 *
 * @author  Jacob Byerine
 * @version 1.0
 * @since   2021-09-08
 */
public class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
