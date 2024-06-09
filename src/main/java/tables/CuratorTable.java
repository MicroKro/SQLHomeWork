package tables;
import db.MySQLConnector;
import objects.Curator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CuratorTable extends BaseTable {
    public CuratorTable() {
        super("Curator");
        columns = new HashMap<>();
        columns.put("id","int PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio","varchar(50)");
        create();
    }

    public CuratorTable(String tableName) {
        super(tableName);
    }

    public ArrayList<Curator> selectAll(){
        String sqlQuery=String.format("SELECT * FROM %s",tableName);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Curator> selectByName(String fio){
        String sqlQuery = String.format("SELECT * FROM %s WHERE fio = '%s'",
                tableName, fio);
        return selectByQuery(sqlQuery);
    }

    private static ArrayList<Curator> selectByQuery(String sqlQuery){
        ArrayList<Curator> curator = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                curator.add(new Curator(
                        rs.getLong("id"),
                        rs.getString("fio")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return curator;
    }

    public void insert(Curator curator){
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (fio)" +
                        "VALUES ('%s')",
                tableName, curator.getFio());
        db.executeRequest(sqlQuery);
        db.close();
    }

    public void update(Curator curator){
        db = new MySQLConnector();
        String sqlQuery = String.format("UPDATE %s SET " +
                        "fio='%s' WHERE id = %d ",
                tableName,
                curator.getFio(),
                curator.getId());
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
