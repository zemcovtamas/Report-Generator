/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.model.tp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 *
 * @author david
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignable {

    @JsonProperty("Id")
    private Integer assignableId;
    @JsonProperty("Project")
    private Project project;
    @JsonProperty("TimeSpent")
    private double spentTime;
    @JsonProperty("Effort")
    private double effort;

    /**
     * @return the assignableId
     */
    public Integer getAssignableId() {
        return assignableId;
    }

    /**
     * @param assignableId the assignableId to set
     */
    public void setAssignableId(Integer assignableId) {
        this.assignableId = assignableId;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the spentTime
     */
    public double getSpentTime() {
        return spentTime;
    }

    /**
     * @param spentTime the spentTime to set
     */
    public void setSpentTime(double spentTime) {
        this.spentTime = spentTime;
    }

    /**
     * @return the effort
     */
    public double getEffort() {
        return effort;
    }

    /**
     * @param effort the effort to set
     */
    public void setEffort(double effort) {
        this.effort = effort;
    }



    public class Project {

        @JsonProperty("Id")
        private Integer id;
        @JsonProperty("Name")
        private String name;

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
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}
