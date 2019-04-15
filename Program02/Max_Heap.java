package Program02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Max_Heap {

    public static class MaxHeep {

        int[] arr;
        int maxSize;
        int size;

        public MaxHeep(int size){

            maxSize = size;
            arr = new int[size];
            Arrays.fill(arr, Integer.MIN_VALUE);
            this.size = 0;
        }

        private int parent(int i){return ((i-1)/2);}

        private int leftChild(int i){return (2*i + 1) ;}

        private int rightChild(int i){return (2*i + 2 );}

        private void swap(int a, int b){

            int temp;
            temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

        public void insert(int data){

            arr[size] = data;
            size++;
            int i = size-1;

            while (i != 0 && arr[i] > arr[parent(i)]){

                swap( i, parent(i));
                i = parent(size);
            }

            heapify(0);
        }

        public int delete(){

            int returnInt = arr[0];
            size--;
            arr[0] = arr[size];
            arr[size] = Integer.MIN_VALUE;
            heapify(0);

            return  returnInt;
        }

        private void heapify(int i){

            if (i >= ((size) / 2) && i <= size ){
                return;
            }

            int left = leftChild(i);
            int right = rightChild(i);

            if (arr[i] < arr[left]){
                swap(i, left);
                heapify(0);
            }

            if (arr[left] < arr[right]){
                swap(left, right);
                heapify(0);
            }
            heapify(i + 1);
        }


        public void print() {

            for (int i = 0; i < size; i++) {
                System.out.print(arr[i] + " ");
            }
        }

        public void preOrder(int i){

            if (i != size) System.out.print(arr[i] + " ");
            if (i >= ((size) / 2) && i <= size) return;
            preOrder(leftChild(i));
            preOrder(rightChild(i));
        }

        public void postOrder(int i){

            if (i >= ((size) / 2) && i <= size) {
                if (i != size) System.out.print(arr[i] + " ");
                return;
            }

            postOrder(leftChild(i));
            System.out.print(arr[i] + " ");
            postOrder(rightChild(i));
        }

        public void inOrder(int i){

            if (i >= ((size) / 2) && i <= size) {
                if (i != size) System.out.print(arr[i] + " ");
                return;
            }

            inOrder(leftChild(i));
            System.out.print(arr[i] + " ");
            inOrder(rightChild(i));
        }
    }


    public static void main(String[] args) {

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
        ArrayList<String[]> arrayList = new ArrayList<>();

        for (String string3 : arrstring2) {
            arrayList.add(string3.split("\\."));
        }

        int count = 0;
        for (String[] arrstring : arrayList){

            for (String str : arrstring){
                if (str.equals("in")){
                    count++;
                }
            }
        }

        MaxHeep maxHeep = new MaxHeep(count);

        for (String[] arrstring3 : arrayList) {
            for (int i = 0; i < arrstring3.length; ++i) {
                if (arrstring3[i].equals("in")) {

                    if (arrstring3[0].equals("in")){
                        maxHeep.inOrder(0);
                    }else {
                        maxHeep.insert(Integer.parseInt(arrstring3[i - 1]));
                    }

                }else if (arrstring3[i].equals("del")) {

                    maxHeep.delete();
                } else if (arrstring3[i].equals("pre")) {

                    maxHeep.preOrder(0);
                    System.out.print("\n");
                } else if (arrstring3[i].equals("post")) {

                    maxHeep.postOrder(0);
                    System.out.print("\n");
                }
            }
        }
    }
}
