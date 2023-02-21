package a1.startercode;

public class Student {
    public int id;
    public String name;
    public a1.startercode.SLinkedList<String> courseCodes;

    public final int COURSE_CAP = 3;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courseCodes = new a1.startercode.SLinkedList<String>();
    }

    public boolean isRegisteredOrWaitlisted(String course) {
        return this.courseCodes.getIndexOf(course) > -1;
    }

    public void addCourse(String course) {
        this.courseCodes.addLast(course);
    }


    public void dropCourse(String course) {
        this.courseCodes.remove(course);
    }
}
