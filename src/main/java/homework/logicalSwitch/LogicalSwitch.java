package homework.logicalSwitch;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LogicalSwitch {
    private final Map<NamedPredicate<Person>, Function<Person, String>> map;
//
    public LogicalSwitch() {
        this.map = new HashMap<>();
        NamedPredicate<Person> p1 = new NamedPredicate<>(p ->p.name().startsWith("M"),"StartsWith M Filter");
        NamedPredicate<Person> p2 = new NamedPredicate<>(p ->p.age() < 10,"Young Filter");
        NamedPredicate<Person> p3 = new NamedPredicate<>(p ->p.age() > 60,"Old Filter");
        NamedPredicate<Person> p4 = new NamedPredicate<>(p ->p.address().length() > 10,"Address Filter");
        this.map.put(p1, (p) -> "%s starts with M".formatted(p.name()));
        this.map.put(p2, (p) -> "%s is young".formatted(p.name()));
        this.map.put(p3, (p) -> "%s is old".formatted(p.name()));
        this.map.put(p4, (p) -> "%s's address too long, replaced with %s...".formatted(p.name(),p.address().substring(0,10)));
    }

    public String compute(Person p){
        return map.entrySet()
                .stream()
                .map(m -> m.getKey().predicate().test(p)?m.getValue().apply(p):"%s not applied for %s".formatted(m.getKey().name(),p.name()))
                .collect(Collectors.joining("\n"));
    }

}
