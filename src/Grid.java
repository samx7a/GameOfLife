import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 * @author sam
 *  Main class for GameOfLife, creates a loop which listens for input to iterate the game.
 *  Starts with an automatically pre-populated list of live cells.
 */
public class Grid {
	// Note the origin of the grid (0,0) corresponds to the top left corner.
	// Grid contains the list of living cells.
	private ArrayList<Point> grid = new ArrayList<Point>();
	public Grid() {
		// Instantiate grid with default set of points.
		this.grid = new ArrayList<Point>();
		grid.add(new Point(1,1));
		grid.add(new Point(1,2));
		grid.add(new Point(1,3));
	}
	private boolean containsPoint(Point point) {
		for(Point p:grid) {
			if((p.getX() == point.getX()) && (p.getY() == point.getY()))
				return true;
		}
		return false;
	}
	private boolean containsPoint(int x,int y) {
		for(Point p:grid) {
			if((p.getX() == x) && (p.getY() == y))
				return true;
		}
		return false;
	}
	/**
	 * 
	 * @param grid A list of cells
	 * @param point A point to check if exists in the list of cells
	 * @return true if point is a live cell in the supplied grid.
	 * This function signature is used when we want to check existence
	 * of a point in a grid that is not the current state, as stored in 
	 * this.grid
	 */
	private boolean containsPoint(ArrayList<Point> grid, Point point) {
		for(Point p:grid) {
			if((p.getX() == point.getX()) && (p.getY() == point.getY()))
				return true;
		}
		return false;
	}
	/**
	 * A method to return all living neighbouring cells for a given cell
	 * @param cell The cell to have its neighbours explored.
	 * @return Returns a list of living cells.
	 */
	private ArrayList<Point> getNeighbours(Point cell){
		ArrayList<Point> adjacentSpaces = generateAdjacentSpaces(cell);
		Iterator<Point> iter = adjacentSpaces.iterator();
		while (iter.hasNext()) {
		    Point space = iter.next();
		    // If the adjacent space is occupied by a living cell in our grid.
		    if(this.containsPoint(space))
		    	continue;
	    	else 
		    	iter.remove();	
		}
		return adjacentSpaces;
	}
	/**
	 * A helper method to generate the 8 adjacent spaces for a given cell
	 * @param cell The living cell to calculate neighbours from
	 * @return We return an ArrayList of Points, of size 8 containing all
	 * potential neighbours for the provided cells.
	 * (Horizontals, Verticals and Diagonals).
	 */
	private ArrayList<Point> generateAdjacentSpaces(Point cell){
		int x = cell.getX();
		int y = cell.getY();
		ArrayList<Point> neighbours = new ArrayList<Point>();
		// Horizontals
		neighbours.add(new Point(x, y + 1));
		neighbours.add(new Point(x, y - 1));
		// Verticals
		neighbours.add(new Point(x + 1, y));
		neighbours.add(new Point(x - 1, y));
		// Diagonals
		neighbours.add(new Point(x + 1, y + 1));
		neighbours.add(new Point(x - 1, y + 1));
		neighbours.add(new Point(x + 1, y - 1 ));
		neighbours.add(new Point(x - 1, y - 1));
		return neighbours;
	}
	public void renderGrid() {
		// Find minimum and maximum x,y values for the grid.
		// Ignore initial values.
		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;
		boolean firstFlag = true;
		for(Point cell : this.grid) {
			if(firstFlag) {
				minX = cell.getX();
				maxX = cell.getX();
				minY = cell.getY();
				maxY = cell.getY();
				firstFlag = false;
			}
			else {
				minX = Math.min(minX, cell.getX());
				maxX = Math.max(maxX, cell.getX());
				minY = Math.min(minY, cell.getY());
				maxY = Math.max(maxY, cell.getY());
			}
		}
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
		System.out.print("\n");
	}
	/**
	 * A procedure to iterate the state of the grid
	 * Determines future states of cells by applying scenario cases in sequence
	 */
	private void iterate() {
		// List of cells present for next state of iteration.
		ArrayList<Point> nextGrid = new ArrayList<Point>();
		// No living cells: As you were
		if(this.grid.size() == 0)
			return;
		
		Iterator<Point> iter = this.grid.iterator();

		while (iter.hasNext()) {
		    Point cell = iter.next();
		    int neighbourCount = getNeighbours(cell).size();
		    /**
		     *  Underpopulation: When a live cell has fewer than two living neighbours
		     *  it dies.
		     *  Overpopulation: When a live cell has more than three neighbours
		     *  it dies.
		     *  Survival: When a live cell has two or three neighbours, it survives.
		     */
		     if ((neighbourCount == 2)||(neighbourCount == 3) )
		    	nextGrid.add(cell);
		     /** 
		      * Creation of life: When a given empty position has exactly three
		      * neighbouring cells, a cell is created in that position.
		      * 
		      * While we are iterating through all the live cells in the current grid:
		      * Check the adjacent spaces of each current cell.
		      * Check whether the given adjacent space has 3 neighbours.
		      * If it does then create a live cell from the empty location point.
		      */
		     ArrayList<Point> adjacentSpaces = this.generateAdjacentSpaces(cell);
		     Iterator<Point> adjacentSpacesIterator = adjacentSpaces.iterator();
		     while(adjacentSpacesIterator.hasNext()){
		    	 Point space = adjacentSpacesIterator.next();
		    	 // Check whether the generated space is already live cell.
		    	 if(this.containsPoint(space)||this.containsPoint(nextGrid,space)) 
		    		 continue;
		    	 int neighbourCountOfSpace = getNeighbours(space).size();
		    	 // If it has three live neighbours then create a new cell here.
		    	 if(neighbourCountOfSpace == 3)
		    		 nextGrid.add(space);
		     }
		}
		this.grid = nextGrid;
	}
	public static void main(String[] args) throws IOException {
		Grid grid = new Grid();
		int iterateCount = 0;
		grid.renderGrid();
		System.out.println("Press key to iterate\n");
		while(true) {
			System.in.read();
			iterateCount++;
			System.out.printf("Number of iterations %d. Press key to iterate.\n", iterateCount);
			grid.iterate();
			grid.renderGrid();
		}
	}
}
