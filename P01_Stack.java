import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class P01_Stack {
    public static class Stack<E> {
        int size;
        int top;
        E[] arr;

        public Stack(int n) {
            this.size = n;
            this.top = -1;
            this.arr = (E[]) new Object[n];
        }

        public int size() {
            return this.top + 1;
        }

        public boolean isEmpty() {
            return this.top == -1;
        }

        public void push(E e) {
            if (this.size() == this.arr.length) {
                throw new IllegalStateException();
            }
            this.arr[++this.top] = e;
        }

        public E pop() {
            if (this.isEmpty()) {
                return null;
            }
            E e = this.arr[this.top];
            this.arr[this.top] = null;
            --this.top;
            return e;
        }

        public E top() {
            return this.isEmpty() ? null : (E) this.arr[this.top];
        }

        public void printStack() {
            for (int i = this.top; i >= 0; --i) {
                System.out.print(this.arr[i] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] arrstring) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String string = scanner.next();
        System.out.println("file Name: " + string);
        File file = new File(string);
        String string2 = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((string2 = bufferedReader.readLine()) != null) {
                stringBuilder.append(string2);
                stringBuilder.append(" ");
            }
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not Found! Exiting....");
            fileNotFoundException.printStackTrace();
            System.exit(0);
        } catch (Exception exception) {
            System.out.println("something went wrong!");
            exception.printStackTrace();
            System.exit(0);
        }
        System.out.println("The data before: " + stringBuilder);
        System.out.println("THe data after:");
        String[] arrstring2 = stringBuilder.toString().split(" ");
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        Stack stack = new Stack(Integer.parseInt(arrstring2[0]));
        for (String string3 : arrstring2) {
            arrayList.add(string3.split("\\."));
        }
        for (String[] arrstring3 : arrayList) {
            for (int i = 0; i < arrstring3.length; ++i) {
                if (arrstring3[i].equals("push")) {
                    stack.push((Object) arrstring3[i - 1]);
                    continue;
                }
                if (!arrstring3[i].equals("pop")) continue;
                stack.pop();
            }
            stack.printStack();
        }
    }
}