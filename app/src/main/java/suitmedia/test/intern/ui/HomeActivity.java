package suitmedia.test.intern.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import suitmedia.test.intern.R;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getToolbarTitle() {
        return "Home";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupView() {

    }

    @OnClick(R.id.btn_next)
    public void onItemClick(View v){
        if (v.getId() == R.id.btn_next){
            if(TextUtils.isEmpty(etName.getText())){
                etName.setError( "Name is required!" );
            }
            else{
                Intent intent = new Intent(this, ChooseActivity.class);
                Bundle extras = new Bundle();
                extras.putString("name", etName.getText().toString());
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }

}
