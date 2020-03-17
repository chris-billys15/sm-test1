package suitmedia.test.intern.data;

import java.util.ArrayList;

import retrofit2.Call;
import suitmedia.test.intern.data.network.ApiClient;
import suitmedia.test.intern.data.network.ApiInterface;

public class DataManager {

    public ApiInterface apiInterface;
    public DataManager(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public Call<ArrayList<GuestModel>> getGuestResponse() {
        return apiInterface.getGuestResponse();
    }

}
