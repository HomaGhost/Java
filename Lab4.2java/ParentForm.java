import javax.swing.*;

public class ParentForm extends JFrame {
    public ParentForm() {
        super("lab4task2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(1200,636,210,350);

        JLabel xLabel = new JLabel("Enter x");
        xLabel.setBounds(10,0,70,50);
        add(xLabel);

        JTextField inX = new JTextField("636");
        inX.setBounds(10,40,70,20);
        add(inX);

        JLabel yLabel = new JLabel("Enter y");
        yLabel.setBounds(100,0,70,50);
        add(yLabel);

        JTextField inY = new JTextField("636");
        inY.setBounds(100,40,70,20);
        add(inY);

        JLabel widLabel = new JLabel("Enter width");
        widLabel.setBounds(10,50,250,50);
        add(widLabel);

        JTextField inWidth = new JTextField("400");
        inWidth.setBounds(10,90,70,20);
        add(inWidth);

        JLabel heiLabel = new JLabel("Enter height");
        heiLabel.setBounds(100,50,250,50);
        add(heiLabel);

        JTextField inHeight = new JTextField("350");
        inHeight.setBounds(100,90,70,20);
        add(inHeight);

        String[] layouts = new String[] {"Choose layout...","GridLayout","GridBagLayout","FlowLayout"};
        JComboBox<String> layoutChooser = new JComboBox<>(layouts);
        layoutChooser.setBounds(10,120,160,20);
        add(layoutChooser);

        JLabel buttonLabel = new JLabel("Enter amount of buttons");
        buttonLabel.setBounds(10,130,160,50);
        add(buttonLabel);

        JTextField Buttons = new JTextField("2");
        Buttons.setBounds(10,170,160,20);
        add(Buttons);

        JLabel markLabel = new JLabel("Enter amount of marks");
        markLabel.setBounds(10,180,160,50);
        add(markLabel);

        JTextField Marks = new JTextField("3");
        Marks.setBounds(10,220,160,20);
        add(Marks);

        JButton createChild = new JButton("Creat child form");
        createChild.setBounds(10,250,160,30);
        createChild.addActionListener(new ChildButtonHolder(inX,inY,inWidth,inHeight,layoutChooser,Buttons,Marks));
        add(createChild);

        setVisible(true);
    }
}
