package DSA.stack_queue.basics;

/**
 * Theory : https://www.geeksforgeeks.org/dsa/implement-stack-using-array/
 * problem : https://takeuforward.org/data-structure/implement-stack-using-array/
* */
class ArrayStack {

    private int[] stack;
    private int top=-1,size;

    ArrayStack(int size){
        top=-1;
        this.size = size;
        stack = new int[size];
    }

    public boolean push(int value){
        if (top>size){
            System.out.println("Stack Overflow");
            return false;
        }
        stack[++top] = value;
        return true;
    }

    public boolean pop(){
        if (top<0){
            System.out.println("Stack Underflow");
            return false;
        }
        top--;
        return true;
    }

    public int peek() {
        if (top < 0) {
            System.out.println("Stack is Empty");
            return 0;
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return top < 0;
    }


    public static void main(String[] args) {
        ArrayStack s = new ArrayStack(5);
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.pop() + " popped from stack");

        System.out.println("Top element is: " + s.peek());

        System.out.print("Elements present in stack: ");
        while (!s.isEmpty()) {
            System.out.print(s.peek() + " ");
            s.pop();
        }
    }
}
