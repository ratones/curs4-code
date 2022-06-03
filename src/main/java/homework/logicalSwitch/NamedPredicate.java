package homework.logicalSwitch;

import java.util.function.Predicate;

public  record NamedPredicate<T>(Predicate<T> predicate, String name ) {
}
