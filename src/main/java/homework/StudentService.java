package homework;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StudentService {
    private final List<Student> students;
    private final CoursService coursService;

    public StudentService(List<Student> students) {
        this.coursService = new CoursService();
        this.students = Optional.ofNullable(students)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public String aggregateStudents(){
        return students.stream()
                .collect(teeing (
                mapping(t -> t.name(),toList()),
                averagingInt(t->t.grade()),
                (names,avg) -> "%s have an average grade of %s".formatted(String.join(",", names),avg)
        ));
    }

    public String getAvailableCourses(){
      return """
        [
            %s
        ]
        """
              .formatted(this.coursService
                      .getCourses()
                      .stream()
                      .map(this::buildCourseAsString)
                      .collect(Collectors.joining(",")));
    }

    public String allocateCoursesToStudents(){
       return  students.stream()
               .map(this::alocateCoursToStudent)
               .map(this::buildStudentInfo)
               .collect(joining("\n"));
    }

    public String gradeStudents() {
        List<String> grades = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            String grade = switch (i) {
                case 0, 1, 2 -> "1st grade";
                case 3 -> "5th grade";
                default -> "7th grade";
            };
            grades.add("%s was graded with %s".formatted(students.get(i).name(),grade));
        }
        return grades.stream().collect(joining("\n"));
    }

    private String buildCourseAsString(Cours cours) {
        return """
                {
                    "course":"%s",
                    "semester":%s
                }
                """.formatted(cours.name(),cours.semester());
    }


    private String buildStudentInfo(StudentCourse studentCoursMap) {
        return """
                %s will participate to course 
                %s
                """.formatted(studentCoursMap.student().name(), buildCourseAsString(studentCoursMap.cours()));
    }

    private StudentCourse alocateCoursToStudent(Student student) {
        var cours = coursService.getRandomCours();
        var map = new StudentCourse(student, cours);
        return map;
    }

}
