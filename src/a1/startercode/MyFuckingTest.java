package a1.startercode;

public class MyFuckingTest {
    public static void main(String[] args){
        Course IntroToComputerScience = new Course("COMP250");
        IntroToComputerScience.capacity=4;
        IntroToComputerScience.code = "ecse202";
        Student s1= new Student(1, "s1");
        Student s2= new Student(2, "s2");
        Student s3= new Student(3, "s3");
        Student s4= new Student(4, "s4");
        Student s5= new Student(5, "s5");
        Student s6= new Student(6, "s6");
        Student s7= new Student(7, "s7");

        System.out.println(IntroToComputerScience.getRegisteredIDs());
        System.out.println("The size is " + IntroToComputerScience.size + " expected 0");
        System.out.println("Now we will fill up the course");
        IntroToComputerScience.put(s1);
        IntroToComputerScience.put(s2);
        IntroToComputerScience.put(s3);
        IntroToComputerScience.put(s4);
        System.out.println("The size is " + IntroToComputerScience.size + " expected 4");
        System.out.println("The capacity is " + IntroToComputerScience.capacity + " expected 4");
        System.out.println("Now I will try to add two more students, who should go to the waitlist");
        IntroToComputerScience.put(s5);
        IntroToComputerScience.put(s6);
        System.out.println("The size is still: " + IntroToComputerScience.size + " expected 4");
        System.out.println("The waitlist is size: " + IntroToComputerScience.waitlist.size() + " expected 2");
        System.out.println("The capacity should not have increased yet, it is: " + IntroToComputerScience.capacity + "expected to be 4");
        System.out.println("Now we will add someone else to the course, and hopefully trigger the expansion of the class");
        IntroToComputerScience.put(s7);
        System.out.println("Capacity should have increasted to 6, it is: " + IntroToComputerScience.capacity);
        System.out.println("Size should have increasted to 6, it is: " + IntroToComputerScience.size);
        System.out.println("because of the student who triggered the class to size up, waitlist should be 1 and it is: " + IntroToComputerScience.waitlist.size());
        System.out.println(IntroToComputerScience.get(8));




    }
}
