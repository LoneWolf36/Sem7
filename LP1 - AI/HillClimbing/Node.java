import java.util.*;

class Node {
    int heuristic;
    int node_number;
    Node parent;
    boolean visited;
	ArrayList<Integer> connections = new ArrayList<>();

	Node(int node_number, int heuristic) {
        visited = false;
        parent = null;
		this.heuristic = heuristic;
		this.node_number = node_number;
    }
    
    public void show() {
        if (parent == null) System.out.println("Currently at root node "+ node_number);
        else System.out.println("Currently at node number "+ node_number +" with parent "+ parent.getName());
        System.out.println("Having connections");
        getConnections();
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
}
