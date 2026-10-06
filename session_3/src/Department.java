public class Department {
    private String departmentName;
    private Professor professor;

    public Department(String departmentName, Professor professor) {
        this.departmentName = departmentName;
        this.professor = professor;
    }

    public void displayDepartment() {
        System.out.println("Department: " + departmentName);
        professor.displayProfessor();
    }
}
