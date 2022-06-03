package homework.logicalSwitch;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LogicalSwitch {
    private final Map<NamedPredicate<Person>, Function<Person, String>> map;
//
    public LogicalSwitch() {
        this.map = new HashMap<>();
        NamedPredicate<Person> p1 = new NamedPredicate<>(p ->p.getName().startsWith("M"),"StartsWith M Filter");
        NamedPredicate<Person> p2 = new NamedPredicate<>(p ->p.getAge() < 10,"Young Filter");
        NamedPredicate<Person> p3 = new NamedPredicate<>(p ->p.getAge() > 60,"Old Filter");
        NamedPredicate<Person> p4 = new NamedPredicate<>(p ->p.getAddress().length() > 10,"Address Filter");
        this.map.put(p1, (p) -> "%s starts with M".formatted(p.getName()));
        this.map.put(p2, (p) -> "%s is young".formatted(p.getName()));
        this.map.put(p3, (p) -> "%s is old".formatted(p.getName()));
        this.map.put(p4, (p) -> "%s's address too long, replaced with %s...".formatted(p.getName(),p.getAddress().substring(0,10)));
    }

    public String compute(Person p){
        return map.entrySet()
                .stream()
                .map(m -> m.getKey().predicate().test(p)?m.getValue().apply(p):"%s not applied for %s".formatted(m.getKey().name(),p.getName()))
                .collect(Collectors.joining("\n"));
    }

}
