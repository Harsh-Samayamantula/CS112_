package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with up to 3 space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

        Graph graph = new Graph(args[0]);

        StdIn.setFile(args[1]);
        String[] input = StdIn.readAllLines();
        String target = input[0];        
        int numCoursesTaken = Integer.valueOf(input[1]);

        ArrayList<String> targets = new ArrayList<String>();
        for(int k = 0; k < numCoursesTaken; k++)
        {
            targets.add(input[k+2]);
        }

        ArrayList<String> prereqsTaken = new ArrayList<String>();

        DFS taken = new DFS(graph, targets);
        for(String[] s: taken.getVisited())
        {
            if(s[1].equals("true"))
            {
                prereqsTaken.add(s[0]);
            }
        }

        StdOut.setFile(args[2]);

        HashMap<Integer, ArrayList<String>> outputMap = new HashMap<Integer, ArrayList<String>>();
        HashMap<String, Integer> priority = new HashMap<String, Integer>();
        priority.put(target, 0);
        int topPriority = 0;

        Set<String> seen = new HashSet<String>(); 

        Queue<String> queue = new LinkedList<>();
        queue.add(target);
        
        while(!queue.isEmpty()) {
            String course = queue.remove();
            topPriority = SchedulePlan.max(topPriority, priority.get(course));
            int newLevel = priority.get(course)+1; 
            
            for (String c: graph.adjacentTo(course)) {

                if (!prereqsTaken.contains(c) && !seen.contains(c)) {

                    if (outputMap.containsKey(newLevel)) {
                        outputMap.get(newLevel).add(c);
                    } else {

                        ArrayList<String> arr = new ArrayList<String>();
                        arr.add(c);
                        outputMap.put(newLevel, arr);
                    }
                    priority.put(c, newLevel);
                    seen.add(c);
                    queue.add(c);
                    
                } else if(!prereqsTaken.contains(c) && seen.contains(c) && priority.get(c) < newLevel) {

                    
                    ArrayList<String> val = outputMap.get(priority.get(c));
                    val.remove(c);

                    if (outputMap.containsKey(newLevel)) {
                        outputMap.get(newLevel).add(c);
                    } else {

                        ArrayList<String> arr = new ArrayList<String>();
                        arr.add(c);
                        outputMap.put(newLevel, arr);
                    }
                    priority.put(c, SchedulePlan.max(newLevel, priority.get(c)));
                }
            }
        }

        StdOut.println(topPriority);
        for (int i = topPriority; i > 0; i--) {
            for (String course: outputMap.get(i)) {
                StdOut.print(course + " ");
            }
            StdOut.println();
        }

    }

    public static int max(int a, int b) {
        if (a > b) {
            return a;
        }
        else
        {
            return b;
        }
    }

}
