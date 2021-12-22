import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

class ChangeProjectPanel extends JFrame {
    public static JButton authButton;
    public static int change_index;
    public ChangeProjectPanel() {
        super("Удалить пользователя");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 500, 500);
        setLayout(null);

        JLabel numLabel = new JLabel("Номер");
        numLabel.setBounds(10, 10, 100, 30);
        add(numLabel);

        JTextField numText = new JTextField();
        numText.setBounds(110, 10, 300, 30);
        add(numText);

        JButton authButton = new JButton("Изменить");
        authButton.setBounds(125, 430, 250, 30);
        authButton.addActionListener(new ChangeProject(numText));
        add(authButton);

        validate();
        setVisible(true);
    }
}

class ChangeProject implements ActionListener {
    JTextField indexText;
    int index;
    public ChangeProject (JTextField indexText) {
        this.indexText = indexText;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        List entities;
        index = Integer.parseInt(indexText.getText());
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List)ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }
        if(index <= entities.size()) {
            MyForm.prev_frame.dispose();
            MyForm.prev_frame = new AddProjectPanel();
            ProjectsPanel.changing = true;
            ChangeProjectPanel.change_index = index;
        } else
                JOptionPane.showMessageDialog(null, "Данная позиция не найдена");

    }
}
