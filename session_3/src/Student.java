public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void meetAdvisor(Advisor advisor) {
        System.out.println(name + " is meeting with Advisor " + advisor.getName());
    }
}

