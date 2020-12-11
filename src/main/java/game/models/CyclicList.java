package game.models;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicList<T> implements Iterable<T> {
    class Node {
        private final T value;
        private Node next;

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

    }

    private transient Node head = null;
    private transient Node tail = null;
    private transient int size = 0;

    public void add(T value) {
        if (head == null) {
            head = tail = new Node(value, null);
        } else {
            tail.next = new Node(value, null);
            tail = tail.next;
            tail.next = head;
        }
        size++;
    }

    public T get() throws Exception {
        if (head == null) {
            throw new Exception("Queue is empty!");
        }
        T result = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return result;
    }

    public void remove(T value) {
        if (head == null)       //если список пуст -
            return;             //ничего не делаем

        if (head == tail) {     //если список состоит из одного элемента
            head = null;        //очищаем указатели начала и конца
            tail = null;
            size--;
            return;             //и выходим
        }

        if (head.value == value) {  //если первый элемент - тот, что нам нужен
            head = head.next;       //переключаем указатель начала на второй элемент
            tail.next = head;
            size--;
            return;                 //и выходим
        }

        Node element = head;                //иначе начинаем искать
        while (element.next != null) {          //пока следующий элемент существует
            if (element.next.value == value) {  //проверяем следующий элемент
                if (tail == element.next) {     //если он последний
                    tail = element;             //то переключаем указатель на последний элемент на текущий
                }
                element.next = element.next.next; //найденный элемент выкидываем
                size--;
                return;                           //и выходим
            }
            element = element.next;               //иначе ищем дальше
        }

    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current.getNext() != null;
            }

            @Override
            public T next() {
                T result = current.getValue();
                current = current.next;
                return result;
            }

            @Override
            public void remove() {

            }

        };
    }

    public Object[] toArray() {
        Object[] values = new Object[this.size];
        if (size > 1) {
            values[0] = this.head.value;
            int count = 1;
            for (Node node = this.head.next; node != this.head; node = node.next) {
                values[count++] = node.value;
            }
        } else {
            values[0] = this.head.value;
        }
        return values;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }
}
