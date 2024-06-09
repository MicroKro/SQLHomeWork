package tables;
import db.MySQLConnector;
import objects.Student;
import objects.StudentSummary;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentTable extends BaseTable {
    public StudentTable(){
        super("Student");
        columns = new HashMap<>();
        columns.put("id","int PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio","varchar(50)");
        columns.put("sex","varchar(10)");
        columns.put("id_group", "int");
        create();
    }

    public StudentTable(String tableName) {
        super(tableName);
    }

    public ArrayList<Student> selectAll(){
        String sqlQuery=String.format("SELECT * FROM %s",tableName);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Student> selectByFio(String fio){
        String sqlQuery = String.format("SELECT * FROM %s WHERE fio = '%s'",
                tableName, fio);

        System.out.println(sqlQuery);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Student> selectBySex(String sex){
        String sqlQuery = String.format("SELECT * FROM %s WHERE sex = '%s'",
                tableName, sex);

        System.out.println(sqlQuery);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Student> selectByGroup(int id_group){
        String sqlQuery = String.format("SELECT * FROM %s WHERE id_group = '%d'",
                tableName, id_group);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<StudentSummary> innerAllData(){
        String sqlQuery = String.format("SELECT st.id as studentId, st.fio, st.sex, gr.name AS groupName, cur.fio AS curatorName " +
                        "FROM Student AS st " +
                        "INNER JOIN `Group` AS gr ON st.id_group = gr.id " +
                        "INNER JOIN Curator AS cur ON gr.id_curator = cur.id",
                tableName);

        ArrayList<StudentSummary> student = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                student.add(new StudentSummary(
                        rs.getInt("studentId"),
                        rs.getString("fio"),
                        rs.getString("sex"),
                        rs.getString("groupName"),
                        rs.getString("curatorName")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return student;
    }

    public ArrayList<Student> attachedGroup(){
        String sqlQuery = String.format("SELECT id, fio, sex, id_group FROM Student WHERE id_group IN (SELECT id FROM `Group` WHERE name = \"Вторая\")"
        );

        ArrayList<Student> student = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                student.add(new Student(
                        rs.getInt("Id"),
                        rs.getString("fio"),
                        rs.getString("sex"),
                        rs.getInt("id_group")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return student;
    }

    private ArrayList<Student> selectByQuery(String sqlQuery){
        ArrayList<Student> student = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                student.add(new Student(
                    rs.getLong("id"),
                    rs.getString("fio"),
                    rs.getString("sex"),
                    rs.getInt("id_group")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return student;
    }

    public void insert(Student student){
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (fio, sex, id_group) " +
                        "VALUES ('%s', '%s', '%d')",
                tableName, student.getFio(), student.getSex(),
                student.getId_group());
        db.executeRequest(sqlQuery);
        db.close();
    }

    public void update(Student student){
        db = new MySQLConnector();
        String sqlQuery = String.format("UPDATE %s SET " +
                        "fio='%s', sex='%s', id_group='%d' WHERE id = %d ",
                tableName,
                student.getFio(),
                student.getSex(),
                student.getId_group(),
                student.getId());
        db.executeRequest(sqlQuery);
        db.close();
    }

    public void delete(long id){
        db = new MySQLConnector();
        String sqlQuery = String.format("DELETE FROM %s WHERE id='%d'",
                tableName, id
        );
        db.executeRequest(sqlQuery);
        db.close();
    }
}
