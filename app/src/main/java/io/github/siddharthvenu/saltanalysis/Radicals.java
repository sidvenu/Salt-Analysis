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
        String formula, h2so4 = "H{2}SO{4}";

        // Acid Radicals
        {
            // Carbonate
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            formula = "CO{3}{{2-}}";

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
            formula = "S{{2-}}";

            experiment.add("Action of dil.HCl:\nTo the salt, dil.HCl is added and heated");
            observation.add("A gas is evolved with the smell of a rotten egg");
            conclusion.add("H{2}S is the gas with the smell of a rotten egg. Salt maybe " + formula);

            experiment.add("Lead Acetate paper is held at the mouth of the test tube");
            observation.add("Turns black");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt, dil.HCl is added and the gas is passed through acidified K{2}Cr{2}O{7}");
            observation.add("The solution turns green with turbidity");
            conclusion.add(formula + " is confirmed. Turbidity is due to the deposition of sulphur");

            experiment.add("Special test for " + formula + ":\nTo the salt, Sodium nitroprusside - Na{2}[Fe(CN){6}NO] is added");
            observation.add("Purple colouration");
            conclusion.add(formula + " is confirmed");

            listRadical.add(new Radical("Sulphide", formula, true, experiment, observation, conclusion));
        }
        {
            // Nitrite
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            formula = "NO{2}{{-}}";

            experiment.add("Dry Test:\nTo the salt, dil.HCl is added");
            observation.add("Pale brown fumes");
            conclusion.add("Maybe " + formula);

            experiment.add("Starch Iodide paper is held at the mouth of the test tube");
            observation.add("Turns blue");
            conclusion.add(formula + " is confirmed");

            experiment.add("Special test for Nitrite:\nTo the salt solution, dil." + h2so4 +
                    " is added followed by KMnO{4}");
            observation.add("Solution becomes colourless");
            conclusion.add(formula + " is confirmed");

            experiment.add("Wet test (Ring test for " + formula + "):\nTo the salt solution, dil." + h2so4 +
                    " is added followed by freshly prepared saturated solution of FeSO{4}" +
                    " (In case of nitrate, we also use conc." + h2so4 + ")");
            observation.add("Brown ring appears at the junction of the aqueous layer and H{2}SO{4}");
            conclusion.add("Due to the formation of Nitrocyl Ferrous Sulphate, brown ring is formed");

            listRadical.add(new Radical("Nitrite", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Oxalate
            formula = "C{2}O{4}{{2-}}";

            experiment.add("To the salt, dil." + h2so4 + " is added and heated followed by KMnO{4}(pink)");
            observation.add("KMnO{4} is decolourised");
            conclusion.add(formula + " maybe present");

            experiment.add("To the salt solution, calcium chloride solution is added");
            observation.add("White precipitate insoluble in acetic acid");
            conclusion.add("CaC{2}O{4} is formed. " + formula + " is confirmed");

            listRadical.add(new Radical("Oxalate", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Sulphate
            formula = "SO{4}{{2-}}";

            experiment.add("Salt solution is acidified with dil.HCl, boiled and BaCl{2} solution is added");
            observation.add("White precipitate is formed and is insoluble in conc.HCl");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt solution, acetic acid is added followed by Lead acetate");
            observation.add("White precipitate");
            conclusion.add("Due to the formation of PbSO{4}, " + formula + " is confirmed");

            listRadical.add(new Radical("Sulphate", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Sulphite
            formula = "SO{3}{{2-}}";

            experiment.add("To the salt, dil.HCl is added");
            observation.add("Gas with a choking smell");
            conclusion.add("Maybe " + formula);

            experiment.add("Evolved gas is passed through acidified K{2}Cr{2}O{7}");
            observation.add("Solution turns green without turbidity");
            conclusion.add(formula + " is present");

            experiment.add("Evolved gas is passed through acidified KMnO{4}");
            observation.add("Solution turns clear and colourless");
            conclusion.add(formula + " is confirmed");

            experiment.add("Wet Test:\nTo the salt solution, BaCl{2} solution is added");
            observation.add("White precipitate readily soluble in dil.HCl");
            conclusion.add("Due to the formation of Barium Sulphate, which readily dissolves in dil.HCl. " + formula + " is confirmed");

            listRadical.add(new Radical("Sulphite", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Acetate
            formula = "CH{3}COO{{-}}";

            experiment.add("To the salt, dil.HCl is added and heated");
            observation.add("Vinegar like odour");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt, conc." + h2so4 + " is added and heated, followeed by 2-3 mL of C{2}H{5}OH");
            observation.add("Pleasant smell");
            conclusion.add("Esterification takes place resulting in the formation of ethyl acetate");

            experiment.add("Special test:\nTo the salt solution, FeCl{3} is added");
            observation.add("Deep red colouration");
            conclusion.add(formula + " is confirmed");

            experiment.add("The solution is diluted and boiled");
            observation.add("Precipitate is formed");
            conclusion.add("Due to the formation of Iron Acetate - Fe(CH{3}COO){3}");

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

            experiment.add("A glass rod dipped in NH{4}OH is held at the mouth of the test tube");
            observation.add("Dense white fumes");
            conclusion.add("Due to the formation of NH{4}Cl");

            experiment.add("MnO{2} is added to the above test tube and heated strongly");
            observation.add("Greenish yellow gas is evolved which turns moist Starch Iodide paper blue");
            conclusion.add("Chloride is confirmed");

            experiment.add("Wet Test:\nTo the salt solution, dil.HNO{3} is added followed by AgNO{3}");
            observation.add("Curdy white precipitate readily soluble in NH{4}OH");
            conclusion.add("AgCl dissolves in NH{4}OH forming a complex");

            experiment.add("Special Test:\nChromyl Chloride Test:\nSalt is mixed with K{2}Cr{2}O{7} and conc."
                    + h2so4 + " and heated strongly");
            observation.add("Reddish brown fumes");
            conclusion.add("Due to the formation of CrO{2}Cl{2}(Chromyl Chloride");

            experiment.add("The gas is passed through NaOH and Lead Acetate is added");
            observation.add("Yellow precipitate");
            conclusion.add("Due to the formation of PbCrO{4}(Lead Chromate). Chloride is confirmed");

            listRadical.add(new Radical("Chloride", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Bromide
            formula = "Br<sup><small>-</small></sup>";

            experiment.add("To the salt, conc." + h2so4 + " is added");
            observation.add("Reddish brown fumes are evolved even before heating");
            conclusion.add("Due to the formation of " + formula);

            experiment.add("Wet Test:\nTo the salt solution, dil.HNO{3} is added followed by AgNO{3}");
            observation.add("Pale yellow precipitate difficult to soluble in NH{4}OH");
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

            experiment.add("Wet Test:\nTo the salt solution, dil.HNO{3} is added followed by AgNO{3}");
            observation.add("Deep yellow precipitate insoluble in NH{4}OH");
            conclusion.add("Iodine is confirmed");

            experiment.add("To the salt solution, chlorine water is added and the mixture is shaken with Carbon tetrachloride");
            observation.add("CCl{4} layer turns violet");
            conclusion.add("Chlorine can liberate iodine from KI. Iodine dissolves in CCl{4}, giving a violet colour");

            experiment.add("To the salt, Pb(NO{3}){2} is added");
            observation.add("Golden yellow spangles");
            conclusion.add("Due to the formation of PbI{2}");

            listRadical.add(new Radical("Iodide", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Phosphate
            formula = "PO{4}{{3-}}";

            experiment.add("To the salt, conc.HNO{3} is added and heated followed by Ammonium Molybdate - (NH{4}){2}MoO{4}");
            observation.add("Canary yellow precipitate");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt solution, few drops of dil.HNO{3} is added followed by AgNO{3} and NH{4}OH (added dropwise)");
            observation.add("Yellow ring appears in the neutral layer");
            conclusion.add(formula + " is confirmed");

            listRadical.add(new Radical("Phosphate", formula, true, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Nitrate
            formula = "NO{3}{{-}}";

            experiment.add("Dry test tube heating:\nThe salt is taken in a dry test tube and heated strongly");
            observation.add("Brown fumes of NO{2} with the evolution of a gas which relights a glowing splinter");
            conclusion.add(formula + " is confirmed");

            experiment.add("To the salt, conc." + h2so4 + " is added and heated strongly followed by copper turnings");
            observation.add("Pale brown fumes which turn deep brown on adding copper turnings. Solution turns blue at the bottom of the test tube");
            conclusion.add("The solution turns bluish green due to copper nitrate - Cu(NO{3}){2}");

            experiment.add("Wet test (Ring test for " + formula + "):\nTo the salt solution, dil." + h2so4 +
                    " is added followed by freshly prepared saturated solution of FeSO" +
                    "{4} and conc." + h2so4 + "is added slowly along the sides of the test tube");
            observation.add("Brown ring appears at the junction of the aqueous layer and H{2}SO{4}");
            conclusion.add("Due to the formation of Nitrocyl Ferrous Sulphate, brown ring is formed");

            listRadical.add(new Radical("Nitrate", formula, true, experiment, observation, conclusion));
        }

        // Basic Radicals
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Ammonium
            formula = "NH{4}{{+}}";

            experiment.add("To the salt, NaOH is added and heated");
            observation.add("White fumes");
            conclusion.add(formula+" is present");

            experiment.add("A glass rod dipped in conc.HCl is held at the mouth of the test tube");
            observation.add("Dense white fumes");
            conclusion.add(formula+" is confirmed");

            experiment.add("A red litmus paper is held at the mouth of the test tube");
            observation.add("Turns blue");
            conclusion.add(formula+" is confirmed");

            experiment.add("To the above solution, Nessler's reagent is added");
            observation.add("Turns brown");
            conclusion.add(formula+" is confirmed");

            listRadical.add(new Radical("Ammonium", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Lead
            formula = "Pb{{2+}}";

            experiment.add("The white precipitate of PbCl{2} is dissolved in hot water" +
                    " and divided into two parts");
            observation.add("");
            conclusion.add("");

            experiment.add("To the 1st part, K{2}CrO{4} is added");
            observation.add("Yellow precipitate of PbCrO{4}");
            conclusion.add("Presence of Lead");

            experiment.add("To the 2nd part, Potassium Iodide - KI is added (heat and cool to see the yellow spangles");
            observation.add("Golden yellow spangles");
            conclusion.add(formula+" is confirmed");

            listRadical.add(new Radical("Lead", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Copper
            formula = "Cu{{2+}}";

            experiment.add("The black precipitate of Copper Sulphide (CuS) is heated with few drops of dil.HNO" +
                    "{3} and excess of NH{4}OH is added");
            observation.add("Deep blue colouration is observed");
            conclusion.add("Due to the formation of Copper tetraammine complex");

            experiment.add("Acetic acid is added to the above solution");
            observation.add("Colour turns blue");
            conclusion.add("Acetic acid removes ammonia from the complex");

            experiment.add("To the above solution, Potassium ferrocyanide - K{4}[Fe(CN){6}]");
            observation.add("Chocolate brown precipitate");
            conclusion.add("Due to the formation of Cupric ferrocyanide - Cu{2}[Fe(CN){6}]");

            listRadical.add(new Radical("Copper", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Arsenic
            formula = "As{{3+}}";

            experiment.add("The yellow precipitate of As{2}S{3} (Arsenic Sulphide) is boiled with NaOH, " +
                    "a few drops of conc.HCl is added and heated");
            observation.add("The yellow precipitate reappears");
            conclusion.add("Due to the formation of As{2}S{3}. "+formula+" is confirmed");

            listRadical.add(new Radical("Arsenic", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Iron
            formula = "Fe{{3+}}";

            experiment.add("Brown precipitate of Fe(OH){3} is boiled with dil.HCl and the solution is divided into 3 parts");
            observation.add("");
            conclusion.add("");

            experiment.add("To the first part, Ammonium Thiocyanate (NH{4}SCN) is added");
            observation.add("Blood red colouration");
            conclusion.add(formula+" is present");

            experiment.add("To the second part, Potassium Ferrocyanide (K{4}[Fe(CN){6}]) is added");
            observation.add("Prussian blue colour is obtained");
            conclusion.add(formula+" is confirmed");

            experiment.add("To the third part, Potassium Ferricyanide (K{3}[Fe(CN){6}]) is added");
            observation.add("Brown colouration");
            conclusion.add(formula+" is confirmed");

            listRadical.add(new Radical("Iron", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Aluminium
            formula = "Al{{3+}}";

            experiment.add("The Gelatinous white precipitate of Al(OH){3} is divided into 3 parts");
            observation.add("");
            conclusion.add("");

            experiment.add("To the first part, dil.HCl is added");
            observation.add("It dissolves");
            conclusion.add("Presence of "+formula+" confirmed");

            experiment.add("To the second part, NaOH is added");
            observation.add("It dissolves completely");
            conclusion.add("Al(OH){3} is amphoteric in nature");

            experiment.add("To the above solution, a saturated solution of NH{4}Cl is added");
            observation.add("Gelatinous white precipitate reappears");
            conclusion.add("Due to the re-formation of Al(OH){3}");

            experiment.add("The third part of Al(OH){3} is dissolved in few drops of conc.HNO{3}. A filter paper is dipped" +
                    " in Cobalt Nitrate solution and then into the test tube and then shown into the flame");
            observation.add("Blue colour on the edge of the filter paper (Ternard's blue)");
            conclusion.add("Due to the formation of CoAl{2}O{4} (Cobalt Aluminate - Ternard's blue)");

            listRadical.add(new Radical("Aluminium", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Zinc
            formula = "Zn{{2+}}";

            experiment.add("The white precipitate of ZnS is dissolved in dil.HCl and the solution is divided into two parts");
            observation.add("");
            conclusion.add("");

            experiment.add("To the first part, Potassium Ferrocyanide is added");
            observation.add("Bluish white precipitate");
            conclusion.add("Due to the formation of Zn{2}[Fe(CN){6}]");

            experiment.add("To the second part, dil.NaOH is added");
            observation.add("White precipitate is formed which is soluble in excess NaOH");
            conclusion.add("Zn(OH){2} is formed followed by Na{2}ZnO{2}. Zn(OH){2} is amphoteric");

            experiment.add("H{2}S is passed through the above solution");
            observation.add("White precipitate is formed");
            conclusion.add("Due to the formation of ZnS");

            experiment.add("The precipitate of ZnS is dissolved in few drops of conc.HNO{3}. A filter paper is dipped" +
                    " in Cobalt Nitrate is soaked in the test solution and shown into the flame");
            observation.add("Green edged ask");
            conclusion.add("Green edged ash due to the formation of CoZnO{2} (Rinman's green)");

            listRadical.add(new Radical("Zinc", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Cobalt
            formula = "Co{{2+}}";

            experiment.add("The black precipitate of CoS is dissolved in minimal quantity of conc.HNO{3} and heated." +
                    " NH{4}SCN is added, followed by Amyl alcohol and the test tube is shaken well");
            observation.add("The alcoholic layer turns blue");
            conclusion.add(formula+" is confirmed");

            listRadical.add(new Radical("Cobalt", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Nickel
            formula = "Ni{{2+}}";

            experiment.add("The black precipitate of NiS is dissolved in Aqua regia (3 parts conc.HCl is to 1 parts conc.HNO{3}). " +
                    "A portion of it is taken and made distinctly alkaline with NH{4}OH and DMG (Dimethylglyoxime) is added");
            observation.add("Rosy red precipitate is formed");
            conclusion.add(formula+" is confirmed");

            listRadical.add(new Radical("Nickel", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Manganese
            formula = "Mn{{2+}}";

            experiment.add("The flesh coloured precipitate of MnS is dissolved in few drops of dil.HCl and boiled to drive off " +
                    "H{2}S. Dil.HNO{3} is added followed by Sodium Bismuthate (NaBiO{3})");
            observation.add("Purple colouration is formed (pink)");
            conclusion.add(formula+" is confirmed");

            listRadical.add(new Radical("Manganese", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Barium
            formula = "Ba{{2+}}";

            experiment.add("The white precipitate of BaCO{3} is dissolved in acetic acid, boiled to drive off CO{2}, divided " +
                    "into 3 parts");
            observation.add("");
            conclusion.add("");

            experiment.add("To the 1st part, K{2}CrO{4} (Potassium Chromate) is added");
            observation.add("Yellow precipitate");
            conclusion.add(formula+" is confirmed");

            experiment.add("To the 2nd part, (NH{4}){2}SO{4} (Ammonium Sulphate) is added");
            observation.add("White precipitate");
            conclusion.add(formula+" is confirmed");

            experiment.add("To the 3rd part, (NH{4}){2}C{2}O{4} (Ammonium Oxalate) is added");
            observation.add("White precipitate");
            conclusion.add(formula+" is confirmed");

            experiment.add("Flame test:\nThe salt is made into a paste with conc.HCl. It is taken in a glass rod " +
                    "and shown in the flame");
            observation.add("Apple green colour");
            conclusion.add(formula+" is confirmed");

            listRadical.add(new Radical("Barium", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Strontium
            formula = "Sr{{2+}}";

            experiment.add("The white precipitate of SrCO{3} is dissolved in acetic acid, boiled to drive off CO{2}, divided " +
                    "into 3 parts");
            observation.add("");
            conclusion.add("");

            experiment.add("To the 1st part, K{2}CrO{4} (Potassium Chromate) is added");
            observation.add("No precipitate");
            conclusion.add("Ba{{2+}} is absent");

            experiment.add("To the 2nd part, (NH{4}){2}SO{4} (Ammonium Sulphate) is added");
            observation.add("White precipitate");
            conclusion.add("Presence of "+formula);

            experiment.add("To the 3rd part, (NH{4}){2}C{2}O{4} (Ammonium Oxalate) is added");
            observation.add("White precipitate");
            conclusion.add("Presence of "+formula+" confirmed");

            experiment.add("Flame test:\nThe salt is made into a paste with conc.HCl. It is taken in a glass rod " +
                    "and shown in the flame");
            observation.add("Crimson red colour");
            conclusion.add("Presence of "+formula+" confirmed");

            listRadical.add(new Radical("Strontium", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Calcium
            formula = "Ca{{2+}}";

            experiment.add("The white precipitate of SrCO{3} is dissolved in acetic acid, boiled to drive off CO{2}, divided " +
                    "into 3 parts");
            observation.add("");
            conclusion.add("");

            experiment.add("To the 1st part, K{2}CrO{4} (Potassium Chromate) is added");
            observation.add("No precipitate");
            conclusion.add("Absence of Ba{{2+}}");

            experiment.add("To the 2nd part, (NH{4}){2}SO{4} (Ammonium Sulphate) is added");
            observation.add("White precipitate");
            conclusion.add("Absence of Sr{{2+}}");

            experiment.add("To the 3rd part, (NH{4}){2}C{2}O{4} (Ammonium Oxalate) is added");
            observation.add("White precipitate");
            conclusion.add("Presence of "+formula+" confirmed");

            experiment.add("Flame test:\nThe salt is made into a paste with conc.HCl. It is taken in a glass rod " +
                    "and shown in the flame");
            observation.add("Brick red colour");
            conclusion.add("Presence of "+formula+" confirmed");

            listRadical.add(new Radical("Calcium", formula, false, experiment, observation, conclusion));
        }
        {
            List<String> experiment = new ArrayList<>(), observation = new ArrayList<>(), conclusion = new ArrayList<>();
            // Magnesium
            formula = "Mg{{2+}}";

            experiment.add("To the filtrate of group 5, Disodium hydrogen phosphate (NaHPO{4}) is added");
            observation.add("White precipitate");
            conclusion.add(formula+" is confirmed");

            experiment.add("To the above precipitate, conc.HCl is added, followed by Magneson reagent and NaOH");
            observation.add("Blue precipitate");
            conclusion.add(formula+" is confirmed");

            listRadical.add(new Radical("Magnesium", formula, false, experiment, observation, conclusion));
        }
        return listRadical;

    }
}
