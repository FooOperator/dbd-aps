import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection conn;

    public void createConnection() throws ClassNotFoundException, SQLException {
        String dbURL = "jdbc:mysql://127.0.0.1:3302/dbd";
        String username = "Lucas";
        String password = "jajajajajajaj1234mfg";
        try {
            this.conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Connection Established!");
        }
        
    }
    public void post(User entry) throws SQLException {
        String sql = String.format("INSERT INTO %s (nome, idade, email, curso, cpf, genero) VALUES (?, ?, ?, ?, ?, ?)", entry.getTable());

        try {
            try {
                createConnection();
            } catch (ClassNotFoundException e) {
                System.out.println("Couldn't connect");
            }
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, entry.getName());
            ps.setInt(2, entry.getAge());
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
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        User user = new User(" ", "", 0, " ", " ", " ", " ");
        try {
            try {
                createConnection();
            } catch (ClassNotFoundException e) {
                System.out.println("Couldn't connect");
            }
            while (rs.next()) {
                user = new User(rs.getString("nome"), rs.getString("genero"), rs.getInt("idade"), rs.getString("email"), rs.getString("curso"), rs.getString("cpf"), table);
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
            try {
                createConnection();
            } catch (ClassNotFoundException e) {
                System.out.println("Couldn't connect");
            }
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, updated.getName());
            ps.setInt(2, updated.getAge());
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
            try {
                createConnection();
            } catch (ClassNotFoundException e) {
                System.out.println("Couldn't connect");
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("%s deleted", name));
        }

    }
    public ArrayList<String> entries(String table) throws SQLException {
        ArrayList<String> query = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s", table);

        try {
            try {
                createConnection();
            } catch (ClassNotFoundException e) {
                System.out.println("Couldn't connect");
            }

            Statement ps = conn.createStatement();

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
               query.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(query);
        }
        return query;
    }
}
