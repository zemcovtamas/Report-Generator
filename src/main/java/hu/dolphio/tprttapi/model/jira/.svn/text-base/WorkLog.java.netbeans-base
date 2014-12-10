/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

/**
 *
 * @author david
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkLog {
    /*
     "comment": "teszt1",
     "created": "2014-10-18T12:46:38.000+0200",
     "updated": "2014-10-18T12:46:38.000+0200",
     "started": "2014-10-18T00:46:00.000+0200",
     "timeSpent": "3h",
     "timeSpentSeconds": 10800,
     "id": "10300"
     */

    private Integer id;
    private String timeSpent;
    private Integer timeSpentSeconds;
    private String comment;
    private Date started;
    private Date created;
    private Date updated;
    private String issueId;

    public WorkLog() {

    }

    public WorkLog(Date started, Integer timeSpentSeconds, String comment) {
        this.started = started;
        this.timeSpentSeconds = timeSpentSeconds;
        this.comment = comment;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the timeSpent
     */
    public String getTimeSpent() {
        return timeSpent;
    }

    /**
     * @param timeSpent the timeSpent to set
     */
    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    /**
     * @return the timeSpentSeconds
     */
    public Integer getTimeSpentSeconds() {
        return timeSpentSeconds;
    }

    /**
     * @param timeSpentSeconds the timeSpentSeconds to set
     */
    public void setTimeSpentSeconds(Integer timeSpentSeconds) {
        this.timeSpentSeconds = timeSpentSeconds;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the started
     */
    public Date getStarted() {
        return started;
    }

    /**
     * @param started the started to set
     */
    public void setStarted(Date started) {
        this.started = started;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @return the issueId
     */
    public String getIssueId() {
        return issueId;
    }

    /**
     * @param issueId the issueId to set
     */
    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

}
