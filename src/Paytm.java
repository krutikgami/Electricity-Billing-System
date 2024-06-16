import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paytm extends JFrame implements ActionListener {
    String meter;
    JButton back;
    Paytm(String meter) {
        this.meter = meter;

        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/linkdien_qr.png"));
            Image i2 = i1.getImage().getScaledInstance(400,500,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(200,30,400,500);
            add(image);
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html> Could not Load. </html>");
        }

        JScrollPane pane = new JScrollPane(j);
        add(pane);

        back = new JButton("Back");
        back.setBounds(640,20,100,30);
        back.addActionListener(this);
        j.add(back);

        setBounds(400,150,800,600);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new PayBill(meter);
    }
    public static void main(String[] args) {
       new Paytm("");
    }
}
