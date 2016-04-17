package ar.valhol.opratelapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import ar.valhol.opratelapp.events.ShowIssueDetailEvent;
import ar.valhol.opratelapp.model.Issue;
import ar.valhol.opratelapp.model.Response;
import ar.valhol.opratelapp.service.RedMineService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ArrayList<Issue> mIssues;
    RedMineService mRedMineService;

    /**
     * Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setTitle(R.string.app_name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        setRetrofit();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mToolbar == null) return;
        TextView titleTextView = (TextView) mToolbar.findViewById(R.id.title);
        titleTextView.setText(title);
    }

    /**
     * Events listeners
     */

    @Subscribe
    public void onShowIssueDetailEvent(ShowIssueDetailEvent showIssueDetailEvent) {
        showIssueDetail(showIssueDetailEvent.issueId);
    }

    /**
     * Setup methods
     */

    private void setRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo.redmine.org/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mRedMineService = retrofit.create(RedMineService.class);

        mRedMineService.getIssues().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                mIssues = response.body().getIssues();
                showIssueList();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e(this.getClass().getName(), "onFailure" + t.getLocalizedMessage());
            }
        });
    }

    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    /**
     * Fragments methods
     */

    private void showIssueList() {
        showFragment(IssueListFragment.newInstance());
    }

    private void showIssueDetail(int issueId) {
        showFragment(IssueDetailFragment.newInstance(issueId));
    }

    private void showFragment(Fragment fragment) {
        try {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment);

            if(fragment instanceof IssueDetailFragment)
                    ft.addToBackStack("ISSUE_DETAIL");

            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
