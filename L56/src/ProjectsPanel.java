import javax.swing.*;
import java.awt.*;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.table.TableRowSorter;

class ProjectsPanel extends JFrame {
    public static JButton authButton;
    public static JTextField loginText, passwordText;
    public static JFrame _projects_panel;
    public static boolean changing;
    public ProjectsPanel() {
        super("Проекты");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 1000, 500);
        setLayout(null);
        AddProgrammerToProject._adding_to_project = false;
        changing = false;
        List entities;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List)ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
            //JOptionPane.showMessageDialog(null, entities.size());
        }
        //ProjectInfo pr = (ProjectInfo)entities.get(0);
        //JOptionPane.showMessageDialog(null, pr.GetStartDate());
        ProjectTableModel model = new ProjectTableModel(entities);
        JTable table2 = new JTable(model);
        // vv Сортировка таблицы по строкам vv
        RowSorter<ProjectTableModel > sorter = new TableRowSorter<ProjectTableModel >(model);
        table2.setRowSorter(sorter);
        // ^^ Сортировка таблицы по строкам^^
        JScrollPane scrollPane = new JScrollPane(table2);
        scrollPane.setBounds(0, 0, 1000, 400);
        add(scrollPane, BorderLayout.CENTER);


        JButton addProjectButton = new JButton("Добавить");
        addProjectButton.setBounds(0, 410, 150, 50);
        addProjectButton.addActionListener(new OpenPanel("add_project"));
        add(addProjectButton);

        JButton changeProjectButton = new JButton("Изменить");
        changeProjectButton.setBounds(150, 410, 150, 50);
        changeProjectButton.addActionListener(new OpenPanel("change_project"));
        add(changeProjectButton);

        JButton deleteProjectButton = new JButton("Удалить");
        deleteProjectButton.setBounds(300, 410, 150, 50);
        deleteProjectButton.addActionListener(new OpenPanel("delete_project"));
        add(deleteProjectButton);

        JLabel numLabel = new JLabel("Номер проекта");
        numLabel.setBounds(600, 410, 100, 20);
        add(numLabel);

        JTextField numText = new JTextField();
        numText.setBounds(600, 430, 100, 30);
        add(numText);

        JButton programmersButton = new JButton("Программисты");
        programmersButton.setBounds(450, 410, 150, 50);
        programmersButton.addActionListener(new OpenProgrammers(numText));
        add(programmersButton);

        if(MyForm.curr_role.equals("manager")){
            addProjectButton.setEnabled(true);
            changeProjectButton.setEnabled(true);
            deleteProjectButton.setEnabled(true);
        }
        else{
            addProjectButton.setEnabled(false);
            changeProjectButton.setEnabled(false);
            deleteProjectButton.setEnabled(false);
        }

        JButton backButton = new JButton("<-");
        backButton.setBounds(900, 410, 50, 50);
        backButton.addActionListener(new CloseProjects());
        add(backButton);

        validate();
        setVisible(true);
    }
}

class OpenProgrammers implements ActionListener {
    JTextField indexText;
    int index;
    public static int needed_project_num;
    public OpenProgrammers (JTextField indexText) {
        this.indexText = indexText;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        List entities;
        try {
            index = Integer.parseInt(indexText.getText());
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List)ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }catch(Exception e) {
            entities = new ArrayList<>();
            index = -1;
        }
        if(index <= entities.size() && index > 0) {
            needed_project_num = index;
            UsersPanel._users_panel = new ProgrammersPanel();
        } else
            JOptionPane.showMessageDialog(null, "Данная позиция не найдена");
    }
}
