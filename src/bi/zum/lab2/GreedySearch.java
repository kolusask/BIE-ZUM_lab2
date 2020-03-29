package bi.zum.lab2;

import bi.zum.lab1.PathRecSearch;
import bi.zum.lab2.util.ZumPriorityQueue;
import cz.cvut.fit.zum.api.AbstractAlgorithm;
import cz.cvut.fit.zum.api.InformedSearch;
import cz.cvut.fit.zum.api.Node;
import java.util.HashMap;
import java.util.HashSet;
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
        
        /**
         * @TODO Implement algorithm
         *          
         */
        
        return null;
    }
        
}
