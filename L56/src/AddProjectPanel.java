import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.DateFormat;

class AddProjectPanel extends JFrame {
    public static JFrame _add_programmers_panel;
    public static JButton addButton;
    public AddProjectPanel() {
        super("Добавить проект");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(250, 250, 500, 500);
        setLayout(null);

        JLabel projectNameLabel = new JLabel("Название");
        projectNameLabel.setBounds(10, 10, 100, 30);
        add(projectNameLabel);
        JLabel ownerNameLabel = new JLabel("Заказчик");
        ownerNameLabel.setBounds(10, 50, 100, 30);
        add(ownerNameLabel);
        JLabel programmersNumLabel = new JLabel("Программистов");
        programmersNumLabel.setBounds(10, 90, 100, 30);
        add(programmersNumLabel);
        JLabel startDateLabel = new JLabel("Начало работ");
        startDateLabel.setBounds(10, 130, 100, 30);
        add(startDateLabel);
        JLabel endDateLabel = new JLabel("Конец работ");
        endDateLabel.setBounds(10, 170, 100, 30);
        add(endDateLabel);

        JTextField projectNameText = new JTextField();
        projectNameText.setBounds(110, 10, 300, 30);
        add(projectNameText);
        JTextField ownerNameText = new JTextField();
        ownerNameText.setBounds(110, 50, 300, 30);
        add(ownerNameText);
        JTextField programmersNumText = new JTextField();
        programmersNumText.setBounds(110, 90, 300, 30);
        add(programmersNumText);
        JTextField startDateNumText = new JTextField();
        startDateNumText.setBounds(110, 130, 300, 30);
        add(startDateNumText);
        JTextField endDateNumText = new JTextField();
        endDateNumText.setBounds(110, 170, 300, 30);
        add(endDateNumText);


        addButton = new JButton("Добавить");
        addButton.setEnabled(true);
        addButton.setBounds(125, 430, 250, 30);
        addButton.addActionListener(new AddProject(projectNameText, ownerNameText, programmersNumText, startDateNumText, endDateNumText));
        add(addButton);

        validate();
        setVisible(true);
    }
}

class AddProject implements ActionListener {
    public static JTextField projectNameText, ownerNameText, programmersNumText, startDateNumText, endDateNumText;
    public static Date start_date, end_date;
    public static int needed_num_of_programmers;
    public static double project_price = 0;
    //ProjectInfo project = new ProjectInfo();
    public AddProject (JTextField projectNameText, JTextField ownerNameText, JTextField programmersNumText, JTextField startDateNumText, JTextField endDateNumText) {
        this.projectNameText = projectNameText;
        this.ownerNameText = ownerNameText;
        this.programmersNumText = programmersNumText;
        this.startDateNumText = startDateNumText;
        this.endDateNumText = endDateNumText;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            start_date = new SimpleDateFormat("dd/MM/yyyy").parse(startDateNumText.getText());
            end_date = new SimpleDateFormat("dd/MM/yyyy").parse(endDateNumText.getText());
            JOptionPane.showMessageDialog(null, "Дата: " + start_date);
            if(end_date.after(start_date)) {
                needed_num_of_programmers = Integer.parseInt(programmersNumText.getText());
                AddProjectPanel._add_programmers_panel = new AddProgrammerPanel();
            }
            else
                JOptionPane.showMessageDialog(null, "Конечная дата должна быть больше начальной");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка в дате");
        }
    }
}
