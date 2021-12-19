import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {
    public Form(){
        super("BrandLOGO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,1300,300);
        validate();
        setVisible(true);
        setContentPane(new MyPanel());
    }


}
