import java.io.Serializable;
import java.util.Date;

public class ProgrammerInfo implements Serializable  {
    String project_name;
    String first_name;
    String second_name;
    String third_name;
    String position;
    Date start_date;
    Date end_date;
    double salary_per_hour;
    boolean is_full_day;
    double full_salary;

    public ProgrammerInfo(String project_name, String first_name, String second_name,
                          String third_name, String position, Date start_date,
                          Date end_date,  double salary_per_hour, boolean is_full_day, double full_salary){

            this.project_name = project_name;
            this.first_name = first_name;
            this.second_name = second_name;
            this.third_name = third_name;
            this.position = position;
            this.start_date = start_date;
            this.end_date = end_date;
            this.salary_per_hour = salary_per_hour;
            this.is_full_day = is_full_day;
            this.full_salary = full_salary;
    }

    public void SetProjectName(String name){
        this.project_name = name;
    }
    public void SetFirstName(String name){
        this.first_name = name;
    }
    public void SetSecondName(String name){
        this.second_name = name;
    }
    public void SetThirdName(String name){
        this.third_name = name;
    }
    public void SetPosition(String name){
        this.position = name;
    }
    public void SetStartDate(Date name){
        this.start_date = name;
    }
    public void SetEndDate(Date name){
        this.end_date = name;
    }
    public void SetSalaryPerHour(double name){
        this.salary_per_hour = name;
    }
    public void SetIsFullDay(boolean name){
        this.is_full_day = name;
    }
    public void SetFullSalary(double name){
        this.full_salary = name;
    }

    public String GetProjectName(){
        return project_name;
    }
    public String GetFirstName(){
        return first_name;
    }
    public String GetSecondName(){
        return second_name;
    }
    public String GetThirdName(){
        return third_name;
    }
    public String GetPosition(){
        return position;
    }
    public Date GetStartDate(){
        return start_date;
    }
    public Date GetEndDate(){
        return end_date;
    }
    public double GetSalaryPerHour(){
        return salary_per_hour;
    }
    public boolean GetIsFullDay(){
        return is_full_day;
    }
    public double GetFullSalary(){
        return full_salary;
    }
}

