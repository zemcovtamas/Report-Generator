package hu.dolphio.tprttapi.model.rtt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportTimeVO {
    private long from;
    private long to;
    private String dateFormatted;
    private String hoursFormatted;
    private double hours;
    private double days;
    private Date date;
    private long diff;
    
    private double hoursInDay;

    public ReportTimeVO() {
    }

    public ReportTimeVO(long from, long to, double hoursInDay) {
        this.from = from;
        this.to = to;
        this.hoursInDay = hoursInDay;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public String getDateFormatted() {
        return dateFormatted;
    }

    public String getHoursFormatted() {
        return hoursFormatted;
    }

    public double getHours() {
        return hours;
    }

    public double getDays() {
        return days;
    }

    public long getDiff() {
        return diff;
    }

    public double getHoursInDay() {
        return hoursInDay;
    }
}
