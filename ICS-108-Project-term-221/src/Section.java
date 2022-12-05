import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Section {

    // the list of sectins in the course offering
    public static ObservableList<Section> sections = FXCollections.observableArrayList();

    private String courseName;
    private String sec;
    private String days;
    private String time;
    private String location;
    private Button addButton;
    private Button removeButton;

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
        this.addButton = new Button("add");
        this.removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            System.out.println("it works too");
        });
        addButton.setOnAction(e -> {
            System.out.println("it works");
        });
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

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    @Override
    public String toString() {
        return "\ncourse: " + this.courseName +
                "section: " + this.sec +
                "days: " + this.days +
                "time: " + this.time +
                "\nthe location is: " + this.location;
    }

    public static ObservableList<Section> getSectionsObservableList() throws FileNotFoundException {

        // opening the course offiering file and adding each section in the list of
        // sections
        File courseOfferingFile = new File("ICS-108-Project-term-221/CourseOffering.csv");
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
            String days = new String(lineComponents[2]);
            String time = new String(lineComponents[3]);
            String location = new String(lineComponents[1]);
            boolean found = (Student.getFinishedCourses().contains(courseN));
            if ( ! found ) {
                sections.add(new Section(courseN, sec, days, time, location));
            }
            
        }

        // for (int i = 0; i < sections.size(); i++) {
        //     if (Student.getFinishedCourses().contains(sections.get(i).getCourseName())) {
        //         sections.remove(i);
        //     }
        // }

        // for (int section = 0; section < sections.size(); section++) {
        // for (int courseInTheSection = 0; courseInTheSection < Course.courses.size();
        // courseInTheSection++) {
        // if (sections.get(section).getCourseName() ==
        // Course.courses.get(courseInTheSection).getCourseName()) {
        // if (Course.courses.get(courseInTheSection).getPrerequisite() != "None") {
        // if (!(Student.getFinishedCourses()
        // .contains(Course.courses.get(courseInTheSection).getPrerequisite()))) {
        // sections.remove(section);
        // }
        // }
        // }
        // }

        // }
        input.close();
        return sections;

    }

}
