package com.example.testepicpic.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.testepicpic.helper.Base64Custom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.example.testepicpic.config.ConfigFirebase;
import com.google.firebase.database.Exclude;

public class Usuario {

    private String idUser;
    private String email;
    private String senha;
    private String nome;
    private int idade;
    private double altura;
    private double peso;
    private String genero;
    private String tipoDiabetes;
    private boolean utilizoInsulina;
    private boolean utilizoMedicacao;
    //private String[] medicacao;
    //private boolean[] lembretes;

    @Exclude
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoDiabetes() {
        return tipoDiabetes;
    }

    public void setTipoDiabetes(String tipoDiabetes) {
        this.tipoDiabetes = tipoDiabetes;
    }

    public boolean isUtilizoInsulina() {
        return utilizoInsulina;
    }

    public void setUtilizoInsulina(boolean utilizoInsulina) {
        this.utilizoInsulina = utilizoInsulina;
    }

    public boolean isUtilizoMedicacao() {
        return utilizoMedicacao;
    }

    public void setUtilizoMedicacao(boolean utilizoMedicacao) {
        this.utilizoMedicacao = utilizoMedicacao;
    }

    /*public String[] getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String[] medicacao) {
        this.medicacao = medicacao;
    }

    public boolean[] getLembretes() {
        return lembretes;
    }

    public void setLembretes(boolean[] lembretes) {
        this.lembretes = lembretes;
    }*/

    public Usuario() {
    }

    public void salvar(){
        DatabaseReference firebase = ConfigFirebase.getFirebase();
        firebase.child("users")
                .child(this.idUser)
                .setValue(this);
    }

  //PARA USAR NO PERFIL

   public static FirebaseUser getUsuarioAtual(){
        FirebaseAuth usuario = ConfigFirebase.getFirebaseAutenticacao();

        return usuario.getCurrentUser();

    }

    public static void atualizarNomeUsuario (String nome){
        try {

            FirebaseUser usuariologado = getUsuarioAtual();

            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(nome)
                    .build();
            usuariologado.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()) {
                        Log.d ("Perfil","Erro ao atualizar o nome no perfil");
                    }

                }
            });

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
