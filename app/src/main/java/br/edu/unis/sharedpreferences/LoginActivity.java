package br.edu.unis.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtLogin;
    EditText edtPassword;
    CheckBox chkKeepConnected;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp = this.getSharedPreferences("login", MODE_PRIVATE);
        if (sp.contains("login")) {
            Intent intent = new Intent(this, PanelActivity.class);
            intent.putExtra("login", sp.getString("login", ""));
            startActivityForResult(intent, 1);
            finish();
        }

        edtLogin = findViewById(R.id.login_edt_login);
        edtPassword = findViewById(R.id.login_edt_password);
        chkKeepConnected = findViewById(R.id.login_chk_keep_connected);
        btnLogin = findViewById(R.id.login_btn_login);

        btnLogin.setOnClickListener(view -> {
            String strLogin = edtLogin.getText().toString();
            String strPassword = edtPassword.getText().toString();

            String expectedLogin = getString(R.string.fake_login);
            String expectedPassword = getString(R.string.fake_password);

            if (!strLogin.equals(expectedLogin) && !strPassword.equals(expectedPassword)) {
                Toast.makeText(this, R.string.login_txt_wrong_credentials, Toast.LENGTH_LONG)
                    .show();
                return;
            }

            if (chkKeepConnected.isChecked()) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("login", strLogin);
                editor.apply();
            }

            Intent intent = new Intent(this, PanelActivity.class);
            intent.putExtra("login", strLogin);

            startActivityForResult(intent, 1);
            finish();
        });
    }
}
