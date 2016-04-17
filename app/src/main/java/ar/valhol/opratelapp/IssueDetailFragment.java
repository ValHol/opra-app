package ar.valhol.opratelapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ar.valhol.opratelapp.model.Issue;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IssueDetailFragment extends Fragment{

    public static final String ARGS_ISSUEID = "issueid";
    Issue mIssue;

    public IssueDetailFragment() {
        //no instance
    }

    public static IssueDetailFragment newInstance(int issueId) {
        Bundle args = new Bundle();
        args.putInt(ARGS_ISSUEID, issueId);
        IssueDetailFragment fragment = new IssueDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mIssue = ((MainActivity) getActivity()).mIssues.get(getArguments().getInt(ARGS_ISSUEID));
        return inflater.inflate(R.layout.fragment_issuedetail, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView authorName = (TextView) view.findViewById(R.id.text_authorname);
        TextView day = (TextView) view.findViewById(R.id.day);
        TextView month = (TextView) view.findViewById(R.id.month);
        TextView subject = (TextView) view.findViewById(R.id.text_subject);
        TextView description = (TextView) view.findViewById(R.id.text_description);
        TextView priority = (TextView) view.findViewById(R.id.text_priority);
        TextView estimatedHours = (TextView) view.findViewById(R.id.text_estimated);
        TextView spentHours = (TextView) view.findViewById(R.id.text_spent);

        authorName.setText(mIssue.getAuthor().getName());
        subject.setText(mIssue.getSubject());
        priority.setText(Html.fromHtml("PRIORITY: <b>" + mIssue.getPriority().getName().toUpperCase() + "</b>"));
        estimatedHours.setText(String.valueOf(mIssue.getEstimated_hours()));
        spentHours.setText(String.valueOf(mIssue.getSpent_hours()));

        String monthString = "";
        String dayString = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
            Date date = sdf.parse(mIssue.getStart_date());
            monthString = (String) DateFormat.format("MMM", date);
            dayString = (String) DateFormat.format("dd", date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!mIssue.getSubject().equals(""))
            subject.setText(Html.fromHtml("<b>Subject: </b>" + mIssue.getSubject()));
        if (!mIssue.getDescription().equals(""))
            description.setText(Html.fromHtml("<b>Description: </b>" + mIssue.getDescription()));
        month.setText(monthString.toUpperCase());
        day.setText(dayString);

    }
}
