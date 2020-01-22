public class SPImpl implements SP {
  private int[] pred;
  private double[] dist;
  
  @Override
  public boolean bellmanFord(WeightedGraph g, int s) {
	return false;
  }
  
  @Override
  public void dijkstra(WeightedGraph g, int s) {
	BinHeap<Double, Integer> heap = new BinHeap<>();
	BinHeap.Entry entries[] = new BinHeap.Entry[g.size()];
	
	pred = new int[g.size()];
	dist = new double[g.size()];
	
	for (int v = 0; v < g.size(); v++) {
	  if (v == s) dist[v] = 0;
	  else dist[v] = SP.INF;
	  pred[v] = SP.NIL;
	  
	  entries[v] = heap.insert(dist[v], v);
	}
	
	while (!heap.isEmpty()) {
	  int u = heap.extractMin().data();
	  
	  for (int i = 0; i < g.deg(u); i++) {
		int v = g.succ(u, i);
		
		if (heap.contains(entries[v])) {
		  double path = dist[u] + g.weight(u, i);
		  
		  if (path < dist[v]) {
			dist[v] = path;
			pred[v] = u;
			heap.changePrio(entries[v], dist[v]);
		  }
		}
	  }
	}
  }
  
  @Override
  public double dist(int v) {
	return dist[v];
  }
  
  @Override
  public int pred(int v) {
	return pred[v];
  }
}
