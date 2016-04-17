package ar.valhol.opratelapp.events;

import ar.valhol.opratelapp.model.Issue;

import java.util.ArrayList;

public class IssuesReceivedEvent {
    public ArrayList<Issue> issues;
    public IssuesReceivedEvent(ArrayList<Issue> issues) {
    }
}
