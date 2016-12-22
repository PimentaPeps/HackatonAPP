package hackaton.com.br.hackatonapp.simulacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.ui.MainActivity;

public class TrocarSenhaActivity extends AppCompatActivity implements View.OnClickListener {

    private int status_changer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_senha);
        findViewById(R.id.imageButton_novasenhacartao).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (status_changer == 0) {
            status_changer = 1;
            ((ImageButton) findViewById(R.id.imageButton_novasenhacartao)).setImageResource(R.drawable.novasenhacartao2);
            Intent i = new Intent(this, this.getClass());
            finish();
            startActivity(i);
        }

        if (status_changer == 1) {
            Intent i = new Intent(this, MainActivity.class);
            finish();
            startActivity(i);
        }
    }
}
