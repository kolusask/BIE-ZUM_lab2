package bi.zum.lab2.util;

import cz.cvut.fit.zum.api.Node;

public class Euclidean {

    public static double distance(Node a, Node b) {
        double deltaX = a.getX() - b.getX();
        double deltaY = a.getY() - b.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
