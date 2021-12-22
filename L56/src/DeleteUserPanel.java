import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

class DeleteUserPanel extends JFrame {
    public static JButton authButton;
    public static JTextField loginText, passwordText;
    public DeleteUserPanel() {
        super("Удалить пользователя");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 500, 500);
        setLayout(null);

        JLabel loginLabel = new JLabel("Номер");
        loginLabel.setBounds(10, 10, 100, 30);
        add(loginLabel);

        JTextField loginText = new JTextField();
        loginText.setBounds(110, 10, 300, 30);
        add(loginText);

        JButton authButton = new JButton("Удалить");
        authButton.setBounds(125, 430, 250, 30);
        authButton.addActionListener(new DeleteUser(loginText));
        add(authButton);

        validate();
        setVisible(true);
    }
}

class DeleteUser implements ActionListener {
    JTextField indexText;
    int index;
    public DeleteUser (JTextField indexText) {
        this.indexText = indexText;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        List entities;
        index = Integer.parseInt(indexText.getText());
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\users.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List)ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }
        if(index <= entities.size()) {
            entities.remove(index - 1);
            try {
                OutputStream os = new FileOutputStream("C:\\Users\\Admin\\L56\\src\\users.bin");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(entities);
                oos.close();
            } catch (IOException e) {
                System.out.println("Невозможно сохранить файл");
            }
            MyForm.prev_frame.dispose();
  //          UsersPanel._users_panel = new UsersPanel();
        } else
            JOptionPane.showMessageDialog(null, "Данная позиция не найдена");
    }
}
