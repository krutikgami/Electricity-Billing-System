import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewInformation extends JFrame implements ActionListener {
JButton cancel;
    ViewInformation(String meter){
        setBounds(150,150,850,650);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70,80,100,20);
        lblname.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblname);

        JTextField name = new JTextField();
        name.setBounds(230,80,130,25);
        name.setBackground(Color.lightGray);
        name.setForeground(Color.BLACK);
        name.setEditable(false);
        name.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(70,140,110,20);
        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblmeternumber);

        JTextField meternumber = new JTextField();
        meternumber.setBounds(230,140,130,25);
        meternumber.setBackground(Color.lightGray);
        meternumber.setForeground(Color.BLACK);
        meternumber.setEditable(false);
        meternumber.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(meternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70,200,100,20);
        lbladdress.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lbladdress);

        JTextField address = new JTextField();
        address.setBounds(230,200,130,25);
        address.setBackground(Color.lightGray);
        address.setForeground(Color.BLACK);
        address.setEditable(false);
        address.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(address);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70,260,100,20);
        lblcity.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblcity);

        JTextField city = new JTextField();
        city.setBounds(230,260,130,25);
        city.setBackground(Color.lightGray);
        city.setForeground(Color.BLACK);
        city.setEditable(false);
        city.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(city);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500,80,100,20);
        lblstate.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblstate);

        JTextField state = new JTextField();
        state.setBounds(630,80,130,25);
        state.setBackground(Color.lightGray);
        state.setForeground(Color.BLACK);
        state.setEditable(false);
        state.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(state);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500,140,100,20);
        lblemail.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblemail);

        JTextField email = new JTextField();
        email.setBounds(630,140,130,25);
        email.setBackground(Color.lightGray);
        email.setForeground(Color.BLACK);
        email.setEditable(false);
        email.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(email);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500,200,100,20);
        lblphone.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblphone);

        JTextField phone = new JTextField();
        phone.setBounds(630,200,130,25);
        phone.setBackground(Color.lightGray);
        phone.setForeground(Color.BLACK);
        phone.setEditable(false);
        phone.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(phone);

        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while (rs.next()){
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350,340,100,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20,350,600,300);
        add(image);

        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        setVisible(false);
    }
    public static void main(String[] args) {
        new ViewInformation("");
    }
}
