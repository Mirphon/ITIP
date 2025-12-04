public class Stack<T> {
    private T[] data;
    private int size;
    public static void main(String[] args) {
        Stack <Integer> stack = new Stack<>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push(4);
        System.out.println(stack.pop());
    }

    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void push(T element) {
        if (size == data.length) {
            throw new IllegalStateException("Стек переполнен.");
        }
        data[size] = element;
        size++;
    }

    public String pop() {
        if (size == 0) {
            return ("Стек пустой.");
        }else{
        size--;
        data[size] = null;
        return "Последний элемент удален";}
    }

    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return data[size - 1];
    }
}
