package com.example.taxadeservico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
// Nome: Thiago da Silva Moura
// ID: 968241
public class MainActivity extends AppCompatActivity {

    // Objetos formatadores de moeda corrente E porcentagem!
    private static final NumberFormat moedaFormatada = NumberFormat.getCurrencyInstance();
    private static final NumberFormat porcentagemFormatada = NumberFormat.getPercentInstance();

    // Valor da conta inserida
    private double valorConta = 0.0;
    // Valor inicial da porcentagem da Tx Serviço
    private double percent = 0.15;
    // Mostra o valor da conta Formato
    private TextView contaTextView;
    // Mostra a porcentagem da Tx de serviço
    private TextView porcentagemTextView;
    // Mostra o valor do serviço já calculado
    private TextView taxaTextView;
    // Mostra o total já calculado
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contaTextView = (TextView) findViewById(R.id.valorTextView);
        porcentagemTextView = (TextView) findViewById(R.id.percentTextView);
        taxaTextView = (TextView) findViewById(R.id.GorjetaTextView);
        totalTextView = (TextView) findViewById(R.id.TotalTextView);

        taxaTextView.setText(moedaFormatada.format(0));
        totalTextView.setText(moedaFormatada.format(0));

        // Configura o receptor TextWatcher de valorEditText
        EditText valorEditText = (EditText) findViewById(R.id.valorEditText);
        valorEditText.addTextChangedListener(valorEditTextWatcher);

        // Configura o receptor OnSeekbarChangeListener de porcentagemSeekbar
        SeekBar porcentagemSeekbar = (SeekBar) findViewById(R.id.percentSeekBar);
        porcentagemSeekbar.setOnSeekBarChangeListener(seekBarListener);
    }

    private void calcular() {
        //Formata a porcentagem e exibe no componente
        porcentagemTextView.setText(porcentagemFormatada.format(percent));

        // Calcula a gorjeta e o total
        double tx = valorConta * percent;
        double total = valorConta + tx;

        // Exibe a gorjeta e o total, formatados já em moeda corrente!
        taxaTextView.setText((moedaFormatada.format(tx)));
        totalTextView.setText(moedaFormatada.format(total));
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    percent = progress / 100.0;
                    calcular();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

            private final TextWatcher valorEditTextWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                    try {
                        valorConta = Double.parseDouble(s.toString());
                        contaTextView.setText(moedaFormatada.format(valorConta));
                    } catch (NumberFormatException e) {
                        contaTextView.setText("");
                        valorConta = 0.0;
                    }
                    calcular();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };

}