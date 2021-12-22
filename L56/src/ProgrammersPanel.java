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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.table.TableRowSorter;

class ProgrammersPanel extends JFrame {
    public static JButton authButton;
    public static JTextField loginText, passwordText;
    public static JFrame _programmers_panel;
    public ProgrammersPanel() {
        super("Программисты проекта");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 1000, 500);
        setLayout(null);

        List entities;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\programmers.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List)ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }


            List<ProgrammerInfo> programmers_needed_project;
            programmers_needed_project = (List<ProgrammerInfo>) entities.get(OpenProgrammers.needed_project_num - 1);
            ProgrammerTableModel model = new ProgrammerTableModel(programmers_needed_project);
            JTable table = new JTable(model);
            // vv Сортировка таблицы по строкам vv
            RowSorter<ProgrammerTableModel> sorter = new TableRowSorter<ProgrammerTableModel >(model);
            table.setRowSorter(sorter);
            // ^^ Сортировка таблицы по строкам^^
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(0, 0, 1000, 400);
            add(scrollPane, BorderLayout.CENTER);

        JLabel numLabel = new JLabel("Номер программиста");
        numLabel.setBounds(450, 400, 150, 20);
        add(numLabel);

        JTextField numText = new JTextField();
        numText.setBounds(460, 420, 100, 30);
        add(numText);

        JButton addProjectButton = new JButton("Добавить");
        addProjectButton.setBounds(0, 400, 150, 50);
        addProjectButton.addActionListener(new AddProgrammerToProject());
        add(addProjectButton);

       // JButton changeProjectButton = new JButton("Изменить");
       // changeProjectButton.setBounds(150, 400, 150, 50);
       // changeProjectButton.addActionListener(new ChangeProgrammer(numText));
       // add(changeProjectButton);

        JButton deleteProjectButton = new JButton("Удалить");
        deleteProjectButton.setBounds(300, 400, 150, 50);
        deleteProjectButton.addActionListener(new DeleteProgrammer(numText));
        add(deleteProjectButton);
        if(MyForm.curr_role.equals("manager")){
            addProjectButton.setEnabled(true);
            //changeProjectButton.setEnabled(true);
            deleteProjectButton.setEnabled(true);
        }
        else{
            addProjectButton.setEnabled(false);
            //changeProjectButton.setEnabled(false);
            deleteProjectButton.setEnabled(false);
        }

        JButton backButton = new JButton("<-");
        backButton.setBounds(600, 400, 50, 50);
        backButton.addActionListener(new CloseProgrammers());
        add(backButton);

        validate();
        setVisible(true);
    }
}

class ChangeProgrammer implements ActionListener {
    JTextField needed_programmer_text;
    public static int _programmer_index;
    public static boolean _programmer_changing = false;
    public ChangeProgrammer (JTextField needed_programmer_text) {
        this.needed_programmer_text = needed_programmer_text;
    }
    //OpenProgrammers.needed_project_num
    @Override
    public void actionPerformed(ActionEvent event) {
        _programmer_index = Integer.parseInt(needed_programmer_text.getText());
        _programmer_changing = true;
        MyForm.extra_frame = new AddProgrammerPanel();
        List entities;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }
        ProjectInfo curr_project = (ProjectInfo) entities.get(OpenProgrammers.needed_project_num - 1);
        AddProject.start_date = curr_project.GetStartDate();
        AddProject.end_date = curr_project.GetEndDate();
    }
}

class DeleteProgrammer implements ActionListener {
    //OpenProgrammers.needed_project_num
    JTextField needed_programmer_text;
    public DeleteProgrammer (JTextField needed_programmer_text) {
        this.needed_programmer_text = needed_programmer_text;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        List entities;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }
        List entities_progr;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\programmers.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities_progr = (List) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            entities_progr = new ArrayList<>();
        }
        List<ProgrammerInfo> programmers_needed_project;
        programmers_needed_project = (List<ProgrammerInfo>) entities_progr.get(OpenProgrammers.needed_project_num - 1);
        int needed_programmer = Integer.parseInt(needed_programmer_text.getText());
        if(needed_programmer <= programmers_needed_project.size()) {
            ProjectInfo curr_project = (ProjectInfo) entities.get(OpenProgrammers.needed_project_num - 1);
            curr_project.SetNumOfProgrammers(curr_project.GetNumOfProgrammersNeeded() - 1);
            curr_project.SetPrice(curr_project.GetPrice() - programmers_needed_project.get(needed_programmer - 1).GetFullSalary());
            entities.set((OpenProgrammers.needed_project_num - 1), curr_project);
            programmers_needed_project.remove(needed_programmer - 1);
            entities_progr.set(OpenProgrammers.needed_project_num - 1, programmers_needed_project);

            try {
                OutputStream os = new FileOutputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(entities);
                oos.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Невозможно сохранить файл");
            }
            try {
                OutputStream os = new FileOutputStream("C:\\Users\\Admin\\L56\\src\\programmers.bin");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(entities_progr);
                oos.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Невозможно сохранить файл");
            }
            if(ProjectsPanel._projects_panel != null)
                ProjectsPanel._projects_panel.dispose();
            if(ProgrammersPanel._programmers_panel != null)
                ProgrammersPanel._programmers_panel.dispose();
            ProgrammersPanel._programmers_panel = new ProgrammersPanel();
            ProjectsPanel._projects_panel = new ProjectsPanel();
        }
        else
            JOptionPane.showMessageDialog(null, "Ошибка в индексе");

    }
}

class AddProgrammerToProject implements ActionListener {
    //OpenProgrammers.needed_project_num
    public static boolean _adding_to_project = false;
    public AddProgrammerToProject () {

    }
    @Override
    public void actionPerformed(ActionEvent event) {
        AddProject.needed_num_of_programmers = 1;
        _adding_to_project = true;
        AddProjectPanel._add_programmers_panel = new AddProgrammerPanel();
        List entities;
        try {
            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
            ObjectInputStream ois = new ObjectInputStream(is);
            entities = (List) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            entities = new ArrayList<>();
        }
        ProjectInfo curr_project = (ProjectInfo) entities.get(OpenProgrammers.needed_project_num - 1);
        try {
            AddProject.start_date = curr_project.GetStartDate();
            AddProject.end_date = curr_project.GetEndDate();
           // JOptionPane.showMessageDialog(null, AddProject.start_date);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка в дате");
        }
    }
}
