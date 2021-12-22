import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

import java.time.Duration;
import java.time.LocalTime;

class AuthPanel extends JFrame {
    public static JButton authButton;
    public static JTextField loginText, passwordText;
    @SuppressWarnings("unchecked")
    public AuthPanel() {
        super("Laba 4 Ex 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 250, 500, 500);
        setLayout(null);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(10, 10, 100, 30);
        add(loginLabel);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 100, 30);
        add(passwordLabel);

        loginText = new JTextField();
        loginText.setBounds(110, 10, 300, 30);
        add(loginText);
        passwordText = new JTextField();
        passwordText.setBounds(110, 50, 300, 30);
        add(passwordText);


        authButton = new JButton("Авторизация");
        authButton.setBounds(125, 430, 250, 30);
        authButton.addActionListener(new AuthCompleted(loginText, passwordText));
        add(authButton);

        validate();
        setVisible(true);
    }
}

class AuthCompleted implements ActionListener {
    JTextField loginText, passwordText;
    List<String> users = new ArrayList<String>();
    public AuthCompleted(JTextField loginText, JTextField passwordText) {
        this.loginText = loginText;
        this.passwordText = passwordText;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        UserInfo user_info = new UserInfo(loginText.getText(), passwordText.getText());
        List entities;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\users.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List)ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
            UserInfo admin = new UserInfo("admin","admin", "admin");
            UserInfo manager = new UserInfo("manager","manager", "manager");
            UserInfo user = new UserInfo("user", "user", "user");
            entities.add(admin);
            entities.add(manager);
            entities.add(user);
        }
        //JOptionPane.showMessageDialog(null, entities.size());
        for(int i = 0; i < entities.size(); ++i) {
            UserInfo user = (UserInfo) entities.get(i);
            if (user_info.GetLogin().equals(user.GetLogin()) && user_info.GetPassword().equals(user.GetPassword())) {
                MyForm.curr_role = user.GetRole();
                break;
            }
        }

        try {
            OutputStream os = new FileOutputStream("C:\\Users\\Admin\\L56\\src\\users.bin");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(entities);
            oos.close();
        } catch(IOException e) {
            System.out.println("Невозможно сохранить файл");
        }
        if(MyForm.curr_role != "unknown") {
            MyForm.extra_frame.dispose();
            MyForm.prev_frame = new MyForm();
            MyForm.exitButton.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Вы - " + MyForm.curr_role);
        }
        else
            JOptionPane.showMessageDialog(null, "Данная роль не найдена");
    }
}

