import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;


class MaximalIndependentSet
{
	int MAX_COLOR = 3;
	Random random;
	int no_of_vertices;
	boolean GRAPH[][];
	int max_color[];
	ArrayList<ArrayList<Integer>> MIS;

	MaximalIndependentSet(int vertices)
	{
		no_of_vertices = vertices;
		random = new Random();

	}
	MaximalIndependentSet()
	{
	}

	void makeRandom(int edges)
	{
		int max_edges = no_of_vertices*(no_of_vertices-1)/2;

		GRAPH = new boolean[no_of_vertices][no_of_vertices];
		for(int i=0; i<no_of_vertices; i++)
			for(int j=i+1; j<no_of_vertices; j++)
				GRAPH[i][j] = GRAPH[j][i] = (random.nextInt(max_edges)<edges);

		max_color = new int[no_of_vertices];
		for (int i=0;i<no_of_vertices ;i++ ) 
			max_color[i] = random.nextInt(MAX_COLOR)+1;

	}
	private boolean[] StringAtoBoolA(String line)
	{
		boolean arr[] = new boolean[line.length()];
		for(int i=0;i<arr.length;i++)
			arr[i]=(line.charAt(i)=='1');
		return  arr;
	}
	boolean fromFile(String filename)
	{
		try
		{
			Scanner in = new Scanner(new FileInputStream(filename));
			String line = in.nextLine();
			line = line.replaceAll(" ","");
			no_of_vertices = line.length();
			GRAPH = new boolean[no_of_vertices][no_of_vertices];
			GRAPH[0] = StringAtoBoolA(line);
			int c=1;
			while(in.hasNext())
			{
				line = in.nextLine().replaceAll(" ","");
				GRAPH[c++] = StringAtoBoolA(line);
			}
			return true;
			
		}catch(IOException e)
		{
			System.err.println("Error : "+e);
			return false;
		}
	}
	void print()
	{
		System.out.println("\nGraph");
		for (int i=0; i<no_of_vertices; i++) {
			for (int j=0; j<no_of_vertices ; j++ ) 
				System.out.print(GRAPH[i][j]?"1 ":"0 ");
			System.out.println();
		}
		if(max_color!=null)
		{
			System.out.println("\n\nMax Colors");
			for (int i=0;i<no_of_vertices ;i++ )
			System.out.print(max_color[i]+" ");
			System.out.println("\n");
			
		}
		
		
	}
	void printMIS()
	{
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
			System.out.println("Number of Set : "+MIS.size());
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
	/*	System.out.print("Enter Number of Vertices : ");
		int no_of_vertices = in.nextInt();
		System.out.print("Enter Rought No of Edges : ");
		int no_of_r_edges = in.nextInt();
	*/	
		MaximalIndependentSet mis = new MaximalIndependentSet();
		//mis.make();
		System.out.print("Enter Filename : ");
		String fname = in.nextLine();
		if(mis.fromFile(fname))
			{
				mis.make();
				mis.print();
				mis.printMIS();
			}

	}
}