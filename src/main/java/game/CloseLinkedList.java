package game;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CloseLinkedList<E> implements Iterable<E>, Cloneable {
    private Node first;
    private Node last;
    private int size;

    CloseLinkedList(E... values) {
        addAll(values);
    }

    CloseLinkedList(Collection<? extends E> list) {
        addAll(list);
    }

    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @SuppressWarnings("unchecked")
    private CloseLinkedList<E> superClone() {
        try {
            return (CloseLinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected CloseLinkedList<E> clone() {
        CloseLinkedList<E> clone = superClone();
        clone.clear();
        Node node = this.first;
        for (int index = 0; index < this.size; index++) {
            clone.add(node.value);
            node = node.next;
        }
        return clone;
    }

    public void addFirst(E value) {
        if (value != null) {
            if (this.first == null) {
                this.first = new Node(value);
                this.first.prev = this.first.next = this.first;
                this.last = this.first;
            } else {
                Node node = new Node(this.first, this.last, value);
                this.last.next = this.first.prev = node;
                this.first = node;
            }
            this.size++;
        }
    }

    public void addLast(E value) {
        if (value != null) {
            if (this.first == null) {
                addFirst(value);
            } else {
                Node node = new Node(this.first, this.last, value);
                this.first.prev = this.last.next = node;
                this.last = node;
                this.size++;
            }
        }
    }

    public boolean addAll(Collection<? extends E> list) {
        boolean result;
        if (result = list != null && !list.isEmpty()) {
            for (E value : list) {
                result = result && add(value);
            }
        }
        return result;
    }

    public boolean addAll(E... values) {
        boolean result;
        if (result = values != null) {
            for (E value : values) {
                result = result && add(value);
            }
        }
        return result;
    }

    @SafeVarargs
    public static <T> CloseLinkedList<T> of(T... values) {
        return new CloseLinkedList<>(values);
    }

    public void add(int index, E value) {
        if (value != null) {
            if (index != 0) {
                checkIndex(index);
            }
            Node node = getNodeByIndex(index);
            if (node == null || node == this.first) {
                addFirst(value);
            } else if (node == this.last) {
                addLast(value);
            } else {
                Node addNode = new Node(node, node.prev, value);
                node.prev = node.prev.next = addNode;
                this.size++;
            }
        }
    }

    public int indexOf(E value) {
        int index = -1;
        if (value != null && !isEmpty()) {
            if (this.first.value.equals(value)) {
                index = 0;
            } else {
                int count = 1;
                for (Node node = this.first.next; node != this.first; node = node.next) {
                    if (node.value.equals(value)) {
                        index = count;
                        break;
                    } else {
                        count++;
                    }
                }
            }
        }
        return index;
    }

    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Incorrect index output...");
        }
    }

    private Node getNodeByIndex(int index) {
        Node result = null;
        if (index == 0) {
            result = this.first;
        } else if (index == this.size - 1) {
            result = this.last;
        } else {
            if (this.size / 2 > index) {
                for (Node node = this.first.next; node != this.first; node = node.next) {
                    if (--index == 0) {
                        result = node;
                        break;
                    }
                }
            } else {
                index = this.size - index - 1;
                for (Node node = this.last.prev; node != this.last; node = node.prev) {
                    if (--index == 0) {
                        result = node;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public int lastIndexOf(E value) {
        int index = -1;
        if (!isEmpty()) {
            if (this.last.value.equals(value)) {
                index = this.size - 1;
            } else {
                int count = this.size - 2;
                for (Node node = this.last.prev; node != this.last; node = node.prev) {
                    if (node.value.equals(value)) {
                        index = count;
                        break;
                    } else {
                        count--;
                    }
                }
            }
        }
        return index;
    }

    public E get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).value;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        E oldElement;
        if (this.size == 1) {
            oldElement = this.first.value;
            clear();
        } else {
            oldElement = this.first.value;
            Node newFirst = this.first.next;
            newFirst.prev = this.last;
            this.last.next = newFirst;
            this.first = newFirst;
            this.size--;
        }
        return oldElement;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        E oldElement;
        if (this.size == 1) {
            oldElement = this.first.value;
            clear();
        } else {
            oldElement = this.last.value;
            Node newLast = this.last.prev;
            newLast.next = this.first;
            this.first.prev = newLast;
            this.last = newLast;
            this.size--;
        }
        return oldElement;
    }

    public boolean remove(E value) {
        boolean result;
        if (result = value != null) {
            if (result = !isEmpty()) {
                if (result = this.first.value.equals(value)) {
                    removeFirst();
                } else {
                    Node delete = null;
                    for (Node node = this.first.next; node != this.first; node = node.next) {
                        if (node.value.equals(value)) {
                            delete = node;
                        }
                    }
                    if (result = delete != null) {
                        if (delete == this.last) {
                            removeLast();
                        } else {
                            delete.prev.next = delete.next;
                            delete.next.prev = delete.prev;
                            this.size--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public E remove(int index) {
        checkIndex(index);
        E old = null;
        if (index == 0) {
            old = this.first.value;
            removeFirst();
        } else if (index == this.size - 1) {
            old = this.last.value;
            removeLast();
        } else {
            Node node = getNodeByIndex(index);
            if (node != null) {
                old = node.value;
                node.prev.next = node.next;
                node.next.prev = node.prev;
                this.size--;
            }
        }
        return old;
    }

    public E set(int index, E value) {
        E result = null;
        if (value != null) {
            checkIndex(index);
            Node set = getNodeByIndex(index);
            result = set.value;
            set.value = value;
        }
        return result;
    }

    public Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @SuppressWarnings("unchecked")
    public E[] toArray(E[] array) {
        array = (E[]) Array.newInstance(array.getClass().getComponentType(), this.size);
        E[] result = array;
        Node node = this.first;
        for (int index = 0; index < this.size; index++) {
            result[index] = node.value;
            node = node.next;
        }
        return result;
    }

    public E getFirst() {
        if (this.first == null) {
            throw new NoSuchElementException("Element not found...");
        }
        return this.first.value;
    }

    public E getLast() {
        if (this.last == null) {
            throw new NoSuchElementException("Element not found...");
        }
        return this.last.value;
    }

    public Object[] toArray() {
        Object[] values = new Object[this.size];
        if (size() > 0) {
            values[0] = this.first.value;
            int count = 1;
            for (Node node = this.first.next; node != this.first; node = node.next) {
                values[count++] = node.value;
            }
        }
        return values;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorLinked();
    }

    private class IteratorLinked implements Iterator<E> {
        private Node cursor = first;
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Elements not found...");
            }
            E value = cursor.value;
            cursor = cursor.next;
            this.count++;
            return value;
        }
    }

    private class Node {
        private Node next;
        private Node prev;
        private E value;

        Node(Node next, Node prev, E value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

        Node(E value) {
            this(null, null, value);
        }
    }
}

