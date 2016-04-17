package ar.valhol.opratelapp.model;

/**
 * Created by Valentín on 16-Apr-16.
 */
public class Issue {
    long id;

    GenericObject project;
    GenericObject tracker;
    GenericObject status;
    GenericObject priority;
    GenericObject author;
    GenericObject assigned_to;

    String subject;
    String description;
    String start_date;
    String due_date;
    int done_ratio;
    String created_on;
    int estimated_hours;
    int spent_hours;
    String updated_on;

    public Issue() {

    }

    public Issue(long id, GenericObject project,
                 GenericObject tracker,
                 GenericObject status,
                 GenericObject priority,
                 GenericObject author,
                 String subject,
                 String description,
                 String start_date,
                 int done_ratio,
                 String created_on,
                 String updated_on,
                 int spent_hours) {
        this.id = id;
        this.project = project;
        this.tracker = tracker;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.subject = subject;
        this.description = description;
        this.start_date = start_date;
        this.done_ratio = done_ratio;
        this.created_on = created_on;
        this.updated_on = updated_on;
        this.spent_hours = spent_hours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GenericObject getProject() {
        return project;
    }

    public void setProject(GenericObject project) {
        this.project = project;
    }

    public GenericObject getTracker() {
        return tracker;
    }

    public void setTracker(GenericObject tracker) {
        this.tracker = tracker;
    }

    public GenericObject getStatus() {
        return status;
    }

    public void setStatus(GenericObject status) {
        this.status = status;
    }

    public GenericObject getPriority() {
        return priority;
    }

    public void setPriority(GenericObject priority) {
        this.priority = priority;
    }

    public GenericObject getAuthor() {
        return author;
    }

    public void setAuthor(GenericObject author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getDone_ratio() {
        return done_ratio;
    }

    public void setDone_ratio(int done_ratio) {
        this.done_ratio = done_ratio;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public int getEstimated_hours() {
        return estimated_hours;
    }

    public void setEstimated_hours(int estimated_hours) {
        this.estimated_hours = estimated_hours;
    }

    public GenericObject getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(GenericObject assigned_to) {
        this.assigned_to = assigned_to;
    }

    public int getSpent_hours() {
        return spent_hours;
    }

    public void setSpent_hours(int spent_hours) {
        this.spent_hours = spent_hours;
    }
}
