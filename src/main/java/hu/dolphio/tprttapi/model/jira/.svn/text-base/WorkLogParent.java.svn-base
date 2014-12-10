/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;

/**
 *
 * @author david
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkLogParent {

    private int startAt;
    private int maxResults;
    private int total;
    @JsonProperty("worklogs")
    private Collection<WorkLog> workLogs;

    public WorkLogParent() {

    }

    /**
     * @return the startAt
     */
    public int getStartAt() {
        return startAt;
    }

    /**
     * @param startAt the startAt to set
     */
    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    /**
     * @return the maxResults
     */
    public int getMaxResults() {
        return maxResults;
    }

    /**
     * @param maxResults the maxResults to set
     */
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the workLogs
     */
    public Collection<WorkLog> getWorkLogs() {
        return workLogs;
    }

    /**
     * @param workLogs the workLogs to set
     */
    public void setWorkLogs(Collection<WorkLog> workLogs) {
        this.workLogs = workLogs;
    }

}
