import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

class CustomerDetails extends JFrame implements ActionListener {
    Choice meternumber,cmonth;
    JTable table;
    JButton search,print;
    CustomerDetails(){
        super("Customer Details");
        setSize(1200,650);
        setLocation(200,150);

        table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);

        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        add(sp);

        print = new JButton("Print");
        print.addActionListener(this);
        add(print,"South");

        setVisible(true);
    }
    public  void actionPerformed(ActionEvent e){
            try{
                table.print();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    public static void main(String[] args) {
        new CustomerDetails();
    }
}