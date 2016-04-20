package ar.valhol.redminesample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import ar.valhol.redminesample.events.ShowIssueDetailEvent;
import ar.valhol.redminesample.model.Issue;
import ar.valhol.redminesample.model.Response;
import ar.valhol.redminesample.service.RedMineService;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    /**
     * UI
     */

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.text_loading)
    TextView mLoadingTextView;

    /**
     * Networking and data
     */

    ArrayList<Issue> mIssues;
    RedMineService mRedMineService;


    /**
     * Application is in background
     */

    private boolean inBackground;

    /**
     * Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        setTitle(R.string.app_name);
    }

    @Override
    protected void onResume() {
        inBackground = false;
        super.onResume();
        EventBus.getDefault().register(this);
        if (mIssues == null || mIssues.isEmpty()) {
            mLoadingTextView.setVisibility(View.VISIBLE);
        } else {
            mLoadingTextView.setVisibility(View.GONE);
            showIssueList();
        }
        setRetrofit();
    }

    @Override
    protected void onPause() {
        inBackground = false;
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        inBackground = true;
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
                .addConverterFactory(JacksonConverterFactory
                        .create(new ObjectMapper()
                                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
                .build();

        mRedMineService = retrofit.create(RedMineService.class);

        mRedMineService.getIssues().enqueue(new Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (mLoadingTextView != null) mLoadingTextView.setVisibility(TextView.GONE);
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

        if (inBackground) return;

        try {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment);

            if (fragment instanceof IssueDetailFragment)
                ft.addToBackStack("ISSUE_DETAIL");

            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
