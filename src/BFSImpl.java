import java.util.*;

public class BFSImpl implements BFS {
  private int[] dist;
  private int[] pred;
  private Queue<Integer> q = new LinkedList<>();
  
  @Override
  public void search(Graph g, int s) {
	dist = new int[g.size()];
	pred = new int[g.size()];
	int u;
	int v;
	
	for (int i = 0; i < g.size(); i++) {
	  dist[i] = INF;
	  pred[i] = INF;
	}
	
	dist[s] = 0;
	
	q.add(s);
	
	while (!q.isEmpty()) {
	  u = q.poll();
	  
	  for (int i = 0; i < g.deg(u); i++) {
		v = g.succ(u, i);
		
		if (dist(v) == INF) {
		  dist[v] = dist(u) + 1;
		  pred[v] = u;
		  q.add(v);
		}
	  }
	}
  }
  
  @Override
  public int dist(int v) {
	return dist[v];
  }
  
  @Override
  public int pred(int v) {
	return pred[v];
  }
}
