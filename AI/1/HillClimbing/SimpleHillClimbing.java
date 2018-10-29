import java.util.*;
import java.lang.*;
import java.io.*;

class SimpleHillClimbing {
	static PriorityQueue<Integer> queue;
	static ArrayList<Integer> visited;
	public static void main(String[] args) {
		visited = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		// number of nodes
		int numOfNodes = sc.nextInt();
		int[][] costAdjMatrix= new int[numOfNodes+1][numOfNodes+1];
		int[] heuristicMatrix= new int[numOfNodes+1];
		System.out.println("Enter start and goal");

		//start and goal
		int start = sc.nextInt();
		int goal = sc.nextInt();

		System.out.println("\nEnter cost matrix");
		// cost adjacency matrix
		for( int i = 1 ; i <= numOfNodes ; i ++ ) {
			for( int j = 1 ; j <= numOfNodes ; j ++ ) {
				costAdjMatrix[i][j] = sc.nextInt();
			}
		}

		System.out.println("\nenter heuristic values");
		// heuristic matrix
		for( int i = 1 ; i <= numOfNodes ; i ++ ) {
				heuristicMatrix[i] = sc.nextInt();
		}

		queue = new PriorityQueue<>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				if ( heuristicMatrix[a] == heuristicMatrix[b] ) {
					return 0;
				} else if ( heuristicMatrix[a] > heuristicMatrix[b] ) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		// queue = new PriorityQueue<>();

		System.out.println();
		queue.add(start);
		visited.add(start);
		boolean flag = false;
		int[] parent = new int[numOfNodes+1];
		int num = 0;
		ArrayList<Integer> list = new ArrayList<>();

		while( queue.size() != 0 ) {
			num = queue.poll();
			visited.add(num);
			queue.clear();
			if( num == goal ) {
				break;
			} else {
				for( int i = 1 ; i <= numOfNodes ; i ++ ) {
					if( costAdjMatrix[num][i] != 0 && !visited.contains(i) ) {
						// if ( heuristicMatrix[i] > heuristicMatrix[num] ) {
						//
						// 	flag = true ;
						// 	break;
						// }
						if( heuristicMatrix[i] < heuristicMatrix[num]) {
							queue.add(i);
							parent[i] = num;
							break;
						}
						//queue.add(i);

						// list.add(i);
						// parent[i] = num;
					}
				}
				//
				// for( int a : list) {
				// 	if( heuristicMatrix[a] < heuristicMatrix[num]) {
				//
				// 		queue.add(a);
				// 		break;
				// 	}
				// }

				// list.clear();
				if ( queue.size() == 0 ) {
					System.out.println("No Solution found");
					flag = true;
					break;
				}
			}
		}

		if ( !flag ) {
			int[] path = new int[numOfNodes+1];
			int i = 1 ;
			num  = goal;
			path[0] = num;
			while( num != start && i <= numOfNodes) {
				path[i++] = parent[num];
				num = parent[num];
			}
			for( i = i-1 ; i >= 0 ; i -- ) {
				System.out.print(path[i] +"\t");
			}
		}
	}
}
/*
10

1 9

0 1 1 1 0 0 0 0 0 0
1 0 0 0 1 1 0 0 0 0
1 0 0 0 0 0 1 1 0 0
1 0 0 0 0 0 0 0 0 1
0 1 0 0 0 0 0 0 1 0
0 1 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 0 1 0 0 0 0 0 0

50
59
45
48
32
38
43
41
25
46

Output : no solution
*/

/*
10

1 9

0 1 1 1 0 0 0 0 0 0
1 0 0 0 1 1 0 0 0 0
1 0 0 0 0 0 1 1 0 0
1 0 0 0 0 0 0 0 0 1
0 1 0 0 0 0 0 0 1 0
0 1 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0
0 0 0 1 0 0 0 0 0 0

50
40
45
48
32
38
43
41
25
46

output : solution
*/