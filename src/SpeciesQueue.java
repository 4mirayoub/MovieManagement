import java.util.Arrays;
import java.util.NoSuchElementException;

public class SpeciesQueue<T> {
    private T[] queue;
    private int size;

    public SpeciesQueue() {
        queue = (T[]) new Animal[10]; // start with capacity 10
        size = 0;
    }

    private void resize() {
        T[] newQueue = (T[]) new Animal[queue.length * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    public void add(T animal) {
        T saveAnimal;
        int flag = 0;
        int i;
        if (animal == null) throw new InvalidInputException();
        if (size == queue.length) {
            resize();
        }
        for (i = 0; i < size; i++) {
            if (queue[i].getDominance() >= animal.getDominance()) {
                flag = 1;
                break;
            }
        }
        while (flag == 1 && i < size) {
            saveAnimal = queue[i];
            queue[i] = animal;
            animal = saveAnimal;
            i++;
        }
        queue[size] = animal;
        size++;
    }

    public T remove() {
        T saveAnimal;
        if (size <= 0) throw new EmptyQueueException();
        saveAnimal = queue[0];
        for (int i = 0; i < size - 1; i++) queue[i] = queue[i + 1];
        size--;
        return saveAnimal;
    }

    public T peek() {
        if (size <= 0) throw new EmptyQueueException();
        return queue[0];
    }

    public int size() {
        return this.size;
    }
}