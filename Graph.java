import java.io.*;
import java.util.*;


class Graph {
    public int m; 
    public LinkedList<Integer> adj[];
  
    @SuppressWarnings("unchecked") Graph(int n)
    {
        m = n;
        adj = new LinkedList[n];
        for (int i = 0; i < n; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int n, int e) //Adds an edge to two vertices, thereby connecting them
    {
        adj[n].add(e); 
		adj[e].add(n);
    }
	
	void deleteEdge(int n, int e) //Removes the edge between two specific vertices, thereby disconnecting them
    {
        adj[n].remove(e); 
		adj[e].remove(n);
    }
	
    public int getDegree(int a)  //Finds the Degree of a Vertex..... Degree of a Vertex is the number of other Vertices it is connected to
	{  
	 int count=0;
	 
     for (int i = 0; i < m; i++)
	 {  
		if(adj[a].indexOf(i)>=0)
		      count++;
	 }
	 return count;
	}	
	
	
	void findVertex(int b)  
	{   
	    if(b>m)
			System.out.println("\nThe vertex is out of bounds for the Graph of size "+m);
		else if(adj[b].isEmpty())
			System.out.println("\nThe vertex "+b+" does not exist");
		else
			System.out.println("\nThe vertex "+b+" exists");
	}	
	
	
	void insertVertex(int c) //Inserts a new vertex into the graph, and connects it to pre-existing vertices
    { 
	 Scanner sc = new Scanner(System.in);
	 System.out.println("\nType all the Adjacent Vertices to "+c);
	 String V = sc.nextLine();	 
	 int L=Integer.parseInt(V);

     while(L>0)
     {
        int r=L%10;
		adj[c].add(L);
		adj[L].add(c);
		L=L%10;
	 }	  
    }
	
	void deleteVertex(int d)  // Deletes a Vertex from the Graph
    {
      adj[d].clear();
	  for(int k = 0; k < m;k++)
	  {
		if(adj[k].contains(d)) 
		{
		  adj[k].remove(d);
		}
	  }
    }                             

	
	//Inserts Vertex in Between two other vertices by disconnecting those two, and then connecting the new vertex to each of the previous two
	void insertinBtw(int e, int f, int g)   //Here e is the Vertex to be inserted, and f,g are the pre-existing vertices
    {                                     
	  if(adj[f].contains(g))
	  {
	    if(e>m)
		   System.out.println("\nThe vertex"+ e +"is out of bounds for the Graph of size "+m);
	    else if (adj[e].isEmpty())
	    {
		 adj[f].remove(g);      // Disconnecting original Vertices
		 adj[g].remove(f);	
	 
		 adj[e].add(f);
		 adj[f].add(e);
		 adj[e].add(g);
		 adj[g].add(e);
	    }
	    else
		   System.out.println("\nThe vertex "+e+" already exists");
	  }
	  else
		  System.out.println("\nThe vertices "+ f +" and "+ g +" aren't connected");
    }
  
  
    void DFS_T(int n, boolean visited[])   //Function for performing DFS Traversal, Does the Iteration and checking of boolean visited
    {
        visited[n] = true;
        System.out.print(n + " ");
  
        Iterator<Integer> i = adj[n].listIterator();   // Iterating over the list, and marking visited nodes
        while (i.hasNext()) 
        {
            n = i.next();
            if (!visited[n])
                DFS_T(n, visited);
        }
    }

    void DFS(int n) //Function for performing DFS Traversal, Initiates the process by Declaring the boolean array and calling DFS_T
    {
		System.out.println("\nFollowing is Depth First Search Traversal, Starting with the vertex "+ n +":");
			
        boolean visited[] = new boolean[m];

        DFS_T(n, visited);
    }
	
	void BFS(int s) //Function for performing BFS Traversal
    {   
	    System.out.println("\nFollowing is Breadth First Search Traversal, Starting with the vertex "+ s +":");
	
        boolean visited[] = new boolean[m];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Performing Dequeue
            s = queue.poll();
            System.out.print(s+" ");
 
            // Iterating over the list, and marking visited nodes
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
	}
	
	public static void main(String args[])
    {
        Graph g = new Graph(30);
  
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 6);
        g.addEdge(2, 5);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
		
		System.out.print(g.getDegree(1));
		g.findVertex(3);
		g.findVertex(22);
		g.findVertex(34);
		g.DFS(0);
		g.BFS(0);
		
    }
}