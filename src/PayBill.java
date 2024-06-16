import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class PayBill extends JFrame implements ActionListener {
    JTextField meternumber,name,units,totalbill,status;
    Choice cmonth;
    JButton pay,back;
    String meter;
    PayBill(String meter){
        this.meter = meter;
        setLayout(null);
        setBounds(300,150,900,600);

        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);

        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,15));
        lblmeter.setBounds(35,80,200,22);
        add(lblmeter);

        meternumber = new JTextField();
        meternumber.setBounds(300,80,200,20);
        meternumber.setBackground(Color.lightGray);
        meternumber.setForeground(Color.BLACK);
        meternumber.setEditable(false);
        add(meternumber);

        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma",Font.BOLD,15));
        lblname.setBounds(35,140,200,22);
        add(lblname);

        name = new JTextField();
        name.setBounds(300,140,200,20);
        name.setBackground(Color.lightGray);
        name.setForeground(Color.BLACK);
        name.setEditable(false);
        add(name);

        JLabel lblmonth = new JLabel("Name");
        lblmonth.setFont(new Font("Tahoma",Font.BOLD,15));
        lblmonth.setBounds(35,200,200,22);
        add(lblmonth);

        cmonth =new Choice();
        cmonth.setBounds(300,200,200,20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);

        JLabel lblunits = new JLabel("Units");
        lblunits.setFont(new Font("Tahoma",Font.BOLD,15));
        lblunits.setBounds(35,260,200,22);
        add(lblunits);

        units = new JTextField();
        units.setBounds(300,260,200,20);
        units.setBackground(Color.lightGray);
        units.setForeground(Color.BLACK);
        units.setEditable(false);
        add(units);

        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setFont(new Font("Tahoma",Font.BOLD,15));
        lbltotalbill.setBounds(35,320,200,22);
        add(lbltotalbill);

        totalbill = new JTextField();
        totalbill.setBounds(300,320,200,20);
        totalbill.setBackground(Color.lightGray);
        totalbill.setForeground(Color.BLACK);
        totalbill.setEditable(false);
        add(totalbill);

        JLabel lblstatus = new JLabel("Status");
        lblstatus.setFont(new Font("Tahoma",Font.BOLD,15));
        lblstatus.setBounds(35,380,200,22);
        add(lblstatus);

        status = new JTextField();
        status.setBounds(300,380,200,20);
        status.setForeground(Color.RED);
        status.setEditable(false);
        add(status);

        try{
            conn c =new conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                meternumber.setText(meter);
                name.setText(rs.getString("name"));
            }
             rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
            while(rs.next()){
                units.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {
                    conn c = new conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' AND month = '" + cmonth.getSelectedItem() + "'");
                    while (rs.next()) {
                        units.setText(rs.getString("units"));
                        totalbill.setText(rs.getString("totalbill"));
                        status.setText(rs.getString("status"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            });

        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,120,600,300);
        add(image);
       setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == pay){
            try{
                conn c = new conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
            }catch (Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        }else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new PayBill("");
    }
}
