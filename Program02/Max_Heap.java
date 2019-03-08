package Program02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Max_Heap {

    public static class MaxHeep {

        int[] arr;
        int maxSize;
        int size;

        public MaxHeep(int size){

            maxSize = size;
            arr = new int[size];
            this.size = 0;
        }

        private int parent(int i){return ((i-1)/2);}

        private int leftChild(int i){return (2*i + 1);}

        private int rightChild(int i){return (2*i + 2);}

        private void swap(int a, int b){

            int temp = a;
            a = b;
            b = temp;
        }

        public void insert(int data){

            arr[size] = data;
            size++;

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

                    maxHeep.insert(Integer.parseInt(arrstring3[i-1]));

                }else if (arrstring3[i].equals("del")) {

                } else if (arrstring3[i].equals("pre")) {

                } else if (arrstring3[i].equals("post")) {

                }
            }
        }
    }
}
