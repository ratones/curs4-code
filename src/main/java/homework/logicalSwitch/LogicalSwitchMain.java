package homework.logicalSwitch;

import java.util.List;

import static java.util.stream.Collectors.*;

public class LogicalSwitchMain {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Cristi", 49, "Bucuresti, Strada sperantei"),
                new Person("Marian", 64, "Cluj"),
                new Person("Mihai", 49, "Timisoara, Piata mare, nr 22"),
                new Person("Ana", 8, "Sibiu")
        );
        var lsw = new LogicalSwitch();
        System.out.println(persons.stream().map(lsw::processPerson).collect(joining("\n\nProcessing person.... \n")));
    }
}
