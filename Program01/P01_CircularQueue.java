import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class P01_CircularQueue {

    public static class CircularQueue<E> {
        int rear = -1;
        int front = -1;
        int size;
        E[] arr;

    public CircularQueue(int n) {
            this.size = n;
            this.arr = (E[]) new Object[n];
        }

        public boolean isFull() {
            return this.front == 0 && this.rear == this.size - 1 || this.front == this.rear + 1;
        }

        public boolean isEmpty() {
            return this.front == -1;
        }

        public void enQueue(E e) {
            if (this.isEmpty()) {
                System.out.println("empty");
                this.rear = 0;
                this.front = 0;
                this.arr[this.rear] = e;
            } else if (this.isFull()) {
                System.out.println("full");
            } else if (this.rear == this.size - 1 && this.front != 0) {
                this.rear = 0;
                this.arr[this.rear] = e;
            } else {
                ++this.rear;
                this.arr[this.rear] = e;
            }
        }

        public E deQueue() {
            if (this.isEmpty()) {
                System.out.println("empty");
                throw new ArrayIndexOutOfBoundsException();
            }
            E e = this.arr[this.front];
            this.arr[this.front] = null;
            int n = this.front = this.front == this.size - 1 ? 0 : this.front + 1;
            if (this.front == this.rear) {
                this.front = this.rear - 1;
            }
            return e;
        }

        public void printQueue() {
            if (this.isEmpty()) {
                return;
            }
            if (this.front <= this.rear) {
                for (int i = this.front; i <= this.rear; ++i) {
                    System.out.print(this.arr[i] + " ");
                }
            } else {
                int n;
                for (n = this.front; n < this.size; ++n) {
                    System.out.print(this.arr[n] + " ");
                }
                for (n = 0; n <= this.rear; ++n) {
                    System.out.print(this.arr[n] + " ");
                }
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
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not Found! Exiting....");
            fileNotFoundException.printStackTrace();
            System.exit(0);
        }
        catch (Exception exception) {
            System.out.println("something went wrong!");
            exception.printStackTrace();
            System.exit(0);
        }
        System.out.println("The data before: " + stringBuilder);
        System.out.println("THe data after:");
        String[] arrstring2 = stringBuilder.toString().split(" ");
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        CircularQueue circularQueue = new CircularQueue(Integer.parseInt(arrstring2[0]));
        for (String string3 : arrstring2) {
            arrayList.add(string3.split("\\."));
        }
        for (String[] arrstring3 : arrayList) {
            for (int i = 0; i < arrstring3.length; ++i) {
                if (arrstring3[i].equals("in")) {
                    circularQueue.enQueue((Object)Integer.parseInt(arrstring3[i - 1]));
                    continue;
                }
                if (!arrstring3[i].equals("del")) continue;
                circularQueue.deQueue();
            }
            circularQueue.printQueue();
        }
        circularQueue.printQueue();
    }
}