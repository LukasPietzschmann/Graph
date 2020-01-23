import java.util.*;

public class BFSImpl implements BFS {
  private int[] dist;
  private int[] pred;
  private Queue<Integer> q = new LinkedList<>();
  
  @Override
  public void search(Graph g, int s) {
    if(g == null || (s < 0 || s >= g.size())) return;
	dist = new int[g.size()];
	pred = new int[g.size()];
	int u;
	int v;
	
	for (int i = 0; i < g.size(); i++) {
	  dist[i] = INF;
	  pred[i] = NIL;
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
    if(v < 0 || v >= dist.length) return -1;
	return dist[v];
  }
  
  @Override
  public int pred(int v) {
	if(v < 0 || v >= pred.length) return -1;
	return pred[v];
  }
}
