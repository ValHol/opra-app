package ar.valhol.opratelapp.events;

import ar.valhol.opratelapp.model.Issue;

/**
 * Created by Valentín on 16-Apr-16.
 */
public class ShowIssueDetailEvent {
    public int issueId;

    public ShowIssueDetailEvent(int issueId) {
        this.issueId = issueId;
    }
}
