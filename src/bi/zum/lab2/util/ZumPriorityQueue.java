package bi.zum.lab2.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Tomáš Řehořek
 * 
 * Encapsulates java.util.PriorityQueue so that no Comparator is needed.
 */
public class ZumPriorityQueue<T> {
    
    private PriorityQueue<T> internalQueue;
    private Map<T,Double> priorities;
    
    public ZumPriorityQueue() {
        
        priorities = new HashMap<T, Double>();
        
        internalQueue = new PriorityQueue(100, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return (int)Math.signum(ZumPriorityQueue.this.priorities.get(o1)
                        - ZumPriorityQueue.this.priorities.get(o2));
            }
        });
    }
    
    public void enqueue(T item, double priority) {
        
        this.priorities.put(item, priority);
        this.internalQueue.add(item);
    }
    
    public T dequeue() {
        
        this.priorities.remove(this.internalQueue.peek());
        return this.internalQueue.poll();
    }
    
    public void updateKey(T item, double priority) {
        
        this.priorities.put(item, priority);
        this.internalQueue.remove(item);
        this.internalQueue.add(item);
    }
    
    public boolean isEmpty() {
        
        return this.internalQueue.isEmpty();
    }
    
    public boolean contains(T item) {
        
        return this.internalQueue.contains(item);
    }
}
