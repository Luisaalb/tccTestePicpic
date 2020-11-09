package com.example.testepicpic.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testepicpic.R;
import com.example.testepicpic.fragment.AddAlimentacaoFragment;
import com.example.testepicpic.fragment.AddBemEstarFragment;
import com.example.testepicpic.fragment.AddExercicioFragment;
import com.example.testepicpic.fragment.AddGlicemiaFragment;
import com.example.testepicpic.fragment.AddInsulinaFragment;
import com.example.testepicpic.fragment.CalendarFragment;
import com.example.testepicpic.fragment.OverviewFragment;
import com.example.testepicpic.fragment.RelatorioFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.example.testepicpic.config.ConfigFirebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth autenticacao;

    private ImageButton fabButton, buttonPerfil;
    private OvershootInterpolator interpolator = new OvershootInterpolator();
    private boolean menuAberto = true;
    private int position;

    private BottomNavigationView bottomNavigationView;
    private static final String TAG = "MainActivity";
    private OverviewFragment overviewFragment = new OverviewFragment();
    private CalendarFragment calendarFragment = new CalendarFragment();
    private RelatorioFragment relatorioFragment = new RelatorioFragment();

    private ImageButton btnHumor,btnAlimento,btnInsulina,btnExercicio,btnGlicemia;

    private AddBemEstarFragment addBemEstarFragment = new AddBemEstarFragment();
    private AddAlimentacaoFragment addAlimentacaoFragment = new AddAlimentacaoFragment();
    private AddExercicioFragment addExercicioFragment = new AddExercicioFragment();
    //private AddGlicemiaFragment addGlicemiaFragment = new AddGlicemiaFragment();
    private AddInsulinaFragment addInsulinaFragment = new AddInsulinaFragment();

    private LineChart graficoGlicemia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graficoGlicemia = (LineChart) findViewById(R.id.grafico_glicemia);

        fabButton = findViewById(R.id.fab_button);
        buttonPerfil = findViewById(R.id.btnPerfil);
        final ConstraintLayout c = findViewById(R.id.constrait);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.item_overview);

        btnHumor = findViewById(R.id.btnHumor);
        btnAlimento = findViewById(R.id.btnAlimento);
        btnExercicio = findViewById(R.id.btnExercicio);
        btnGlicemia = findViewById(R.id.btnGlicemia);
        btnInsulina = findViewById(R.id.bntInsulina);


        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuAberto) {
                    fabButton.animate().setInterpolator(interpolator).rotation(45f).setDuration(500).start();
                    Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.to_down);

                    c.startAnimation(animation);

                    menuAberto =! menuAberto;
                    c.setVisibility(View.VISIBLE);


                } else {
                    fabButton.animate().setInterpolator(interpolator).rotation(0f).setDuration(500).start();
                    Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.from_top);

                    c.startAnimation(animation);

                    menuAberto =! menuAberto;
                    c.setVisibility(View.GONE);

                }
            }
        });

        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AjustesActivity.class));
            }
        });


        btnHumor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = 0;
                Intent intent = new Intent(MainActivity.this, AddInfosActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });

        btnAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = 1;
                Intent intent = new Intent(MainActivity.this, AddInfosActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });

        btnExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = 2;
                Intent intent = new Intent(MainActivity.this, AddInfosActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });

        btnInsulina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = 3;
                Intent intent = new Intent(MainActivity.this, AddInfosActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        btnGlicemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = 4;
                Intent intent = new Intent(MainActivity.this, AddInfosActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });


    }

    public void sair(View view){
        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        autenticacao.signOut();
        startActivity(new Intent(this, SliderActivity.class));
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        TextView txtOndaPrincipal = (TextView) findViewById(R.id.txt_onda_principal);
        FrameLayout ondaPrincipal = (FrameLayout) findViewById(R.id.onda_principal);
        BottomNavigationView nav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        ImageButton fabButton = (ImageButton) findViewById(R.id.fab_button);

        switch(item.getItemId()) {
            case R.id.item_overview:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.substituicao, overviewFragment).commit();
                txtOndaPrincipal.setText("Visão geral");
                return true;

            case R.id.item_calendario:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.substituicao, calendarFragment).commit();
                txtOndaPrincipal.setText("Calendário");
                return true;

            case R.id.item_reltorio:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.substituicao, relatorioFragment).commit();
                txtOndaPrincipal.setText("Relatório");
                return true;
        }

        return false;
    }
}