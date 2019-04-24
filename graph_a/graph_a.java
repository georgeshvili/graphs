package graph_a;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class graph_a {

	public LinkedList<Integer>[] list;
	int size;
	int[][] weights;
	
	public graph_a(int size, int[][] weights) {
		this.size=size;
		list = new LinkedList[size];
		this.weights = weights;
	}
	
	public graph_a(int size) {
		this.size=size;
		list = new LinkedList[size];
	}
	
	public void addVershina(int i) {
		list[i]=new LinkedList<>();
	}
	
	public void addSvyaz(int ver1, int ver2) {
		list[ver1].add(ver2);
	}
	
	public void addSvyazTS(int ver1, int ver2) {
		list[ver1].add(ver2);
		list[ver2].add(ver1);
	}
	
	public void addSvyaz(int ver1, int ver2, int weight) {
		list[ver1].add(ver2);
		weights[ver1][ver2] = weight;
	}
	
	public void addSvyazTS(int ver1, int ver2, int weight) {
		list[ver1].add(ver2);
		list[ver2].add(ver1);
		weights[ver1][ver2] = weight;
		weights[ver2][ver1] = weight;
	}
	
	public void dfsRec(int s){
		boolean [] isVisited = new boolean[size];
		dfs(s, isVisited);
	}
		
	private void dfs(int s, boolean [] isVisited){
		isVisited[s] = true;
		System.out.print("["+s + "]");
		for (int i = 0; i < list[s].size() ; i++) {
			int y = list[s].get(i);
			if(isVisited[y] == false)
				dfs(y,isVisited);
		}
	}
	
	public void dfsStack(int s) {
		
		boolean [] isVisited = new boolean[size];
		Stack<Integer> stack = new Stack<>();
		
		stack.push(s);
		
		while(!stack.empty()) {
			int x = stack.pop();
			if(isVisited[x] == false) {
				System.out.print("["+x + "]");
				isVisited[x] = true;
				for (int i = 0; i < list[x].size() ; i++) {
					int y = list[x].get(i);
					if(isVisited[y] == false) {
						stack.push(y);
					}
				}
			}
		}
	}
	
	public void bfs (int s) {
		
		boolean [] isVisited = new boolean[size];
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(s);
		isVisited[s] = true;
		
		while(queue.size() != 0) {
			int x = queue.poll();
			System.out.print("["+ x + "]");
			for (int i = 0; i < list[x].size() ; i++) {
				int y = list[x].get(i);
				if(isVisited[y] == false) {
					queue.add(y);
					isVisited[y] = true;
				}
			}
		}
	}
	
	public void printArray(int arr[])  { 
        int l = arr.length; 
        for (int i=0; i<l; ++i) 
            System.out.print("["+arr[i]+"]"); 
        System.out.println(); 
    }
	
	public int findMin(int[] mas1, Queue<Integer> queue) {
		int min = ((LinkedList<Integer>)queue).get(0);
		
		for(int j = 0; j < queue.size(); j++) {
			if(mas1[((LinkedList<Integer>)queue).get(j)] < mas1[min]) {
				min = ((LinkedList<Integer>)queue).get(j);
			}
		}
		return min;
	}
	
	public void dijkstra (int s) {
		
		int[] rast = new int[size];
		Queue<Integer> queue = new LinkedList<>();
		
		Arrays.fill(rast, Integer.MAX_VALUE);
		
		for(int i = 0; i < size; i++) {
			queue.add(i);
		}
		
		rast[s] = 0;
		
		while(queue.size() != 0) {
			int v = findMin(rast,queue);
			queue.remove(v);
			
			for(int i = 0; i < size; i++) {
					if(rast[i] > rast[v] + weights[v][i] && weights[v][i] != 0) {
						if(rast[i] == Integer.MAX_VALUE) {
							rast[i] = rast[v] + weights[v][i];
						}
					}
			}
		}

		printArray(rast);
		
	}
	
	private boolean isEverythingVisited(boolean[] mas) {
		for(int i = 0; i < size; i++) {
			if(mas[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	private int findMin(int[] mas1, boolean[] mas) {
	
		int n = 0; 
		int min = Integer.MAX_VALUE;
		
        for (int j = 0; j < size; j++)
            if (!mas[j] && mas1[j] < min) {
                min = mas1[j];
                n = j;
            }
        
        return n;
	}
	
	public void prima (int s) {
		
		int[] rast = new int[size];
		int[] parent = new int[size];
		boolean[] isVisited = new boolean[size];
		
		Arrays.fill(rast,(Integer.MAX_VALUE)/2);
		Arrays.fill(parent, -1);
		Arrays.fill(isVisited, false);
		
		rast[s] = 0;
		
		while(!isEverythingVisited(isVisited)) {
			
			int n = findMin(rast, isVisited);
            isVisited[n] = true;
			
			for(int v = 0; v < size; v++) {
				if(weights[n][v] != 0) {
					if(weights[n][v] < rast[v] && isVisited[v] == false) {
						rast[v] = weights[n][v];
						parent[v] = n;
					}
				}
			}
		}
		
		for(int v = 0; v < size; v++) {
			if(parent[v] != -1) {
				System.out.print("(" + v + ","); System.out.print(" "+parent[v]+")");
				System.out.println();
			}
		}
		
 	}
	
	private int findMin(int x, int y) {
		if(x > y) {
			return y;
		} else {
			return x;
		}
	}
	
	public void floyd_warshall () {
				
		int i, j, k;
		
		for(i = 0; i < size; i++) {
			for(j = 0; j < size; j++) {
				if(i != j && weights[i][j] == 0) {
					weights[i][j] = 99999;
				}
			}
		}

		for(k = 0; k < size; k++) {
			for(i = 0; i < size; i++) {
				for(j = 0; j < size; j++) {
					weights[i][j] = findMin(weights[i][j], weights[i][k] + weights[k][j]);
				}
			}
		}
		
		for (i = 0; i < weights.length; i++) {
			for (j = 0; j < weights[i].length; j++) {
				System.out.print(weights[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
    private void removeEdge(Integer ver1, Integer ver2) { 
        list[ver1].remove(ver2); 
        list[ver2].remove(ver1); 
    } 
  
    public void fleury() { 

        Integer u = 0; 
        for (int i = 0; i < size; i++) { 
            if (list[i].size() % 2 == 1) { 
                u = i; 
                break; 
            } 
        } 
        printVershina(u); 
    } 

    private void printVershina(Integer u) { 
        for (int i = 0; i < list[u].size(); i++) { 
            Integer v = list[u].get(i); 
            if (isNext(u, v))  { 
                System.out.println("(" + u + "," + v + ")"); 
                removeEdge(u, v);  
                printVershina(v); 
            } 
        } 
    } 

    private boolean isNext(Integer u, Integer v) { 

        if (list[u].size() == 1) { 
            return true; 
        } 

        boolean[] isVisited = new boolean[size]; 
        int countVer1 = dfsCount(u, isVisited); 

        removeEdge(u, v); 
        isVisited = new boolean[size]; 
        int countVer2 = dfsCount(u, isVisited); 

        addSvyazTS(u, v); 
        if(countVer1 > countVer2) {
        	return false;
        } else {
        	return true;
        }
    } 

    private int dfsCount(Integer v, boolean[] isVisited) { 

        isVisited[v] = true; 
        int count = 1; 
        for(int i = 0; i < list[v].size(); i++) {
            if (isVisited[list[v].get(i)] == false) { 
                count = count + dfsCount(list[v].get(i), isVisited); 
            } 
        } 
        return count; 
    }
}
