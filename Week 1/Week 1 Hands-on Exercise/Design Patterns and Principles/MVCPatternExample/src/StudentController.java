// StudentController.java
public class StudentController {
    private Student student;
    private StudentView studentView;

    public StudentController(Student student, StudentView studentView) {
        this.student = student;
        this.studentView = studentView;
    }

    public String getStudentName() {
        return student.getName();
    }

    public void setStudentName(String name) {
        student.setName(name);
    }

    public String getStudentId() {
        return student.getId();
    }

    public void setStudentId(String id) {
        student.setId(id);
    }

    public String getStudentGrade() {
        return student.getGrade();
    }

    public void setStudentGrade(String grade) {
        student.setGrade(grade);
    }

    public void updateView() {
        studentView.displayStudentDetails(student.getName(), student.getId(), student.getGrade());
    }
}
