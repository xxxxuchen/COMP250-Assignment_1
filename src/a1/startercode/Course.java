package a1.startercode;
// Assignment 1
// Author: Xu Chen
// ID: 260952566
public class Course {
    public String code;
    public int capacity;
    public SLinkedList<Student>[] studentTable;
    public int size;
    public SLinkedList<Student> waitlist;

    public Course(String code) {
        this.code = code;
        this.studentTable = new SLinkedList[10];
        this.size = 0;
        this.waitlist = new SLinkedList<Student>();
        this.capacity = 10;
    }

    public Course(String code, int capacity) {
        this.code = code;
        this.studentTable = new SLinkedList[capacity];
        this.size = 0;
        this.waitlist = new a1.startercode.SLinkedList<>();
        this.capacity = capacity;
    }

    public void changeArrayLength(int m) {
        // update capacity
        SLinkedList<Student>[] newStudentTable = new SLinkedList[m];
        for (int i = 0; i < capacity; i++) {
            if (studentTable[i] == null) {
                continue;
            }
            SLinkedList<Student> linkedStudent = studentTable[i];
            Student lastStudent = linkedStudent.getLast();
            int lastIndex = linkedStudent.getIndexOf(lastStudent);
            for (int j = 0; j <= lastIndex; j++) {
                Student movedStudent = linkedStudent.get(j);
                int newPosition = movedStudent.id % m;
                if (newStudentTable[newPosition] == null) {
                    SLinkedList<Student> sll0 = new a1.startercode.SLinkedList<>();
                    newStudentTable[newPosition] = sll0;
                    sll0.addLast(movedStudent);
                } else {
                    newStudentTable[newPosition].addLast(movedStudent);
                }
            }
        }
        capacity = m;
        studentTable = newStudentTable;
    }

    public boolean put(Student s) {
        boolean isRegistered = false;
        if(s.isRegisteredOrWaitlisted(code)){
            return false;
        }
        if(s.courseCodes.size() < s.COURSE_CAP) {
            if (size < capacity) {
                int index = s.id % capacity;
                if (studentTable[index] == null) {
                    SLinkedList<Student> sll0 = new SLinkedList<>();
                    studentTable[index] = sll0;
                    sll0.addLast(s);
                }else{
                    studentTable[index].addLast(s);
                }
                size++;
                s.addCourse(code);
                isRegistered = true;
            }
            else{ // put to waitlist
                int maxWaited = (int)(capacity * 0.5);
                if(waitlist.size() < maxWaited){
                    waitlist.addLast(s);
                    s.addCourse(code);
                    isRegistered = true;
                }
                else { // new table
                    int newCap = (int) (capacity * 1.5);
                    changeArrayLength(newCap);
                    Student lastStudent = waitlist.getLast();
                    int lastIndex = waitlist.getIndexOf(lastStudent);
                    for (int m = 0; m <= lastIndex; m++) {
                        Student wStudent = waitlist.get(m);
                        int location = wStudent.id % capacity;
                        if (studentTable[location] == null) {
                            SLinkedList<Student> sll = new a1.startercode.SLinkedList<>();
                            studentTable[location] = sll;
                            sll.addLast(wStudent);

                        } else {
                            studentTable[location].addLast(wStudent);
                        }
                    }
                    size = size + waitlist.size();
                    SLinkedList<Student> newWaitlist = new SLinkedList<Student>();
                    newWaitlist.addLast(s);
                    waitlist = newWaitlist;
                    isRegistered = true;
                    s.addCourse(code);
                }
            }
        }
        return isRegistered;
    }

    public Student get(int id) {
        int position = id % capacity;
        SLinkedList<Student> studentLinked = studentTable[position];
        if (studentLinked != null) {
            Student last = studentLinked.getLast();
            int lastIndex = studentLinked.getIndexOf(last);
            for (int j = 0; j <= lastIndex; j++) {
                Student stu = studentLinked.get(j);
                if (stu.id == id) {
                    return stu;
                }
            }
            Student lastWaited = waitlist.getLast();
            int lastIdx = waitlist.getIndexOf(lastWaited);
            for (int k = 0; k <= lastIdx; k++) {
                Student waitedStu = waitlist.get(k);
                if (waitedStu.id == id) {
                    return waitedStu;
                }
            }

        } else {
            Student lastWaited = waitlist.getLast();
            int lastIdx = waitlist.getIndexOf(lastWaited);
            for (int k = 0; k <= lastIdx; k++) {
                Student waitedStu = waitlist.get(k);
                if (waitedStu.id == id) {
                    return waitedStu;
                }
            }
        }
        return null;
    }


