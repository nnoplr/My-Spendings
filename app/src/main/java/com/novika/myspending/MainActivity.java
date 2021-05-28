package com.novika.myspending;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_REPLY = 1;

    RecyclerView rvSpendings;

    SpendingsDB spendingsDB;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

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
        Intent intent = null;
        if (item.getItemId() == R.id.Add) {
            intent = new Intent(this,AddData.class);

        }
        if(intent!=null){
            startActivityForResult(intent, REQUEST_CODE_REPLY);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centerActionBar();
        rvSpendings = findViewById(R.id.rvSpendings);


        spendingsDB = new SpendingsDB(this);
        ArrayList<Spendings> spendings = spendingsDB.getAllProducts();


        SpendingAdapter spendingAdapter = new SpendingAdapter();
        spendingAdapter.setArrayListData(spendings);

        rvSpendings.setAdapter(spendingAdapter);
        rvSpendings.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_REPLY && resultCode == Activity.RESULT_OK) {

            spendingsDB = new SpendingsDB(this);
            ArrayList<Spendings> spendings = spendingsDB.getAllProducts();

            SpendingAdapter spendingAdapter = new SpendingAdapter();
            spendingAdapter.setArrayListData(spendings);

            rvSpendings.setAdapter(spendingAdapter);
            rvSpendings.setLayoutManager(new LinearLayoutManager(this));
        }

    }


}