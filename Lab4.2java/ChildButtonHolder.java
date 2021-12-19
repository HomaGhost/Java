import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChildButtonHolder implements ActionListener {
    private JTextField X;
    private JTextField Y;
    private JTextField Width;
    private JTextField Height;
    private JComboBox<String> Layout;
    private JTextField Buttons;
    private JTextField Marks;

    public ChildButtonHolder(JTextField inX, JTextField inY, JTextField inWidth, JTextField inHeight,
            JComboBox layoutChooser, JTextField Buttons, JTextField Marks) {
        this.X = inX;
        this.Y = inY;
        this.Width = inWidth;
        this.Height = inHeight;
        this.Layout = layoutChooser;
        this.Buttons = Buttons;
        this.Marks = Marks;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            new ChildForm(Integer.parseInt(X.getText()),Integer.parseInt(Y.getText()),
                    Integer.parseInt(Width.getText()),Integer.parseInt(Height.getText()),
                    Layout.getSelectedItem().toString(),Integer.parseInt(Buttons.getText()),
                    Integer.parseInt(Marks.getText()));
        }  catch (Exception exception) {
            JOptionPane.showMessageDialog(null,"Error: WRONG DATA");
        }
    }
}
