package graph_b;

public class edge {
	
	int x;
	int y;
	int weight;
	
	public edge(int x, int y, int weight) {
		
		this.x = x;
		this.y = y;
		this.weight = weight;
		
	}
	
	public Integer getW() {
		return weight;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
