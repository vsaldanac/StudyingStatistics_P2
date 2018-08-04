package cl.vero.studyingstatistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import cl.vero.studyingstatistics.models.StatisticalConcept;

public class DescriptionActivity extends AppCompatActivity {

    private StatisticalConcept statisticalConcept;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        textView = findViewById(R.id.descriptionTv);

        long id = getIntent().getLongExtra(MainActivity.KEY_ITEM,0);

        statisticalConcept = StatisticalConcept.findById(StatisticalConcept.class,id);
        getSupportActionBar().setTitle(statisticalConcept.getName());

    }

    @Override
    protected void onResume() {
        if (statisticalConcept.getDescription() != null){
            textView.setText(statisticalConcept.getDescription());
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        String description = String.valueOf(textView.getText());
        statisticalConcept.setDescription(description);
        statisticalConcept.save();
        super.onPause();
    }
}
