package bi.zum.lab1;

import cz.cvut.fit.zum.api.AbstractAlgorithm;
import cz.cvut.fit.zum.api.Node;
import cz.cvut.fit.zum.api.UninformedSearch;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.openide.util.lookup.ServiceProvider;

/**
 * Depth-first search
 *
 * @see http://en.wikipedia.org/wiki/Depth-first_search
 */
@ServiceProvider(service = AbstractAlgorithm.class, position = 10)
public class DFS extends AbstractAlgorithm implements UninformedSearch {

    private LinkedList<Node> opened;
    private HashSet<Node> closed;
    private Map<Node, Node> prev;
    private List<Node> path;

    @Override
    public String getName() {
        return "DFS";
    }

    @Override
    public List<Node> findPath(Node startNode) {
        opened = new LinkedList<Node>();
        closed = new HashSet<Node>();
        prev = new HashMap<Node, Node>();
        path = null;
        
        Node target = null;
        
        Stack<Node> stack = new Stack<Node>();
        stack.push(startNode);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.isTarget()) {
                target = current;
                break;
            }
            opened.add(current);
            for (Node n : current.expand()) {
                if (!closed.contains(n) && !opened.contains(n)) {
                    opened.add(n);
                    stack.push(n);
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
