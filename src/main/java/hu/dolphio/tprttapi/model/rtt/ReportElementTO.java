package hu.dolphio.tprttapi.model.rtt;

public class ReportElementTO {
    private String subReportName;
    private ReportUserVO user;
    private ReportMetadataVO category;
    private ReportMetadataVO role;
    private ReportCycleVO cycle;
    private ReportTimeVO time;
    private ReportProjectVO project;
    private String comment;
    private int trackingId;
    private String ticketId;

    public ReportElementTO() {
    }

    public ReportElementTO(String subReportName, ReportUserVO user, ReportMetadataVO category, ReportMetadataVO role, ReportCycleVO cycle, ReportTimeVO time, ReportProjectVO project, String comment, int trackingId, String ticketId) {
        this.subReportName = subReportName;
        this.user = user;
        this.category = category;
        this.role = role;
        this.cycle = cycle;
        this.time = time;
        this.project = project;
        this.comment = comment;
        this.trackingId = trackingId;
        this.ticketId = ticketId;
    }

    public String getSubReportName() {
        return subReportName;
    }

    public void setSubReportName(String subReportName) {
        this.subReportName = subReportName;
    }

    public ReportUserVO getUser() {
        return user;
    }

    public void setUser(ReportUserVO user) {
        this.user = user;
    }

    public ReportMetadataVO getCategory() {
        return category;
    }

    public void setCategory(ReportMetadataVO category) {
        this.category = category;
    }

    public ReportMetadataVO getRole() {
        return role;
    }

    public void setRole(ReportMetadataVO role) {
        this.role = role;
    }

    public ReportCycleVO getCycle() {
        return cycle;
    }

    public void setCycle(ReportCycleVO cycle) {
        this.cycle = cycle;
    }

    public ReportTimeVO getTime() {
        return time;
    }

    public void setTime(ReportTimeVO time) {
        this.time = time;
    }

    public ReportProjectVO getProject() {
        return project;
    }

    public void setProject(ReportProjectVO project) {
        this.project = project;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    
    
}
