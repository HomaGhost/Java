import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Date;

public class ProgrammerTableModel implements TableModel {
    private List<ProgrammerInfo> users;

    /* передаём классу список объектов,
     * который будет отображаться в таблице */
    public ProgrammerTableModel(List<ProgrammerInfo> users) {
        this.users = users;
    }

    /* устанавливаем количество столбцов таблицы */
    @Override
    public int getColumnCount() {
        return 10;
    }

    /* определяем заголовки столбцов */
    @Override
    public String getColumnName(int index) {
        switch(index) {
            case 0: return "Проект";
            case 1: return "Имя";
            case 2: return "Фамилия";
            case 3: return "Отчество";
            case 4: return "Должность";
            case 5: return "Начало работы";
            case 6: return "Конец работы";
            case 7: return "Зарплата/час";
            case 8: return "Полный день";
            case 9: return "Зарплата за проект";
        }
        return null;
    }

    /* определяем типы данных в столбцах */
    @Override
    public Class<?> getColumnClass(int index) {
        switch(index) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: return String.class;
            case 5:
            case 6: return Date.class;
            case 7: return Double.class;
            case 8: return Boolean.class;
            case 9: return Double.class;
        }
        return null;
    }

    /* определяем количество строк */
    @Override
    public int getRowCount() {
        return users.size();
    }

    /* получаем ячейку в указанной строке и столбце */
    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0: return users.get(rowIndex).GetProjectName();
            case 1: return users.get(rowIndex).GetFirstName();
            case 2: return users.get(rowIndex).GetSecondName();
            case 3: return users.get(rowIndex).GetThirdName();
            case 4: return users.get(rowIndex).GetPosition();
            case 5: return users.get(rowIndex).GetStartDate();
            case 6: return users.get(rowIndex).GetEndDate();
            case 7: return users.get(rowIndex).GetSalaryPerHour();
            case 8: return users.get(rowIndex).GetIsFullDay();
            case 9: return users.get(rowIndex).GetFullSalary();
        }
        return null;
    }

    /* записываем ячейку в указанную строку и столбец */
    @Override
    public void setValueAt(Object value, int rowIndex, int colIndex) {
        switch(colIndex) {
            case 1:
                users.get(rowIndex).SetProjectName((String)value);
                break;
            case 2:
                users.get(rowIndex).SetFirstName((String)value);
                break;
            case 3:
                users.get(rowIndex).SetSecondName((String)value);
                break;
            case 4:
                users.get(rowIndex).SetThirdName((String)value);
                break;
            case 5:
                users.get(rowIndex).SetPosition((String)value);
                break;
            case 6:
                users.get(rowIndex).SetStartDate((Date)value);
                break;
            case 7:
                users.get(rowIndex).SetEndDate((Date)value);
                break;
            case 8:
                users.get(rowIndex).SetSalaryPerHour((double)value);
                break;
            case 9:
                users.get(rowIndex).SetIsFullDay((boolean)value);
                break;
            case 10:
                users.get(rowIndex).SetFullSalary((double)value);
                break;
        }
    }

    /* определяем, можно ли редактировать
     * ячейку в указанной строке и столбце */
    @Override
    public boolean isCellEditable(int rowIndex, int colIndex) {
        switch(colIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9: return false;

        }
        return false;
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {}

    @Override
    public void removeTableModelListener(TableModelListener listener) {}
}
