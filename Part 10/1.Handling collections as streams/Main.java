import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(3);
        values.add(2);
        values.add(1);
        values.add(17);
        values.add(8);
        values.add(4);
        values.stream().filter(value -> value % 2 == 0).forEach(value -> System.out.println(value));
    }
}

