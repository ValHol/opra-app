package ar.valhol.redminesample.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;

public class Response {
    ArrayList<Issue> issues;
    int total_count;
    int offset;
    int limit;

    public Response() {

    }

    public Response(ArrayList<Issue> issues, int total_count, int offset, int limit) {
        this.issues = issues;
        this.total_count = total_count;
        this.offset = offset;
        this.limit = limit;
    }

    public ArrayList<Issue> getIssues() {
        return issues;
    }

    public void setIssues(ArrayList<Issue> issues) {
        this.issues = issues;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
