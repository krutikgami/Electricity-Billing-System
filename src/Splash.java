import javax.swing.*;
import java.awt.*;

public  class Splash extends JFrame implements Runnable{
    Thread t1;
     Splash() {
         super("Electricity Billing System");
         ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/splash1.jpg"));
         Image i1 = icon.getImage().getScaledInstance(842,720,Image.SCALE_DEFAULT);
         ImageIcon i2 =new ImageIcon(i1);
         JLabel img =new JLabel(i2);
         add(img);

         setVisible(true);
         for (int i=10;i<810;i+=4) {

             setSize(i, i);
             setLocation(350, 18);
         }

         t1 = new Thread(this);
         t1.start();
         setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void run(){
        try {
            Thread.sleep(3000);
            setVisible(false);

            new Login();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Splash();

    }
}