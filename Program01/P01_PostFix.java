import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class P01_PostFix {
    static String OPENINGS = "({[";
    static String CLOSINGS = ")}]";
    static String NUMS = "0123456789";

    private static int priority(char c) {
        if (c == '^') {
            return 3;
        }
        if (c == '*' || c == '/') {
            return 2;
        }
        if (c == '-' || c == '\u2013' || c == '+') {
            return 1;
        }
        return 0;
    }

    public static String infix(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (NUMS.contains(Character.toString(c))) {
                stringBuilder.append(c);
                continue;
            }
            if (OPENINGS.contains(Character.toString(c))) {
                stack.push(Character.valueOf(c));
                continue;
            }
            if (CLOSINGS.contains(Character.toString(c))) {
                while (!stack.empty() && !OPENINGS.contains(Character.toString(((Character) stack.peek()).charValue()))) {
                    stringBuilder.append(stack.pop());
                }
                if (!stack.empty() && !OPENINGS.contains(Character.toString(((Character) stack.peek()).charValue()))) {
                    return null;
                }
                stack.pop();
                continue;
            }
            while (!stack.empty() && P01_PostFix.priority(c) <= P01_PostFix.priority(((Character) stack.peek()).charValue())) {
                stringBuilder.append(stack.pop());
            }
            stack.push(Character.valueOf(c));
        }
        while (!stack.empty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
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
        char[] arrc = stringBuilder.toString().toCharArray();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < arrc.length; ++i) {
            if (arrc[i] == ' ') continue;
            stringBuilder2.append(arrc[i]);
        }
        System.out.println("The data before: " + stringBuilder2.toString());
        System.out.println("the Data after: " + P01_PostFix.infix(stringBuilder2.toString()));
    }
}