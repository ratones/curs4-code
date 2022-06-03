package homework;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public record Student(String name, LocalDate birthDate, int grade) {

    Student(String name, int grade, int age){
        this(validateName(name), generateRandomBirthDate(age), validateGrade(grade));
    }


    private static String validateName(String name){
        return name.isBlank()?"Anonymous":name;
    }

    private static int validateGrade(int grade){
        return Math.max(0, grade);
    }

    private static LocalDate generateRandomBirthDate(int age){
        if(age <= 16){
            throw new RuntimeException("Cannot add student under age 16!");
        }
        int month = new Random().nextInt(1,12);
        int day = switch (month) {
            case 2 -> new Random().nextInt(1,28);
            case 1,3,5,7,8,10,12 -> new Random().nextInt(1,31);
            default -> new Random().nextInt(1,30);
        };
        int year = LocalDate.now().getYear() - age;
        return LocalDate.of(year,month,day);
    }

    public int getAge(){
        return Period.between(birthDate(),LocalDate.now()).getYears();
    }
}
