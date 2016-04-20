package ar.valhol.redminesample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ar.valhol.redminesample.events.IssuesReceivedEvent;
import ar.valhol.redminesample.events.ShowIssueDetailEvent;
import ar.valhol.redminesample.model.Issue;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class IssueListFragment extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<Issue> mIssues;
    String TAG = this.getClass().getName();

    public IssueListFragment() {

    }

    public static IssueListFragment newInstance() {
        return new IssueListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_issuelist, null);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.list_issue);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setList();
        return layout;
    }

    @Override
    public void onAttach(Context context) {
        EventBus.getDefault().register(this);
        if (mIssues == null) mIssues = ((MainActivity) getActivity()).mIssues;
        setList();
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }

    @Subscribe
    public void onIssuesReceivedEvent(IssuesReceivedEvent issuesReceivedEvent) {
        mIssues = issuesReceivedEvent.issues;
        setList();
    }

    public void setList() {
        if (mRecyclerView != null && mIssues != null)
            mRecyclerView.setAdapter(new IssueAdapter(mIssues));
    }

    class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {

        ArrayList<Issue> issuesList;

        public IssueAdapter(ArrayList<Issue> issues) {
            this.issuesList = issues;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final Issue issue = issuesList.get(position);
            String month = "";
            String day = "";
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
                Date date = sdf.parse(issue.getStart_date());
                month = (String) DateFormat.format("MMM", date);
                day = (String) DateFormat.format("dd", date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!issue.getSubject().equals(""))
                holder.setSubject(Html.fromHtml("<b>Subject: </b>" + issue.getSubject()));
            if (!issue.getDescription().equals(""))
                holder.setDescription(Html.fromHtml("<b>Description: </b>" + issue.getDescription()));
            holder.setMonth(month.toUpperCase());
            holder.setDay(day);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new ShowIssueDetailEvent(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return issuesList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView month;
            TextView day;
            TextView subject;
            TextView description;

            public ViewHolder(View itemView) {
                super(itemView);
                this.subject = (TextView) itemView.findViewById(R.id.text_subject);
                this.description = (TextView) itemView.findViewById(R.id.text_description);
                this.month = (TextView) itemView.findViewById(R.id.month);
                this.day = (TextView) itemView.findViewById(R.id.day);
            }

            public void setSubject(Spanned subjectText) {
                subject.setText(subjectText);
            }

            public void setDescription(Spanned descriptionText) {
                description.setText(descriptionText);
            }

            public void setMonth(String monthText) {
                month.setText(monthText);
            }

            public void setDay(String dayText) {
                day.setText(dayText);
            }

        }
    }


}
