import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {

    private static ArrayList<String> finishedCourses = new ArrayList<>();

    public Student() {

    }


    public static ArrayList<String> getFinishedCourses() throws FileNotFoundException {

        // opening the finished courses file and adding each course in the list of finishedCourses
        File finishedCoursesFile = new File("ICS-108-Project-term-221/FinishedCourses.csv");
        Scanner input = new Scanner(finishedCoursesFile);

        // to skep the first row because is has the labels
        input.nextLine();

        // here i will read each line and then split it by the "," dilamitr and then
        // store the components in an array then make a Student opject and then store it
        // in the arrayList finishedCourses
        while (input.hasNext()) {

            String line = input.nextLine();
            String[] lineComponents = line.split(",");
            String finishedCourse = new String(lineComponents[0]);
            finishedCourses.add(finishedCourse);
        }
        input.close();
        return finishedCourses;

    }

    // public static void main(String[] args) throws FileNotFoundException {
    //     System.out.println(getFinishedCourses());
    // }
    
}
