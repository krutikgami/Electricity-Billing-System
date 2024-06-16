import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Asignup extends JFrame implements ActionListener {
    JButton create,login;
    Choice accounttype;
    JTextField userfield,name,password;
    Asignup(){
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),
                "Create Account",
                TitledBorder.LEADING,
                TitledBorder.TOP));
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setForeground(new Color(34,139,34));
        add(panel);

        JLabel heading = new JLabel("Create Account");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(heading);

        accounttype = new Choice();
        accounttype.add("Admin");
        accounttype.setBounds(260,50,150,20);
        panel.add(accounttype);

        JLabel username = new JLabel("Username");
        username.setBounds(100,90,140,20);
        username.setForeground(Color.GRAY);
        username.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(username);

        userfield = new JTextField();
        userfield.setBounds(260,90,150,20);
        panel.add(userfield);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,130,140,20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblname);

        name = new JTextField();
        name.setBounds(260,130,150,20);
        panel.add(name);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100,170,140,20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblpassword);

        password = new JTextField();
        password.setBounds(260,170,150,20);
        panel.add(password);

        create = new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140,210,120,30);
        create.addActionListener(this);
        panel.add(create);

        login = new JButton("Back");
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setBounds(290,210,120,30);
        login.addActionListener(this );
        panel.add(login);

        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410,30,250,250);
        panel.add(image);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == login){
            new Login();
            setVisible(false);
        } else if (e.getSource() == create) {
            String atype = accounttype.getSelectedItem();
            String user = userfield.getText();
            String name1 = name.getText();
            String pass = password.getText();

            try {
                conn c = new conn();
                String query;
                    if(user.isEmpty() || name1.isEmpty() || pass.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter the field value!!");
                    }else {
                        if (pass.length()<6){
                            JOptionPane.showMessageDialog(null,"Password length must greater than or equal to 6.");
                        }else {
                            query = "insert into signup values('','" + user + "','" + name1 + "','" + pass + "','" + atype + "')";

                            c.s.executeUpdate(query);

                            JOptionPane.showMessageDialog(null, "Account created Succesfully");

                            setVisible(false);
                            new Login();
                        }
                    }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new Asignup();
    }
}