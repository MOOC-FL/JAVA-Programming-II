import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

public class collect {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(3);
        values.add(2);
        values.add(-17);
        values.add(-6);
        values.add(8);
        ArrayList<Integer> positives = values.stream().filter(value -> value > 0)
                .collect(Collectors.toCollection(ArrayList::new));
        positives.stream().forEach(value -> System.out.println(value));
    }
}
