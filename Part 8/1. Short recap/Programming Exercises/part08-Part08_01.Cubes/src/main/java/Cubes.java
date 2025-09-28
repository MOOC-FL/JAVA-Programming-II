
import java.util.Scanner;

public class Cubes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //System.out.println("Please Enter any Numbe :");
            String input = String.valueOf(scanner.nextLine());
            if (input.equals("end")) {
                break;
            } else {
                int output = Integer.valueOf(input);
                System.out.println(output * output * output);
                
            }
        }
    }
}
