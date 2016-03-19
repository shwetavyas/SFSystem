/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import bean.*;
import dao.UserDAO;
import dao.UserDAOImpl;
import java.util.ArrayList;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author it3680110
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String response = "";
    private User theModel;
    private int count = 1;

    public LoginController() {
        theModel = new User();
    }

    public User getTheModel() {
        return theModel;
    }

    public void setTheModel(User userBean) {
        this.theModel = userBean;
    }

    public String getResponse() {
        return response;
    }

    public String countAttempt(ComponentSystemEvent event) {
        String navi = null;

        if (count > 3) {

            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("access-denied?faces-redirect=true");

        }

        return navi;
    }

    public String authenticate() {
        response = "";
        UserDAO aUserDAO = new UserDAOImpl();
        int accessLevel;
        accessLevel = aUserDAO.authenticate(theModel);

        if (accessLevel == 1) {
            //admin
            return "adminLandingPage.xhtml";
        } else if (accessLevel == 0){
            // Faculty
            count++;
            return "facultyLandingPage.xhtml";

        }else if (accessLevel == -1) {
            // DB error
            return "errorPage.xhtml";
        }
        else if (accessLevel == -2){
        
            //Non existing user
            return "signUp.xhtml";
        }
        else{
        return "";
        }

    }
    

}
