package bi.zum.lab1;

import cz.cvut.fit.zum.api.AbstractAlgorithm;
import cz.cvut.fit.zum.api.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tomáš Řehořek
 */
public class PathRecSearch extends AbstractAlgorithm {
 
    protected Map<Node, Node> prev;
    
    @Override
    public String getName() {
        return "not set";
    }
    
    protected List<Node> buildPath(Node target) {
        
        List<Node> path = new ArrayList<Node>();
        
        Node curr = target;
        
        while(curr != null) {
            path.add(curr);
            curr = prev.get(curr);
        }

        return path;
    }
}
