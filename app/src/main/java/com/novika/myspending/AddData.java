package com.novika.myspending;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddData extends AppCompatActivity {

    EditText edName, edNom;
    Button btnSubmit;

    SpendingsDB spendingsDB;

    private void centerActionBar() {

        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        AppCompatTextView appCompatTextView = (AppCompatTextView) textViews.get(0);

        ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        appCompatTextView.setLayoutParams(params);
        appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        //tengahin action bar
        centerActionBar();

        // panggil action bar
        ActionBar actionBar = getSupportActionBar();

        // tunjukkan back button di action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        edName = findViewById(R.id.textName2);
        edNom = findViewById(R.id.textNominal2);
        btnSubmit = findViewById(R.id.btnSubmit);

        spendingsDB = new SpendingsDB(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                String datee = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

                String name = edName.getText().toString();
                String nominal = edNom.getText().toString();
                int countNumber = 0;

                if (nominal.length() > 0) {
                    int temp = 0;
                    char a;
                    for (int N = 0; N < nominal.length(); N++) {
                        a = nominal.charAt(N);
                        temp = (int) a;
                        if (temp < 48 || temp > 57) {
                            countNumber++;
                        }
                    }
                }

                if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Spending Name must be filled", Toast.LENGTH_SHORT).show();
                } else if (nominal.equals("")) {
                    Toast.makeText(getApplicationContext(), "Nominal must be filled", Toast.LENGTH_SHORT).show();
                } else if (countNumber > 0) {
                    Toast.makeText(getApplicationContext(), "Nominal must be numbers", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();


                    Spendings spendings = new Spendings();
                    spendings.name = edName.getText().toString();
                    spendings.nominal = Integer.parseInt(edNom.getText().toString());
                    spendings.date = datee;

                    spendingsDB.insertProduct(spendings);

                    setResult(Activity.RESULT_OK, intent);

                    finish();
                }

            }
        });

    }


}