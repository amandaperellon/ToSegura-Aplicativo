package com.ifsp.tosegura.remote;

import retrofit2.Call;

import com.ifsp.tosegura.model.Cadastro;
import com.ifsp.tosegura.model.ResObj;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CadastroService {

    @GET("Usuarios/") //link
    Call<List<Cadastro>> getCadastros();

    @GET("Usuarios/login/{email}/{senha}") //link
    Call<ResObj> getLogin(@Path("email") String email, @Path("senha") String senha);

    @POST("Usuarios/")
    Call<Cadastro> addCadastro(@Body Cadastro cadastro);

    @PUT("Usuarios/{id}")
    Call<Cadastro> updateCadastro(@Path("id") int id, @Body Cadastro cadastro );

    @DELETE("Usuarios/{id}")
    Call<Cadastro> deleteCadastro (@Path("id") int id);
}

