import java.util.*;

class Node {
		int f;
    int heuristic;
    int g;
    int node_number;
    Node parent;
    boolean visited;
	ArrayList<Integer> connections = new ArrayList<>();

	Node(int node_number, int heuristic) {
        visited = false;
        parent = null;
        g = 0;
        f = 0;
				this.heuristic = heuristic;
				this.node_number = node_number;
    }
    
    public void show() {
        if (parent == null) System.out.println("Currently at root node "+ node_number);
        else System.out.println("Currently at node number "+ node_number +" with parent "+ parent.getName());
        System.out.println("Having connections");
        getConnections();
    }
    
    public int getF() {
				f = g + heuristic;
				return f;
    }

    public void setParent(Node parent) { this.parent = parent; }

    public Node getParent() { return parent; }

    public int getName() { return node_number; }

    public void setVisit(boolean visit) { this.visited = visit; }

    public boolean getVisit() { return visited; }

    public void getConnections() {
        for (Integer connection : connections){
            System.out.print(" "+connection);
        }
    }

    public int getHeuristic() { return heuristic; }
    
    public void setG(int g) { this.g = g; }
    
    public int getG() { return g; }
}

/*

How many nodes are there in the graph? :8
1
1
1
1
1
1
1
1

Enter START node number: 0

Enter GOAL node number: 7

Enter heuristic for node 0: 39

Enter heuristic for node 1: 36

Enter heuristic for node 2: 26

Enter heuristic for node 3: 25

Enter heuristic for node 4: 18

Enter heuristic for node 5: 7

Enter heuristic for node 6: 16

Enter the connections between various nodes in the graph: 

Is there an edge between nodes 0 and 0: 0

Is there an edge between nodes 0 and 1: 1

Is there an edge between nodes 0 and 2: 1

Is there an edge between nodes 0 and 3: 0

Is there an edge between nodes 0 and 4: 0

Is there an edge between nodes 0 and 5: 0

Is there an edge between nodes 0 and 6: 0

Is there an edge between nodes 0 and 7: 0

Is there an edge between nodes 1 and 0: 1 0 0 1 0 0 0 0

Is there an edge between nodes 1 and 1: 
Is there an edge between nodes 1 and 2: 
Is there an edge between nodes 1 and 3: 
Is there an edge between nodes 1 and 4: 
Is there an edge between nodes 1 and 5: 
Is there an edge between nodes 1 and 6: 
Is there an edge between nodes 1 and 7: 
Is there an edge between nodes 2 and 0: 1 0 0 1 1 0 0 0 

Is there an edge between nodes 2 and 1: 
Is there an edge between nodes 2 and 2: 
Is there an edge between nodes 2 and 3: 
Is there an edge between nodes 2 and 4: 
Is there an edge between nodes 2 and 5: 
Is there an edge between nodes 2 and 6: 
Is there an edge between nodes 2 and 7: 
Is there an edge between nodes 3 and 0: 0 1 1  0 0 0 1 0

Is there an edge between nodes 3 and 1: 
Is there an edge between nodes 3 and 2: 
Is there an edge between nodes 3 and 3: 
Is there an edge between nodes 3 and 4: 
Is there an edge between nodes 3 and 5: 
Is there an edge between nodes 3 and 6: 
Is there an edge between nodes 3 and 7: 
Is there an edge between nodes 4 and 0: 0 0 1 0 0 1 1 0

Is there an edge between nodes 4 and 1: 
Is there an edge between nodes 4 and 2: 
Is there an edge between nodes 4 and 3: 
Is there an edge between nodes 4 and 4: 
Is there an edge between nodes 4 and 5: 
Is there an edge between nodes 4 and 6: 
Is there an edge between nodes 4 and 7: 
Is there an edge between nodes 5 and 0: 0 0 0 0 1 0 1 1 

Is there an edge between nodes 5 and 1: 
Is there an edge between nodes 5 and 2: 
Is there an edge between nodes 5 and 3: 
Is there an edge between nodes 5 and 4: 
Is there an edge between nodes 5 and 5: 
Is there an edge between nodes 5 and 6: 
Is there an edge between nodes 5 and 7: 
Is there an edge between nodes 6 and 0: 0 0 0 1 1 1 0 0

Is there an edge between nodes 6 and 1: 
Is there an edge between nodes 6 and 2: 
Is there an edge between nodes 6 and 3: 
Is there an edge between nodes 6 and 4: 
Is there an edge between nodes 6 and 5: 
Is there an edge between nodes 6 and 6: 
Is there an edge between nodes 6 and 7: 
Is there an edge between nodes 7 and 0: 0 0 0 0 0 1 0 0

Is there an edge between nodes 7 and 1: 
Is there an edge between nodes 7 and 2: 
Is there an edge between nodes 7 and 3: 
Is there an edge between nodes 7 and 4: 
Is there an edge between nodes 7 and 5: 
Is there an edge between nodes 7 and 6: 
Is there an edge between nodes 7 and 7: 
The nodes of the graph and their corresponding heuristic values are: 
*Heuristic Value:* 39
*Node number:* 0
0 1 1 0 0 0 0 0 
*Heuristic Value:* 36
*Node number:* 1
1 0 0 1 0 0 0 0 
*Heuristic Value:* 26
*Node number:* 2
1 0 0 1 1 0 0 0 
*Heuristic Value:* 25
*Node number:* 3
0 1 1 0 0 0 1 0 
*Heuristic Value:* 18
*Node number:* 4
0 0 1 0 0 1 1 0 
*Heuristic Value:* 7
*Node number:* 5
0 0 0 0 1 0 1 1 
*Heuristic Value:* 16
*Node number:* 6
0 0 0 1 1 1 0 0 
*Heuristic Value:* 0
*Node number:* 7
0 0 0 0 0 1 0 0 
Initialization...
---------------=---------------
Currently at root node 0
Having connections
 0 1 1 0 0 0 0 0
OPEN list: 
0

CLOSED list: 

---------------=---------------
Currently at node number 2 with parent 0
Having connections
 1 0 0 1 1 0 0 0
OPEN list: 
1
2

CLOSED list: 
0

---------------=---------------
Currently at node number 4 with parent 2
Having connections
 0 0 1 0 0 1 1 0
OPEN list: 
1
3
4

CLOSED list: 
0
2

---------------=---------------
Currently at node number 5 with parent 4
Having connections
 0 0 0 0 1 0 1 1
OPEN list: 
1
3
5
6

CLOSED list: 
0
2
4

------OUT OF LOOP-----------
OPEN list: 
1
3
6
7

CLOSED list: 
0
2
4
5

Found Goal node!!!
Currently at node number 7 with parent 5
Having connections
 0 0 0 0 0 1 0 0Parent Traversal :: 

Currently at node number 7 with parent 5
Having connections
 0 0 0 0 0 1 0 0Currently at node number 5 with parent 4
Having connections
 0 0 0 0 1 0 1 1Currently at node number 4 with parent 2
Having connections
 0 0 1 0 0 1 1 0Currently at node number 2 with parent 0
Having connections
 1 0 0 1 1 0 0 0Currently at root node 0
Having connections
 0 1 1 0 0 0 0 0END Traversal :: 

*/
