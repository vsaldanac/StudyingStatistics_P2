package cl.vero.studyingstatistics.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import cl.vero.studyingstatistics.R;
import cl.vero.studyingstatistics.models.StatisticalConcept;

public class StatisticalConceptAdapter extends RecyclerView.Adapter<StatisticalConceptAdapter.ViewHolder>{

    public StatisticalConceptAdapter(List<StatisticalConcept> statisticalConceptList, StatisticalConceptClickListener statisticalConceptClickListener) {
        this.statisticalConceptList = statisticalConceptList;
        this.statisticalConceptClickListener = statisticalConceptClickListener;
    }

    private List<StatisticalConcept> statisticalConceptList;
    private StatisticalConceptClickListener statisticalConceptClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_statistical_concept,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        StatisticalConcept statisticalConcept = statisticalConceptList.get(i);
        viewHolder.nameConcept.setText(statisticalConcept.getName());

        int image;
        if (statisticalConcept.getGrade() == 1){
            image = 0x7f0d0000;
        } else { if (statisticalConcept.getGrade() == 2){
            image = 0x7f0d0001;
        } else { if (statisticalConcept.getGrade() == 3){
            image = 0x7f0d0002;
        } else { image = 0x7f0d0003; }}}

        viewHolder.gradeConcept.setImageResource(image);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statisticalConceptClickListener.clickedId(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return statisticalConceptList.size();
    }

    public void update(StatisticalConcept statisticalConcept){
        statisticalConceptList.add(statisticalConcept);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameConcept;
        private ImageView gradeConcept;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameConcept = itemView.findViewById(R.id.nameConceptTv);
            gradeConcept = itemView.findViewById(R.id.gradeConceptIv);
        }
    }
}
