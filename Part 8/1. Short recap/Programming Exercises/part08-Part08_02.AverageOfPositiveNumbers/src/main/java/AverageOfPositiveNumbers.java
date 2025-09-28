
import java.util.Scanner;

public class AverageOfPositiveNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double average = 0;
        int count =0;
        int sum = 0;
        while (true) {
            int input = Integer.valueOf(scanner.nextLine());
            if (input == 0) {
                break;
            }
            if (input > 0) {
                sum = sum + input;
                count++;
            }
        }
        if (count > 0) {
            average = (double) sum / count;
            System.out.println(average);
        } else {
            System.out.println("Cannot calculatr the average");
        }
    }
}
