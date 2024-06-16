import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class BillDetails extends JFrame {
    String meter;
    BillDetails(String meter){
        this.meter = meter;
        setBounds(400,100,700,650);
        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        try{
            conn c = new conn();
            String query = "select * from bill where meter_no = '"+meter+"'";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0,0,700,650);
        add(pane);

        setVisible(true);
    }
    public static void main(String[] args) {
        new BillDetails("");
    }
}
