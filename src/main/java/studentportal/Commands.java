package studentportal;

public interface Commands {

    String EXIT = "0";
    String ADD_LESSON = "1";
    String PRINT_ALL_LESSONS = "2";
    String ADD_STUDENT = "3";
    String PRINT_ALL_STUDENTS = "4";

    static void printCommands(){
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_LESSON + " for ADD_LESSON");
        System.out.println("Please input " + PRINT_ALL_LESSONS + " for PRINT_ALL_LESSONS");
        System.out.println("Please input " + ADD_STUDENT + " for ADD_STUDENT");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " for PRINT_ALL_STUDENTS");
    }
}
