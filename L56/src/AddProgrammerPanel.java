import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.time.DayOfWeek;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.DateFormat;

class AddProgrammerPanel extends JFrame {
    public static JTextField firstNameText, secondNameText, thirdNameText, startDateNumText, endDateNumText, salaryPerHourText, positionText;
    public static  JCheckBox fullDayBox;
    public AddProgrammerPanel() {
        super("Добавить программиста");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 500, 500);
        setLayout(null);
        if(AddProjectPanel.addButton != null)
            AddProjectPanel.addButton.setEnabled(false);
        JLabel firstNameLabel = new JLabel("Имя");
        firstNameLabel.setBounds(10, 10, 100, 30);
        add(firstNameLabel);
        JLabel secondNameLabel = new JLabel("Фамилия");
        secondNameLabel.setBounds(10, 50, 100, 30);
        add(secondNameLabel);
        JLabel thirdNameLabel = new JLabel("Отчество");
        thirdNameLabel.setBounds(10, 90, 100, 30);
        add(thirdNameLabel);
        JLabel startDateLabel = new JLabel("Начало работ");
        startDateLabel.setBounds(10, 130, 100, 30);
        add(startDateLabel);
        JLabel endDateLabel = new JLabel("Конец работ");
        endDateLabel.setBounds(10, 170, 100, 30);
        add(endDateLabel);
        JLabel salaryPerHourLabel = new JLabel("Оплата/час");
        salaryPerHourLabel.setBounds(10, 210, 100, 30);
        add(salaryPerHourLabel);
        JLabel positionLabel = new JLabel("Должность");
        positionLabel.setBounds(10, 300, 100, 30);
        add(positionLabel);

        firstNameText = new JTextField();
        firstNameText.setBounds(110, 10, 300, 30);
        add(firstNameText);
        secondNameText = new JTextField();
        secondNameText.setBounds(110, 50, 300, 30);
        add(secondNameText);
        thirdNameText = new JTextField();
        thirdNameText.setBounds(110, 90, 300, 30);
        add(thirdNameText);
        startDateNumText = new JTextField();
        startDateNumText.setBounds(110, 130, 300, 30);
        add(startDateNumText);
        endDateNumText = new JTextField();
        endDateNumText.setBounds(110, 170, 300, 30);
        add(endDateNumText);
        salaryPerHourText  = new JTextField();
        salaryPerHourText.setBounds(110, 210, 300, 30);
        add(salaryPerHourText);
        fullDayBox = new JCheckBox("Полный день");
        fullDayBox.setBounds(210, 250, 300, 30);
        add(fullDayBox);
        positionText = new JTextField();
        positionText.setBounds(110, 300, 300, 30);
        add(positionText);

        JButton authButton = new JButton("Добавить");
        authButton.setBounds(125, 430, 250, 30);
        authButton.addActionListener(new AddProgrammer(firstNameText, secondNameText, thirdNameText, startDateNumText,
                endDateNumText, salaryPerHourText, fullDayBox, positionText));
        add(authButton);

        validate();
        setVisible(true);
    }
}

class AddProgrammer implements ActionListener {
    public JTextField firstNameText, secondNameText, thirdNameText, startDateNumText, endDateNumText, salaryPerHourText, positionText;
    String pr_name, f_name, s_name, th_name, position;
    double salary_per_hour;
    public JCheckBox fullDayBox;
    public double full_salary = 0;
    List<ProgrammerInfo> programmers = new ArrayList<>();
    public AddProgrammer (JTextField  firstNameText, JTextField secondNameText, JTextField thirdNameText,
                          JTextField startDateNumText, JTextField endDateNumText, JTextField salaryPerHourText,
                          JCheckBox fullDayBox, JTextField positionText) {
        this.firstNameText = firstNameText;
        this.secondNameText = secondNameText;
        this.thirdNameText = thirdNameText;
        this.startDateNumText = startDateNumText;
        this.endDateNumText = endDateNumText;
        this.salaryPerHourText = salaryPerHourText;
        this.fullDayBox = fullDayBox;
        this.positionText = positionText;
    }

