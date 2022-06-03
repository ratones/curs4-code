package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoursService {
    private final List<Cours> courses = List.of(
            new Cours("JAVA Spring Boot",1),
            new Cours("Oracle Database",1),
            new Cours("Angular Framework",2),
            new Cours("Elastic Search",2)
    );

    public List<Cours> getCourses() {
        return new ArrayList<>(courses);
    }

    public Cours getRandomCours(){
        return courses.get(new Random().nextInt(0, courses.size()));
    }
}
