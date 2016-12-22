package hackaton.com.br.hackatonapp.simulacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import hackaton.com.br.hackatonapp.R;
import hackaton.com.br.hackatonapp.ui.MainActivity;

public class ChequeDevolvidoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheque_devolvido);

        findViewById(R.id.imageButton_chequedevolvido).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
