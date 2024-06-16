import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection c;
    Statement s;
    conn()  {
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_signup","root","root");
            s = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new conn();
    }
}