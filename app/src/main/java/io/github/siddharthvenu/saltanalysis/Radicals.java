package io.github.siddharthvenu.saltanalysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddh on 27-02-2017.
 */

class Radicals {

    static class Radical {
        String name, formula;
        boolean isAcidic;
        List<String> experiment, observation, conclusion;

        Radical(String name, String formula, boolean isAcidic, List<String> experiment, List<String> observation, List<String> conclusion) {
            this.name = name;
            this.formula = formula;
            this.isAcidic = isAcidic;
            this.experiment = experiment;
            this.observation = observation;
            this.conclusion = conclusion;
        }
    }

    static List<Radical> getRadicalDetails() {

        List<Radical> listRadical = new ArrayList<>();
        String formula, h2so4 = "H<sub><small><small>2</small></small></sub>SO<sub><small><small>4</small></small></sub>";
        {
            // Carbonate
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            formula = "CO<sub><small><small>3</small></small></sub><sup><small><small>2-</small></small></sup>";

            experiment.add("Dry test tube heating:\nSalt is taken in a dry test tube and heated strongly");
            observation.add("A gas is evolved which turns lime water milky and extinguishes a lighted matchstick");
            conclusion.add("Presence of " + formula + " is confirmed");

            experiment.add("Action of dil.HCl:\nTo the salt, dil.HCl is added");
            observation.add("Brisk effervescence with the evolution of a gas which turns lime water milky");
            conclusion.add(formula + " is confirmed");

            listRadical.add(new Radical("Carbonate", formula, true, experiment, observation, conclusion));
        }
        {
            // Sulphide
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            formula = "S<sup><small><small>2-</small></small></sup>";

            experiment.add("Action of dil.HCl:\nTo the salt, dil.HCl is added and heated");
            observation.add("A gas is evolved with the smell of a rotten egg");
            conclusion.add("H<sub><small><small>2</small></small></sub>S is the gas with the smell of a rotten egg. Salt maybe " + formula);

            experiment.add("Lead Acetate paper is held at the mouth of the test tube");
            observation.add("Turns black");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt, dil.HCl is added and the gas is passed through acidified K<sub><small><small>2</small></small></sub>" +
                    "Cr<sub><small><small>2</small></small></sub>O<sub><small><small>7</small></small></sub>");
            observation.add("The solution turns green with turbidity");
            conclusion.add(formula + " is confirmed. Turbidity is due to the deposition of sulphur");

            experiment.add("Special test for " + formula + ":\nTo the salt, Sodium nitroprusside - Na" +
                    "<sub><small><small>2</small></small></sub>[Fe(CN)<sub><small><small>6</small></small></sub>NO] is added");
            observation.add("Purple colouration");
            conclusion.add(formula + " is confirmed");

            listRadical.add(new Radical("Sulphide", formula, true, experiment, observation, conclusion));
        }
        {
            // Nitrite
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            formula = "NO<sub><small><small>2</small></small></sub><sup><small>-</small></sup>";

            experiment.add("Dry Test:\nTo the salt, dil.HCl is added");
            observation.add("Pale brown fumes");
            conclusion.add("Maybe " + formula);

            experiment.add("Starch Iodide paper is held at the mouth of the test tube");
            observation.add("Turns blue");
            conclusion.add(formula + " is confirmed");

            experiment.add("Special test for Nitrite:\nTo the salt solution, dil." + h2so4 +
                    " is added followed by KMnO<sub><small><small>4</small></small></sub>");
            observation.add("Solution becomes colourless");
            conclusion.add(formula + " is confirmed");

            experiment.add("Wet test (Ring test for " + formula + "):\nTo the salt solution, dil." + h2so4 +
                    " is added followed by freshly prepared saturated solution of FeSO" +
                    "<sub><small><small>4</small></small></sub> (In case of nitrate, we also use conc." + h2so4 + ")");
            observation.add("Brown ring appears at the junction of the aqueous layer and H<sub><small><small>2</small></small></sub>SO" +
                    "<sub><small><small>4</small></small></sub>");
            conclusion.add("Due to the formation of Nitrocyl Ferrous Sulphate, brown ring is formed");

            listRadical.add(new Radical("Nitrite", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Oxalate
            formula = "C<sub><small><small>2</small></small></sub>O<sub><small><small>4</small></small></sub><sup><small><small>2-</small></small></sup>";

            experiment.add("To the salt, dil." + h2so4 + " is added and heated followed by KMnO<sub><small><small>4</small></small></sub>(pink)");
            observation.add("KMnO<sub><small><small>4</small></small></sub> is decolourised");
            conclusion.add(formula + " maybe present");

            experiment.add("To the salt solution, calcium chloride solution is added");
            observation.add("White precipitate insoluble in acetic acid");
            conclusion.add("CaC<sub><small><small>2</small></small></sub>O<sub><small><small>4</small></small></sub> is formed. " + formula + " is confirmed");

            listRadical.add(new Radical("Oxalate", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Sulphate
            formula = "SO<sub><small><small>4</small></small></sub><sup><small><small>2-</small></small></sup>";

            experiment.add("Salt solution is acidified with dil.HCl, boiled and BaCl<sub><small><small>2</small></small></sub> solution is added");
            observation.add("White precipitate is formed and is insoluble in conc.HCl");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt solution, acetic acid is added followed by Lead acetate");
            observation.add("White precipitate");
            conclusion.add("Due to the formation of PbSO<sub><small><small>4</small></small></sub>, " + formula + " is confirmed");

            listRadical.add(new Radical("Sulphate", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Sulphite
            formula = "SO<sub><small><small>3</small></small></sub><sup><small><small>2-</small></small></sup>";

            experiment.add("To the salt, dil.HCl is added");
            observation.add("Gas with a choking smell");
            conclusion.add("Maybe " + formula);

            experiment.add("Evolved gas is passed through acidified K<sub><small><small>2</small></small></sub>Cr<sub><small><small>2</small></small></sub>O" +
                    "<sub><small><small>7</small></small></sub>");
            observation.add("Solution turns green without turbidity");
            conclusion.add(formula + " is present");

            experiment.add("Evolved gas is passed through acidified KMnO<sub><small><small>4</small></small></sub>");
            observation.add("Solution turns clear and colourless");
            conclusion.add(formula + " is confirmed");

            experiment.add("Wet Test:\nTo the salt solution, BaCl<sub><small><small>2</small></small></sub> solution is added");
            observation.add("White precipitate readily soluble in dil.HCl");
            conclusion.add("Due to the formation of Barium Sulphate, which readily dissolves in dil.HCl. " + formula + " is confirmed");

            listRadical.add(new Radical("Sulphite", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Acetate
            formula = "CH<sub><small><small>3</small></small></sub>COO<sup><small>-</small></sup>";

            experiment.add("To the salt, dil.HCl is added and heated");
            observation.add("Vinegar like odour");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt, conc." + h2so4 + " is added and heated, followeed by 2-3 mL of C<sub><small><small>2</small></small></sub>H" +
                    "<sub><small><small>5</small></small></sub>OH");
            observation.add("Pleasant smell");
            conclusion.add("Esterification takes place resulting in the formation of ethyl acetate");

            experiment.add("Special test:\nTo the salt solution, FeCl<sub><small><small>3</small></small></sub> is added");
            observation.add("Deep red colouration");
            conclusion.add(formula + " is confirmed");

            experiment.add("The solution is diluted and boiled");
            observation.add("Precipitate is formed");
            conclusion.add("Due to the formation of Iron Acetate - Fe(CH<sub><small><small>3</small></small></sub>COO)<sub><small><small>3</small></small></sub>");

            experiment.add("dil.HCl is added to the above precipitate");
            observation.add("Solution becomes colourless");
            conclusion.add(formula + " is confirmed");

            listRadical.add(new Radical("Acetate", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Chloride
            formula = "Cl<sup><small>-</small></sup>";

            experiment.add("To the salt, conc." + h2so4 + " is added");
            observation.add("White fumes");
            conclusion.add("Maybe chloride");

            experiment.add("A glass rod dipped in NH<sub><small><small>4</small></small></sub>OH is held at the mouth of the test tube");
            observation.add("Dense white fumes");
            conclusion.add("Due to the formation of NH<sub><small><small>4</small></small></sub>Cl");

            experiment.add("MnO<sub><small><small>2</small></small></sub> is added to the above test tube and heated strongly");
            observation.add("Greenish yellow gas is evolved which turns moist Starch Iodide paper blue");
            conclusion.add("Chloride is confirmed");

            experiment.add("Wet Test:\nTo the salt solution, dil.HNO<sub><small><small>3</small></small></sub> is added followed by " +
                    "AgNO<sub><small><small>3</small></small></sub>");
            observation.add("Curdy white precipitate readily soluble in NH<sub><small><small>4</small></small></sub>OH");
            conclusion.add("AgCl dissolves in NH<sub><small><small>4</small></small></sub>OH forming a complex");

            experiment.add("Special Test:\nChromyl Chloride Test:\nSalt is mixed with K<sub><small><small>2</small></small></sub>Cr" +
                    "<sub><small><small>2</small></small></sub>O<sub><small><small>7</small></small></sub> and conc." + h2so4 + " and heated strongly");
            observation.add("Reddish brown fumes");
            conclusion.add("Due to the formation of CrO<sub><small><small>2</small></small></sub>Cl<sub><small><small>2</small></small></sub>(Chromyl Chloride");

            experiment.add("The gas is passed through NaOH and Lead Acetate is added");
            observation.add("Yellow precipitate");
            conclusion.add("Due to the formation of PbCrO<sub><small><small>4</small></small></sub>(Lead Chromate). Chloride is confirmed");

            listRadical.add(new Radical("Chloride", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Bromide
            formula = "Br<sup><small>-</small></sup>";

            experiment.add("To the salt, conc." + h2so4 + " is added");
            observation.add("Reddish brown fumes are evolved even before heating");
            conclusion.add("Due to the formation of " + formula);

            experiment.add("Wet Test:\nTo the salt solution, dil.HNO<sub><small><small>3</small></small></sub> is added followed by " +
                    "AgNO<sub><small><small>3</small></small></sub>");
            observation.add("Pale yellow precipitate difficult to soluble in NH<sub><small><small>4</small></small></sub>OH");
            conclusion.add("Bromide is confirmed");

            listRadical.add(new Radical("Bromide", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Iodide
            formula = "I<sup><small>-</small></sup>";

            experiment.add("To the salt, conc." + h2so4 + " is added");
            observation.add("Violet vapours");
            conclusion.add("Due to the formation of " + formula);

            experiment.add("Wet Test:\nTo the salt solution, dil.HNO<sub><small><small>3</small></small></sub> is added followed by " +
                    "AgNO<sub><small><small>3</small></small></sub>");
            observation.add("Deep yellow precipitate insoluble in NH<sub><small><small>4</small></small></sub>OH");
            conclusion.add("Iodine is confirmed");

            experiment.add("To the salt solution, chlorine water is added and the mixture is shaken with Carbon tetrachloride");
            observation.add("CCl<sub><small><small>4</small></small></sub> layer turns violet");
            conclusion.add("Chlorine can liberate iodine from KI. Iodine dissolves in CCl<sub><small><small>4</small></small></sub>, " +
                    "giving a violet colour");

            experiment.add("To the salt, Pb(NO<sub><small><small>3</small></small></sub>)<sub><small><small>2</small></small></sub> is added");
            observation.add("Golden yellow spangles");
            conclusion.add("Due to the formation of PbI<sub><small><small>2</small></small></sub>");

            listRadical.add(new Radical("Iodide", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Phosphate
            formula = "PO<sub><small><small>4</small></small></sub><sup><small><small>3-</small></small></sup>";

            experiment.add("To the salt, conc.HNO<sub><small><small>3</small></small></sub> is added and heated followed by Ammonium Molybdate - " +
                    "(NH<sub><small><small>4</small></small></sub>)<sub><small><small>2</small></small></sub>MoO<sub><small><small>4</small></small></sub>");
            observation.add("Canary yellow precipitate");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt solution, few drops of dil.HNO<sub><small><small>3</small></small></sub> is added followed by " +
                    "AgNO<sub><small><small>3</small></small></sub> and NH<sub><small><small>4</small></small></sub>OH (added dropwise)");
            observation.add("Yellow ring appears in the neutral layer");
            conclusion.add(formula + " is confirmed");

            listRadical.add(new Radical("Phosphate", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Nitrate
            formula = "NO<sub><small><small>3</small></small></sub><sup><small>-</small></sup>";

            experiment.add("Dry test tube heating:\nThe salt is taken in a dry test tube and heated strongly");
            observation.add("Brown fumes of NO<sub><small><small>2</small></small></sub> with the evolution of a gas which relights " +
                    "a glowing splinter");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt, conc." + h2so4 + " is added and heated strongly followed by copper turnings");
            observation.add("Pale brown fumes which turn deep brown on adding copper turnings. Solution turns blue at the bottom of the test tube");
            conclusion.add("The solution turns bluish green due to copper nitrate - Cu(NO<sub><small><small>3</small></small></sub>)" +
                    "<sub><small><small>2</small></small></sub>");

            experiment.add("Wet test (Ring test for " + formula + "):\nTo the salt solution, dil." + h2so4 +
                    " is added followed by freshly prepared saturated solution of FeSO" +
                    "<sub><small><small>4</small></small></sub> and conc." + h2so4 + "is added slowly along the sides of the test tube");
            observation.add("Brown ring appears at the junction of the aqueous layer and H<sub><small><small>2</small></small></sub>SO" +
                    "<sub><small><small>4</small></small></sub>");
            conclusion.add("Due to the formation of Nitrocyl Ferrous Sulphate, brown ring is formed");

            listRadical.add(new Radical("Nitrate", formula, true, experiment, observation, conclusion));
        }
        return listRadical;

    }
}
