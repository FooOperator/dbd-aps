import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    Connection db;

    public static void main(String args[]) {
        Database kj = new Database();
        User test1 = new User("Lucas Guerra Cardoso",
                "Masculino",
                (byte) 21,
                "example@gmail.com",
                "Ciência Da Computação",
                "11122233345",
                "Alunos");
        User test2;
        try {
            kj.createConnection();
            kj.post(test1);
            test2 = kj.get("Alunos", "Lucas Guerra Cardoso");
            System.out.println(test1.getName());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void createConnection() throws ClassNotFoundException, SQLException {
        String dbURL = "jdbc:mysql://127.0.0.1:3302/dbd";
        String username = "Lucas";
        String password = "jajajajajajaj1234mfg";
        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            db = conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Connection Established!");
        }
        
    }
    public void post(User entry) throws SQLException {
        String sql = String.format("INSERT INTO %s (nome, idade, email, curso, cpf, genero) VALUES (?, ?, ?, ?, ?, ?)", entry.getTable());
        
        try {
            PreparedStatement ps = db.prepareStatement(sql);
            ps.setString(1, entry.getName());
            ps.setByte(2, entry.getAge());
            ps.setString(3, entry.getEmail());
            ps.setString(4, entry.getCourse());
            ps.setString(5, entry.getCpf());
            ps.setString(6, entry.getGender());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("%s inserted", entry.getName()));
        }
    }
    public User get(String table, String name) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE nome LIKE ?", table);
        PreparedStatement ps = db.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        User user = new User(" ", "", (byte)0, " ", " ", " ", " ");
        try {
            while (rs.next()) {
                user = new User(rs.getString("nome"), rs.getString("genero"), rs.getByte("idade"), rs.getString("email"), rs.getString("curso"), rs.getString("cpf"), table);
            } 
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("%s Got!", user.getName()));
        }
        return user;
    }
    public void put(String table, String name) throws SQLException {
        String sql = String.format("UPDATE %s SET", table);
    }
    public void delete(String table, String name) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE nome LIKE ?", table);
        try {
            PreparedStatement ps = db.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("%s deleted", name));
        }
        
    }
    public void entriesSort(String table, String gender) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE genero LIKE ?", table);
        PreparedStatement ps = db.prepareStatement(sql);
        ps.setString(1, gender);
    }
}
