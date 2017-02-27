package io.github.siddharthvenu.saltanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by siddh on 27-02-2017.
 */

public class Radicals {
    private Map<Radical, Integer> indexRadical = new HashMap<>();

    private class Radical {
        String name, formula;
        List<String> experiment, observation, conclusion;

        Radical(String name, String formula, List<String> experiment, List<String> observation, List<String> conclusion) {
            this.name = name;
            this.formula = formula;
            this.experiment = experiment;
            this.observation = observation;
            this.conclusion = conclusion;
        }
    }

    public Radical getRadicalDetails(String name) {
        List<Radical> listRadical = new ArrayList<>();
        List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
        String formula, h2so4 = "H<sub><small>2</small></sub>SO<sub><small>4</small></sub>";

        /*********** Carbonate ***********/
        formula = "CO<sub><small>3</small></sub><sup><small>2-</small></sup>";

        experiment.add("Dry test tube heating:\nSalt is taken in a dry test tube and heated strongly");
        observation.add("A gas is evolved which turns lime water milky and extinguishes a lighted matchstick");
        conclusion.add("Presence of "+formula+" is confirmed");

        experiment.add("Action of dil.HCl:\nTo the salt, dil.HCl is added");
        observation.add("Brisk effervescence with the evolution of a gas which turns lime water milky");
        conclusion.add(formula+" is confirmed");

        listRadical.add(new Radical("Carbonate", formula, experiment,observation,conclusion));
        /***********----------***********/

        experiment.clear();
        observation.clear();
        conclusion.clear();

        /*********** Sulphide ***********/
        formula = "S<sup><small>2-</small></sup>";

        experiment.add("Action of dil.HCl:\nTo the salt, dil.HCl is added and heated");
        observation.add("A gas is evolved with the smell of a rotten egg");
        conclusion.add("H<sub><small>2</small></sub>S is the gas with the smell of a rotten egg. Salt maybe "+formula);

        experiment.add("Lead Acetate paper is held at the mouth of the test tube");
        observation.add("Turns black");
        conclusion.add(formula+" is confirmed");

        experiment.add("To the salt, dil.HCl is added and the gas is passed through acidified K<sub><small>2</small></sub>" +
                "Cr<sub><small>2</small></sub>O<sub><small>7</small></sub>");
        observation.add("The solution turns green with turbidity");
        conclusion.add(formula+" is confirmed. Turbidity is due to the deposition of sulphur");

        experiment.add("Special test for S<sup><small>2-</small></sup>:\nTo the salt, Sodium nitroprusside - Na" +
                "<sub><small>2</small></sub>[Fe(CN)<sub><small>6</small></sub>NO] is added");
        observation.add("Purple colouration");
        conclusion.add(formula+" is confirmed");

        listRadical.add(new Radical("Sulphide",formula, experiment,observation,conclusion));
        /***********----------***********/

        experiment.clear();
        observation.clear();
        conclusion.clear();

        /*********** Nitrite ***********/
        formula = "NO<sub><small>2</small></sub><sup><small>-</small></sup>";

        experiment.add("Dry Test:\nTo the salt, dil.HCl is added");
        observation.add("Pale brown fumes");
        conclusion.add("Maybe "+formula);

        experiment.add("Starch Iodide paper is held at the mouth of the test tube");
        observation.add("Turns blue");
        conclusion.add(formula+" is confirmed");

        experiment.add("Special test for Nitrite:\nTo the salt solution, dil." + h2so4 +
                " is added followed by KMnO<sub><small>4</small></sub>");
        observation.add("Solution becomes colourless");
        conclusion.add(formula+" is confirmed");

        experiment.add("Wet test(Ring test for "+formula+"):\nTo the salt solution, dil." + h2so4 +
                " is added followed by freshly prepared saturated solution of FeSO" +
                "<sub><small>4</small></sub> (In case of nitrate, we also use conc."+h2so4+")");
        observation.add("Brown ring appears at the junction of the aqueous layer and H<sub><small>2</small></sub>SO" +
                "<sub><small>4</small></sub>");
        conclusion.add("Due to the formation of Nitrocyl Ferrous Sulphate, brown ring is formed");

        listRadical.add(new Radical("Nitrite", formula, experiment,observation,conclusion));
        /***********----------***********/

        experiment.clear();
        observation.clear();
        conclusion.clear();

        /*********** Oxalate ***********/
        formula = "C<sub><small>2</small></sub>O<sub><small>4</small></sub><sup><small>2-</small></sup>";

        experiment.add("To the salt, dil."+h2so4+" is added and heated followed by KMnO<sub><small>4</small></sub>(pink)");
        observation.add("KMnO<sub><small>4</small></sub> is decolourised");
        conclusion.add(formula+" maybe present");

        experiment.add("To the salt solution, calcium chloride solution is added");
        observation.add("White precipitate insoluble in acetic acid");
        conclusion.add("CaC<sub><small>2</small></sub>O<sub><small>4</small></sub> is formed. "+formula+" is confirmed");

        listRadical.add(new Radical("Oxalate", formula, experiment,observation,conclusion));
        /***********----------***********/

        experiment.clear();
        observation.clear();
        conclusion.clear();

        /*********** Sulphate ***********/
        formula = "SO<sub><small>4</small></sub><sup><small>2-</small></sup>";

        experiment.add("Salt solution is acidified with dil.HCl, boiled and BaCl<sub><small>2</small></sub> solution is added");
        observation.add("White precipitate is formed and is insoluble in conc.HCl");
        conclusion.add(formula+" is confirmed");

        experiment.add("To the salt solution, acetic acid is added followed by Lead acetate");
        observation.add("White precipitate");
        conclusion.add("Due to the formation of PbSO<sub><small>4</small></sub>, "+formula+" is confirmed");

        listRadical.add(new Radical("Sulphate", formula, experiment,observation,conclusion));
        /***********----------***********/

        experiment.clear();
        observation.clear();
        conclusion.clear();

        /*********** Sulphite ***********/
        formula = "SO<sub><small>3</small></sub><sup><small>2-</small></sup>";

        experiment.add("To the salt, dil.HCl is added");
        observation.add("Gas with a choking smell");
        conclusion.add("Maybe "+formula);

        experiment.add("Evolved gas is passed through acidified K<sub><small>2</small></sub>Cr<sub><small>2</small></sub>O" +
                "<sub><small>7</small></sub>");
        observation.add("Solution turns green without turbidity");
        conclusion.add(formula+" is present");

        experiment.add("Evolved gas is passed through acidified KMnO<sub><small>4</small></sub>");
        observation.add("Solution turns clear and colourless");
        conclusion.add(formula+" is confirmed");

        experiment.add("Wet Test:\nTo the salt solution, BaCl<sub><small>2</small></sub> solution is added");
        observation.add("White precipitate readily soluble in dil.HCl");
        conclusion.add("Due to the formation of Barium Sulphate, which readily dissolves in dil.HCl. "+formula+"is confirmed");

        listRadical.add(new Radical("Sulphite", formula, experiment,observation,conclusion));
        /***********----------***********/

        return new Radical("","", experiment, observation, conclusion);
    }
}
