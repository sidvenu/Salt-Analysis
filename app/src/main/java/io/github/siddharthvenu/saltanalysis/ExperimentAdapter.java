package io.github.siddharthvenu.saltanalysis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by siddh on 02-03-2017.
 */

public class ExperimentAdapter extends ArrayAdapter<Experiment> {
    private ArrayList<Experiment> data;

    public ExperimentAdapter(ArrayList<Experiment> data, Context context) {
        super(context, R.layout.experiment_item, data);
        this.data=data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.experiment_item, parent, false);
        }
        TextView textView= (TextView) convertView.findViewById(R.id.experiment_text_view);
        textView.setText(Html.fromHtml(data.get(position).getExperiment().replace("\n","<br />")));

        textView = (TextView) convertView.findViewById(R.id.observation_text_view);
        textView.setText(Html.fromHtml(data.get(position).getObservation().replace("\n","<br />")));

        textView = (TextView) convertView.findViewById(R.id.conclusion_text_view);
        textView.setText(Html.fromHtml(data.get(position).getConclusion().replace("\n","<br />")));

        return convertView;
    }
}
