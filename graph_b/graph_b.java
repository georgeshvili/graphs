package graph_b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

public class graph_b {

	int size;
	public LinkedList<edge> allE = new LinkedList<>();
	int[][] weights;
	int[][] svyaz;
	
	public graph_b (int size) {
		this.size = size;
		this.weights = weights;
		weights = new int[size][size];
		svyaz = new int[size][size];
	}
	
	public void addEdge (int x, int y, int weight) {
		allE.add(new edge(x, y, weight));
		weights[x][y] = weight;
		svyaz[x][y] = 1;
	}
	
	public void addEdgeTS (int x, int y, int weight) {
		allE.add(new edge(x, y, weight));
		allE.add(new edge(y, x, weight));
		weights[x][y] = weight;
		weights[y][x] = weight;
		svyaz[x][y] = 1;
		svyaz[y][x] = 1;
	}
	
	public void kruskal () {
		
		int[] rast = new int[size];
		LinkedList<edge> result =  new LinkedList<>();
		
		Comparator<edge> comparator = Comparator.comparing(obj -> obj.getW());
		Collections.sort(allE, comparator);

		for(int v = 0; v < size; v++) {
			rast[v] = v;
		}
		
		for(int i = 0; i < allE.size(); i++) {
			if(rast[allE.get(i).getX()] != rast[allE.get(i).getY()]) {
				result.add(allE.get(i));
				int temp = rast[allE.get(i).getX()];
				for(int j = 0; j < size; j++) {
					if(rast[j] == temp) {
						rast[j] = rast[allE.get(i).getY()];
					}
				}
			}
		}
		
		for(int i = 0; i < result.size(); i++) {
			System.out.printf("(%d, %d) ", result.get(i).getX(), result.get(i).getY());
	        System.out.print("\n");
		}
		
	}
	
	private void dfsInv(int x, boolean[] isVisited, Stack<Integer> stack) {
		
		isVisited[x] = true;
		int y = 0;
		
		for(int i = 0; i < allE.size(); i++) {
			if(allE.get(i).getX() == x) {
				 y = allE.get(i).getY();
				 if(isVisited[y] == false) {
						dfsInv(y, isVisited, stack);
					}
			}
		}
		System.out.print("["+x+"]");
		stack.push(x);
	}
	
	public void tarjan() {
		
		boolean[] isVisited = new boolean[size];
		Arrays.fill(isVisited, false);
		Stack<Integer> stack = new Stack<>();
		
		for(int v = 0; v < size; v++) {
			if(isVisited[v] == false) {
				dfsInv(v, isVisited, stack);
			}
		}
	}
	
	private void removeE(int x, int y) {
		
		for(int i = 0; i < allE.size(); i++) {

			if(x == allE.get(i).getX() && y == allE.get(i).getY()) {
				if(allE.size() == 2) {
					allE.remove(i);
					allE.remove(i);
					break;
				}
				if(x > y) {
				allE.remove(i);
				allE.remove(i-1);
				} else {
					allE.remove(i);
					allE.remove(i);
				}
			}
		}
	}
	
	public void eulirianPath(int s) {
		
		ArrayList<Integer> result = new ArrayList<>();
		
		eulirianPathRec(s, result);
		
		Arrays.toString(result.toArray());
		System.out.println(result);
	}
	
	private void eulirianPathRec(int v, ArrayList<Integer> result) {
		
		for(int i = 0; i < allE.size(); i++) {
			if(v == allE.get(i).getX()) {
				int u = allE.get(i).getY();
				removeE(v,u);
				eulirianPathRec(u, result);
			}
		}
		result.add(v);
	}
	
	private void dfsInv (int x, boolean[] mas, int[][] matrix, Stack<Integer> stack) {
		
		mas[x] = true;
		
		for(int y = 0; y < size; y++) {
			if(matrix[x][y] == 1) {
				if(mas[y] == false) {
					dfsInv(y, mas, matrix, stack);
				}
			}
		}
		
		System.out.print("["+x+"]");
		stack.push(x);
		
	}
	
	public Stack<Integer> kosarajuGT() {
		
		boolean[] isVisited = new boolean[size];
		Stack<Integer> stack = new Stack<>();
		
		int[][] matrix = svyaz;
		
		for (int i = 0; i < matrix.length; i++) {
	        for (int j = i+1; j < matrix[0].length; j++) {
	            int temp = matrix[i][j];
	            matrix[i][j] = matrix[j][i];
	            matrix[j][i] = temp;
	        }
	    }
		
		Arrays.fill(isVisited, false);
		
		for(int v = 0; v < size; v++) {
			if(isVisited[v] == false) {
				dfsInv(v, isVisited, matrix, stack);
			}
		}
		
		return stack;
		
	}
	
	
	public void kosarajuG(Stack<Integer> stack) {
		
		boolean[] isVisited = new boolean[size];
		
		Arrays.fill(isVisited, false);
		
		while(stack.empty() == false) {
			int v = stack.pop();
			if(isVisited[v] == false) {
				dfsInv(v,isVisited,svyaz,stack);
			}
		}
	}
}
