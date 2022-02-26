package prereqchecker;

import java.util.HashMap;
import java.util.ArrayList;

public class Graph 
{
    private HashMap<String, ArrayList<String>> preReqGraph;
    private String[] vertexes;
    public Graph(String inputFile)
    {
        StdIn.setFile(inputFile);
        String[] input = StdIn.readAllLines();
        int a = Integer.valueOf(input[0]);
        preReqGraph = new HashMap<String, ArrayList<String>>();
        vertexes = new String[a];
        for(int i = 0; i < a; i++)
        {
            preReqGraph.put(input[i+1], new ArrayList<String>());
            vertexes[i] = input[i+1];
        }
        int eqnum = Integer.valueOf(input[a+1]);
        for(int i = a+2; i < a+2+eqnum; i++)
        {
            String[] x = input[i].split(" ");
            preReqGraph.get(x[0]).add(x[1]);
        }
        
    }

    public void addPreReq(String vertex, String prerequisite)
    {
        preReqGraph.get(vertex).add(prerequisite);
    }

    public void adjListOutputFile(String outputFile)
    {
        StdOut.setFile(outputFile);
        for(String vertex: vertexes)
        {
            StdOut.print(vertex + " ");
            for(String adj: preReqGraph.get(vertex))
            {
                StdOut.print(adj + " ");
            }
            StdOut.print("\n");
        }
    }

    public ArrayList<String> adjacentTo(String vertex)
    {
        return preReqGraph.get(vertex);
    }

    public String[] returnKeys()
    {
        return vertexes;
    }

    public int getSize()
    {
        return preReqGraph.size();
    }
}