    public Student remove(int id) {
        int position = id % capacity;
        Student removedStu = get(id);
        SLinkedList<Student> stuRegistered = studentTable[position];
        if (removedStu == null) {
            return null;
        }
        if (stuRegistered != null) {
            if (stuRegistered.getIndexOf(removedStu) > -1) {
                int indexOfRegisterRemoved = stuRegistered.getIndexOf(removedStu);
                removedStu.dropCourse(code);
                // remove student when waitlist is empty
                if (waitlist.isEmpty()) {
                    size--;
                    return (stuRegistered.remove(indexOfRegisterRemoved));
                }
                // replace student, queue behavior
                Student replacedStudent = stuRegistered.remove(indexOfRegisterRemoved);
//                Student firstWaitStudent = waitlist.getFirst();
//                stuRegistered.add(indexOfRegisterRemoved, firstWaitStudent);
//                waitlist.removeFirst();
//                return replacedStudent;
                Student firstWaitStudent = waitlist.getFirst();
                int pos = firstWaitStudent.id % capacity;
                if (studentTable[pos] == null) {
                    a1.startercode.SLinkedList<Student> sll = new a1.startercode.SLinkedList<>();
                    studentTable[pos] = sll;
                    sll.addLast(firstWaitStudent);
                } else {
                    studentTable[pos].addLast(firstWaitStudent);
                }
                waitlist.removeFirst();
                return replacedStudent;
            }
        }
        // remove student on waitlist
        Student lastWaited = waitlist.getLast();
        int lastIndex = waitlist.getIndexOf(lastWaited);
        for (int j = 0; j <= lastIndex; j++) {
            if (waitlist.get(j).id == id) {
                waitlist.get(j).dropCourse(code);
                return (waitlist.remove(j));
            }
        }
        return null;
    }

    public int getCourseSize() {
        return size;
    }


    public int[] getRegisteredIDs() {
        if(size == 0){
            int [] IDs = new int[0];
            return IDs;
        }
        int [] IDs = new int[size];
        int index = 0;
        for(int i = 0; i < capacity; i++){
            if (studentTable[i] == null) {
                continue;
            }
            a1.startercode.SLinkedList<Student> sdtList = studentTable[i];
            Student lastStudent = sdtList.getLast();
            int lastIndex = sdtList.getIndexOf(lastStudent);
            for(int j = 0; j<= lastIndex; j++){
                IDs[index] = sdtList.get(j).id;
                index++;
            }
        }
        return IDs;
    }

    public Student[] getRegisteredStudents() {
        if(size == 0){
            Student [] students = new Student[0];
            return students;
        }
        Student [] students = new Student[size];
        int index = 0;
        for(int i = 0; i < capacity; i++){
            if (studentTable[i] == null) {
                continue;
            }
            a1.startercode.SLinkedList<Student> sdtList = studentTable[i];
            Student lastStudent = sdtList.getLast();
            int lastIndex = sdtList.getIndexOf(lastStudent);
            for(int j = 0; j<= lastIndex; j++){
                students[index] = sdtList.get(j);
                index++;
            }
        }
        return students;
    }

    public int[] getWaitlistedIDs() {
        if(waitlist.size() == 0){
            int [] IDs = new int[0];
            return IDs;
        }
        int [] IDs = new int[waitlist.size()];
        Student lastStudent = waitlist.getLast();
        int lastIndex = waitlist.getIndexOf(lastStudent);
        for(int j = 0; j<= lastIndex; j++){
            IDs[j] = waitlist.get(j).id;
        }

        return IDs;

    }

    public Student[] getWaitlistedStudents() {
        if(waitlist.size() == 0){
            Student [] students = new Student[0];
            return students;
        }
        Student [] students = new Student[waitlist.size()];
        Student lastStudent = waitlist.getLast();
        int lastIndex = waitlist.getIndexOf(lastStudent);
        for(int j = 0; j<= lastIndex; j++){
            students[j] = waitlist.get(j);
        }
        return students;
    }

    public String toString() {
        String s = "Course: "+ this.code +"\n";
        s += "--------\n";
        for (int i = 0; i < this.studentTable.length; i++) {
            s += "|"+i+"     |\n";
            s += "|  ------> ";
            a1.startercode.SLinkedList<Student> list = this.studentTable[i];
            if (list != null) {
                for (int j = 0; j < list.size(); j++) {
                    Student student = list.get(j);
                    s +=  student.id + ": "+ student.name +" --> ";
                }
            }
            s += "\n--------\n\n";
        }

        return s;
    }
}
