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
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Tomáš Řehořek
 */
@ServiceProvider(service = AbstractAlgorithm.class)
public class AStar extends PathRecSearch implements InformedSearch  {
    
    private ZumPriorityQueue<Node> open;
    private HashSet<Node> closed;
    
    @Override
    public String getName() {
        return "A*";
    }
    
    @Override
    public List<Node> findPath(Node startNode, Node endNode) {

        open = new ZumPriorityQueue<Node>();
        closed = new HashSet<Node>();
        prev = new HashMap<Node, Node>();
        
        Map<Node, Double> dist = new HashMap<Node, Double>();
        
        open.enqueue(startNode, 0);
        dist.put(startNode, 0.0);
        
        Node target = null;
        
        while (!open.isEmpty()) {
            Node current = open.dequeue();
            for (Node n : current.expand()) {
                if (!open.contains(n) && !closed.contains(n)) {
                    double distance = dist.get(current) + Euclidean.distance(current, n);
                    if (!prev.containsKey(n) || distance < dist.get(n)) {
                        dist.put(n, distance);
                        prev.put(n, current);
                        if (n.isTarget())
                            target = n;
                    }
                    open.enqueue(n, Math.min(distance, Euclidean.distance(n, endNode)));
                }
            }
            closed.add(current);
        }   
        
        List<Node> path = new LinkedList<Node>();
        if (target != null) {
            for (Node current = target; current != startNode; current = prev.get(current))
                path.add(current);
        }
        
        return path;
    }

}
