import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private int totalClasses;
    private int attendedClasses;

    public Student(String name) {
        this.name = name;
        this.totalClasses = 0;
        this.attendedClasses = 0;
    }

    public String getName() {
        return name;
    }

    public void incrementTotalClasses() {
        totalClasses++;
    }

    public void markAttendance(boolean isPresent) {
        if (isPresent) {
            attendedClasses++;
        }
        incrementTotalClasses();
    }

    public double calculateAttendancePercentage() {
        if (totalClasses == 0) {
            return 0.0;
        }
        return ((double) attendedClasses / totalClasses) * 100;
    }
}

class AttendanceSystem {
    private Map<Student, Boolean> attendanceRecords;

    public AttendanceSystem() {
        attendanceRecords = new HashMap<>();
    }

    public void addStudent(Student student) {
        attendanceRecords.put(student, false);  
    }

    public void markAttendance(Student student, boolean isPresent) {
        if (attendanceRecords.containsKey(student)) {
            attendanceRecords.put(student, isPresent);
        }
    }

    public void displayAttendanceReport() {
        System.out.println("Attendance Report:\n");
        for (Map.Entry<Student, Boolean> entry : attendanceRecords.entrySet()) {
            Student student = entry.getKey();
            String attendanceStatus = entry.getValue() ? "Present" : "Absent";
            double attendancePercentage = student.calculateAttendancePercentage();
            System.out.println("Student: " + student.getName());
            System.out.println("Attendance Status: " + attendanceStatus);
            System.out.println("Attendance Percentage: " + attendancePercentage + "%");
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendanceSystem attendanceSystem = new AttendanceSystem();

        
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter student " + (i + 1) + " name: ");
            String name = scanner.nextLine();
            Student student = new Student(name);
            attendanceSystem.addStudent(student);

            System.out.print("Is " + student.getName() + " present? (true/false): ");
            boolean isPresent = scanner.nextBoolean();
            attendanceSystem.markAttendance(student, isPresent);
            scanner.nextLine();  
        }


        attendanceSystem.displayAttendanceReport();
    }
}
