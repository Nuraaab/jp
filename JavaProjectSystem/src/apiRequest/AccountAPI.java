package apiRequest;

import java.util.List;

import apiEntity.Account;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountAPI {
	 @GET("acco/findall")
	    Call<List<Account>> findAllAccount();
	    @GET("acco/find/{id}")
	    Call<Account> findById(@Path("id") int id);
	    @POST("acco/create")
	    Call<Void> createAccount(@Body Account acc);
	    @PUT("acco/update") 
	    Call<Void> updateAccount(@Body Account  acc);
	    @DELETE("acco/delete")
	    Call<Void> deleteById(@Path("id") int id);
}
