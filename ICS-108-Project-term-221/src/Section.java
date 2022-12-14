import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.media.Track;

public class Section {

    // the list of sectins in the course offering
    public static ObservableList<Section> sections = FXCollections.observableArrayList();
    public static ArrayList<Section> basket = new ArrayList<>();
    private String courseName;
    private String sec;
    private String days;
    private String time;
    private String location;
    // private Button addButton;
    // private Button removeButton;

    public Section() {

    }

    public Section(
            String courseName,
            String sec,
            String days,
            String time,
            String location) {
        this.courseName = courseName;
        this.sec = sec;
        this.days = days;
        this.time = time;
        this.location = location;
        // this.addButton = new Button("add");
        // this.removeButton = new Button("Remove");

    }

    public String getCourseName() {
        return courseName;
    }

    public String getSec() {
        return sec;
    }

    public String getDays() {
        return days;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    // public Button getAddButton() {
    //     return addButton;
    // }

    // public Button getRemoveButton() {
    //     return removeButton;
    // }

    @Override
    public String toString() {
        return "\ncourse: " + this.courseName +
                "section: " + this.sec +
                "days: " + this.days +
                "time: " + this.time +
                "\nthe location is: " + this.location;
    }

    public static ObservableList<Section> getSectionsObservableList() throws FileNotFoundException {

        ArrayList<Course> courses = Course.getCourses();

        // opening the course offiering file and adding each section in the list of
        // sections
        File courseOfferingFile = new File("ICS-108-Project-term-221/CourseOfferingNew.csv");
        Scanner input = new Scanner(courseOfferingFile);

        // to skep the first row because is has the labels
        input.nextLine();

        // here i will read each line and then split it by the "," dilamitr and then
        // store the components in an array then make a Section opject and then store it
        // in the arrayList sectinos
        while (input.hasNext()) {

            String line = input.nextLine();
            String[] lineComponents = line.split(",");
            String[] course_sec = lineComponents[0].split("-");
            String courseN = new String(course_sec[0]);
            String sec = new String(course_sec[1]);
            String days = new String(lineComponents[1]);
            String time = new String(lineComponents[2]);
            String location = new String(lineComponents[3]);

            // if (!(Student.getFinishedCourses().contains(courseN))) {
            // sections.add(new Section(courseN, sec, days, time, location));
            // }

            int counter = 0;
            int coursesSize = courses.size();

            while (counter < coursesSize) {
                String courseNameFromCourses = courses.get(counter).getCourseName();
                String courseNameFromSection = courseN;
                boolean courseIsNotFinished = !(Student.getFinishedCourses().contains(courseN));
                boolean prerequisiteIsNotNone = true;

                boolean found = (courseNameFromSection.equals(courseNameFromCourses));
                if (found) {
                    ArrayList<String> Prerequisite = courses.get(counter).getPrerequisite();

                    for (int i = 0; i < Prerequisite.size(); i++) {
                        if (Prerequisite.get(i).equals("None")) {
                            prerequisiteIsNotNone = false;
                        }
                    }

                    if (prerequisiteIsNotNone) {
                        boolean haveFinishedPrerequisite = Student.getFinishedCourses().containsAll(Prerequisite);
                        if (haveFinishedPrerequisite) {
                            sections.add(new Section(courseN, sec, days, time, location));
                            counter = courses.size();
                        }
                    } else if ((!prerequisiteIsNotNone) & courseIsNotFinished) {
                        sections.add(new Section(courseN, sec, days, time, location));
                        counter = courses.size();
                    }
                }
                counter++;
            }
        }

        // الكوندشن مضروب يضيف الكورسات الي قبل مثل ENGLS 102


        input.close();
        return sections;
    }

    // public static void main(String[] args) throws FileNotFoundException {
    //     System.out.println(getSectionsObservableList());
    // }
}
