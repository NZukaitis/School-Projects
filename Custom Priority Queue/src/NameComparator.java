/**
 * Comparator that compares the name fields of two Employee objects
 * @author Nicholas Zukaitis
 * @version 1.0
 */

import java.util.Comparator;

public class NameComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Employee object1 = (Employee) o1;
        Employee object2 = (Employee) o2;
        return object1.getName().compareToIgnoreCase(object2.getName());
    }
}
