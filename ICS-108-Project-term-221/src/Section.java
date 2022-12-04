import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Section {

    // the list of sectins in the course offering
    public static ArrayList<Section> sections = new ArrayList<>();

    private String course_sec;
    private String location;
    private String days_time;

    public Section() {

    }

    public Section(
            String course_sec,
            String location,
            String days_time) {
        this.course_sec = course_sec;
        this.location = location;
        this.days_time = days_time;
    }

    public String getTime_days() {
        return days_time;
    }

    public String getCourse_sec() {
        return course_sec;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "\nthe course and the section are: " + this.course_sec +
                "\nthe location is: " + this.location +
                "\nthe time and days are: " + this.days_time;
    }

    public static void main(String[] args) throws FileNotFoundException {

        // opening the course offiering file and adding each section in the list of
        // sections
        File courseOfferingFile = new File("ICS-108-Project-term-221/CourseOffering.csv");
        Scanner input = new Scanner(courseOfferingFile);

        // to skep the first row because is has the labels
        input.nextLine();

        // just for verifying the it works (the method below)
        int index = 0;

        // here i will read each line and then split it by the "," dilamitr and then
        // store the components in an array then make a Section opject and then store it
        // in the arrayList sectinos
        while (input.hasNext()) {

            String line = input.nextLine();
            String[] lineComponents = line.split(",");

            String Course = new String(lineComponents[0]);
            String location = new String(lineComponents[1]);
            String day_time = new String(lineComponents[3] + " " + lineComponents[2]);

            sections.add(new Section(Course, location, day_time));

            System.out.println(sections.get(index));
            index++;
        }

        input.close();
    }

}
