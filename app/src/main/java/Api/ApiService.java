package Api;

import Model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api/user")
    Call<UserResponse> getUser();
}
