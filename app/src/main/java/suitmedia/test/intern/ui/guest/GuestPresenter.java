package suitmedia.test.intern.ui.guest;

import android.content.Context;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import suitmedia.test.intern.data.DataManager;
import suitmedia.test.intern.data.GuestModel;

public class GuestPresenter {
    private DataManager dataManager;
    private Context context;
    public GuestView view;

    public GuestPresenter(Context context, GuestView view){
        this.context = context;
        dataManager = new DataManager();
        this.view = view;
    }

    public void getGuest() {
        dataManager.getGuestResponse().enqueue(new Callback<ArrayList<GuestModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GuestModel>> call, Response<ArrayList<GuestModel>> response) {
                view.onSucceedSetView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<GuestModel>> call, Throwable t) {

            }
        });
    }
}
