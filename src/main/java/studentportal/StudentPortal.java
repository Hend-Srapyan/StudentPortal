package studentportal;

import studentportal.model.Lesson;
import studentportal.model.Student;
import studentportal.service.LessonService;
import studentportal.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentPortal implements Commands {

    private static Scanner scanner = new Scanner(System.in);
    private static LessonService lessonService = new LessonService();
    private static StudentService studentService = new StudentService();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_ALL_LESSONS:
                    System.out.println(lessonService.getAllLessons());
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    System.out.println(studentService.getAllStudents());
                    break;
                default:
                    System.err.println("INVALID COMMAND!!!");
            }
        }

    }

    private static void addStudent() {
        List<Lesson> allLessons = lessonService.getAllLessons();
        System.err.println("HERE ARE ALL LESSONS:");
        for (Lesson lesson : allLessons) {
            System.out.println(lesson.getId() + " --> " + lesson.getName() + " --> " + lesson.getPrice());
        }
        System.out.println("Please input lesson id");
        int lessonId = Integer.parseInt(scanner.nextLine());
        Lesson lessonById = lessonService.getLessonById(lessonId);
        if (lessonById != null) {
            System.out.println("Please input student name, surname, email, phone");
            String studentDataStr = scanner.nextLine();
            String[] studentDataArr = studentDataStr.split(",");
            studentService.add(Student.builder()
                    .name(studentDataArr[0])
                    .surname(studentDataArr[1])
                    .email(studentDataArr[2])
                    .phone(studentDataArr[3])
                    .lesson(lessonById)
                    .build());
            System.err.println("STUDENT ADDED!!!");
        }

    }

    private static void addLesson() {
        System.out.println("Please input lesson name, lecturer name, price ");
        String lessonDataStr = scanner.nextLine();
        String[] lessonDataArr = lessonDataStr.split(",");
        lessonService.add(Lesson.builder()
                .name(lessonDataArr[0])
                .lecturerName(lessonDataArr[1])
                .price(Double.parseDouble(lessonDataArr[2]))
                .build());
        System.err.println("LESSON ADDED!");
    }
}
