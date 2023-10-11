package br.edu.unis.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class PanelActivity extends AppCompatActivity {

    TextView txtGreetings;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        String login = (String) Objects.requireNonNull(this.getIntent().getExtras()).get("login");

        txtGreetings = findViewById(R.id.panel_txt_greetings);

        String greetings = String.format(getString(R.string.panel_txt_greetings), login);
        txtGreetings.setText(greetings);

        btnLogout = findViewById(R.id.panel_btn_logout);
        btnLogout.setOnClickListener(view -> {
            SharedPreferences sp = this.getSharedPreferences("login", MODE_PRIVATE);
            sp.edit().clear().apply();
            finishFromChild(this);
        });
    }
}