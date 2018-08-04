package cl.vero.studyingstatistics;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.vero.studyingstatistics.adapters.StatisticalConceptAdapter;
import cl.vero.studyingstatistics.adapters.StatisticalConceptClickListener;
import cl.vero.studyingstatistics.models.StatisticalConcept;

import static cl.vero.studyingstatistics.R.id.toolbar;

public class MainActivity extends AppCompatActivity implements StatisticalConceptClickListener{

    private StatisticalConceptAdapter adapter;
    public static final String KEY_ITEM = "cl.vero.studyingstatistics.KEY.KEY_ITEM";
    List<StatisticalConcept> statisticalConceptList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.statisticalConceptRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        statisticalConceptList = new ArrayList<>();

        adapter = new StatisticalConceptAdapter(statisticalConceptList,this);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.dialog_statistical_concept);

                Button saveBtn = dialog.findViewById(R.id.saveBtn);
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText nameEt = dialog.findViewById(R.id.dialogNameEt);
                        EditText gradeEt = dialog.findViewById(R.id.dialogGradeEt);
                        EditText descriptionEt = dialog.findViewById(R.id.dialogDescriptionEt);
                        String name = nameEt.getText().toString();
                        String grade = gradeEt.getText().toString();
                        String description = descriptionEt.getText().toString();
                        if (name.trim().length() > 0 && description.trim().length() > 0 && grade.contains("1") || grade.contains("2") || grade.contains("3") || grade.contains("4")){
                            StatisticalConcept statisticalConcept = new StatisticalConcept();
                            statisticalConcept.setName(name);
                            statisticalConcept.setGrade(Integer.parseInt(grade));
                            statisticalConcept.setDescription(description);
                            statisticalConcept.save();
                            updateList(statisticalConcept);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Ingrese toda la información solicitada. Los niveles de Enseñanza Media son 1, 2, 3 o 4 Medio.", Toast.LENGTH_SHORT).show(); }
                    }
                });

                dialog.getWindow().setLayout(-1,-2);
                dialog.show();
            }
        });


    }

    public void updateList(StatisticalConcept statisticalConcept){
        adapter.update(statisticalConcept);
    }

    @Override
    public void clickedId(int id) {
        Log.d("TAG", String.valueOf(id));
        StatisticalConcept statisticalConcept = statisticalConceptList.get(id);
        Intent intent = new Intent(MainActivity.this,DescriptionActivity.class);
        intent.putExtra(KEY_ITEM, statisticalConcept.getId());
        startActivity(intent);
    }


}
