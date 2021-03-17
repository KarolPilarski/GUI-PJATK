public class Zadanie1 {
        public static void main(String args[]) {
            Person p1 = new Person("Jan", 50);
            Student s1 = new Student("Jasiek", 20);
            Person p3 = (Person) s1;
            p1.sayHelloTo(p3);
            Subject s = new Subject("GUI");
            s.setTeacher(p1);
            try {
                s.addStudent(s1);
            } catch (TooManyStudentsException e) {
                e.printStackTrace();
            }
            System.out.println(s);
        }
}

class Person{
    protected String name;
    protected int age;

    Person(String name, int age){
        this.name=name;
        this.age=age;
    }
    public void sayHelloTo(Person person){
        System.out.println("Hi "+person.name);
    }
}

class Student extends Person{
    Student(String name, int age){
        super(name,age);
    }
}

class Subject{
    protected String name;
    protected Person teacher;
    protected Student[] students = new Student[100];
    protected int studentscount=0;
    Subject(String name){
        this.name=name;
    }
    public void setTeacher(Person teacher){
        this.teacher=teacher;
    }
    public void addStudent(Student student) throws TooManyStudentsException{
        if(studentscount>=100) throw new TooManyStudentsException();
        else{
            students[studentscount]=student;
            studentscount++;
        }
    }
    protected String allStudents(){
        String wynik="";
        for(int i=0;i<studentscount;i++){
            wynik=wynik+(students[i].name+", ");
        }
        return wynik;
    }
    public String toString(){
        return (name+", teacher: "+teacher.name+", students: "+allStudents());
    }
}

class TooManyStudentsException extends Exception{
}
