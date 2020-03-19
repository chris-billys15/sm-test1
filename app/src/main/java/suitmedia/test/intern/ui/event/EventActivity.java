package suitmedia.test.intern.ui.event;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import suitmedia.test.intern.R;
import suitmedia.test.intern.data.EventModel;
import suitmedia.test.intern.ui.base.BaseActivity;
import suitmedia.test.intern.ui.ItemClickListener;

public class EventActivity extends BaseActivity implements ItemClickListener {

    @BindView(R.id.rv_event)
    RecyclerView rvEvent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_new)
    ImageView btnNew;
    @BindView(R.id.fragment_event)
    FrameLayout frameLayout;
    EventRVAdapter mAdapter;
    ArrayList<EventModel> mData;

    //Fragment
    EventFragment eventFragment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar(toolbar);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mData.add(new EventModel("Let Sucess Make The Noise", "https://m.media-amazon.com/images/M/MV5BMTYzOTc2NzU3N15BMl5BanBnXkFtZTcwNjY3MDE3NQ@@._V1_SX300.jpg", "Nov 04 2020", this.getString(R.string.desc_text), -6.888525, 107.608396));
        mData.add(new EventModel("Semangat Tahun Baru", "https://m.media-amazon.com/images/M/MV5BNzViNmQ5MTYtMmI4Yy00N2Y2LTg4NWUtYWU3MThkMTVjNjk3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg", "Oct 21 2020", this.getString(R.string.desc_text),-6.875221, 107.605209));
        mData.add(new EventModel("Work Hard In Smile", "https://m.media-amazon.com/images/M/MV5BMTAwNjc4MTkyNjBeQTJeQWpwZ15BbWU3MDg3NTQyMzI@._V1_SX300.jpg", "Jun 14 2020", this.getString(R.string.desc_text), -6.890306, 107.611739));
        setupView();
        mAdapter.setClickListener(this);
        initFragment();
    }

    @Override
    protected String getToolbarTitle() {
        return "EVENT";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event;
    }

    @Override
    protected void setupView() {
        mAdapter = new EventRVAdapter(this, "activity");
        rvEvent.setAdapter(mAdapter);
        mAdapter.setData(mData);
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //setup carousel Fragment
        eventFragment = EventFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_event,eventFragment);
        fragmentTransaction.commit();
//        eventFragment.rvEvent.setVisibility(View.GONE);
    }

    @OnClick({R.id.btn_new,R.id.btn_back})
    public void onClickBtn(View view){
        switch (view.getId()){
            case R.id.btn_new:
                eventFragment.setData(mData);
                eventFragment.rvEvent.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                rvEvent.setVisibility(View.GONE);
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }
    @Override
    public void onItemClick(View view, String str, String date) {
        Intent intent = new Intent();
        intent.putExtra("event_name", str);
//        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();
        setResult(1, intent);
        finish();
    }
}
