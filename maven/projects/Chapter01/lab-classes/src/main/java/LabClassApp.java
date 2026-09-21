public class LabClassApp {
  public static void main(String[] args) {
    LabClass labClass = new LabClass(4);
    labClass.setInstructor("Dr. John Doe");
    labClass.setRoom("Room 101");
    labClass.setTime("Monday, 10am");

    labClass.enrollStudent(new Student("Alice Smith", "S123456"));
    labClass.enrollStudent(new Student("Bob Johnson", "S234567"));
    labClass.enrollStudent(new Student("Charlie Brown", "S345678"));
    labClass.enrollStudent(new Student("Diana Prince", "S456789"));

    labClass.printList();
  }
}
