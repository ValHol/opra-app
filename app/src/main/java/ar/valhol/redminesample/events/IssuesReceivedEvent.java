package ar.valhol.redminesample.events;

import ar.valhol.redminesample.model.Issue;

import java.util.ArrayList;

public class IssuesReceivedEvent {
    public ArrayList<Issue> issues;
    public IssuesReceivedEvent(ArrayList<Issue> issues) {
    }
}
