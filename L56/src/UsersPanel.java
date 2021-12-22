import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

class UsersPanel extends JFrame {//admin
    public static JButton authButton;
    public static JTextField loginText, passwordText;
    public static JFrame _users_panel;
    public UsersPanel() {
        super("Пользователи");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 250, 500, 500);
        setLayout(null);

        List entities;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\users.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List)ois.readObject();
            //JOptionPane.showMessageDialog(null, "Вы - " + entities.size());
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }
        PersonTableModel model = new PersonTableModel(entities);
        JTable table = new JTable(model);
        // vv Сортировка таблицы по строкам vv
        RowSorter<PersonTableModel> sorter = new TableRowSorter<PersonTableModel>(model);
        table.setRowSorter(sorter);
        // ^^ Сортировка таблицы по строкам^^
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 500, 400);
        add(scrollPane, BorderLayout.CENTER);

        JButton addUserButton = new JButton("Добавить");
        addUserButton.setBounds(0, 400, 150, 50);
        addUserButton.addActionListener(new OpenPanel("add"));
        add(addUserButton);

        JButton changeUserButton = new JButton("Изменить");
        changeUserButton.setBounds(150, 400, 150, 50);
        changeUserButton.addActionListener(new OpenPanel("change"));
        add(changeUserButton);

        JButton deleteUserButton = new JButton("Удалить");
        deleteUserButton.setBounds(300, 400, 150, 50);
        deleteUserButton.addActionListener(new OpenPanel("delete"));
        add(deleteUserButton);

        JButton backButton = new JButton("<-");
        backButton.setBounds(450, 400, 50, 50);
        backButton.addActionListener(new Back());
        add(backButton);

        validate();
        setVisible(true);
    }
}