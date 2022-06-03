package homework;

import java.util.List;

public class StudentMain {
    public static void main(String[] args) {
        var students = List.of(
                new Student("Cristi",5,18),
                new Student("Alex",10,19),
                new Student("Marius",10,23),
                new Student("Mihai",10,21),
                new Student("Ioan",10,42),
                new Student("",10,42)

        );
        var service = new StudentService(students);
        System.out.println(service.aggregateStudents());
//        System.out.println(service.getAvailableCourses());
        System.out.println(service.allocateCoursesToStudents());
        System.out.println(service.gradeStudents());
    }
}
