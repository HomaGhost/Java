import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicField extends JPanel implements ActionListener {
    JTextField enterBegin;
    JTextField enterEnd;
    JTextField enterA;
    JTextField enterB;
    JTextField enterC;
    JTextField enterD;
    boolean isCreated;
    int i =1;

    GraphicField(JTextField enterBegin,
            JTextField enterEnd,
            JTextField enterA,
            JTextField enterB,
            JTextField enterC,
            JTextField enterD)
    {
        this.enterBegin = enterBegin;
        this.enterEnd = enterEnd;
        this.enterA = enterA;
        this.enterB = enterB;
        this.enterC = enterC;
        this.enterD = enterD;
        isCreated = false;
        validate();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int begin = Integer.parseInt(enterBegin.getText());
        int end = Integer.parseInt(enterEnd.getText());

        int a = Integer.parseInt(enterA.getText());
        int b = Integer.parseInt(enterB.getText());
        int c = Integer.parseInt(enterC.getText());
        int d = Integer.parseInt(enterD.getText());

        this.add(new Graphic(begin,end,a,b,c,d));
    }

}
