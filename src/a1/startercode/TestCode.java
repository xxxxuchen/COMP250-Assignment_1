package a1.startercode;
import java.util.Arrays;

public class TestCode {
    public static void main(String[] args){
        System.out.println("First let's test the initialization.");
        a1.startercode.Course IntroToComputerScience = new a1.startercode.Course("COMP250"); // you can change capacity if you want, code below should take it just fine
        System.out.print("You should get an array of 0 here: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredIDs())); // check registered id initialization
        System.out.print("And an array of null here: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredStudents())); // check registered student initialization
        System.out.println("Number of students registered: "+IntroToComputerScience.getCourseSize());
        System.out.print("Depending on your implementation, you might get an empty array or something similar: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedIDs())); // check waitlisted id initialization
        System.out.print("And another one: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedStudents())); // check waitlisted student initialization
        System.out.println("\nNow I'll fill the course with students.");
        for (int i = 0; i < IntroToComputerScience.capacity; i++){ // map all available seats with students
            a1.startercode.Student s = new a1.startercode.Student((261000000+i),"RegisteredStudent".concat(String.valueOf(i)));
            IntroToComputerScience.put(s);
            if (i == 0 || i == IntroToComputerScience.capacity - 1){ // just arbitrary values
                System.out.println("I'm trying to register "+s.name+" twice: ");
                IntroToComputerScience.put(s); // this checks for redundancy
            }
        }
        System.out.print("Here you'll see all students registered in this course in their ID: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredIDs())); // check registered id
        System.out.print("This should be an array of nonsense, but still an array: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredStudents())); // check registered student
        System.out.println("Number of students registered: "+IntroToComputerScience.getCourseSize());
        System.out.print("The following two should still be empty as for now: ");
        System.out.print(Arrays.toString(IntroToComputerScience.getWaitlistedIDs())); // check waitlisted id
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedStudents())); // check waitlisted student
        System.out.println("\nNow I'll fill some people in waitlist.");

        for (int i = 0; i < (int)(IntroToComputerScience.capacity/2); i++){
            a1.startercode.Student s = new a1.startercode.Student((261000100+i),"waitlistStudent".concat(String.valueOf(i)));
            IntroToComputerScience.put(s);
            if (i == 0){ // just an arbitrary value
                System.out.println("I'm trying to register "+s.name+" twice: ");
                IntroToComputerScience.put(s); // check for waitlist redundancy
            }
        }
        System.out.print("The following two arrays should be exactly the same as before: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredIDs())); // check registered id
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredStudents())); // check registered student
        System.out.println("Number of students registered: "+IntroToComputerScience.getCourseSize());
        System.out.print("And this array is now filled with ID of students in waitlist: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedIDs())); // check waitlisted id
        System.out.print("This one is again nonsense, but must be an array with values: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedStudents())); // check waitlisted student
        System.out.println("\nNow I'll add one more student to the waitlist, that is already full. ");
        IntroToComputerScience.put(new a1.startercode.Student(261000200,"The teacher's pet")); // put one more student in
        System.out.println("If you remember the instuctions, when all classes and the waitlist are full, you get more capacity.");
        System.out.print("You should get a larger array than before (order doesn't matter): ");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredIDs())); // check registered id
        System.out.print("Just making sure: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredStudents())); // check registered student
        System.out.println("Number of students registered: "+IntroToComputerScience.getCourseSize());
        System.out.print("Waitlist should now be cleared, you should get empty arrays again except the student who just registered: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedIDs())); // check waitlisted id
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedStudents())); // check waitlisted student
        System.out.println("\nNow let's say that The teacher's pet (the name I gave to the last student) no longer wants to take this course.");
        System.out.println("I'll try to remove this student from the course.");
        IntroToComputerScience.remove(261000200); // remove student
        System.out.println("You shouldn't see the student ID 261000200 anymore in any of the arrays: ");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredIDs())); // check registered id
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedIDs())); // check waitlisted id
        System.out.println("Number of students registered: "+IntroToComputerScience.getCourseSize());
        System.out.println("And if you try to access this student, you can't.");
        System.out.print("Which means that this will return null (not error! a null pointer): ");
        System.out.println(IntroToComputerScience.get(261000200));
        System.out.print("But if you try getting someone else (let's say the very first student): ");
        System.out.println(IntroToComputerScience.get(261000000).name);
        System.out.println("\nAnd finally, remember that a student can take up to 3 courses.");
        a1.startercode.Course c1 = new a1.startercode.Course("ABCD111");
        a1.startercode.Course c2 = new a1.startercode.Course("ABCD222");
        a1.startercode.Course c3 = new a1.startercode.Course("ABCD333");
        a1.startercode.Student stu = new a1.startercode.Student(261000300,"The one no one remembers :-(");
        c1.put(stu);
        c2.put(stu);
        c3.put(stu);
        System.out.println("If you don't like reading code, I just registered a student to 3 other courses.");
        System.out.println("Now I'll try to register this student to this course (which will be his/her 4th course. ");
        IntroToComputerScience.put(stu);
        System.out.println("If you see 261000300 is registered or waitlisted, you might want to modify your code:");
        System.out.println(Arrays.toString(IntroToComputerScience.getRegisteredIDs())); // check registered id
        System.out.println(Arrays.toString(IntroToComputerScience.getWaitlistedIDs())); // check waitlisted id
        System.out.println("Number of students registered: "+IntroToComputerScience.getCourseSize());
        System.out.println("\nIf your code has passed all the tests above, I think you've done a pretty good job!");
        System.out.println(IntroToComputerScience);
    }
}

