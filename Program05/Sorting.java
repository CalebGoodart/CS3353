package Program05;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Sorting {

    public static int[] BuckitSort(int[] arr) {

        ArrayList<Integer> buckits[] = new ArrayList[100];

        for (int i = 0; i < buckits.length; i++) {

            buckits[i] = new ArrayList<>();
        }

        for (int n : arr) {

            int index = (n * 10) / 9999;
            buckits[index].add(n);
        }

        int count = 0;

        for (ArrayList array : buckits) {

            for (int i = 0; i < array.size() - 1; i++) {

                int j = i;

                while (j >= 0 && (Integer) array.get(j) > (Integer) array.get(j + 1)) {

                    int temp = (int) array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                    j--;
                }
            }
        }

        for (ArrayList list : buckits) {

            for (Object num : list) {
                arr[count] = (int) num;
                count++;
            }
        }
        return arr;
    }

    public static int[] QuickSort(int[] arr) {

        return sort(arr, 0, arr.length - 1);

    }

    public static int[] sort(int[] arr, int str, int end) {

        int pivot = arr[new Random().nextInt(end)];
        int i = str;
        int j = end;

        while (i <= j) {

            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {

                j--;
            }

            if (i <= j) {

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }

            if (str < j) {

                sort(arr, str, j);
            }
            if (i < end) {
                sort(arr, i, end);
            }

        }
        return arr;
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
                stringBuilder.append(",");
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


        string2 = stringBuilder.toString();

        String[] str = string2.split(",");
        int[] arr = new int[str.length];

        for (int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(str[i]);
        }

        System.out.println("Enter then min range (Zero Based)");
        int min = scanner.nextInt();
        System.out.println("Enter then max range");
        int max = scanner.nextInt();

        System.out.println("Sorting..... (This may take some time)");
        arr = QuickSort(arr);

        for (int i = min; i < max; i++){

            System.out.print(arr[i] + ",");
        }

//
//        PrintWriter writer;
//        try {
//            writer = new PrintWriter("ouput5.txt");
//
//            for (int i = 1000; i <= 100000; i += 1000){
//                long start = System.currentTimeMillis();
//                QuickSort(Arrays.copyOfRange(arr, 0, i-1));
//                writer.println((System.currentTimeMillis() - start));
//                writer.flush();
//            }
//
//            for (int i = 1000; i <= 100000; i += 1000){
//                long start = System.currentTimeMillis();
//                BuckitSort(Arrays.copyOfRange(arr, 0, i-1));
//                writer.println((System.currentTimeMillis() - start));
//                writer.flush();
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
