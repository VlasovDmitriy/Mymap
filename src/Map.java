import java.util.ArrayList;
import java.util.List;

public class Map<T, V> {
    public static class MapException extends Exception {
        public MapException(String message) {
            super(message);
        }
    }

    private class SimpleMap {
        public T key;
        public V value;
        public SimpleMap next;

        public SimpleMap(T key, V value, SimpleMap next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public SimpleMap(T key, V value) {
            this(key, value, null);
        }
    }

    private SimpleMap head = null;
    private SimpleMap tail = null;
    private int size = 0;


    public void addFirst(T key, V value) {
        head = new SimpleMap(key, value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(T key, V value) {
        if (size == 0) {
            head = tail = new SimpleMap(key, value);
        } else {
            tail.next = new SimpleMap(key, value);
            tail = tail.next;
        }
        size++;
    }

    private void checkEmptyError() throws MapException {
        if (size == 0) {
            throw new MapException("Empty Map");
        }
    }

    private SimpleMap getNode(T key) {
        SimpleMap curr = head;
        while((curr.key != key) || (curr != null)) {
            curr = curr.next;
        }
        return curr;
    }
    private SimpleMap getNodePrev(T key) {
        SimpleMap curr = head;
        SimpleMap prev = head;
        while((curr.key != key) || (curr != null)) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    public void removeFirst() throws MapException {
        checkEmptyError();
        head = head.next;
        if (size == 1) {
            head = null;
        }
        size--;

    }
    public void removeByKey(T key) throws MapException {
        if (getNode(key) == null) {
            throw new MapException("incorrect key");
        }
        if (size == 1) {
            removeFirst();
        } else {
            SimpleMap prev = getNodePrev(key);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
        }
    }
    public void removeByValue(V value) throws MapException {
        if (getNode(key) == null) {
            throw new MapException("incorrect key");
        }
        if (size == 1) {
            removeFirst();
        } else {
            SimpleMap prev = getNodePrev(key);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
        }
    }

    public int size() {
        return size;
    }

    public T getKey(T key) throws MapException {
        return getNode(key).key;
    }

    public V getValue(T key) throws MapException {
        return getNode(key).value;
    }

    public T getFirstKey() throws MapException {
        checkEmptyError();
        return head.key;
    }

    public V getFirstValue() throws MapException {
        checkEmptyError();
        return head.value;
    }

    public T getLastKey() throws MapException {
        checkEmptyError();
        return tail.key;
    }

    public V getLastValue() throws MapException {
        checkEmptyError();
        return tail.value;
    }
    public List<T> getKeys(){
        List<T> t = new ArrayList<>();
        SimpleMap curr = head;
        t.add(head.key);
        while(curr.next != null)  {
            curr = curr.next;
            t.add(curr.key);
        }
        return t;
    }
}
