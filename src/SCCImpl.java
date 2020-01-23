import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SCCImpl implements SCC {
  // Key: Knoten
  // Value: Zusammenhangskomponente
  Map<Integer, Integer> components;
  
  @Override
  public void compute(Graph g) {
    if(g == null) return;
	components = new HashMap<>();
	
	DFS dfs1 = new DFSImpl();
	dfs1.search(g);
	DFS dfs2 = new DFSImpl();
	dfs2.search(g.transpose(), dfs1);
	
	// Knoten mit höchsten Abschlusszeit ist ein Wurzelknoten.
	// Nehme die Entdeckungszeit dessen und der Knoten mit der
	// Entdeckungszeit - 1 als Abschlusszeit ist der nächste Wurzelknoten usw.
	
	ArrayList<Integer> roots = new ArrayList<>();
	int a = 2 * g.size();
	int k;
	
	while (a > 0) {
	  // Suche Knoten k mit Abschlusszeit a
	  for (k = 0; k < g.size(); k++) {
		if (dfs2.fin(k) == a) {
		  // Füge k der Liste der Wurzelknoten hinzu
		  roots.add(k);
		  break;
		}
	  }
	  // Die neue gesuchte Abschlusszeit ist die Entdeckungszeit
	  // des aktuellen Wurzelknotens - 1
	  a = dfs2.det(k) - 1;
	}
	
	// Iteration über alle Wurzelknoten
	for (int rk : roots) {
	  int e = dfs2.det(rk);
	  boolean foundSth = false;
	  for (int i = g.size() - 1; i >= 0; i--) {
		// Überprüfung ob die Entdeckungszeiten aufsteigend sind
		int u = dfs2.sequ(i);
		if (dfs2.det(u) == e) {
		  foundSth = true;
		  components.put(u, rk);
		  e++;
		}else if (foundSth) break;
	  }
	}
  }
  
  @Override
  public int component(int v) {
    if(v < 0 || v >= components.size()) return -1;
	return components.get(v);
  }
}
