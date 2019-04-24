package test;
import java.util.Stack;

import graph_a.graph_a;
import graph_b.graph_b;

public class test {

	public static void main(String[] args) {
		
		int sizeP = 7;
		int sizeD = 7;
		int sizeFlo = 4;
		int sizeDFSBFS = 7;
		int sizeFle = 4;
		int sizeKos = 8;
		int sizeEu = 6;
		int sizeT = 7;
		int sizeKru = 7;

		int[][] weightsPrima = new int[sizeP][sizeP];
		int[][] weightsDijkstra = new int[sizeD][sizeD];
		int[][] weightsFloydWarshall = new int[sizeFlo][sizeFlo];
	
		graph_a graphPrima = new graph_a(sizeP, weightsPrima);
		for(int i = 0; i < sizeP; i++) {
			graphPrima.addVershina(i);
		}
		graphPrima.addSvyazTS(0,1,2);
		graphPrima.addSvyazTS(0,2,1);
		graphPrima.addSvyazTS(0,3,3);
		graphPrima.addSvyazTS(0,5,6);
		graphPrima.addSvyazTS(1,3,1);
		graphPrima.addSvyazTS(1,4,3);
		graphPrima.addSvyazTS(2,3,2);
		graphPrima.addSvyazTS(2,5,3);
		graphPrima.addSvyazTS(3,6,1);
		graphPrima.addSvyazTS(4,6,4);
		graphPrima.addSvyazTS(5,6,7);
		System.out.println("Prima: ");
		graphPrima.prima(4);
		
		graph_a graphDFS = new graph_a(sizeDFSBFS);
		for(int i = 0; i < sizeDFSBFS; i++) {
			graphDFS.addVershina(i);
		}
		graphDFS.addSvyaz(0,1);
		graphDFS.addSvyaz(0,2);
		graphDFS.addSvyaz(1,3);
		graphDFS.addSvyaz(2,3);
		graphDFS.addSvyaz(2,5);
		graphDFS.addSvyaz(2,6);
		graphDFS.addSvyaz(1,4);
		System.out.println("DFS recursive: ");
		graphDFS.dfsRec(0);
		System.out.println();
		System.out.println("DFS stack: ");
		graphDFS.dfsStack(0);
		System.out.println();
		
		graph_a graphBFS = new graph_a(sizeDFSBFS);
		for(int i = 0; i < sizeDFSBFS; i++) {
			graphBFS.addVershina(i);
		}
		graphBFS.addSvyaz(0,1);
		graphBFS.addSvyaz(0,2);
		graphBFS.addSvyaz(1,3);
		graphBFS.addSvyaz(2,3);
		graphBFS.addSvyaz(2,5);
		graphBFS.addSvyaz(2,6);
		graphBFS.addSvyaz(1,4);
		System.out.println("BFS: ");
		graphBFS.bfs(0);
		System.out.println();
		
		graph_a graphDijkstra = new graph_a(sizeD, weightsDijkstra);
		for(int i = 0; i < sizeD; i++) {
			graphDijkstra.addVershina(i);
		}
		graphDijkstra.addSvyaz(0,1,4);
		graphDijkstra.addSvyaz(0,2,8);
		graphDijkstra.addSvyaz(1,3,1);
		graphDijkstra.addSvyaz(2,3,3);
		graphDijkstra.addSvyaz(2,5,5);
		graphDijkstra.addSvyaz(2,6,4);
		graphDijkstra.addSvyaz(1,4,7);
		System.out.println("Dijkstra: ");
		graphDijkstra.dijkstra(0);
		
		graph_a graphFloydWarshall = new graph_a(sizeFlo, weightsFloydWarshall);
		for(int i = 0; i < sizeFlo; i++) {
			graphFloydWarshall.addVershina(i);
		}
		graphFloydWarshall.addSvyaz(0,1,5);
		graphFloydWarshall.addSvyaz(0,3,10);
		graphFloydWarshall.addSvyaz(1,2,3);
		graphFloydWarshall.addSvyaz(2,3,1);
		System.out.println("Floyd-Warshall: ");
		graphFloydWarshall.floyd_warshall();
		
		graph_a graphFleury = new graph_a(sizeFle);
		for(int i = 0; i < sizeFlo; i++) {
			graphFleury.addVershina(i);
		}
		graphFleury.addSvyazTS(0, 1);
		graphFleury.addSvyazTS(0, 2);
		graphFleury.addSvyazTS(1, 2);
		graphFleury.addSvyazTS(2, 3);
		System.out.println("Fleury: ");
		graphFleury.fleury();
		
		graph_b graphKosaraju = new graph_b (sizeKos);
		graphKosaraju.addEdgeTS(0, 3, 0);
		graphKosaraju.addEdgeTS(2, 6, 0);
		graphKosaraju.addEdge(1, 0, 0);
		graphKosaraju.addEdge(4, 1, 0);
		graphKosaraju.addEdge(3, 4, 0);
		graphKosaraju.addEdge(7, 6, 0);
		graphKosaraju.addEdge(7, 4, 0);
		graphKosaraju.addEdge(6, 5, 0);
		graphKosaraju.addEdge(5, 4, 0);
		graphKosaraju.addEdge(5, 2, 0);
		graphKosaraju.addEdge(6, 1, 0);
		graphKosaraju.addEdge(2, 4, 0);
		graphKosaraju.addEdge(2, 1, 0);
		graphKosaraju.addEdge(2, 4, 0);
		System.out.println("Kosaraju GT: ");
		Stack<Integer> stackKos = graphKosaraju.kosarajuGT();
		System.out.println();
		System.out.println("Kosaraju G: ");
		graphKosaraju.kosarajuG(stackKos);
		System.out.println();
		
		graph_b graphEuler = new graph_b (sizeEu);
		graphEuler.addEdgeTS(0, 1, 0);
		graphEuler.addEdgeTS(1, 4, 0);
		graphEuler.addEdgeTS(3, 4, 0);
		graphEuler.addEdgeTS(0, 2, 0);
		graphEuler.addEdgeTS(2, 3, 0);
		graphEuler.addEdgeTS(0, 3, 0);
		graphEuler.addEdgeTS(0, 4, 0);
		graphEuler.addEdgeTS(1, 3, 0);
		graphEuler.addEdgeTS(1, 2, 0);
		graphEuler.addEdgeTS(2, 5, 0);
		graphEuler.addEdgeTS(4, 5, 0);
		System.out.println("Eulirian path: ");
		graphEuler.eulirianPath(5);
		
		graph_b graphTarjan = new graph_b (sizeT);
		graphTarjan.addEdge(0,1,0);
		graphTarjan.addEdge(0,2,0);
		graphTarjan.addEdge(1,3,0);
		graphTarjan.addEdge(2,3,0);
		graphTarjan.addEdge(2,5,0);
		graphTarjan.addEdge(4,5,0);
		graphTarjan.addEdge(5,6,0);
		graphTarjan.addEdge(3,6,0);
		System.out.println("Tarjan: ");
		graphTarjan.tarjan();
		System.out.println();
		
		graph_b graphKruskal = new graph_b (sizeKru);
		graphKruskal.addEdge(0,1,2);
		graphKruskal.addEdge(0,2,1);
		graphKruskal.addEdge(0,3,3);
		graphKruskal.addEdge(0,5,6);
		graphKruskal.addEdge(1,3,1);
		graphKruskal.addEdge(1,4,3);
		graphKruskal.addEdge(2,3,2);
		graphKruskal.addEdge(2,5,3);
		graphKruskal.addEdge(3,6,1);
		graphKruskal.addEdge(4,6,4);
		graphKruskal.addEdge(5,6,7);
		System.out.println("Kruskal: ");
		graphKruskal.kruskal();
	}
}
