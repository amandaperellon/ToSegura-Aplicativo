package com.ifsp.tosegura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ifsp.tosegura.model.Cadastro;
import com.ifsp.tosegura.model.ResObj;
import com.ifsp.tosegura.remote.APIUtils;
import com.ifsp.tosegura.remote.CadastroService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    CadastroService userService;
    EditText edtEmail;
    EditText edtSenha;
    Button btnEntrar;
    TextView txtCadastrese;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }*/

    public void abrirCadastro(View view){
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);

    }

    public void abrirFormulario(View view){
        Intent i = new Intent(LoginActivity.this, FormularioActivity.class);
        startActivity(i);

    }

    private void doLogin(final String username,final String password){
        Call<ResObj> call = userService.getLogin(username,password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if(response.isSuccessful()){
                    ResObj resObj = response.body();
                    if(resObj.getStatus().equals("login efetuado com sucesso")){
                        Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "O usuario ou a senha est�o incorretos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Erro, por favor tente novamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanteState) {
        super.onCreate(savedInstanteState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.TextEmail);
        edtSenha = (EditText) findViewById(R.id.TextSenha);
        btnEntrar = (Button) findViewById(R.id.buttonEntrar);
        txtCadastrese = (TextView) findViewById(R.id.TextCadastrese);

        //buttonCadastrar = (Button) findViewById(R.id.buttonCadastrar);
        userService = APIUtils.getCadastroService();

        btnEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cadastro u = new Cadastro();
                u.setEmail(edtEmail.getText().toString());
                u.setSenha(edtSenha.getText().toString());
                doLogin(u.getEmail(), u.getSenha());

            }
        });
    }
}
