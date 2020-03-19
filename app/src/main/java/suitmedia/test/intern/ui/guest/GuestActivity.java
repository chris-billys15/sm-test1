package suitmedia.test.intern.ui.guest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import suitmedia.test.intern.R;
import suitmedia.test.intern.data.GuestModel;
import suitmedia.test.intern.ui.ChooseActivity;
import suitmedia.test.intern.ui.base.BaseActivity;
import suitmedia.test.intern.ui.ItemClickListener;
import suitmedia.test.intern.util.Utility;

public class GuestActivity extends BaseActivity implements GuestView, ItemClickListener {

    @BindView(R.id.rv_guest)
    RecyclerView rvGuest;
    @BindView(R.id.toolbar_guest)
    Toolbar toolbar;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    GuestRVAdapter mAdapter;
    GuestPresenter mPresenter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar(toolbar);
        mPresenter = new GuestPresenter(this,this);
        mPresenter.getGuest();
        rvGuest.setLayoutManager(new GridLayoutManager(this,2));
        setupView();
        mAdapter.setClickListener(this);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getGuest();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    protected String getToolbarTitle() {
        return "GUEST";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guest;
    }

    public void setupView(){
        mAdapter = new GuestRVAdapter(this);
        rvGuest.setAdapter(mAdapter);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onSucceedSetView(ArrayList<GuestModel> guests) {
        mAdapter.setData(guests);
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void viewToast(String str) {
        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, String guestName, String date) {
        String[] str = date.split("-");
        int month = Integer.parseInt(str[1]);
        if(Utility.isPrime(month)){
            showAlertDialog(month+" is Prime", guestName, date);
        }
        else{
            showAlertDialog(month+" is not Prime", guestName, date);
        }
    }

    public void showAlertDialog(String message, String guestName, String date){
        Intent intent = new Intent();
        new AlertDialog.Builder(this)
                .setTitle("Prime Checker")
                .setMessage(message)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        dialog.dismiss();
                        intent.putExtra("guest_name", guestName);
                        intent.putExtra("birth_date", date);
                        setResult(2, intent);
                        finish();
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setIcon(android.R.drawable.btn_star)
                .show();
    }
    @Override
    public void setToolBar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                if (getToolbarTitle() != null) {
                    setDisplayHomeEnabled(true);
                    getSupportActionBar().setTitle(getToolbarTitle());
                }
            }
        }
    }
}
