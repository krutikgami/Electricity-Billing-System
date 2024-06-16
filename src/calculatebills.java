import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

class calculatebills extends JFrame implements ActionListener {
    JTextField tfunit;
    JButton next,cancel;
    JLabel lblname1,lbladdress_la;
    Choice meterno,months;
    calculatebills(){
        setSize(700,500);
        setLocation(400,200);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(150,15,250,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);

        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(100,80,100,20);
        p.add(lblmeterno);

        meterno = new Choice();
        try{
           conn c = new conn();
            ResultSet rs = c.s.executeQuery("Select * from customer");
            while(rs.next()){
                meterno.add(rs.getString("meter_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        meterno.setBounds(240,80,200,20);
        p.add(meterno);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,120,100,20);
        p.add(lblname);

        lblname1 = new JLabel();
        lblname1.setBounds(240,120,100,20);
        p.add(lblname1);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);

        lbladdress_la = new JLabel();
        lbladdress_la.setBounds(240,160,200,20);
        p.add(lbladdress_la);


        meterno.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    conn c = new conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterno.getSelectedItem()+"'");
                    while(rs.next()){
                        lblname1.setText(rs.getString("name"));
                        lbladdress_la.setText(rs.getString("address"));
                    }
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        JLabel lblcity = new JLabel("Units Consumed");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);

        tfunit = new JTextField();
        tfunit.setBounds(240,200,200,20);
        p.add(tfunit);

        JLabel lblstate = new JLabel("Month");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);

        months =new Choice();
        months.setBounds(240,240,200,20);
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        p.add(months);

        next = new JButton("Submit");
        next.setBounds(120,350,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(250,350,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        setLayout(new BorderLayout());
        add(p,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 =  i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image =  new JLabel(i3);
        add(image,"West");
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == next){
            String meter = meterno.getSelectedItem();
            String units = tfunit.getText();
            String month = months.getSelectedItem();

            int totalbill = 0;
            int unit_consumed = Integer.parseInt(units);
            String query = "select * from tax";
            try{
                conn c =new conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()) {
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill += Integer.parseInt(rs.getString("meter_rent"));
                    totalbill += Integer.parseInt(rs.getString("service_charge"));
                    totalbill += Integer.parseInt(rs.getString("service_tax"));
                    totalbill += Integer.parseInt(rs.getString("fixed_tax"));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            String query2 = "insert into bill values('"+meter+"','"+month+"','"+units+"','"+totalbill+"','Not Paid')";
            try{
                conn c = new conn();
                if( units.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Field should not be Empty!");
                }else {
                    c.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "Customer Details Added Successfully!");
                    setVisible(false);
                }

            }catch (Exception e2){
                e2.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new calculatebills();
    }
}