import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

class AddUserPanel extends JFrame {
    public static JButton authButton;
    public static JTextField loginText, passwordText;
    public AddUserPanel() {
        super("Добавить пользователя");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 500, 500);
        setLayout(null);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(10, 10, 100, 30);
        add(loginLabel);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 100, 30);
        add(passwordLabel);
        JLabel roleLabel = new JLabel("Role");
        roleLabel.setBounds(10, 90, 100, 30);
        add(roleLabel);

        JTextField loginText = new JTextField();
        loginText.setBounds(110, 10, 300, 30);
        add(loginText);
        JTextField passwordText = new JTextField();
        passwordText.setBounds(110, 50, 300, 30);
        add(passwordText);

        JTextField roleText = new JTextField();
        roleText.setBounds(110, 90, 300, 30);
        add(roleText);


        JButton authButton = new JButton("Добавить");
        authButton.setBounds(125, 430, 250, 30);
        authButton.addActionListener(new AddUser(loginText, passwordText, roleText));
        add(authButton);

        validate();
        setVisible(true);
    }
}

class AddUser implements ActionListener {
    JTextField loginText, passwordText, roleText;
    public AddUser (JTextField loginText, JTextField passwordText, JTextField roleText) {
        this.loginText = loginText;
        this.passwordText = passwordText;
        this.roleText = roleText;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        List entities;
        boolean user_exist = false;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\users.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List)ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }
        for(int i = 0; i < entities.size(); ++i){
            UserInfo user = (UserInfo)entities.get(i);
            if(loginText.getText().equals(user.GetLogin()) && passwordText.getText().equals(user.GetPassword())) {
                user_exist = true;
                break;
            }
        }
        if(!user_exist) {
            if (roleText.getText().equals("admin") || roleText.getText().equals("manager") || roleText.getText().equals("user")) {
                UserInfo new_user = new UserInfo(loginText.getText(), passwordText.getText(), roleText.getText());
                entities.add(new_user);
                try {
                    OutputStream os = new FileOutputStream("C:\\Users\\Admin\\L56\\src\\users.bin");
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(entities);
                    oos.close();
                } catch (IOException e) {
                    System.out.println("Невозможно сохранить файл");
                }
                UsersPanel._users_panel.dispose();
                UsersPanel._users_panel = new UsersPanel();
            } else
                JOptionPane.showMessageDialog(null, "Данная роль не найдена");
        }else
            JOptionPane.showMessageDialog(null, "Пользователь уже есть");
    }
}
