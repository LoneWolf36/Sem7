import java.util.*;

public class AStarAlgorithm {
    public static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("How many nodes are there in the graph? :");
        int nodes_count;
        nodes_count = scan.nextInt();
        Algorithm a = new Algorithm(nodes_count); 
        a.getter();
        a.Astar();
    }    
}

class Algorithm {
    int GOAL;
    int START;
    int count;
    int distances[];
    ArrayList<Node> CLOSED = new ArrayList<>(); // Stores the nodes that have been visited
    ArrayList<Node> OPEN = new ArrayList<>(); // Stores the nodes to be visited
    ArrayList<Node> nodes = new ArrayList<>();
    public Scanner scan = new Scanner(System.in);
     
    Algorithm(int nodes_count) {
        count = nodes_count;
        distances = new int[nodes_count];
        Arrays.fill(distances, 1);
    }

    public void getter() {
    	for(int i : distances)
    	System.out.println(i);
        System.out.print("\nEnter START node number: ");
        START = scan.nextInt();
        System.out.print("\nEnter GOAL node number: ");
        GOAL = scan.nextInt();
	
	    //Set heuristic for node objects
        for (int i=0;i<count;i++) {
            if (i == GOAL) {
                nodes.add(new Node(i, 0));
                continue;
            }
            System.out.printf("\nEnter heuristic for node %d: ", i);
            int value = scan.nextInt();
            nodes.add(new Node(i, value));
        }

        //Set node objects connections arraylist
        System.out.println("\nEnter the connections between various nodes in the graph: ");
        for (Node node : nodes) {
            for(int j=0;j<nodes.size();j++){                
                System.out.printf("\nIs there an edge between nodes %d and %d: ",node.node_number,j);
                node.connections.add(scan.nextInt());
            }
        }

        System.out.println("\nThe nodes of the graph and their corresponding heuristic values are: ");
        
        for(Node node : nodes) {
            System.out.println("*Heuristic Value:* "+node.heuristic+ "\n" + "*Node number:* " + node.node_number);
            for(Integer i : node.connections) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
}

    public void Astar() {

        Node current = nodes.get(START);
        current.setVisit(true);
        System.out.println("Initialization...");
        OPEN.add(current);

        while(current.getHeuristic() != 0 && !OPEN.isEmpty()) {
            System.out.println("---------------=---------------");
            current.show();
            System.out.println();

            listsDisplay();

            getChildren(current);

            closeNode(current);
            
            if (getOptimal() == null) break;
            current = getOptimal();
        }

        System.out.println("------OUT OF LOOP-----------");
        listsDisplay();
        if (current.getHeuristic() == 0) {
            System.out.println("Found Goal node!!!");
            current.show();
            traverseParents(current);
        } else {
            System.out.println("Search stopped! Not found!");
            current.show();
        }
    }

    public Node getOptimal() {
        Node optimal = null;
        if (!OPEN.isEmpty()) optimal = OPEN.get(0);
        else return null;

        for (Node node : OPEN) {
        		int f = node.getF();
            if(optimal.getF() > node.getF()) optimal = node;
        }
        return optimal;
    }

    public void getChildren(Node node) {
        ArrayList<Integer> connections = new ArrayList<>();
        connections = node.connections;

        for(int i=0;i<connections.size();i++) {
            if (connections.get(i) == 1 && nodes.get(i).getVisit() == false) {
                OPEN.add(nodes.get(i));
                nodes.get(i).setVisit(true);
                nodes.get(i).setParent(node);
                int g = nodes.get(i).getG();
                nodes.get(i).setG(g+1);
            }
        }
    }

    public void closeNode(Node node) {
        CLOSED.add(node);
        OPEN.remove(node);
    }

    public static void traverseParents(Node node) {
        System.out.println("Parent Traversal :: \n");
        while (node != null) {
            node.show();
            node = node.getParent();
        }
        System.out.println("END Traversal :: \n");
    }

    public void listsDisplay() {
        System.out.println("OPEN list: ");
        for (Node node : OPEN) {
            System.out.println(node.node_number);
        }
        System.out.println();
        System.out.println("CLOSED list: ");
        for (Node node : CLOSED) {
            System.out.println(node.node_number);
        }
        System.out.println();
    }
}

/*
How many nodes are there in the graph? :14
Enter START node number: 0
Enter GOAL node number: 9
Enter heuristic for node 0: 12
Enter heuristic for node 1: 3
Enter heuristic for node 2: 6
Enter heuristic for node 3: 5
Enter heuristic for node 4: 9
Enter heuristic for node 5: 8
Enter heuristic for node 6: 12
Enter heuristic for node 7: 14
Enter heuristic for node 8: 7
Enter heuristic for node 10: 6
Enter heuristic for node 11: 1
Enter heuristic for node 12: 10
Enter heuristic for node 13: 2

0 1 1 1 0 0 0 0 0 0 0 0 0 0
1 0 0 0 1 1 0 0 0 0 0 0 0 0 
1 0 0 0 0 0 1 1 0 0 0 0 0 0 
1 0 0 0 0 0 0 0 1 0 0 0 0 0 
0 1 0 0 0 0 0 0 0 0 0 0 0 0 
0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 
0 0 1 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 1 0 0 0 0 0 1 1 0 0 0 
0 0 0 0 0 0 0 0 1 0 0 1 1 1 
0 0 0 0 0 0 0 0 1 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 1 0 0 0 0 
0 0 0 0 0 0 0 0 0 1 0 0 0 0 
0 0 0 0 0 0 0 0 0 1 0 0 0 0
*/