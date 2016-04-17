package ar.valhol.opratelapp.service;

import ar.valhol.opratelapp.model.Issue;
import ar.valhol.opratelapp.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Valentín on 16-Apr-16.
 */
public interface RedMineService {

    @GET("issues.json")
    Call<Response> getIssues();

    @GET("{issueid}.json")
    Call<Issue> getIssueDetail(@Path("issueid") long issueId);

}
