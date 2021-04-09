public class User {
    private String name;
    private Byte age;
    private String email;
    private String course;
    private String cpf;
    private String table;

    public User(String name, Byte age, String email, String course, String cpf, String table) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
        this.cpf = cpf;
        this.table = table;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getAge() {
        return this.age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCourse() {
        return this.course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTable() {
        return this.table;
    }
    
    public void setTable(String table) {
        this.table = table;
    }
}

