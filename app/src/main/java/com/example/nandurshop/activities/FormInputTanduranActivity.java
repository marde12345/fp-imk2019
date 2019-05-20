package com.example.nandurshop.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nandurshop.Model.Commodity;
import com.example.nandurshop.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class FormInputTanduranActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    Commodity plant = new Commodity();
    DatePickerDialog datePickerDialog;
    SimpleDateFormat simpleDateFormat;
    Button btnDatePicker;
    EditText etNama;
    FloatingActionButton fabForm;
    TextView tvDateResult;
    Spinner sp;
    String datefix;
    String[] variety = {
            "Bayam",
            "Tomat",
            "Lombok",
            "Sawi",
            "Bawang"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_input_tanduran);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        tvDateResult = (TextView) findViewById(R.id.dateResult);
        btnDatePicker = (Button) findViewById(R.id.datePicker);
        sp = (Spinner) findViewById(R.id.sp_variety);
        etNama = (EditText) findViewById(R.id.etNama);
        fabForm = (FloatingActionButton) findViewById(R.id.fabForm);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, variety);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FormInputTanduranActivity.this, adapter.getItem(position)+" dipilih", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnDatePicker.setOnClickListener(this);
        fabForm.setOnClickListener(this);
    }
    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                datefix = simpleDateFormat.format(newDate.getTime());
                tvDateResult.setText("Tanggal dipilih : "+datefix);
                plant.setPlantedAt(datefix);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.fabForm:
                intent = new Intent(this,CameraFormActivity.class);
                plant.setName(etNama.getText().toString());
                plant.setVarietyId(Arrays.asList(variety).indexOf(sp.getSelectedItem().toString()));
                intent.putExtra("plant", plant);
                startActivity(intent);
                break;
            case R.id.datePicker:
                showDateDialog();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
