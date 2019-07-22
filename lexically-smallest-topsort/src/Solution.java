import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int num;
    int in;
    int out;
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
    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        // create graph with all nodes/edges then do dfs topsort, print shortest
        int nodes = 0; // all nodes must be visited for all stages to be finished
        int edges = 0;
        int counter = 0;
        Node[] nodeset = new Node[0];
        Node n = new Node();
        Node n2 = new Node();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        PriorityQueue<Node> queue = new PriorityQueue<Node>(); //heap
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
            }
                counter++;
            }
       for (int x = 1; x < nodeset.length; x++){
        		if (nodeset[x].in == 0){
        			queue.add(nodeset[x]);
        		}
            }
        String ans = "";
        while (queue.size() > 0){
            n = queue.remove();
            ans += n.num + " ";
            for (int x = 0; x < n.nodes.size(); x++){
            	n.nodes.get(x).in -= 1;
            	n.nodes.get(x).nodes.remove(n);
            	if (n.nodes.get(x).in == 0){
            		queue.add(n.nodes.get(x));
            	}
            }
        }
        String[] check = ans.split(" ");
        if (check.length < nodes || check.length == 0){
            System.out.print(-1);
        }else{
            System.out.print(ans);
        }
    }
}