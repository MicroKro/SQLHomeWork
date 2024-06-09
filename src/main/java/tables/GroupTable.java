package tables;
import db.MySQLConnector;
import objects.Group;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupTable extends BaseTable {
    public GroupTable() {
        super("`Group`");
        columns = new HashMap<>();
        columns.put("id","int PRIMARY KEY AUTO_INCREMENT");
        columns.put("name","varchar(50)");
        columns.put("id_curator", "int");
        create();
    }

    public GroupTable(String tableName) {
        super(tableName);
    }

    public Group selectById(int searchId){
        String sqlQuery=String.format("SELECT * FROM %s WHERE id=%d",tableName, searchId);
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);

        Group group = null;
        try {
            while (rs.next()) {
                group = new Group(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("id_curator")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return group;
    }

    public ArrayList<Group> selectAll(){
        String sqlQuery=String.format("SELECT * FROM %s",tableName);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Group> selectByName(String name){
        String sqlQuery = String.format("SELECT * FROM %s WHERE name = '%s'",
                tableName, name);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Group> selectByCurator(int id_curator){
        String sqlQuery = String.format("SELECT * FROM %s WHERE id_curator = '%d'",
                tableName, id_curator);
        return selectByQuery(sqlQuery);
    }

    private static ArrayList<Group> selectByQuery(String sqlQuery){
        ArrayList<Group> group = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                group.add(new Group(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("id_curator")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return group;
    }

    public void insert(Group group){
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (name, id_curator) " +
                        "VALUES ('%s', '%d')",
                tableName, group.getName(), group.getId_curator());
        db.executeRequest(sqlQuery);
        db.close();
    }

    public void update(Group group){
        db = new MySQLConnector();
        String sqlQuery = String.format("UPDATE %s SET " +
                        "name='%s', id_curator='%d' WHERE id = %d ",
                tableName,
                group.getName(),
                group.getId_curator(),
                group.getId());
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
