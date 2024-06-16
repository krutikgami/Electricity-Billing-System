import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Login extends JFrame implements ActionListener{
    JButton login1,cancel,csignup,aadmin;
    Choice login;
    JTextField usr,password;
    Login(){
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setSize(650,321);
        setLocation(400,200);

        setLayout(null);

        JLabel lusr = new JLabel("Username");
        lusr.setBounds(300,30,100,20);
        add(lusr);

         usr = new JTextField();
        usr.setBounds(405,30, 150,20);
        add(usr);

        JLabel lpass = new JLabel("Password");
        lpass.setBounds(300,70,100,20);
        add(lpass);

         password = new JTextField();
        password.setBounds(405,70,150,20);
        add(password);

        JLabel loginas = new JLabel("Login as");
        loginas.setBounds(300,110,100,20);
        add(loginas);

        login = new Choice();
        login.add("Admin");
        login.add("Customer");
        login.setBounds(405,110,150,20);
        add(login);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(15,15,Image.SCALE_DEFAULT);
         login1 = new JButton("Login",new ImageIcon(i2));
        login1.setBounds(325,170,120,20);
        login1.addActionListener(this);
        add(login1);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(15,15,Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(455,170,120,20);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(17,17,Image.SCALE_DEFAULT);
        csignup = new JButton("CSignup", new ImageIcon(i6));
        csignup.setBounds(325,210,120,20);
        csignup.addActionListener(this);
        add(csignup);

        ImageIcon i9 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i10 = i9.getImage().getScaledInstance(17,17,Image.SCALE_DEFAULT);
        aadmin = new JButton("ASignup", new ImageIcon(i10));
        aadmin.setBounds(455,210,120,20);
        aadmin.addActionListener(this);
        add(aadmin);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second1.jpg.png"));
        Image i8 = i7.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(i8);
        JLabel image = new JLabel(img);
        image.setBounds(50,25,200,200);
        add(image);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String susername = usr.getText();
        String spassword = password.getText();
        String user = login.getSelectedItem();
        String cred = "Admin";
        if (e.getSource() == login1){
            try{
                conn c = new conn();
                String query = "Select * from signup where username = '"+susername+"' and password_u = '"+spassword+"' and user_u = '"+user+"'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()){
                    if (susername.equals(rs.getString("username")) && spassword.equals(rs.getString("password_u")) && user.equals(rs.getString("user_u"))) {
                        String meter = rs.getString("meter_no");
                        System.out.println("validate user successfully!!");
                        setVisible(false);
                        new Project(user, meter);
                    }
                    else if(susername.isEmpty() || spassword.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Field value is empty!");
                    }
                    else {
                        System.out.println("Invalid login!!");
                        JOptionPane.showMessageDialog(null,"Invalid Login!!");
                        usr.setText("");
                        password.setText("");
                    }
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        else if (e.getSource() == cancel){
            setVisible(false);
        }
        else if(e.getSource() == csignup){
            setVisible(false);
            new signup();
        } else if (e.getSource() == aadmin) {
            try {
                conn c = new conn();
                String query = "Select * from signup where username = '"+susername+"' and password_u = '"+spassword+"' and user_u = '"+user+"'";
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()){
                    if (susername.equals(rs.getString("username")) && spassword.equals(rs.getString("password_u")) && user.equals(rs.getString("user_u")) && cred.equals(rs.getString("user_u"))) {
                        System.out.println("validate user successfully!!");
                        setVisible(false);
                        new Asignup();
                    } else {
                        System.out.println("Invalid login!!");
                        JOptionPane.showMessageDialog(null,"Invalid Login!!");
                        usr.setText("");
                        password.setText("");
                    }
                }else {
                    System.out.println("Invalid login!!");
                    JOptionPane.showMessageDialog(null,"Invalid Login!!");
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new Login();
    }
}