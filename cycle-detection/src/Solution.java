import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class Node implements Comparable<Node>{
    int num;
    int in;
    int out;
    int visited = 0;
    Node parent;
    ArrayList<Node> nodes = new ArrayList<Node>();    
        
    void connect(Node n){
        nodes.add(n);
        n.in += 1;
        this.out += 1;
    }
    
    public int compareTo(Node other){
	   return Integer.compare(this.num, other.num);
    }
    
}

class Solution extends Node{
    
    /*
    static int dfs1(Node n){
        n.visited += 1;
        for (int x = 0; x < n.nodes.size(); x++){
            if (n.nodes.get(x).visited > 1){
                return 1;
            }else if (n.nodes.get(x).visited == 1){
                System.out.print(n.nodes.get(x).num + " ");
                dfs1(n.nodes.get(x));
            }else{
                dfs1(n.nodes.get(x));
            }
        }
        return 0;
    }
    */
    static ArrayList<Node> dfs(Node n, ArrayList<Node> possible){
        n.visited = 1;
        for (int x = 0; x < n.nodes.size(); x++){
            if (n.nodes.get(x).visited == 0){
                dfs(n.nodes.get(x), possible);
            }else if (n.nodes.get(x).visited == 1){
                possible.add(n.nodes.get(x));
            }
        }
        n.visited = 2;
        return possible;
    }
    
    static ArrayList<Node> helper(Node n, Node start, ArrayList<Node> cycle){
        n.visited += 1;
        if (n.compareTo(start) == 0 && cycle.size() > 1){
            System.out.println(1);
            for (int x = 0; x < cycle.size(); x++){
                //if (x < cycle.size()-1 || (x == cycle.size()-1 && cycle.get(x).nodes.contains(start)))
                if (x == 0 || cycle.get(x).visited < 4)
                System.out.print(cycle.get(x).num + " ");
            }
        }
        cycle.add(n);
        for (int x = 0; x < n.nodes.size(); x++){
            if (n.nodes.get(x).visited <= 3){
                helper(n.nodes.get(x), start, cycle);
            }
        }
        n.visited += 1;
        return null;
    }
    
    /*
    static ArrayList<Node> helper(Node n, Node start, ArrayList<Node> cycle){
        n.visited += 1;
        for (int x = 0; x < n.nodes.size(); x++){
            helper(n.nodes.get(x), n, cycle);
        }
    }
    */
    /*
    
    static int dfs2(Node n, Node start){
        if (n.visited == 1){
            if (n.compareTo(start) == 0){
            }
            return n.num;
        }
        n.visited = 1;
        for (int x = 0; x < n.nodes.size(); x++){
            dfs2(n.nodes.get(x), start);
        }
        return 0;
    }
    
    */
    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        // create graph with all nodes/edges then do dfs topsort, print shortest
        int nodes = 0; // all nodes must be visited for all stages to be finished
        int edges = 0;
        int counter = 0;
        int check = 0;
        Node[] nodeset = new Node[0];
        Node n = new Node();
        Node n2 = new Node();
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
                edges = Integer.parseInt(holder[1]);
            }else{
                if (nodeset.length > 2){
                    String[] holder = line.split(" ");
                    if (nodeset[Integer.parseInt(holder[0])] == null){
                        n = new Node();
                        n.num = Integer.parseInt(holder[0]);
                        nodeset[Integer.parseInt(holder[0])] = n;
                    }else{
                        n = nodeset[Integer.parseInt(holder[0])];
                    }
                    if (nodeset[Integer.parseInt(holder[1])] == null){
                        n2 = new Node();
                        n2.num = Integer.parseInt(holder[1]);
                        nodeset[Integer.parseInt(holder[1])] = n2;
                    }else{
                        n2 = nodeset[Integer.parseInt(holder[1])];
                    }
                    n.connect(n2);
                }else{
                    check = 1;
                    System.out.println(0);
                }
            }
                counter++;
            }
        //
        if (check == 0){
            ArrayList<Node> cycle = new ArrayList<Node>();
            ArrayList<Node> possible = new ArrayList<Node>();

            for (int x = 1; x < nodeset.length; x++){
                if (nodeset.length > 2 && nodeset[x].visited == 0){
                    dfs(nodeset[x], possible);
                }
            }
            if (possible.size() > 0){
                helper(possible.get(0), possible.get(0), cycle);
            }else{
                System.out.print(0);
            }
        }
    }
}