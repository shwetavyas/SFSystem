/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AllScheduleDAO;
import dao.AllScheduleDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import bean.AllScheduleBean;

/**
 *
 * @author Shweta Vyas
 */
@ManagedBean
@SessionScoped
public class AllScheduleController {
    
    private AllScheduleBean theAllScheduleModel;
    private String term;
    private String year;
    private String updateStatus;
   // private List<AllScheduleBean> allScheduleList;
    public AllScheduleController(){
        theAllScheduleModel = new AllScheduleBean();
    }
    public AllScheduleBean getTheAllScheduleModel() {
        return theAllScheduleModel;
    }

    public void setTheAllScheduleModel(AllScheduleBean theAllScheduleModel) {
        this.theAllScheduleModel = theAllScheduleModel;
    }

    /**
     * @return the updateStatus
     */
    public String getUpdateStatus() {
        return updateStatus;
    }

    /**
     * @param updateStatus the updateStatus to set
     */
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
    public String addNewAllScheduleId() {
        AllScheduleDAO addAllScheduleIdDAO = new AllScheduleDAOImpl();
        theAllScheduleModel = addAllScheduleIdDAO.addNewAllScheduleID(theAllScheduleModel,term,year);
        if (theAllScheduleModel !=null ) {
            
            updateStatus = "New Schedule Created Successfully";
        } else {
            updateStatus = "Errors encountered while adding new course. Please try again later.";
        }
        return null;
    }

}