    //start_date = LocalDate.parse(startDateNumText.getText(), DateTimeFormatter.ofPattern("dd/MM/yyy"));
    //            end_date = LocalDate.parse(endDateNumText.getText(), DateTimeFormatter.ofPattern("dd/MM/yyy"));
    @Override
    public void actionPerformed(ActionEvent event) {
        Date start_date, end_date;
        double price_per_day;
        full_salary  = 0;
        try {
            start_date = new SimpleDateFormat("dd/MM/yyyy").parse(startDateNumText.getText());
            end_date = new SimpleDateFormat("dd/MM/yyyy").parse(endDateNumText.getText());
                if (fullDayBox.isSelected())
                    price_per_day = 8 * Double.parseDouble(salaryPerHourText.getText());
                else
                    price_per_day = 4 * Double.parseDouble(salaryPerHourText.getText());
                if (end_date.after(start_date)) {
                    if (start_date.before(AddProject.start_date) || end_date.after(AddProject.end_date))
                        JOptionPane.showMessageDialog(null, "Программист должен работать в пределах дат проекта");
                     else {
                    Calendar c = Calendar.getInstance();
                    for (Date i = start_date; i.before(end_date); ) {
                        c.setTime(i);
                        //JOptionPane.showMessageDialog(null, "День " + c.get(Calendar.DAY_OF_WEEK));
                        c.add(Calendar.DATE, 1);
                        i = c.getTime();
                        if (c.get(Calendar.DAY_OF_WEEK) == 7 || c.get(Calendar.DAY_OF_WEEK) == 1)
                            continue;
                        full_salary += price_per_day;
                    }

                        full_salary *= 1.77f;
                        //JOptionPane.showMessageDialog(null, "Полная зарплата " + full_salary);
                        pr_name = AddProject.projectNameText.getText();
                        f_name = firstNameText.getText();
                        s_name = secondNameText.getText();
                        th_name = thirdNameText.getText();
                        position = positionText.getText();
                        salary_per_hour = Double.parseDouble(salaryPerHourText.getText());
                        ProgrammerInfo programmer = new ProgrammerInfo(pr_name, f_name, s_name, th_name, position, start_date, end_date,
                                salary_per_hour, fullDayBox.isSelected(), full_salary);
                        programmers.add(programmer);
                        AddProject.project_price += full_salary;
                        if (!AddProgrammerToProject._adding_to_project || !ChangeProgrammer._programmer_changing)
                            full_salary = 0;
                        AddProgrammerPanel.firstNameText.setText("");
                        AddProgrammerPanel.secondNameText.setText("");
                        AddProgrammerPanel.thirdNameText.setText("");
                        AddProgrammerPanel.startDateNumText.setText("");
                        AddProgrammerPanel.endDateNumText.setText("");
                        AddProgrammerPanel.salaryPerHourText.setText("");
                        AddProgrammerPanel.fullDayBox.setSelected(false);
                        AddProgrammerPanel.positionText.setText("");
                        --AddProject.needed_num_of_programmers;
                        JOptionPane.showMessageDialog(null, "Осталось добавить программистов: " + AddProject.needed_num_of_programmers);
                        JOptionPane.showMessageDialog(null, "Программистов в проекте : " + programmers.size());
                   }
                    if (AddProject.needed_num_of_programmers == 0) {
                        List entities;
                        try {
                            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\programmers.bin");
                            ObjectInputStream ois = new ObjectInputStream(is);
                            entities = (List) ois.readObject();
                            ois.close();
                        } catch (IOException | ClassNotFoundException e) {
                            entities = new ArrayList<>();
                        }
                        if(!AddProgrammerToProject._adding_to_project) {
                            if (!ProjectsPanel.changing)
                                entities.add(programmers);
                            else
                                entities.set((ChangeProjectPanel.change_index - 1), programmers);
                        }
                        else{
                            List<ProgrammerInfo> programmers_needed_project;
                            //ChangeProgrammer._programmer_changing
                            programmers_needed_project = (List<ProgrammerInfo>) entities.get(OpenProgrammers.needed_project_num - 1);
                            if(!ChangeProgrammer._programmer_changing)
                                programmers_needed_project.add(programmers.get(programmers.size() - 1));
                            else
                                programmers_needed_project.set(ChangeProgrammer._programmer_index, programmers.get(programmers.size() - 1));
                            entities.set((OpenProgrammers.needed_project_num - 1), programmers_needed_project);
                        }
                        try {
                            OutputStream os = new FileOutputStream("C:\\Users\\Admin\\L56\\src\\programmers.bin");
                            ObjectOutputStream oos = new ObjectOutputStream(os);
                            oos.writeObject(entities);
                            oos.close();
                        } catch (IOException e) {
                            System.out.println("Невозможно сохранить файл");
                        }
                        List entities_pr;
                        ProjectInfo  project;
                        if(!AddProgrammerToProject._adding_to_project) {
                            project = new ProjectInfo(AddProject.projectNameText.getText(), AddProject.ownerNameText.getText(),
                                    Integer.parseInt(AddProject.programmersNumText.getText()), AddProject.start_date,
                                    AddProject.end_date, AddProject.project_price);
                        }
                        else
                            project = new ProjectInfo();

                        try {
                            InputStream is = new FileInputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
                            ObjectInputStream ois = new ObjectInputStream(is);
                            entities_pr = (List) ois.readObject();
                            ois.close();
                        } catch (IOException | ClassNotFoundException e) {
                            entities_pr = new ArrayList<>();
                        }
                        if(!AddProgrammerToProject._adding_to_project) {
                            if (!ProjectsPanel.changing)
                                entities_pr.add(project);
                            else if(ChangeProgrammer._programmer_changing) {
                                project = (ProjectInfo)entities_pr.get(OpenProgrammers.needed_project_num - 1);
                                double salary_diff = project.GetPrice() - full_salary;
                                project.SetPrice(project.GetPrice() + salary_diff);
                                entities_pr.set((ChangeProjectPanel.change_index - 1), project);
                            }
                            else
                                entities_pr.set((ChangeProjectPanel.change_index - 1), project);
                        }
                        else{
                            ProjectInfo needed_project;
                            needed_project = (ProjectInfo) entities_pr.get(OpenProgrammers.needed_project_num - 1);
                            needed_project.SetNumOfProgrammers(needed_project.GetNumOfProgrammersNeeded() + 1);
                            needed_project.SetPrice(needed_project.GetPrice() + full_salary);
                            entities_pr.set((OpenProgrammers.needed_project_num - 1), needed_project);
                        }
                        try {
                            OutputStream os = new FileOutputStream("C:\\Users\\Admin\\L56\\src\\projects.bin");
                            ObjectOutputStream oos = new ObjectOutputStream(os);
                            oos.writeObject(entities_pr);
                            oos.close();
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Невозможно сохранить файл");
                        }
                        if(!AddProgrammerToProject._adding_to_project) {
                            MyForm.prev_frame.dispose();
                            AddProjectPanel._add_programmers_panel.dispose();
                            ProjectsPanel._projects_panel.dispose();
                            ProjectsPanel._projects_panel = new ProjectsPanel();
                        }
                        else{
                            AddProjectPanel._add_programmers_panel.dispose();
                            UsersPanel._users_panel.dispose();
                            UsersPanel._users_panel = new ProjectsPanel();
                            UsersPanel._users_panel = new ProgrammersPanel();
                        }
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Конечная дата должна быть больше начальной");
            }catch(Exception e){
                //if(!AddProgrammerToProject._adding_to_project)
                    JOptionPane.showMessageDialog(null, "Ошибка в дате");
            }
    }
}

