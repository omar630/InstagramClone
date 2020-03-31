package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);
    }
    @Override
    public void onClick(View v){
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            edtName = findViewById(R.id.edtName);
            edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
            edtPunchPower = findViewById(R.id.edtPunchPower);
            edtKickPower = findViewById(R.id.edtKickPower);
            edtKickSpeed = findViewById(R.id.edtPunchSpeed);
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", edtPunchPower.getText().toString());
            kickBoxer.put("punchPower", edtPunchPower.getText().toString());
            kickBoxer.put("KickSpeed", edtKickSpeed.getText().toString());
            kickBoxer.put("kickPower", edtKickPower.getText().toString());
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to Server.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        }catch(Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}
