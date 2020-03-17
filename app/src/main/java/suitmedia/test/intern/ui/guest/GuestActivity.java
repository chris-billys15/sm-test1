package suitmedia.test.intern.ui.guest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import suitmedia.test.intern.R;
import suitmedia.test.intern.data.GuestModel;
import suitmedia.test.intern.ui.BaseActivity;
import suitmedia.test.intern.ui.ItemClickListener;

public class GuestActivity extends BaseActivity implements GuestView, ItemClickListener {

    @BindView(R.id.rv_guest)
    RecyclerView rvGuest;
    GuestRVAdapter mAdapter;
    GuestPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new GuestPresenter(this,this);
        mPresenter.getGuest();
        rvGuest.setLayoutManager(new GridLayoutManager(this,2));
        setupView();
        mAdapter.setClickListener(this);
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
    }

    @Override
    public void onSucceedSetView(ArrayList<GuestModel> guests) {
        mAdapter.setData(guests);
    }

    @Override
    public void viewToast(String str) {
        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, String str, String date) {
        Intent intent = new Intent();
        intent.putExtra("guest_name", str);
        intent.putExtra("birth_date", date);
//        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();
        setResult(2, intent);
        finish();
    }
}
