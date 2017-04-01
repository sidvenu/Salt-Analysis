package io.github.siddharthvenu.saltanalysis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static io.github.siddharthvenu.saltanalysis.ProjectUtilities.formatString;
/**
 * Created by siddh on 02-03-2017.
 */

class ExperimentAdapter extends ArrayAdapter<Experiment> {
    private ArrayList<Experiment> data;

    ExperimentAdapter(ArrayList<Experiment> data, Context context) {
        super(context, R.layout.experiment_item, data);
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.experiment_item, parent, false);
        }
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.expt_linear_layout);
        TextView generalText = (TextView) convertView.findViewById((R.id.general_text_view));

        Experiment curExpt = data.get(position);

        if(TextUtils.isEmpty(curExpt.getObservation())&&TextUtils.isEmpty(curExpt.getConclusion())){
            linearLayout.setVisibility(View.GONE);
            generalText.setText(formatString(curExpt.getExperiment()));
            generalText.setVisibility(View.VISIBLE);
        }
        else {
            linearLayout.setVisibility(View.VISIBLE);
            generalText.setVisibility(View.GONE);

            TextView textView = (TextView) convertView.findViewById(R.id.experiment_text_view);
            textView.setText(formatString(curExpt.getExperiment()));

            textView = (TextView) convertView.findViewById(R.id.observation_text_view);
            textView.setText(formatString(curExpt.getObservation()));

            textView = (TextView) convertView.findViewById(R.id.conclusion_text_view);
            textView.setText(formatString(curExpt.getConclusion()));
        }
        return convertView;
    }
}
