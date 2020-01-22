public class MSFImpl implements MSF {
  int pred[];
  
  @Override
  public void compute(WeightedGraph g, int s) {
	BinHeap<Double, Integer> heap = new BinHeap();
	BinHeap.Entry entries[] = new BinHeap.Entry[g.size()];
	
	pred = new int[g.size()];
	
	for (int v = 0; v < g.size(); v++) {
	  if (v != s) entries[v] = heap.insert((double) MSF.NIL, v);
	  pred[v] = MSF.NIL;
	}
	
	int u = s;
	
	while (!heap.isEmpty()) {
	  for (int i = 0; i < g.deg(u); i++) {
		int v = g.succ(u, i);
		
		if (heap.contains(entries[v]) && ((Double) entries[v].prio() == MSF.NIL || g.weight(u, i) < (Double) entries[v].prio())) {
		  heap.changePrio(entries[v], g.weight(u, i));
		  pred[v] = u;
		}
	  }
	  u = (Integer) heap.extractMin().data();
	}
  }
  
  @Override
  public int pred(int v) {
	return pred[v];
  }
}