import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {
    JTextField name,meternumber,city,state,email,phone,address;
    JButton update,cancel;
    String meter;
    UpdateInformation(String meter){
        this.meter = meter;
        setBounds(300,150,1100,450);
        setLocation(220,200);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);



         JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(110,0,400,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30,70,100,20);
        lblname.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblname);

        name = new JTextField();
        name.setBounds(160,70,180,20);
        name.setForeground(Color.BLACK);
        name.setBackground(Color.lightGray);
        name.setEditable(false);
        name.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(name);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(30,140,110,20);
        lblmeternumber.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblmeternumber);

        meternumber = new JTextField();
        meternumber.setBounds(160,140,180,20);
        meternumber.setForeground(Color.BLACK);
        meternumber.setBackground(Color.lightGray);
        meternumber.setEditable(false);
        meternumber.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(meternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30,210,100,20);
        lbladdress.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lbladdress);

        address = new JTextField();
        address.setBounds(160,210,180,20);
        address.setForeground(Color.BLACK);
        address.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(address);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30,280,100,20);
        lblcity.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblcity);

        city = new JTextField();
        city.setBounds(160,280,180,20);
        city.setForeground(Color.BLACK);
        city.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(city);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(390,70,100,20);
        lblstate.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblstate);

        state = new JTextField();
        state.setBounds(480,70,180,20);
        state.setForeground(Color.BLACK);
        state.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(state);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(390,140,100,20);
        lblemail.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblemail);

        email = new JTextField();
        email.setBounds(480,140,180,20);
        email.setForeground(Color.BLACK);
        email.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(email);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(390,210,100,20);
        lblphone.setFont(new Font("Tahoma",Font.BOLD,15));
        add(lblphone);

        phone = new JTextField();
        phone.setBounds(480,210,180,20);
        phone.setForeground(Color.BLACK);
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

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(250,340,100,25);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(400,340,100,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(350,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(700,50,350,300);
        add(image);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == update){
            String address_u = address.getText();
            String city_u = city.getText();
            String state_u = state.getText();
            String email_u = email.getText();
            String phone_u = phone.getText();
            try{
                conn c =  new conn();
                if (address_u.isEmpty() || city_u.isEmpty() || state_u.isEmpty() || email_u.isEmpty() || phone_u.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Field is empty!");
                }else {
                    c.s.executeUpdate("update customer set address ='" + address_u + "',city ='" + city_u + "', state ='" + state_u + "', email ='" + email_u + "', phone ='" + phone_u + "' where meter_no = '" + meter + "'");
                    JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new UpdateInformation("");
    }
}
