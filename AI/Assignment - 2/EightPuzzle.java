import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class EightPuzzle {
	
	public int dimension = 3;
	
	// Bottom, left, top, right
	int[] row = { 1, 0, -1, 0 };
	int[] col = { 0, -1, 0, 1 };
	
	// Calculates heuristic
	public int calculateCost(int[][] initial, int[][] goal) {
		int count = 0;
		int n = initial.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (initial[i][j] != 0 && initial[i][j] != goal[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
	
	// To keep array out of bound in check
	public boolean isSafe(int x, int y) {
		return (x >= 0 && x < dimension && y >= 0 && y < dimension);
	}
	
	public void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// Recursive function
	public void printPath(Node root) {
		if (root == null) {
			return;
		}
		printPath(root.parent);
		printMatrix(root.matrix);
		System.out.println();
	}
	
	public void solve(int[][] initial, int[][] goal, int x, int y) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>(1000, (a, b) -> (a.cost + a.level) - (b.cost + b.level));
		Node root = new Node(initial, x, y, x, y, 0, null);
		root.cost = calculateCost(initial, goal);
		pq.add(root);
		
		while (!pq.isEmpty()) {
			Node min = pq.poll();
			// If at goal node
			if (min.cost == 0) {
				printPath(min);
				return;
			}
			for (int i = 0; i < 4; i++) {
			    if (isSafe(min.x + row[i], min.y + col[i])) {
			    	Node child = new Node(min.matrix, min.x, min.y, min.x + row[i], min.y + col[i], min.level + 1, min);
			    	child.cost = calculateCost(child.matrix, goal);
			    	pq.add(child);
			    }
			}
		}
	}
	public static void main(String[] args) {
		int[][] initial = { {1, 8, 2}, {0, 4, 3}, {7, 6, 5} };
		int[][] goal    = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
		// int[][] initial = new int[3][3];
		// int[][] goal = new int[3][3];
		Scanner sc = new Scanner(System.in);
		
		// System.out.println("Enter initial state: ");
		// for(int i = 0 ; i < 3 ; i++) {
		// 	for(int j = 0 ; j < 3 ; j++) {
		// 		initial[i][j] = sc.nextInt();
		// 	}
		// }
		// System.out.println("\nEnter goal state: ");
		// for(int i = 0 ; i < 3 ; i++) {
		// 	for(int j = 0 ; j < 3 ; j++) {
		// 		goal[i][j] = sc.nextInt();
		// 	}
		// }
		
		System.out.println("\nSolution :\n");
		
		// Blank tile coordinate
		int x = 1, y = 0;
		EightPuzzle puzzle = new EightPuzzle();
		puzzle.solve(initial, goal, x, y);
	}
}

class Node {

	public Node parent;
	public int[][] matrix;
	
	// Blank tile cordinates
	public int x, y;
	
	// Number of misplaced tiles
	public int cost;
	
	// The number of moves so far
	public int level;
	
	public Node(int[][] matrix, int x, int y, int newX, int newY, int level, Node parent) {
	
		this.parent = parent;
		this.matrix = new int[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			this.matrix[i] = matrix[i].clone();
		}
		
		// Swap value
		this.matrix[x][y]       = this.matrix[x][y] + this.matrix[newX][newY];
		this.matrix[newX][newY] = this.matrix[x][y] - this.matrix[newX][newY];
		this.matrix[x][y]       = this.matrix[x][y] - this.matrix[newX][newY];
		
		this.cost = Integer.MAX_VALUE;
		this.level = level;
		this.x = newX;
		this.y = newY;
	}
	
}
