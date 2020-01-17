import java.util.ArrayList;

public class DFSImpl implements DFS {
  DFSGraph dg;
  ArrayList<Integer> sortedQueue;
  
  @Override
  public void search(Graph g) {
	sortedQueue = new ArrayList<>();
	dg = new DFSGraph(g);
	
	for (int u = 0; u < g.size(); u++) {
	  if (dg.colors[u] == DFSGraph.WHITE) {
		dg.pred[u] = DFSGraph.NIL;
		search(u, dg, false);
	  }
	}
  }
  
  private boolean search(int u, DFSGraph dg, boolean breakIfCircle) {
	dg.setDiscovery(u);
	for (int i = 0; i < dg.graph.deg(u); i++) {
	  int v = dg.graph.succ(u, i);
	  
	  if (dg.colors[v] == DFSGraph.WHITE) {
		dg.pred[v] = u;
		if (!search(v, dg, breakIfCircle)) return false;
		//TODO evtl doch mit Exception lösen und nicht mit Rückgabewert, funktioniert aber auch super so :)
	  }else if (breakIfCircle && dg.colors[v] == DFSGraph.GREY) return false;
	}
	
	dg.setCompletion(u);
	sortedQueue.add(u);
	
	return true;
  }
  
  @Override
  public void search(Graph g, DFS d) {
	sortedQueue = new ArrayList<>();
	dg = new DFSGraph(g);
	
	for (int i = g.size() - 1; i >= 0; i--) {
	  int u = d.sequ(i);
	  if (dg.colors[u] == DFSGraph.WHITE) {
		dg.pred[u] = DFSGraph.NIL;
		search(u, dg, false);
	  }
	}
  }
  
  @Override
  public boolean sort(Graph g) {
	sortedQueue = new ArrayList<>();
	dg = new DFSGraph(g);
	
	for (int u = 0; u < g.size(); u++) {
	  if (dg.colors[u] == DFSGraph.WHITE) {
		dg.pred[u] = DFSGraph.NIL;
		if (!search(u, dg, true)) return false;
	  }
	}
	
	return true;
  }
  
  @Override
  public int det(int v) {
	return dg.discovery[v];
  }
  
  @Override
  public int fin(int v) {
	return dg.completion[v];
  }
  
  @Override
  public int sequ(int i) {
	return sortedQueue.get(i);
  }
  
  private static class DFSGraph {
	public static final int WHITE = 0;
	public static final int GREY = 1;
	public static final int BLACK = 2;
	public static final int NIL = -1;
	
	private Graph graph;
	
	private int colors[];
	private int pred[];
	private int discovery[];
	private int completion[];
	
	private int t;
	
	public DFSGraph(Graph g) {
	  graph = g;
	  t = 1;
	  int size = g.size();
	  
	  colors = new int[size];
	  pred = new int[size];
	  discovery = new int[size];
	  completion = new int[size];
	  
	  for (int i = 0; i < size; i++) {
		colors[i] = WHITE;
		pred[i] = NIL;
		discovery[i] = NIL;
		completion[i] = NIL;
	  }
	}
	
	public void setDiscovery(int u) {
	  discovery[u] = t;
	  colors[u] = GREY;
	  t += 1;
	}
	
	public void setCompletion(int u) {
	  completion[u] = t;
	  colors[u] = BLACK;
	  t += 1;
	}
  }
}
