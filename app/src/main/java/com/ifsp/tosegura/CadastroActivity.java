package com.ifsp.tosegura;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ifsp.tosegura.model.Cadastro;
import com.ifsp.tosegura.remote.APIUtils;
import com.ifsp.tosegura.remote.CadastroService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    CadastroService userService;
    EditText edtEmail2;
    EditText edtUsername;
    EditText edtSenha2;
    Button btnCadastrar;
    TextView txtLogin;

    public void abrirLogin(View view){
        Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(i);

    }

    public void abrirFormulario(View view){
        Intent i = new Intent(CadastroActivity.this, FormularioActivity.class);
        startActivity(i);

    }

    Button buttonCadastrar;
    CadastroService cadastroService;
    List<Cadastro> list = new ArrayList<Cadastro>();

    public void addUser(Cadastro user){
        Call<Cadastro> call = cadastroService.addCadastro(user);
        call.enqueue(new Callback<Cadastro>() {
            @Override
            public void onResponse(Call<Cadastro> call, Response<Cadastro> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Usu�rio cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Cadastro> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    protected void onCreate(Bundle savedInstanteState) {
        super.onCreate(savedInstanteState);
        setContentView(R.layout.activity_cadastro);

        edtUsername = (EditText) findViewById(R.id.TextNome);
        edtEmail2 = (EditText) findViewById(R.id.TextEmail2);
        edtSenha2 = (EditText) findViewById(R.id.TextSenha2);
        btnCadastrar = (Button) findViewById(R.id.buttonCadastrar);
        txtLogin = (TextView) findViewById(R.id.TextLogin);

        buttonCadastrar = (Button) findViewById(R.id.buttonCadastrar);
        cadastroService = APIUtils.getCadastroService();

        buttonCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cadastro u = new Cadastro();
                u.setNome(edtUsername.getText().toString());
                u.setEmail(edtEmail2.getText().toString());
                u.setSenha(edtSenha2.getText().toString());
                addUser(u);
            }
        });
    }
}
