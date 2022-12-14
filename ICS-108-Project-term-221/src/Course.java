import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {

    // the list of courses in the degree plan 
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<String> coursesThatHavePrerequisites = new ArrayList<>();

    private ArrayList<String> prerequisite;
    private ArrayList<String> corequisite;
    private String courseName;
    private String courseCredit;

    public Course() {

    }

    public Course(
            String courseName,
            String courseCredit,
            ArrayList<String> prerequisite,
            ArrayList<String> corequisite) {
        this.prerequisite = prerequisite;
        this.corequisite = corequisite;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
    }

    public ArrayList<String> getCorequisite() {
        return corequisite;
    }

    public ArrayList<String> getPrerequisite() {
        return prerequisite;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    @Override
    public String toString() {
        return "\nthe course name is: " + this.courseName +
                "\nthe course credit is: " + this.courseCredit +
                "\nthe prerequisite is: " + this.prerequisite +
                "\nthe corequisite is: " + this.corequisite;
    }

    public static ArrayList<Course> getCourses() throws FileNotFoundException {

        // opening the degree plan file and adding each course in the list of courses
        File degreePlanFile = new File("ICS-108-Project-term-221/DegreePlan.csv");
        Scanner input = new Scanner(degreePlanFile);

        // to skep the first row because is has the labels
        input.nextLine();

        // just for verifying the it works (the method below)
        // int index = 0;


        // here i will read each line and then split it by the "," dilamitr and then
        // store the components in an array then make a Cours opject and then store it in the arrayList courses
            while (input.hasNext()) {
            String line = input.nextLine();
            String[] lineComponents = line.split(",");

            String courseName = new String(lineComponents[0]);
            String creditHours = new String(lineComponents[1]);
            
            String[] tempStringArrayToFillPrerequisiteArrayList = lineComponents[2].split(";");
            ArrayList<String> prerequisite = new ArrayList<>();
            for (int i = 0; i < tempStringArrayToFillPrerequisiteArrayList.length; i++) {
                prerequisite.add(tempStringArrayToFillPrerequisiteArrayList[i]);
            }

            String[] tempStringArrayToFillCorequisiteArrayList = lineComponents[3].split(";");
            ArrayList<String> corequisite = new ArrayList<>();
            for (int i = 0; i < tempStringArrayToFillCorequisiteArrayList.length; i++) {
                corequisite.add(tempStringArrayToFillCorequisiteArrayList[i]);
            }

            courses.add(new Course(courseName, creditHours, prerequisite, corequisite));

        }
        input.close();
        return courses;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(getCourses());
    }
}