package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseService {
    private final List<Course> courses = List.of(
            new Course("JAVA Spring Boot",1),
            new Course("Oracle Database",1),
            new Course("Angular Framework",2),
            new Course("Elastic Search",2)
    );

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public Course getRandomCourse(){
        var course = courses.get(new Random().nextInt(0, courses.size()));
        return new Course(course.name(), course.semester());
    }
}
