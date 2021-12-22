import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class PersonTableModel implements TableModel {
    private List<UserInfo> users;

    /* передаём классу список объектов,
     * который будет отображаться в таблице */
    public PersonTableModel(List<UserInfo> users) {
        this.users = users;
    }

    /* устанавливаем количество столбцов таблицы */
    @Override
    public int getColumnCount() {
        return 3;
    }

    /* определяем заголовки столбцов */
    @Override
    public String getColumnName(int index) {
        switch(index) {
            case 0: return "Логин";
            case 1: return "Пароль";
            case 2: return "Роль";
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
            case 3: return String.class;
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
            case 0: return users.get(rowIndex).GetLogin();
            case 1: return users.get(rowIndex).GetPassword();
            case 2: return users.get(rowIndex).GetRole();
        }
        return null;
    }

    /* записываем ячейку в указанную строку и столбец */
    @Override
    public void setValueAt(Object value, int rowIndex, int colIndex) {
        switch(colIndex) {
            case 1:
                users.get(rowIndex).SetLogin((String)value);
                break;
            case 2:
                users.get(rowIndex).SetPassword((String)value);
                break;
            case 3:
                users.get(rowIndex).SetRole((String)value);
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
            case 2: return false;
        }
        return false;
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {}

    @Override
    public void removeTableModelListener(TableModelListener listener) {}
}