/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.ProfessorBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nikunj
 */
public class ProfessorDAOImpl implements ProfessorDAO{
     private final String myDB = "jdbc:derby://localhost:1527/sfsystem";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";

    @Override
    public ArrayList findAllProfessors() {
        ArrayList allProfessors = new ArrayList();
        ProfessorBean aProfessor;

        String query = "SELECT * FROM SFADMIN.PROFESSOR";
        try {
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                aProfessor = new ProfessorBean();
                aProfessor.setProfessorName(rs.getString("PROF_NAME"));
                aProfessor.setUlid(rs.getString("ULID"));
                int is_employed = rs.getInt("EMPLOYMENT_STATUS");
                if(is_employed==0){
                    aProfessor.setEmploymentStatus("InActive");
                }else{
                    aProfessor.setEmploymentStatus("Active");
                }
                
                int is_teaching = rs.getInt("TEACHING_STATUS");
                if(is_teaching==0){
                    aProfessor.setTeachingStatus("NotAvailable");
                }else if(is_teaching==1){
                    aProfessor.setTeachingStatus("Available");
                }
                
                allProfessors.add(aProfessor);
            }
            rs.close();
            stmt.close();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println("ERROR: Problems with SQL select in findAllProfessors()");
            System.err.println(e.getMessage());
        }
        return allProfessors;
    }

    
    public int updateProfessorInfo(ProfessorBean theUpdateProfessorModel){
         System.out.println("New Employement Status: "+theUpdateProfessorModel.getEmploymentStatus());
        System.out.println("New Teaching Status: "+theUpdateProfessorModel.getTeachingStatus());
        System.out.println("Emp Ulid: "+theUpdateProfessorModel.getUlid());
        
        int newEmpStatus = 0;
        if(theUpdateProfessorModel.getEmploymentStatus().equals("Active"))
            newEmpStatus = 1;
        else
            newEmpStatus = 0;
        
        
        int newTeachStatus = 0;
        if(theUpdateProfessorModel.getTeachingStatus().equals("Available"))
            newTeachStatus = 1;
        else
            newTeachStatus = 0;
        
        String ulid = theUpdateProfessorModel.getUlid();
        DBHelper.loadDriver(driver);
        Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
        
        int rowCount = 0;
        try{
            String updateString;
           
            Statement stmt = DBConn.createStatement();
            
            updateString = "UPDATE SFADMIN.PROFESSOR SET "
                    + "EMPLOYMENT_STATUS = " + newEmpStatus+ ", "
                    + "TEACHING_STATUS = " + newTeachStatus+ ", "
                    + "LAST_UPDT_TS = CURRENT_TIMESTAMP "
                    + " WHERE ULID = '"+ulid+"'";
                    
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("Update String: "+updateString);
            stmt.close();
            DBConn.close();
        }catch (SQLException e){
            System.err.println("ERROR: Problems with SQL select in findAllProfessors()");
            System.err.println(e.getMessage());
        }
        return rowCount;
    
    }
    
    public int addNewProfessor(ProfessorBean aProfessor){
        int is_employed = 0;
        if(aProfessor.getEmploymentStatus().equals("Active"))
            is_employed = 1;
        else
            is_employed = 0;
        
        int is_teaching = 0;
        if(aProfessor.getTeachingStatus().equals("Available"))
            is_teaching = 1;
        else
            is_teaching = 0;
        
        String insertString = "INSERT INTO SFADMIN.PROFESSOR (PROF_NAME, ULID, EMPLOYMENT_STATUS, TEACHING_STATUS,CREATED_TS,LAST_UPDT_TS) VALUES ('"
                    + aProfessor.getProfessorName()
                    + "','" + aProfessor.getUlid()
                    + "'," + is_employed
                    + "," + is_teaching
                    + ", CURRENT_TIMESTAMP"
                    +", CURRENT_TIMESTAMP )" ;
        
        int rowCount = 0;
        
        try{
            DBHelper.loadDriver(driver);
            Connection DBConn = DBHelper.connect2DB(myDB, "sfadmin", "sfadmin");
            Statement stmt = DBConn.createStatement();
            
                    
            rowCount = stmt.executeUpdate(insertString);
            System.out.println("Insert String: "+insertString);

            stmt.close();
            DBConn.close();
        }catch (SQLException e){
            System.err.println("ERROR: Problems with SQL select in findAllProfessors()");
            System.err.println(e.getMessage());
        }
        return rowCount;
    }
}
