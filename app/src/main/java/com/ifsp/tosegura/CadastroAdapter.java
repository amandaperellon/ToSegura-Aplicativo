package com.ifsp.tosegura;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ifsp.tosegura.model.Cadastro;

import java.util.List;

public class CadastroAdapter extends ArrayAdapter<Cadastro> {

    private Context context;
    private List<Cadastro> cadastros;



    public CadastroAdapter(Context context, int resource, List<Cadastro> objects) {
        super(context, resource, objects);
        this.context = context;
        this.cadastros = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_cadastro, parent, false);

        TextView txtCadastroId = rowView.findViewById(R.id.txtCadastroId);
        TextView txtCadastroNome = rowView.findViewById(R.id.txtCadastroNome);

        txtCadastroId.setText(String.format("cadastro_id: %d", cadastros.get(pos).getId()));
        txtCadastroNome.setText(String.format("cadastro_nome: %s", cadastros.get(pos).getNome()));

        ((View) rowView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start Activity Cadastro Form
                Intent intent = new Intent(context, Cadastro.class);
                intent.putExtra("cadastro_id", String.valueOf(cadastros.get(pos).getId()));
                intent.putExtra("cadastro_nome", cadastros.get(pos).getNome());
                context.startActivity(intent);

            }
        });

        return rowView;
    }
}
