package com.ifsp.tosegura.remote;

public class APIUtils {

    private APIUtils(){ };

    public static final String API_URL = "https://to-segura-rest.herokuapp.com/rest/";

    public static CadastroService getCadastroService(){
       return RetrofitClient.getClient(API_URL).create(CadastroService.class);
    }
}
