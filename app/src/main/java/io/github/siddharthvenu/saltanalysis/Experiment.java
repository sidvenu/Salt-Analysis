package io.github.siddharthvenu.saltanalysis;

/**
 * Created by siddh on 02-03-2017.
 */

public class Experiment {
    private String experiment, observation, conclusion;

    public Experiment(String experiment, String observation, String conclusion){
        this.experiment=experiment;
        this.observation=observation;
        this.conclusion=conclusion;
    }

    public void setExperiment(String experiment){
        this.experiment=experiment;
    }
    public String getExperiment(){
        return experiment;
    }

    public void setObservation(String observation){
        this.observation=observation;
    }
    public String getObservation() {
        return observation;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getConclusion() {
        return conclusion;
    }
}
