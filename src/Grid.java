import java.util.ArrayList;
/**
 * 
 * @author sam
 *  Main class for GameOfLife, creates a loop which listens for input to iterate the game.
 *  Starts with an automatically pre-poulated list of live cells.
 */
public class Grid {
	// Note the origin of the grid (0,0) corresponds to the top left corner.
	// Grid contains the list of living cells.
	private ArrayList<Point> grid = new ArrayList<Point>();
	// Update these values as we populate the grid list. They are used for rendering purposes.
	private int maxY = 0;
	private int maxX = 0;
	private int minY = 0;
	private int minX = 0;
	public Grid() {
		// Instantiate grid with default set of points.
		this.grid = new ArrayList<Point>();
		grid.add(new Point(1,1));
		grid.add(new Point(1,2));
		grid.add(new Point(1,3));
		this.minX = 1;
		this.maxX = 1;
		this.minY = 1;
		this.maxY = 3;
	}
	private boolean containsPoint(int x,int y) {
		for(Point p:grid) {
			if((p.getX() == x) && (p.getY() == y))
				return true;
		}
		return false;
	}
	public void renderGrid() {
		String renderFormat = "%s|";
		// Render grid with 1 buffer column and 1 buffer row.
		for(int x = minX - 1 ; x <= maxX + 1; x++) {
			for(int y = minY - 1; y<= maxY + 1; y++) {
				System.out.printf(renderFormat, containsPoint(x,y) ? "•" : " ");
			}
			System.out.print("\n");
			for(int y2 = minY - 1; y2<= maxY + 1; y2++) {
				System.out.print("-+");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {
		Grid grid = new Grid();
		grid.renderGrid();
	}
}
