/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Shweta Vyas
 */
public class AllScheduleBean {
    private String scheduleName;
    private String scheduleDescription;
    private int all_Sch_Id;
    
    public AllScheduleBean(){
        scheduleName = "";
        scheduleDescription = "";
    }

    public AllScheduleBean(String scheduleName, String scheduleDescription) {
        this.scheduleName = scheduleName;
        this.scheduleDescription = scheduleDescription;
    }


    public int getAll_Sch_Id() {
        return all_Sch_Id;
    }

    public void setAll_Sch_Id(int all_Sch_Id) {
        this.all_Sch_Id = all_Sch_Id;
    }
    
    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleDescription() {
        return scheduleDescription;
    }

    public void setScheduleDescription(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }
    
}
