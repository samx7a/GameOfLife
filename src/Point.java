/**
 * 
 * @author sam
 * Class to represent a point in the grid inhabited by a cell.
 */
public class Point {
	private int x = 0;
	private int y = 0;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
