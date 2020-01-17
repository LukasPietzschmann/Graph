public class MSFImpl implements MSF {
  BinHeap bHeap = new BinHeap();
  int pred[];
  BinHeap.Entry entryArr[];

  @Override
  public void compute(WeightedGraph g, int s) {
    pred = new int[g.size()];
    entryArr = new BinHeap.Entry[g.size()];

    for(int i = 0; i < g.size(); i++)
    {
      if(i != s) {
        //entry in the 0th spot is always null -- s never gets a prio
        entryArr[i] = bHeap.insert(-1, i);
      }
      pred[i] = -1;
    }

    int u = s;

    while(!bHeap.isEmpty()){
      for(int i = 0; i < g.deg(u); i++){
        int v = g.succ(u, i);

        if(bHeap.contains(entryArr[v]) && (double) entryArr[v].prio() > g.weight(u, v)){
          bHeap.changePrio(entryArr[v], g.weight(u, v));
          pred[v] = u;
        }
      }
      u = (int) bHeap.extractMin().data();

    }
  }
  
  @Override
  public int pred(int v) {
	return pred[v];
  }
}