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

	for(int i = 0; i < g.size(); i++)
    {
        this.dist[i] = INF;
        this.pred[i] = INF;
    }

	this.dist[s] = 0;

	this.q.add(s);

	while(!q.isEmpty())
    {
        u = this.q.remove();
        for(int i = 0; i < g.deg(u); i++)
        {
            v = g.succ(u, i);
            if(dist(v) == INF)
                this.dist[v] = dist(u) + 1;
                this.pred[v] = u;
                q.add(v);
        }

    }

  }
  
  @Override
  public int dist(int v) {
	return this.dist[v];
  }
  
  @Override
  public int pred(int v) {
	return this.pred[v];
  }
}
