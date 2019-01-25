import java.util.ArrayList;
/**
 * 
 * @author sam
 *  Main class for GameOfLife, creates a loop which listens for input to iterate the game.
 *  Starts with an automatically pre-poulated list of live cells.
 */
public class Grid {
	// Note the origin of the grid corresponds to the top left corner.
	// Grid contains the list of living cells.
	private ArrayList<Point> grid = new ArrayList<Point>();
	public Grid() {
		// Instantiate grid with default set of points.
		this.grid = new ArrayList<Point>();
	}
	
	// Update these values as we populate the grid list. They are used for rendering purposes.
	private int maxY = 0;
	private int maxX = 0;

	
	public static void main(String[] args) {
		Grid grid = new Grid();
	}
}
