/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AllScheduleBean;
/**
 *
 * @author Shweta Vyas
 */
public interface AllScheduleDAO {
    public int findAllScheduleIDs(String term,String year);
    public AllScheduleBean addNewAllScheduleID(AllScheduleBean aScheduleBean, String term, String year);
    public int findAllScheduleId();
}
