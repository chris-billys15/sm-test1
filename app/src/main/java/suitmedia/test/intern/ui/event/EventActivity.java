package suitmedia.test.intern.ui.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import suitmedia.test.intern.R;
import suitmedia.test.intern.data.EventModel;
import suitmedia.test.intern.ui.BaseActivity;
import suitmedia.test.intern.ui.ItemClickListener;

public class EventActivity extends BaseActivity implements ItemClickListener {

    @BindView(R.id.rv_event)
    RecyclerView rvEvent;
    EventRVAdapter mAdapter;
    ArrayList<EventModel> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mData.add(new EventModel("ABC Event", "https://m.media-amazon.com/images/M/MV5BMTYzOTc2NzU3N15BMl5BanBnXkFtZTcwNjY3MDE3NQ@@._V1_SX300.jpg", "24 Juni 2020"));
        mData.add(new EventModel("DEF Event", "https://m.media-amazon.com/images/M/MV5BNzViNmQ5MTYtMmI4Yy00N2Y2LTg4NWUtYWU3MThkMTVjNjk3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg", "24 Juli 2020"));
        mData.add(new EventModel("GHI Event", "https://m.media-amazon.com/images/M/MV5BMTAwNjc4MTkyNjBeQTJeQWpwZ15BbWU3MDg3NTQyMzI@._V1_SX300.jpg", "24 Agustus 2020"));
        setupView();
        mAdapter.setClickListener(this);
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
        mAdapter = new EventRVAdapter(this);
        rvEvent.setAdapter(mAdapter);
        mAdapter.setData(mData);
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
