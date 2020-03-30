package bi.zum.lab2;

import bi.zum.lab1.PathRecSearch;
import bi.zum.lab2.util.Euclidean;
import bi.zum.lab2.util.ZumPriorityQueue;
import cz.cvut.fit.zum.api.AbstractAlgorithm;
import cz.cvut.fit.zum.api.InformedSearch;
import cz.cvut.fit.zum.api.Node;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Tomáš Řehořek
 */
@ServiceProvider(service = AbstractAlgorithm.class)
public class GreedySearch extends PathRecSearch implements InformedSearch {
    
    private ZumPriorityQueue<Node> open;
    private HashSet<Node> closed;
    
    @Override
    public String getName() {
        return "Greedy search";
    }
    
    @Override
    public List<Node> findPath(Node startNode, Node endNode){
        
        open = new ZumPriorityQueue<Node>();
        closed = new HashSet<Node>();
        prev = new HashMap<Node, Node>();
        
        open.enqueue(startNode, Euclidean.distance(startNode, endNode));
        while (!open.isEmpty()) {
            Node current = open.dequeue();
            for (Node n : current.expand()) {
                if (!closed.contains(n) && !open.contains(n)) {
                    open.enqueue(n, Euclidean.distance(n, endNode));
                    prev.put(n, current);
                    if (n.isTarget()) {
                        List<Node> path = new LinkedList<Node>();
                        for (current = endNode; current != startNode; current = prev.get(current))
                            path.add(current);

                        path.add(startNode);
                        return path;
                    }
                }
            }
            closed.add(current);
        }
        
        return null;
    }
        
}
