import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class MaximalIndependentSet
{
	int MAX_COLOR = 3;
	Random random;
	int no_of_vertices;
	boolean GRAPH[][];
	int max_color[];
	ArrayList<ArrayList<Integer>> MIS;

	MaximalIndependentSet(int vertices, int edges)
	{
		no_of_vertices = vertices;
		random = new Random();
		int max_edges = vertices*(vertices-1)/2;

		GRAPH = new boolean[vertices][vertices];
		for(int i=0; i<vertices; i++)
			for(int j=i+1; j<vertices; j++)
				GRAPH[i][j] = GRAPH[j][i] = (random.nextInt(max_edges)<edges);

		max_color = new int[vertices];
		for (int i=0;i<no_of_vertices ;i++ ) 
			max_color[i] = random.nextInt(MAX_COLOR)+1;

	}

	void print()
	{
		System.out.println("\nGraph");
		for (int i=0; i<no_of_vertices; i++) {
			for (int j=0; j<no_of_vertices ; j++ ) 
				System.out.print(GRAPH[i][j]?"1 ":"0 ");
			System.out.println();
		}

		System.out.println("\n\nMax Colors");
		for (int i=0;i<no_of_vertices ;i++ )
			System.out.print(max_color[i]+" ");
		System.out.println("\n");
		
		if(MIS!=null)
		{
			System.out.println("Maximal Indepedent Sets");
			for (int i=0;i<MIS.size() ;i++ ) {
				ArrayList<Integer> set = MIS.get(i);
				System.out.print("{");
				for(Integer x:set)
					System.out.print(x+" ");
				System.out.print("}");
				System.out.println();
		
			}
	
		}
	}

	void make()
	{
		MIS = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		for (int i=0;i<no_of_vertices ;i++ )
			vertices.add(i);

		while(!vertices.isEmpty())
		{
			ArrayList<Integer> current = new ArrayList<Integer>(vertices);
			ArrayList<Integer> set = new ArrayList<Integer>();
			while(!current.isEmpty())
			{
				int x = current.get(0);
				int index;
				set.add(x);
				current.remove(0);//Index
				vertices.remove(vertices.indexOf(x));
				for (int i=0; i<no_of_vertices; i++)
					if(GRAPH[x][i] && (index=current.indexOf(i))>=0)
						current.remove(index);
			}
			MIS.add(set);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Number of Vertices : ");
		int no_of_vertices = in.nextInt();
		System.out.print("Enter Rought No of Edges : ");
		int no_of_r_edges = in.nextInt();
		
		MaximalIndependentSet mis = new MaximalIndependentSet(no_of_vertices, no_of_r_edges);
		mis.make();
		mis.print();

	}
}