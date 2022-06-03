package homework;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StudentService {
    private final List<Student> students;
    private final CourseService coursService;

    public StudentService(List<Student> students) {
        this.coursService = new CourseService();
        this.students = Optional.ofNullable(students)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public String aggregateStudents(){
        return students.stream()
                .collect(teeing (
                mapping(Student::name,toList()),
                averagingInt(Student::grade),
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
               .map(this::allocateCourseToStudent)
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
        return String.join("\n", grades);
    }

    private String buildCourseAsString(Course cours) {
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
                """.formatted(studentCoursMap.student().name(), buildCourseAsString(studentCoursMap.course()));
    }

    private StudentCourse allocateCourseToStudent(Student student) {
        var course = coursService.getRandomCourse();
        return new StudentCourse(student, course);
    }


}
