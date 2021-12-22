import java.io.Serializable;
import java.util.Date;

public class ProjectInfo implements Serializable  {
    String project_name;
    String owner_name;
    int num_of_programmers_needed;
    Date start_date;
    Date end_date;
    double project_price;

    public ProjectInfo(){

    }
    public ProjectInfo(String project_name, String owner_name,  int num_of_programmers_needed,
                       Date start_date, Date end_date, double project_price){
        this.project_name = project_name;
        this.owner_name = owner_name;
        this.num_of_programmers_needed = num_of_programmers_needed;
        this.start_date = start_date;
        this.end_date = end_date;
        this.project_price = project_price;
    }

    public void SetProjectName(String name){
        this.project_name = name;
    }
    public void SetOwnerName(String name){
        this.owner_name = name;
    }
    public void SetNumOfProgrammers(int num){
        this.num_of_programmers_needed = num;
    }

    public void SetStartDate(Date date){
        this.start_date = date;
    }

    public void SetEndDate(Date date){
        this.end_date = date;
    }

    public void SetPrice(double price){
        this.project_price = price;
    }

    public String GetProjectName(){
        return project_name;
    }

    public String GetOwnerName(){
        return owner_name;
    }

    public double GetPrice(){
        return project_price;
    }

    public Date GetStartDate(){
        return start_date;
    }

    public Date GetEndDate(){
        return end_date;
    }

    public int GetNumOfProgrammersNeeded(){
        return num_of_programmers_needed;
    }


    //public List<ProgrammerInfo> GetProgrammerInfo(){
    //    return programmers;
   // }
}
