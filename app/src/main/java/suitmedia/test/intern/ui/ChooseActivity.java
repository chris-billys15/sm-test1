package suitmedia.test.intern.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.OnClick;
import suitmedia.test.intern.R;
import suitmedia.test.intern.ui.event.EventActivity;
import suitmedia.test.intern.ui.guest.GuestActivity;

public class ChooseActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_event)
    Button btnEvent;
    @BindView(R.id.btn_guest)
    Button btnGuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
    }

    @Override
    protected String getToolbarTitle() {
        return "Choose";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose;
    }

    protected void setupView(){
        tvName.setText(getIntent().getExtras().getString("name"));
    }

    @OnClick({R.id.btn_guest, R.id.btn_event})
    public void onItemClick(View v){
        if (v.getId() == R.id.btn_event){
            Intent intent = new Intent(this, EventActivity.class);
            startActivityForResult(intent,1);
        }
        else if(v.getId() == R.id.btn_guest){
            Intent intent = new Intent(this, GuestActivity.class);
            startActivityForResult(intent,2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            btnEvent.setText(data.getStringExtra("event_name"));
        }
        else{
            btnGuest.setText(data.getStringExtra("guest_name"));
            String strDate = data.getStringExtra("birth_date");
            String[] str = strDate.split("-");
            int day = Integer.parseInt(str[2]);
            if(day % 2 ==0 && day %3 ==0){
                Toast.makeText(this, "iOS", Toast.LENGTH_SHORT).show();
            }
            else if(day%2 == 0 ){
                Toast.makeText(this, "blackberry", Toast.LENGTH_SHORT).show();
            }
            else if(day%3 == 0 ){
                Toast.makeText(this, "android", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Tanggal Lahir bukan kelipatan 2 atau 3", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
