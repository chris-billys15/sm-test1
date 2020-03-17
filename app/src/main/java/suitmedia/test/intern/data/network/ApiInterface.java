package suitmedia.test.intern.data.network;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import suitmedia.test.intern.data.GuestModel;

public interface ApiInterface {
    @GET("v2/596dec7f0f000023032b8017")
    Call<ArrayList<GuestModel>> getGuestResponse();
}
