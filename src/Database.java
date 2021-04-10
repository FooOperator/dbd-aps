import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    Connection db;
    static final String aluno = "Alunos";
    static final String prof = "Professores";
    static final String nome = "Lucas Guerra Cardoso";
    static Database kj;

    public static void main(String[] args) {
        kj = new Database();
        User here;
        User postTest = new User(nome, "Masculino", (byte) 21, "example@gmail.com", "Ciência Da Computação", "11122233345", aluno);
        User putTest = new User("Luska Guelra", "Feminino", (byte) 21, "example@gmail.com", "Ciência Da Computação", "11122233345", aluno);

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
    public void put(User updated, String target) throws SQLException {
        String sql = String.format("UPDATE %s SET nome = ?, idade = ?, email = ?, curso = ?, cpf = ?, genero = ? WHERE nome = ?", updated.getTable());

        try {
            PreparedStatement ps = db.prepareStatement(sql);

            ps.setString(1, updated.getName());
            ps.setByte(2, updated.getAge());
            ps.setString(3, updated.getEmail());
            ps.setString(4, updated.getCourse());
            ps.setString(5, updated.getCpf());
            ps.setString(6, updated.getGender());
            ps.setString(7, target);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("%s updated!", target));
        }
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
