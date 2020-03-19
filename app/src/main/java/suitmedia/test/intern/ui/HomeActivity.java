package suitmedia.test.intern.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.internal.Utils;
import okhttp3.internal.Util;
import suitmedia.test.intern.R;
import suitmedia.test.intern.ui.base.BaseActivity;
import suitmedia.test.intern.util.Utility;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.btn_next)
    Button btnNext;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        return R.layout.activity_home;
    }

    @Override
    protected void setupView() {

    }

    public void showAlertDialog(String str){
        Intent intent = new Intent(this, ChooseActivity.class);
        new AlertDialog.Builder(this)
                .setTitle("Palindrome Checker")
                .setMessage(str)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        dialog.dismiss();
                        Bundle extras = new Bundle();
                        extras.putString("name", etName.getText().toString());
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setIcon(android.R.drawable.btn_star)
                .show();
    }
    @OnClick(R.id.btn_next)
    public void onItemClick(View v){
        if (v.getId() == R.id.btn_next){
            if(TextUtils.isEmpty(etName.getText())){
                etName.setError( "Name is required!" );
            }
            else{
                if(Utility.isPalindrome(etName.getText().toString())){
                    showAlertDialog(etName.getText().toString()+ " is a Palindrome");
                }
                else{
                    showAlertDialog(etName.getText().toString()+ " is not a Palindrome");
                }
            }
        }
    }

}
