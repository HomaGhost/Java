import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.time.Duration;
import java.time.LocalTime;

//javac -encoding utf8 L4E1.java
class MyForm extends JFrame {
    public MyForm() {
        super("Laba 4 Ex 1");
        setBounds(100, 50, 380, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel firstOperandLabel = new JLabel("Время:");
        firstOperandLabel.setBounds(10, 10, 350, 30);
        add(firstOperandLabel);
        JTextField firstOperandTextField = new JTextField();
        firstOperandTextField.setBounds(10, 50, 350, 30);
        add(firstOperandTextField);
        JLabel secondOperandLabel = new JLabel("Число секунд:");
        secondOperandLabel.setBounds(10, 90, 350, 30);
        add(secondOperandLabel);
        JTextField secondOperandTextField = new JTextField();
        secondOperandTextField.setBounds(10, 130, 350, 30);
        add(secondOperandTextField);
		JTextField timeLineTextField = new JTextField();
        timeLineTextField.setBounds(100, 10, 250, 30);
		timeLineTextField.setEditable(false);
        add(timeLineTextField);
		
        JButton calculateButton = new JButton("Вычислить сумму");
        calculateButton.setBounds(60, 170, 250, 30);
        calculateButton.addActionListener( new CalculateButtonHandler(firstOperandTextField,secondOperandTextField, timeLineTextField) );
        add(calculateButton);
	
        validate();
        setVisible(true);
    }
}

class CalculateButtonHandler implements ActionListener {
    private JTextField f1, f2, f3;
    public CalculateButtonHandler(JTextField f1, JTextField f2, JTextField f3) {
        this.f1 = f1;
        this.f2 = f2;
		this.f3 = f3;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            int seconds = Integer.parseInt(f2.getText());
			LocalTime time = LocalTime.parse(f1.getText());
			LocalTime new_time = time.plus(Duration.ofSeconds(seconds));
            JOptionPane.showMessageDialog(null, new_time);
			
			if(new_time.isAfter(time))
				f3.setText("Будущее");
			else if (new_time.isBefore(time))
				f3.setText("Прошлое");
			else
				f3.setText("Настоящее");
        } catch(NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Неверное число");
        } catch(java.lang.Throwable e){
			JOptionPane.showMessageDialog(null, "Неверная дата");
		}
    }
}

public class L4E1 {
    public static void main(String[] args) {
        new MyForm();
    }
}