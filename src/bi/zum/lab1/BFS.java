package bi.zum.lab1;

import cz.cvut.fit.zum.api.AbstractAlgorithm;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import cz.cvut.fit.zum.api.Node;
import cz.cvut.fit.zum.api.UninformedSearch;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import org.openide.util.lookup.ServiceProvider;

/**
 * Breadth-first search
 *
 *
 * @see http://en.wikipedia.org/wiki/Breadth-first_search
 *
 * 1 procedure BFS(G,v): 2 create a queue Q 3 enqueue v onto Q 4 mark v 5 while
 * Q is not empty: 6 t ← Q.dequeue() 7 if t is what we are looking for: 8 return
 * t 9 for all edges e in G.adjacentEdges(t) do 12 u ← G.adjacentVertex(t,e) 13
 * if u is not marked: 14 mark u 15 enqueue u onto Q 16 return none
 *
 *
 */
@ServiceProvider(service = AbstractAlgorithm.class, position = 5)
public class BFS extends AbstractAlgorithm implements UninformedSearch {

    private LinkedList<Node> opened;
    private HashSet<Node> closed;
    private Map<Node, Node> prev;
    private List<Node> path;

    @Override
    public String getName() {
        return "BFS";
    }

    /**
     * When implementing uninformed search the only way how to find out whether
     * you reached your destination is calling node.isTarget() on each Node you
     * find. When you've checked a Node you should add it to closed list, so
     * that you'll avoid infinite loops in your program.
     *
     * @param startNode starting Node is represented by red color on the
     * @return List of Nodes which represents the shortest path from targetNode
     * (which you have to find first) to startNode
     */
    @Override
    public List<Node> findPath(Node startNode) {

        opened = new LinkedList<Node>();
        closed = new HashSet<Node>();
        prev = new HashMap<Node, Node>();
        path = null;
        
        Node target = null;

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.isTarget()) {
                target = current;
                break;
            }
            opened.add(current);
            for (Node n : current.expand()) {
                if (!opened.contains(n) && !closed.contains(n)) {
                    opened.add(n);
                    queue.add(n);
                    prev.put(n, current);
                }
            }
            opened.remove(current);
            closed.add(current);
        }
        
        if (target != null) {
            path = new LinkedList<Node>();
            Node current = target;
            while (current != startNode) {
                path.add(current);
                current = prev.get(current);
            }
        }
        
        return path;
    }
}