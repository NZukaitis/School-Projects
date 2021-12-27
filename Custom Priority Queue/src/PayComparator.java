/**
 * Comparator that compares the pay field of two Employee objects
 * @author Nicholas Zukaitis
 * @version 1.0
 */

import java.util.Comparator;

public class PayComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Employee object1 = (Employee) o1;
        Employee object2 = (Employee) o2;
        return Integer.compare(object1.getPay(), object2.getPay());
    }
}
