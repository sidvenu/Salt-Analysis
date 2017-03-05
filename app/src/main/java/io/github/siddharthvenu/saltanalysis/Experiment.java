package io.github.siddharthvenu.saltanalysis;

/**
 * Created by siddh on 02-03-2017.
 */

class Experiment {
    private String experiment, observation, conclusion;

    Experiment(String experiment, String observation, String conclusion) {
        setExperiment(experiment);
        setObservation(observation);
        setConclusion(conclusion);
    }

    private void setExperiment(String experiment) {
        this.experiment = experiment.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }

    String getExperiment() {
        return experiment;
    }

    private void setObservation(String observation) {
        this.observation = observation.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }

    String getObservation() {
        return observation;
    }

    private void setConclusion(String conclusion) {
        this.conclusion = conclusion.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }

    String getConclusion() {
        return conclusion;
    }
}
