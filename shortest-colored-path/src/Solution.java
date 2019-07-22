import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class Node implements Comparable<Node>{
    int num;
    int distance = 1000000;
    int copy = 0;
    ArrayList<Edge> edges = new ArrayList<Edge>();    
        
    public Edge connect(Node n, int w, int t){
        Edge edge = new Edge();
        edge.node1 = this;
        edge.node2 = n;
        edge.weight = w;
        edge.type = t;
        edges.add(edge);
        return edge;
    }
    
    public boolean equals(Node other){
        if (this.num == other.num && this.copy == other.copy){
            return true;
        }else{
            return false;
        }
    }
    
    public int compareTo(Node other){
	   return Integer.compare(this.distance, other.distance);
    }
    
}

class Edge implements Comparable<Edge>{
    int weight;
    int type;
    Node node1;
    Node node2;
    
    public int compareTo(Edge other){
	   return Integer.compare(this.weight, other.weight);
    }
    
}

class Solution extends Node{

    static int path(Node n, PriorityQueue<Node> nodes){
        int shortest = -1;
        while (!nodes.isEmpty()){
            Node node = nodes.remove();
            ArrayList<Edge> e = node.edges;
            for (int x = 0; x < e.size(); x++){ //for all the edges of the node
                Node dest = e.get(x).node2; //set destination node to edge x's node2 could be on the other graph
                int newdist = e.get(x).weight + node.distance;
                if (newdist < dest.distance){ //if this edges weight + node1's distance < node2's distance
                    nodes.remove(dest); //is this unique
                    dest.distance = newdist;
                    nodes.add(dest);
                }
            }
        }
        if (n.distance == 1000000){
            System.out.println(-1);
        }else{
            System.out.println(n.distance);
        }
        shortest = n.distance;
        return shortest;
    }
    
    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        // create graph with all nodes/edges then do dfs topsort, print shortest
        int nodes = 0; // all nodes must be visited for all stages to be finished
        int edges = 0;
        int counter = 0;
        int check = 0;
        Node[] nodeset = new Node[0];
        Node[] nodesetcopy = new Node[0];
        ArrayList<Edge> edgeset = new ArrayList<Edge>();    
        Node n = new Node();
        Node n2 = new Node();
        
        Node ncopy = new Node();
        Node n2copy = new Node();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while ((line = br.readLine()) != null){
            if (line.length() == 0){
                continue;
            }
            if (counter == 0){
                String[] holder = line.split(" "); 
                nodes = Integer.parseInt(holder[0]);
                nodeset = new Node[nodes+1];
                nodesetcopy = new Node[nodes+1];
                edges = Integer.parseInt(holder[1]);
            }else{
                if (nodeset.length > 2){
                    
                    String[] holder = line.split(" ");
                    if (nodeset[Integer.parseInt(holder[0])] == null){
                        n = new Node();
                        n.num = Integer.parseInt(holder[0]);
                        nodeset[Integer.parseInt(holder[0])] = n;
                        
                        ncopy = new Node();
                        ncopy.num = Integer.parseInt(holder[0]);
                        ncopy.copy = 1;
                        nodesetcopy[Integer.parseInt(holder[0])] = ncopy;
                    }else{
                        n = nodeset[Integer.parseInt(holder[0])]; 
                        
                        ncopy = nodesetcopy[Integer.parseInt(holder[0])]; 
                    }
                    
                    if (nodeset[Integer.parseInt(holder[1])] == null){
                        n2 = new Node();
                        n2.num = Integer.parseInt(holder[1]);
                        nodeset[Integer.parseInt(holder[1])] = n2;
                        
                        n2copy = new Node();
                        n2copy.num = Integer.parseInt(holder[1]);
                        n2copy.copy = 1;
                        nodesetcopy[Integer.parseInt(holder[1])] = n2copy;
                    }else{
                        n2 = nodeset[Integer.parseInt(holder[1])];
                        
                        n2copy = nodesetcopy[Integer.parseInt(holder[1])];
                    }
                    
                    if (Integer.parseInt(holder[2]) == 1){
                        edgeset.add(n.connect(n2,0,0));
                    }else{
                        edgeset.add(n.connect(n2,1,1));
                    }
                    
                }else{
                    check = 1;
                    System.out.println(-1);
                }
            }
                counter++;
            }
        
        for (int x = 0; x < edgeset.size(); x++){
            int newweight = 1;
            if (edgeset.get(x).type == 0){
                newweight = 0;
                edgeset.get(x).node1.connect(nodesetcopy[edgeset.get(x).node2.num],newweight,0);
            }
            edgeset.get(x).node1.connect(edgeset.get(x).node2,newweight,1);
            nodesetcopy[edgeset.get(x).node1.num].connect(nodesetcopy[edgeset.get(x).node2.num], newweight,1);
        }
        
        nodeset[1].distance = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (int x = 1; x < nodeset.length; x++){
             pq.add(nodeset[x]); 
             //pq.add(nodesetcopy[x]); 
        }

        path(nodesetcopy[nodesetcopy.length-1],pq);
    }
}