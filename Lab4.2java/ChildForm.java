import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChildForm extends JFrame {
    public ChildForm(Integer x,Integer y, Integer width, Integer height,String layout, Integer Buttons, Integer Marks) {
        super("ChildForm");
        setBounds(x,y,width,height);
        JPanel contents;
        switch (layout){
            case "GridLayout":
                setLayout(null);
                contents = new JPanel(new GridLayout());
                break;
            case "FlowLayout":
                setLayout(null);
                contents =new JPanel(new FlowLayout());
                break;
            case "GridBagLayout":
                setLayout(null);        //BorderLayout
                contents = new JPanel(new GridBagLayout());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + layout);
        }
        setContentPane(contents);
        for(Integer i=1;i<Buttons+1;i++) {
            JButton b = new JButton(i+"");
            b.setEnabled(false);
            contents.add(b);
        }
        for(Integer i=1;i<Marks+1;i++) {
            JRadioButton m = new JRadioButton(i+"");
            //b.setEnabled(false);
            contents.add(m);
        }

        setVisible(true);
    }
}
