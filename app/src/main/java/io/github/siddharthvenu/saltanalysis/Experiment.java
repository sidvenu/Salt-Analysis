package io.github.siddharthvenu.saltanalysis;

/**
 * Created by siddh on 02-03-2017.
 */
class Experiment {
    private String experiment, observation, conclusion;
    private String tag = null;
    private boolean isDryTest;

    Experiment(String experiment, String observation, String conclusion, String tag, boolean isDryTest) {
        setExperiment(experiment);
        setObservation(observation);
        setConclusion(conclusion);
        this.isDryTest = isDryTest;
        boolean isAllowed = false;
        String[] allowedTags = {"HCL", "H2SO4", "HEAT", "AGNO3", "SPL", "CONTINUE"};
        for (String x : allowedTags) {
            if (x.equals(tag)) {
                isAllowed = true;
                break;
            }
        }
        if (isAllowed)
            this.tag = tag;
        else throw new IllegalArgumentException("ExperimentCollection tag illegal");
    }

    Experiment(String experiment, String observation, String conclusion) {
        this.experiment = experiment;
        this.observation = observation;
        this.conclusion = conclusion;
    }

    String getExperiment() {
        return experiment;
    }

    private void setExperiment(String experiment) {
        this.experiment = experiment.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }

    String getObservation() {
        return observation;
    }

    private void setObservation(String observation) {
        this.observation = observation.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }

    String getConclusion() {
        return conclusion;
    }

    private void setConclusion(String conclusion) {
        this.conclusion = conclusion.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }

    String getTag() {
        return tag;
    }

    boolean isDryTest() {
        return isDryTest;
    }
}
