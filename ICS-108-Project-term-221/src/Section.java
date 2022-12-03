
public class Section {

    private String course_sec;
    private String time_days;
    private String location;

    public Section() {

    }

    public Section(
            String course_sec,
            String time_days,
            String location) {
        this.course_sec = course_sec;
        this.time_days = time_days;
        this.location = location;
    }

    public String getTime_days() {
        return time_days;
    }

    public String getCourse_sec() {
        return course_sec;
    }

    public String getLocation() {
        return location;
    }

}
