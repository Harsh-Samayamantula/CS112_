package prereqchecker;
import java.util.*;
import java.util.Queue;


public class BFS 
{
    private String[][] marked;
    private String[][] edgeTo;
    private String[][] distTo;

    public BFS(Graph G, String target)
    {
        int numver = G.getSize();
        marked = new String[numver][2];
        String[] vertices = G.returnKeys();
        for(int i = 0; i < vertices.length; i++)
        {
            marked[i][0] = vertices[i];
            marked[i][1] = "false";
        }
        distTo = new String[numver][2];
        for(int i = 0; i < vertices.length; i++)
        {
            distTo[i][0] = vertices[i];
            distTo[i][1] = "unvisited";
        }
        bfs(G, target);
    }

    private void bfs(Graph G, String target)
    {
        Queue<String> q = new LinkedList<String>();
        q.add(target);
        marked[findIndex(target, this.marked)][1] = "true";
        distTo[findIndex(target, this.distTo)][1] = "0";

        while(!q.isEmpty())
        {
            String x = q.remove();
            for(String course: G.adjacentTo(x))
            {
                if(visited(course)==false)
                {
                    q.add(course);
                    marked[findIndex(course, this.marked)][1] = "true";
                    distTo[findIndex(course, distTo)][1] = (Integer.valueOf(distTo[findIndex(x, distTo)][1]) + 1) + "";
                }
            }
        }
    }

    private int findIndex(String target, String[][] marked)
   {
    for(int i = 0; i < marked.length; i++)
    {
         if(marked[i][0].equals(target))
         {
             return i;
         }
    }
    System.out.println("something aint working big time");
    return -1;
   }

   public boolean visited(String target)
   {
       int i = findIndex(target, this.marked);
        if(marked[i][1].equals("false")){ return false;}
        if(marked[i][1].equals("true")){ return true;}
       System.out.println("something aint working");
       return false;
   }

   public String[][] getDistToArray()
   {
       return distTo;
   }

   public ArrayList<String> getCourseByDistance(int distance, String[][] distTo)
   {
        ArrayList<String> coursesByDistance = new ArrayList<String>();
        for(int i =0; i < distTo.length; i++)
        {
            if(distTo[i][1].equals(""+distance))
            {
                coursesByDistance.add(distTo[i][0]);
            }
        }
        return coursesByDistance;
   }

   public int getDistanceOf(String course, String[][] distanceArray)
   {
       int result = -1;
       for(int i = 0; i < distanceArray.length; i++)
       {
           if(distanceArray[i][0].equals(course))
           {
               result = Integer.valueOf(distanceArray[i][1]);
               break;
           }
       }
       return result;
   }

}
