/*
 * Decompiled with CFR 0_132.
 *
 * Could not load the following classes:
 *  P01_DoublyLinkedList$DoublyLinkedList
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class P01_DoublyLinkedList {
    public static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next){
            this.element = data;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            return this.element;
        }

        public Node getPrev() {
            return this.prev;
        }

        public Node getNext() {
            return this.next;
        }

        public void setPrev(Node<E> node) {
            this.prev = node;
        }

        public void setNext(Node<E> node) {
            this.next = node;
        }
    }

    public static class DoublyLinkedList<E>

    {
        Node<E> header = new Node(null, null, null);
        Node<E> trailer = new Node(null, this.header, null);

        public DoublyLinkedList() {
            this.header.setNext(this.trailer);
        }

        public void addNext(E e, E e2) {
            if (this.search(e)) {
                System.out.println("Duplicates Found!");
                return;
            }
            for (Node node = this.header.getNext(); node != this.trailer; node = node.getNext()) {
                if (!node.getElement().equals(e2)) continue;
                Node node2 = new Node(e, node, node.getNext());
                node.getNext().setPrev(node2);
                node.setNext(node2);
                return;
            }
        }

        public void add(E e) {
            if (this.search(e)) {
                System.out.println("Duplicates Found!");
                return;
            }
            Node node = this.trailer.getPrev();
            Node node2 = new Node(e, node, this.trailer);
            node.setNext(node2);
            this.trailer.setPrev(node2);
        }

        public void remove(E e) {
            for (Node node = this.header.getNext(); node != this.trailer; node = node.getNext()) {
                if (!node.getElement().equals(e)) continue;
                Node node2 = node.getPrev();
                Node node3 = node.getNext();
                node2.setNext(node3);
                node3.setPrev(node2);
                return;
            }
        }

        public boolean search(E e) {
            for (Node node = this.header.getNext(); node != this.trailer; node = node.getNext()) {
                if (!node.getElement().equals(e)) continue;
                return true;
            }
            return false;
        }

        public void printList() {
            for (Node node = this.header.getNext(); node != this.trailer; node = node.getNext()) {
                System.out.print(node.getElement() + " ");
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
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        String[] arrstring2 = stringBuilder.toString().split(" ");
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        for (String arrstring3 : arrstring2) {
            arrayList.add(arrstring3.split("\\."));
        }
        for (String[] arrstring4 : arrayList) {
            for (int i = 0; i < arrstring4.length; ++i) {
                if (arrstring4[i].equals("in")) {
                    doublyLinkedList.add((Object) arrstring4[i - 1]);
                    doublyLinkedList.printList();
                    continue;
                }
                if (arrstring4[i].equals("del")) {
                    doublyLinkedList.remove((Object) arrstring4[i - 1]);
                    doublyLinkedList.printList();
                    continue;
                }
                if (arrstring4[i].equals("sch")) {
                    String string3 = doublyLinkedList.search((Object) arrstring4[i - 1]) ? "Found" : "Not Found";
                    System.out.println(arrstring4[i - 1] + " was " + string3 + "\n");
                    continue;
                }
                if (!arrstring4[i].contains("in_")) continue;
                String[] arrstring3 = arrstring4[i].split("_");
                doublyLinkedList.addNext((Object) arrstring4[i - 1], (Object) arrstring3[1]);
                doublyLinkedList.printList();
            }
        }
    }
}