package ar.valhol.opratelapp.service;

import ar.valhol.opratelapp.model.Issue;
import ar.valhol.opratelapp.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by Valentín on 16-Apr-16.
 */
public interface RedMineService {

    @GET("issues.json")
    Call<Response> getIssues();

}
