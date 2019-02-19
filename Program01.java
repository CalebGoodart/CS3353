import java.io.*;
import java.util.ArrayList;

public class Program01 {

    //Node for linked list with basic methods
    private static class Node<E> {

        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {

            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public static class DoublyLinkedList<E> {

        Node<E> header;
        Node<E> trailer;

        public DoublyLinkedList() {

            header = new Node<E>(null, null, null);
            trailer = new Node<E>(null, header, null);
            header.setNext(trailer);
        }

        //adds a new node after a given node
        public void addNext(E newData, E prevData) {

            if (search(newData)) {
                System.out.println("Duplicates Found!");
                return;
            }

            Node<E> current = header.getNext();
            while (current != trailer) {

                if (current.getElement().equals(prevData)) {

                    Node<E> newNode = new Node<>(newData, current, current.getNext());
                    current.getNext().setPrev(newNode);
                    current.setNext(newNode);
                    return;
                }
                current = current.getNext();
            }
        }

        //adds a new node at the end of the list
        public void add(E data) {

            if (search(data)) {

                System.out.println("Duplicates Found!");
                return;
            }

            Node<E> prev = trailer.getPrev();
            Node<E> newNode = new Node<E>(data, prev, trailer);
            prev.setNext(newNode);
            trailer.setPrev(newNode);
        }

        //removes a node given its data
        public void remove(E data) {

            Node<E> current = header.getNext();

            while (current != trailer) {
                if (current.getElement().equals(data)) {

                    Node<E> prev = current.getPrev();
                    Node<E> next = current.getNext();
                    prev.setNext(next);
                    next.setPrev(prev);
                    return;
                }
                current = current.getNext();
            }
        }

        //searches for the given data, used to see if some data is already in the list
        public boolean search(E data) {

            Node<E> current = header.getNext();

            while (current != trailer) {

                if (current.getElement() == data) return true;

                current = current.getNext();
            }

            return false;
        }
    }


    public static void main(String[] args) {

        File file = new File(args[0]);
        String line = "";
        BufferedReader in;
        StringBuilder instructions = new StringBuilder();

        // this gets the input for the linked list
        try {
            in = new BufferedReader(new FileReader(file));

            while ((line = in.readLine()) != null) {

                instructions.append(line);
            }
            in.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not Found! Exiting....");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {

            System.out.println("something went wrong!");
            e.printStackTrace();
            System.exit(0);
        }

        DoublyLinkedList list = new DoublyLinkedList();

        String[] arr = instructions.toString().split(" ");  // gets separates all of the instructions
        ArrayList<String[]> strings = new ArrayList<>();    //where all instructions will be held

        for (String var : arr) {
            strings.add(var.split("\\."));  //further brakes down the instructions
        }

        for (String[] things : strings) {   //iterates though all instructions and checks for what operations to execute

            for (int i = 0; i < things.length; i++) {

                if (things[i].equals("in")) {

                    list.add(things[i - 1]);

                } else if (things[i].equals("del")) {

                    list.remove(things[i - 1]);
                } else if (things[i].equals("sch")) {

                    String result = list.search(things[i - 1]) ? "Not Found" : "Found";
                    System.out.println(things[i - 1] + " was " + result + "\n");
                } else if (things[i].contains("in_")) {

                    String[] temp = things[i].split("_");

                    list.addNext(things[i - 1], temp[1]);
                }
            }
        }

        Node current = list.header.getNext();       //prints the list
        while (current != list.trailer) {

            System.out.println(current.getElement());
            current = current.getNext();
        }
    }
}
