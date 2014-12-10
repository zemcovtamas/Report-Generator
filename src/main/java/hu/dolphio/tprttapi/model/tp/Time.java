/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.model.tp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author david
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Time {

    @JsonProperty("Id")
    private Integer timeID;
    private String description;
    private Integer projectId;
    private int assignmentId;
    private double spent;
    private double remain;
    private String date;

    public Time() {

    }

    //[Description,Project,Assignable,Spent,Remain]
    public Time(String description, Integer projectId, int assignmentId, double spent, double remain, Date date) {
        this.description = description;
        this.projectId = projectId;
        this.assignmentId = assignmentId;
        this.spent = spent;
        this.remain = remain;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.date = format1.format(date);
    }

    /**
     * @return the projectId
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the assignmentId
     */
    public int getAssignmentId() {
        return assignmentId;
    }

    /**
     * @param assignmentId the assignmentId to set
     */
    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    /**
     * @return the spent
     */
    public double getSpent() {
        return spent;
    }

    /**
     * @param spent the spent to set
     */
    public void setSpent(double spent) {
        this.spent = spent;
    }

    /**
     * @return the remain
     */
    public double getRemain() {
        return remain;
    }

    /**
     * @param remain the remain to set
     */
    public void setRemain(double remain) {
        this.remain = remain;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{Description:\"" + description + "\", Project:{Id:" + projectId + "}, Assignable:{Id:" + assignmentId + "}, Spent:" + spent + ", Remain:" + remain + ", Date:\"" + date + "\"}";
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the timeID
     */
    public Integer getTimeID() {
        return timeID;
    }

    /**
     * @param timeID the timeID to set
     */
    public void setTimeID(Integer timeID) {
        this.timeID = timeID;
    }
}
