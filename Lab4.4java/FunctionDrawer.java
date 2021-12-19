import javax.swing.*;
import java.awt.event.ActionListener;

// a*ln(b*x*x+1)+c*e^(1-d*x*x)

public class FunctionDrawer extends JFrame {
    FunctionDrawer(){
        super("FunctionDrawer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        setBounds(100,100,1200,700);

        JTextField enterBegin = new JTextField("Begin");
        enterBegin.setBounds(10,10,150,40);
        add(enterBegin);

        JTextField enterEnd = new JTextField("End");
        enterEnd.setBounds(10,60,150,40);
        add(enterEnd);

        JTextField enterA = new JTextField("A");
        enterA.setBounds(10,110,150,40);
        add(enterA);

        JTextField enterB = new JTextField("B");
        enterB.setBounds(10,160,150,40);
        add(enterB);

        JTextField enterC = new JTextField("C");
        enterC.setBounds(10,210,150,40);
        add(enterC);

        JTextField enterD = new JTextField("D");
        enterD.setBounds(10,260,150,40);
        add(enterD);

        JButton button = new JButton("Draw");
        button.setBounds(10,310,150,40);
        add(button);

        GraphicField graphicField = new GraphicField(enterBegin,enterEnd,enterA,enterB,enterC,enterD);
        button.addActionListener(graphicField);
        graphicField.setBounds(180,10,1000,650);
        add(graphicField);

        setVisible(true);
    }
}
