import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class MyForm extends JFrame {
    public static JButton authButton, exitButton, projectsButton;
    public static JButton seeUsersButton;
    public static String curr_role = "unknown";
    public static JFrame extra_frame;
    public static JFrame prev_frame;
    public MyForm() {
        super("Laba 4 Ex 2");
        setBounds(0, 0, 1000, 1000);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        authButton = new JButton("Авторизация");
        authButton.setBounds(125, 0, 250, 30);
        authButton.addActionListener(new ActivateExitAuth(0));
        add(authButton);

        exitButton = new JButton("Выйти");
        exitButton.setBounds(375, 0, 250, 30);
        exitButton.addActionListener(new ActivateExitAuth(1));
        add(exitButton);
        if(curr_role.equals("unknown"))
            exitButton.setEnabled(false);
        else
            exitButton.setEnabled(true);
        projectsButton = new JButton("Проекты");
        projectsButton.setBounds(0, 250, 250, 30);
        projectsButton.addActionListener(new OpenProjects());
        add(projectsButton);

        seeUsersButton = new JButton("Пользователи");
        seeUsersButton.setBounds(250, 440, 250, 30);
        seeUsersButton.addActionListener(new OpenUsers());
        add(seeUsersButton);
        if(curr_role.equals("admin"))
            seeUsersButton.setEnabled(true);
        else
            seeUsersButton.setEnabled(false);

        validate();
        setVisible(true);
    }
}

class ActivateExitAuth implements ActionListener {
    int num;
    public ActivateExitAuth(int num) {
        this.num = num;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(num == 0) { // Авторизация
            MyForm.prev_frame.dispose();
            MyForm.extra_frame = new AuthPanel();

        }
        else{   // Выход
            MyForm.prev_frame.dispose();
            MyForm.curr_role = "unknown";
            MyForm.prev_frame = new MyForm();
        }
    }
}

class OpenProjects implements ActionListener {
    public OpenProjects() {
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        MyForm.prev_frame.dispose();
        ProjectsPanel._projects_panel = new ProjectsPanel();
    }
}

class CloseProjects implements ActionListener {
    public CloseProjects() {
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        ProjectsPanel._projects_panel.dispose();
        MyForm.prev_frame = new MyForm();
    }
}

class OpenUsers implements ActionListener {
    public OpenUsers () {
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        MyForm.prev_frame.dispose();
        UsersPanel._users_panel = new UsersPanel();
    }
}

class Back implements ActionListener {
    public Back () {
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(UsersPanel._users_panel != null)
            UsersPanel._users_panel.dispose();
        if(ProjectsPanel._projects_panel != null)
            ProjectsPanel._projects_panel.dispose();
        MyForm.prev_frame = new MyForm();
    }
}

class OpenPanel implements ActionListener {
    String text;
    public OpenPanel (String text) {
        this.text = text;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(text.equals("add"))
            MyForm.prev_frame = new AddUserPanel();
        else if(text.equals("delete"))
            MyForm.prev_frame = new DeleteUserPanel();
        else if(text.equals("change"))
            MyForm.prev_frame = new ChangeUserPanel();
        else if(text.equals("add_project"))
            MyForm.prev_frame = new AddProjectPanel();
        else if(text.equals("delete_project"))
            MyForm.prev_frame = new DeleteProjectPanel();
        else if(text.equals("change_project"))
            MyForm.prev_frame = new ChangeProjectPanel();
    }
}
class CloseProgrammers implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        if (UsersPanel._users_panel != null)
            UsersPanel._users_panel.dispose();
    }
}

public class MainPanel {
    public static void main(String[] args) {
        MyForm.prev_frame = new MyForm();
    }
}
