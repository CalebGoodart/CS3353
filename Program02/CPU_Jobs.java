package Program02;

import java.io.*;
import java.util.*;

public class CPU_Jobs {

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

        String[] temp = stringBuilder.toString().split(" ");
        ArrayList<String[]> jobs = new ArrayList<>();

        for (int i = 0; i < temp.length; i += 4) {
            String[] arr = new String[]{temp[i], temp[i + 1], temp[i + 2], temp[i + 3]};
            jobs.add(arr);

        }

        PriorityQueue<String[]> priorityQueue = new PriorityQueue<>(new sort());


        int cycle = 1;
        while (true) {

            System.out.println("\nCycle #" + (cycle));
            for (String[] job : jobs) {
                if (Integer.parseInt(job[1]) == cycle-1) {
                    System.out.println(job[0] + " has Arrived");

                    priorityQueue.add(job);
                }
            }

            if (priorityQueue.peek() == null){

                System.out.println("No Jobs!");

                System.out.println("Out of jobs press anything to quit!");
                String input = scanner.next();
                    System.exit(0);

            }else{

                System.out.print("Executing" );
                for (String str : priorityQueue.peek()){

                    System.out.print(" " + str);
                }


                priorityQueue.peek()[3] = Integer.toString(Integer.parseInt(priorityQueue.peek()[3]) - 1);

                if (priorityQueue.peek()[3].equals("0")){

                    priorityQueue.poll();

                }
            }
            cycle++;
        }
    }
}

class sort implements Comparator<String[]> {

    @Override
    public int compare(String[] a, String[] b) {
        return Integer.parseInt(a[2]) - Integer.parseInt(b[2]);
    }
}
