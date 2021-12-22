import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Date;

public class ProjectTableModel implements TableModel {
    private List<ProjectInfo> users;

    /* передаём классу список объектов,
     * который будет отображаться в таблице */
    public ProjectTableModel(List<ProjectInfo> users) {
        this.users = users;
    }

    /* устанавливаем количество столбцов таблицы */
    @Override
    public int getColumnCount() {
        return 6;
    }

    /* определяем заголовки столбцов */
    @Override
    public String getColumnName(int index) {
        switch(index) {
            case 0: return "Проект";
            case 1: return "Заказчик";
            case 2: return "Программистов";
            case 3: return "Дата начала";
            case 4: return "Дата окончания";
            case 5: return "Стоимость";
        }
        return null;
    }

    /* определяем типы данных в столбцах */
    @Override
    public Class<?> getColumnClass(int index) {
        switch(index) {
            case 0:
            case 1:
            case 3:
            case 4: return String.class;
            case 2:
            case 5: return Integer.class;
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
            case 1: return users.get(rowIndex).GetOwnerName();
            case 2: return users.get(rowIndex).GetNumOfProgrammersNeeded();
            case 3: return users.get(rowIndex).GetStartDate();
            case 4: return users.get(rowIndex).GetEndDate();
            case 5: return users.get(rowIndex).GetPrice();
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
                users.get(rowIndex).SetOwnerName((String)value);
                break;
            case 3:
                users.get(rowIndex).SetNumOfProgrammers((Integer) value);
                break;
            case 4:
                users.get(rowIndex).SetStartDate((Date) value);
                break;
            case 5:
                users.get(rowIndex).SetEndDate((Date) value);
                break;
            case 6:
                users.get(rowIndex).SetPrice((Double)value);
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
            case 5: return false;
        }
        return false;
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {}

    @Override
    public void removeTableModelListener(TableModelListener listener) {}
}
