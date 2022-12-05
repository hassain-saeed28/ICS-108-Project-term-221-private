import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Section {

    // the list of sectins in the course offering
    public static ObservableList<Section> sections = FXCollections.observableArrayList() ;

    private String course;
    private String sec;
    private String days;
    private String time;
    private String location;
    private Button addButton;
    private Button removeButton;


    public Section() {

    }

    public Section(
            String course,
            String sec,
            String days,
            String time,
            String location) {
        this.course = course;
        this.sec = sec;
        this.days = days;
        this.time = time;
        this.location = location;
        this.addButton = new Button("add");
        this.removeButton = new Button("Remove");
        removeButton.setOnAction(e->{
            System.out.println("it works too");
        });
        addButton.setOnAction(e->{
            System.out.println("it works");
        });
    }

    public String getCourse() {
        return course;
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
        return "\ncourse: " + this.course +
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
            String course = new String(course_sec[0]);
            String sec = new String(course_sec[1]);
            String days = new String(lineComponents[2]);
            String time = new String(lineComponents[3]);
            String location = new String(lineComponents[1]);

            sections.add(new Section(course, sec, days, time, location));
        }
        input.close();
        return sections;

    }

}
