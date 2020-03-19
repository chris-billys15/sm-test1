package suitmedia.test.intern.ui.base;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import butterknife.ButterKnife;
import suitmedia.test.intern.R;

public abstract class BaseActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));
    }

    protected abstract String getToolbarTitle();

    protected abstract int getLayoutId();

    protected abstract void setupView();

    public void setToolBar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                if (getToolbarTitle() != null) {
//                    setDisplayHomeEnabled(true);
                }
            }
        }
    }

    public void setDisplayHomeEnabled(boolean b) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(b);
        }
    }
}
