package objects;

public class StudentSummary {
    public int studentId;
    public String studentFio;
    public String studentSex;
    public String groupName;
    public String curatorName;

    public StudentSummary(int studentId, String studentFio, String studentSex, String groupName, String curatorName) {
        this.studentId = studentId;
        this.studentFio = studentFio;
        this.studentSex = studentSex;
        this.groupName = groupName;
        this.curatorName = curatorName;
    }

    @Override
    public String toString() {
        return "StudentSummary{" +
                "id=" + studentId +
                ", fio='" + studentFio + '\'' +
                ", sex='" + studentSex + '\'' +
                ", group='" + groupName + '\'' +
                ", curator='" + curatorName + '\'' +
                '}';
    }
}