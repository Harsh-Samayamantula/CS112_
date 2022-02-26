package prereqchecker;
import java.util.ArrayList;
//import java.util.Dictionary;

public class DFS 
{
   private String[][] marked;
   public DFS(Graph G, ArrayList<String> targets)
   {
        int numver = G.getSize();
        marked = new String[numver][2];
        String[] vertices = G.returnKeys();
        for(int i = 0; i < vertices.length; i++)
        {
            marked[i][0] = vertices[i];
            marked[i][1] = "false";
        }
        for(String target: targets)
        {
            dfs(G, target);
        }
   }
   public DFS(Graph G, String target)
   {
        int numver = G.getSize();
        marked = new String[numver][2];
        String[] vertices = G.returnKeys();
        for(int i = 0; i < vertices.length; i++)
        {
            marked[i][0] = vertices[i];
            marked[i][1] = "false";
        }
        dfs(G, target);
   }

   public boolean visited(String target)
   {
       int i = findIndex(target);
        if(marked[i][1].equals("false")){ return false;}
        if(marked[i][1].equals("true")){ return true;}
       System.out.println("something aint working");
       return false;
   }

   private int findIndex(String target)
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

   private void dfs(Graph G, String target)
   {
       //System.out.println(target + " -->");
       int index = findIndex(target);
       marked[index][1] = "true";
       for(String t: G.adjacentTo(target))
       {
           if(visited(t)==false)
           {
               //System.out.println(t);
               dfs(G, t);
           }
       }
   }

   public String[][] getVisited()
   {
       return marked;
   }

   public void markUnvisited(String target)
   {
       marked[findIndex(target)][1] = "false";
   }

   public void markVisited(String target)
   {
       marked[findIndex(target)][1] = "true";
   }
}
