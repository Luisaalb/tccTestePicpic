package com.example.testepicpic.fragment;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testepicpic.R;
import com.example.testepicpic.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastroNomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroNomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private TextInputEditText nome, idade, peso, altura;
    private TextInputLayout lnome, lidade, lpeso, laltura;
    private Spinner genero;
    private Button btnPronto01;

    public String pNome, pIdade, pPeso, pAltura, pGenero;

    private CadastroTipoDiabetesFragment cadastroTipoDiabetesFragment = new CadastroTipoDiabetesFragment();
    private Usuario user = new Usuario();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CadastroNomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastroNomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroNomeFragment newInstance(String param1, String param2) {
        CadastroNomeFragment fragment = new CadastroNomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_nome, container, false);

        genero = view.findViewById(R.id.spinnergenero2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.genero, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter);
        genero.setOnItemSelectedListener(this);

        lnome = view.findViewById(R.id.textInputLayout);
        lidade = view.findViewById(R.id.textInputLayout2);
        laltura = view.findViewById(R.id.textInputLayout4);
        lpeso = view.findViewById(R.id.textInputLayout5);


        nome = view.findViewById(R.id.edtNome);
        idade = view.findViewById(R.id.edtIdade);
        peso = view.findViewById(R.id.edtPeso);
        altura = view.findViewById(R.id.edtAltura);

        btnPronto01 = view.findViewById(R.id.btnPronto01);

        btnPronto01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = nome.getText().toString();
                String textoIdade = idade.getText().toString();
                String textoPeso = peso.getText().toString();
                String textoAltura = altura.getText().toString();
                String textoGenero = genero.getSelectedItem().toString();

                if(!textoNome.isEmpty()) {
                    nome.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edt_cadastro1));
                    if(!textoIdade.isEmpty()) {
                        idade.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edt_cadastro_pqn));
                        if(!textoPeso.isEmpty()) {
                            peso.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edt_cadastro_pqn));
                            if(!textoAltura.isEmpty()) {
                                altura.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edt_cadastro_pqn));
                                    pNome = textoNome;
                                    pIdade = textoIdade;
                                    pAltura = textoAltura;
                                    pPeso = textoPeso;
                                    pGenero = textoGenero;

                                    FragmentManager manager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction transaction = manager.beginTransaction();
                                    Bundle args = new Bundle();
                                    args.putString("pNome", pNome);
                                    args.putString("pIdade", pIdade);
                                    args.putString("pAltura", pAltura);
                                    args.putString("pPeso", pPeso);
                                    args.putString("pGenero", pGenero);
                                    cadastroTipoDiabetesFragment.setArguments(args);
                                    transaction.setCustomAnimations( R.anim.to_left, R.anim.from_right, R.anim.to_left, R.anim.from_right);
                                    transaction.replace(R.id.frameConteudoCad, cadastroTipoDiabetesFragment);
                                    transaction.addToBackStack(null);
                                    transaction.commit();

                            } else {
                                altura.setError(null);
                                altura.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edt_cadastro_erro_pqn));
                                Toast.makeText(getActivity(), "Prencha o campo altura", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            peso.setError(null);
                            peso.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edt_cadastro_erro_pqn));
                            Toast.makeText(getActivity(), "Prencha o campo campo peso", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        idade.setError(null);
                        idade.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edt_cadastro_erro_pqn));
                        Toast.makeText(getActivity(),"Preenca o campo idade", Toast.LENGTH_LONG).show();
                    }
                } else {
                    nome.setError(null);
                    nome.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.edt_cadastro_erro));
                    Toast.makeText(getActivity(), "Preencha o campo nome", Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}