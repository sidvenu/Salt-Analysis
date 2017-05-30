package io.github.siddharthvenu.saltanalysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siddh on 27-02-2017.
 */

class Radicals {

    static List<Radical> getRadicalDetails() {

        List<Radical> listRadical = new ArrayList<>();
        String formula, h2so4 = "H{2}SO{4}";

        // Acid Radicals
        {
            // Carbonate
            ArrayList<Experiment> experimentList = new ArrayList<>();
            formula = "CO{3}{{2-}}";
            experimentList.add(new Experiment("Dry test tube heating:\nSalt is taken in a dry test tube and heated strongly",
                    "A gas is evolved which turns lime water milky and extinguishes a lighted matchstick",
                    "Presence of " + formula + " is confirmed",
                    "HEAT", true));

            experimentList.add(new Experiment("Action of dil.HCl:\nTo the salt, dil.HCl is added",
                    "Brisk effervescence with the evolution of a gas which turns lime water milky",
                    formula + " is confirmed",
                    "HCL", true));

            listRadical.add(new Radical("Carbonate", formula, true, experimentList));
        }
        {
            // Sulphide
            ArrayList<Experiment> experimentList = new ArrayList<>();
            formula = "S{{2-}}";

            experimentList.add(new Experiment("Action of dil.HCl:\nTo the salt, dil.HCl is added and heated",
                    "A gas is evolved with the smell of a rotten egg",
                    "H{2}S is the gas with the smell of a rotten egg. Salt maybe " + formula,
                    "HCL", true));

            experimentList.add(new Experiment("Lead Acetate paper is held at the mouth of the test tube",
                    "Turns black",
                    formula + " is confirmed",
                    "CONTINUE", true));

            experimentList.add(new Experiment("To the salt, dil.HCl is added and the gas is passed through acidified K{2}Cr{2}O{7}",
                    "The solution turns green with turbidity",
                    formula + " is confirmed. Turbidity is due to the deposition of sulphur",
                    "SPL", true));

            experimentList.add(new Experiment("Special test for " + formula + ":\nTo the salt, Sodium nitroprusside - Na{2}[Fe(CN){6}NO] is added",
                    "Purple colouration",
                    formula + " is confirmed",
                    "SPL", false));

            listRadical.add(new Radical("Sulphide", formula, true, experimentList));
        }
        {
            // Nitrite
            ArrayList<Experiment> experimentList = new ArrayList<>();
            formula = "NO{2}{{-}}";

            experimentList.add(new Experiment("Dry Test:\nTo the salt, dil.HCl is added",
                    "Pale brown fumes",
                    "Maybe " + formula,
                    "HCL", true));

            experimentList.add(new Experiment("Starch Iodide paper is held at the mouth of the test tube",
                    "Turns blue",
                    formula + " is confirmed",
                    "CONTINUE", true));

            experimentList.add(new Experiment("Special test for Nitrite:\nTo the salt solution, dil." + h2so4 +
                    " is added followed by KMnO{4}",
                    "Solution becomes colourless",
                    formula + " is confirmed",
                    "SPL", false));

            experimentList.add(new Experiment("Wet test (Ring test for " + formula + "):\nTo the salt solution, dil." + h2so4 +
                    " is added followed by freshly prepared saturated solution of FeSO{4}" +
                    " (In case of nitrate, we also use conc." + h2so4 + ")",
                    "Brown ring appears at the junction of the aqueous layer and H{2}SO{4}",
                    "Due to the formation of Nitrocyl Ferrous Sulphate, brown ring is formed",
                    "SPL", false));

            listRadical.add(new Radical("Nitrite", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Oxalate
            formula = "C{2}O{4}{{2-}}";

            experimentList.add(new Experiment("To the salt, dil." + h2so4 + " is added and heated followed by KMnO{4}(pink)",
                    "KMnO{4} is decolourised",
                    formula + " maybe present",
                    "SPL", true));

            experimentList.add(new Experiment("To the salt solution, calcium chloride solution is added",
                    "White precipitate insoluble in acetic acid",
                    "CaC{2}O{4} is formed. " + formula + " is confirmed",
                    "SPL", false));

            listRadical.add(new Radical("Oxalate", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Sulphate
            formula = "SO{4}{{2-}}";

            experimentList.add(new Experiment("Salt is acidified with dil.HCl, boiled and BaCl{2} solution is added",
                    "White precipitate is formed and is insoluble in conc.HCl",
                    formula + " is confirmed",
                    "SPL", true));

            experimentList.add(new Experiment("To the salt solution, acetic acid is added followed by Lead acetate",
                    "White precipitate",
                    "Due to the formation of PbSO{4}, " + formula + " is confirmed",
                    "SPL", false));

            listRadical.add(new Radical("Sulphate", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Sulphite
            formula = "SO{3}{{2-}}";

            experimentList.add(new Experiment("To the salt, dil.HCl is added",
                    "Gas with a choking smell",
                    "Maybe " + formula,
                    "HCL", true));

            experimentList.add(new Experiment("Evolved gas is passed through acidified K{2}Cr{2}O{7}",
                    "Solution turns green without turbidity",
                    formula + " is present",
                    "CONTINUE", true));

            experimentList.add(new Experiment("Evolved gas is passed through acidified KMnO{4}",
                    "Solution turns clear and colourless",
                    formula + " is confirmed",
                    "CONTINUE", true));

            experimentList.add(new Experiment("Wet Test:\nTo the salt solution, BaCl{2} solution is added",
                    "White precipitate readily soluble in dil.HCl",
                    "Due to the formation of Barium Sulphate, which readily dissolves in dil.HCl. " + formula + " is confirmed",
                    "SPL", false));

            listRadical.add(new Radical("Sulphite", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Acetate
            formula = "CH{3}COO{{-}}";

            experimentList.add(new Experiment("To the salt, dil.HCl is added and heated",
                    "Vinegar like odour",
                    formula + " is confirmed",
                    "SPL", true));

            experimentList.add(new Experiment("To the salt, conc." + h2so4 + " is added and heated, followeed by 2-3 mL of C{2}H{5}OH",
                    "Pleasant smell",
                    "Esterification takes place resulting in the formation of ethyl acetate",
                    "SPL", true));

            experimentList.add(new Experiment("Special test:\nTo the salt solution, FeCl{3} is added",
                    "Deep red colouration",
                    formula + " is confirmed",
                    "SPL", false));

            experimentList.add(new Experiment("The solution is diluted and boiled",
                    "Precipitate is formed",
                    "Due to the formation of Iron Acetate - Fe(CH{3}COO){3}",
                    "CONTINUE", false));

            experimentList.add(new Experiment("dil.HCl is added to the above precipitate",
                    "Solution becomes colourless",
                    formula + " is confirmed",
                    "CONTINUE", false));

            listRadical.add(new Radical("Acetate", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Chloride
            formula = "Cl<sup><small>-</small></sup>";

            experimentList.add(new Experiment("To the salt, conc." + h2so4 + " is added",
                    "White fumes",
                    "Maybe chloride",
                    "H2SO4", true));

            experimentList.add(new Experiment("A glass rod dipped in NH{4}OH is held at the mouth of the test tube",
                    "Dense white fumes",
                    "Due to the formation of NH{4}Cl",
                    "CONTINUE", true));

            experimentList.add(new Experiment("MnO{2} is added to the above test tube and heated strongly",
                    "Greenish yellow gas is evolved which turns moist Starch Iodide paper blue",
                    "Chloride is confirmed",
                    "CONTINUE", true));

            experimentList.add(new Experiment("Wet Test:\nTo the salt solution, dil.HNO{3} is added followed by AgNO{3}",
                    "Curdy white precipitate readily soluble in NH{4}OH",
                    "AgCl dissolves in NH{4}OH forming a complex",
                    "AGNO3", false));

            experimentList.add(new Experiment("Special Test:\nChromyl Chloride Test:\nSalt is mixed with K{2}Cr{2}O{7} and conc."
                    + h2so4 + " and heated strongly",
                    "Reddish brown fumes",
                    "Due to the formation of CrO{2}Cl{2}(Chromyl Chloride",
                    "SPL", false));

            experimentList.add(new Experiment("The gas is passed through NaOH and Lead Acetate is added",
                    "Yellow precipitate",
                    "Due to the formation of PbCrO{4}(Lead Chromate). Chloride is confirmed",
                    "CONTINUE", false));

            listRadical.add(new Radical("Chloride", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Bromide
            formula = "Br<sup><small>-</small></sup>";

            experimentList.add(new Experiment("To the salt, conc." + h2so4 + " is added",
                    "Reddish brown fumes are evolved even before heating",
                    "Due to the formation of " + formula,
                    "H2SO4", true));

            experimentList.add(new Experiment("Wet Test:\nTo the salt solution, dil.HNO{3} is added followed by AgNO{3}",
                    "Pale yellow precipitate difficult to soluble in NH{4}OH",
                    "Bromide is confirmed",
                    "AGNO3", false));

            listRadical.add(new Radical("Bromide", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Iodide
            formula = "I<sup><small>-</small></sup>";

            experimentList.add(new Experiment("To the salt, conc." + h2so4 + " is added",
                    "Violet vapours",
                    "Due to the formation of " + formula,
                    "H2SO4", true));

            experimentList.add(new Experiment("Wet Test:\nTo the salt solution, dil.HNO{3} is added followed by AgNO{3}",
                    "Deep yellow precipitate insoluble in NH{4}OH",
                    "Iodine is confirmed",
                    "AGNO3", false));

            experimentList.add(new Experiment("To the salt solution, chlorine water is added and the mixture is shaken with Carbon tetrachloride",
                    "CCl{4} layer turns violet",
                    "Chlorine can liberate iodine from KI. Iodine dissolves in CCl{4}, giving a violet colour",
                    "SPL", false));

            experimentList.add(new Experiment("To the salt solution, chlorine water is added and the mixture is shaken with Carbon tetrachloride",
                    "CCl{4} layer turns violet",
                    "Chlorine can liberate iodine from KI. Iodine dissolves in CCl{4}, giving a violet colour",
                    "SPL", false));

            experimentList.add(new Experiment("To the salt, Pb(NO{3}){2} is added",
                    "Golden yellow spangles",
                    "Due to the formation of PbI{2}",
                    "SPL", false));

            listRadical.add(new Radical("Iodide", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Phosphate
            formula = "PO{4}{{3-}}";

            experimentList.add(new Experiment("To the salt, conc.HNO{3}" + "is added and heated followed by Ammonium Molybdate - (NH{4}){2}MoO{4}",
                    "Canary yellow precipitate",
                    formula + " is confirmed",
                    "SPL", true));


            experimentList.add(new Experiment("To the salt solution, few drops of dil.HNO{3} is added followed by AgNO{3} and NH{4}OH (added dropwise)",
                    "Yellow ring appears in the neutral layer",
                    formula + " is confirmed",
                    "SPL", false));


            listRadical.add(new Radical("Phosphate", formula, true, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Nitrate
            formula = "NO{3}{{-}}";

            experimentList.add(new Experiment("Dry test tube heating:\nThe salt is taken in a dry test tube and heated strongly",
                    "Brown fumes of NO{2} with the evolution of a gas which relights a glowing splinter",
                    formula + " is confirmed",
                    "HEAT", true));


            experimentList.add(new Experiment("To the salt, conc." + h2so4 + " is added and heated strongly followed by copper turnings",
                    "Pale brown fumes which turn deep brown on adding copper turnings. Solution turns blue at the bottom of the test tube",
                    "The solution turns bluish green due to copper nitrate - Cu(NO{3}){2}",
                    "CONTINUE", true));


            experimentList.add(new Experiment("Wet test (Ring test for " + formula + "):\nTo the salt solution, dil." + h2so4 +
                    " is added followed by freshly prepared saturated solution of FeSO" +
                    "{4} and conc." + h2so4 + "is added slowly along the sides of the test tube",
                    "Brown ring appears at the junction of the aqueous layer and H{2}SO{4}",
                    "Due to the formation of Nitrocyl Ferrous Sulphate, brown ring is formed",
                    "SPL", false));


            listRadical.add(new Radical("Nitrate", formula, true, experimentList));
        }

        // Basic Radicals
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Ammonium
            formula = "NH{4}{{+}}";

            experimentList.add(new Experiment("To the salt, NaOH is added and heated",
                    "White fumes",
                    formula + " is present"));


            experimentList.add(new Experiment("A glass rod dipped in conc.HCl is held at the mouth of the test tube",
                    "Dense white fumes",
                    formula + " is confirmed"));


            experimentList.add(new Experiment("A red litmus paper is held at the mouth of the test tube",
                    "Turns blue",
                    formula + " is confirmed"));


            experimentList.add(new Experiment("To the above solution, Nessler's reagent is added",
                    "Turns brown",
                    formula + " is confirmed"));


            listRadical.add(new Radical("Ammonium", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Lead
            formula = "Pb{{2+}}";

            experimentList.add(new Experiment("The white precipitate of PbCl{2} is dissolved in hot water" +
                    " and divided into two parts",
                    "",
                    ""));


            experimentList.add(new Experiment("To the 1st part, K{2}CrO{4} is added",
                    "Yellow precipitate of PbCrO{4}",
                    "Presence of Lead"));


            experimentList.add(new Experiment("To the 2nd part, Potassium Iodide - KI is added (heat and cool to see the yellow spangles)",
                    "Golden yellow spangles",
                    formula + " is confirmed"));


            listRadical.add(new Radical("Lead", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Copper
            formula = "Cu{{2+}}";

            experimentList.add(new Experiment("The black precipitate of Copper Sulphide (CuS) is heated with few drops of dil.HNO" +
                    "{3} and excess of NH{4}OH is added",
                    "Deep blue colouration is observed",
                    "Due to the formation of Copper tetraammine complex"));


            experimentList.add(new Experiment("Acetic acid is added to the above solution",
                    "Colour turns blue",
                    "Acetic acid removes ammonia from the complex"));


            experimentList.add(new Experiment("To the above solution, Potassium ferrocyanide - K{4}[Fe(CN){6}]",
                    "Chocolate brown precipitate",
                    "Due to the formation of Cupric ferrocyanide - Cu{2}[Fe(CN){6}]"));


            listRadical.add(new Radical("Copper", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Arsenic
            formula = "As{{3+}}";

            experimentList.add(new Experiment("The yellow precipitate of As{2}S{3} (Arsenic Sulphide) is boiled with NaOH, " +
                    "a few drops of conc.HCl is added and heated",
                    "The yellow precipitate reappears",
                    "Due to the formation of As{2}S{3}. " + formula + " is confirmed"));


            listRadical.add(new Radical("Arsenic", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Iron
            formula = "Fe{{3+}}";

            experimentList.add(new Experiment("Brown precipitate of Fe(OH){3} is boiled with dil.HCl and the solution is divided into 3 parts",
                    "",
                    ""));


            experimentList.add(new Experiment("To the first part, Ammonium Thiocyanate (NH{4}SCN) is added",
                    "Blood red colouration",
                    formula + " is present"));


            experimentList.add(new Experiment("To the second part, Potassium Ferrocyanide (K{4}[Fe(CN){6}]) is added",
                    "Prussian blue colour is obtained",
                    formula + " is confirmed"));


            experimentList.add(new Experiment("To the third part, Potassium Ferricyanide (K{3}[Fe(CN){6}]) is added",
                    "Brown colouration",
                    formula + " is confirmed"));


            listRadical.add(new Radical("Iron", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Aluminium
            formula = "Al{{3+}}";

            experimentList.add(new Experiment("The Gelatinous white precipitate of Al(OH){3} is divided into 3 parts",
                    "",
                    ""));


            experimentList.add(new Experiment("To the first part, dil.HCl is added",
                    "It dissolves",
                    "Presence of " + formula + " confirmed"));


            experimentList.add(new Experiment("To the second part, NaOH is added",
                    "It dissolves completely",
                    "Al(OH){3} is amphoteric in nature"));


            experimentList.add(new Experiment("To the above solution, a saturated solution of NH{4}Cl is added",
                    "Gelatinous white precipitate reappears",
                    "Due to the re-formation of Al(OH){3}"));


            experimentList.add(new Experiment("The third part of Al(OH){3} is dissolved in few drops of conc.HNO{3}. A filter paper is dipped" +
                    " in Cobalt Nitrate solution and then into the test tube and then shown into the flame",
                    "Blue colour on the edge of the filter paper (Ternard's blue)",
                    "Due to the formation of CoAl{2}O{4} (Cobalt Aluminate - Ternard's blue)"));


            listRadical.add(new Radical("Aluminium", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Zinc
            formula = "Zn{{2+}}";

            experimentList.add(new Experiment("The white precipitate of ZnS is dissolved in dil.HCl and the solution is divided into two parts",
                    "",
                    ""));


            experimentList.add(new Experiment("To the first part, Potassium Ferrocyanide is added",
                    "Bluish white precipitate",
                    "Due to the formation of Zn{2}[Fe(CN){6}]"));


            experimentList.add(new Experiment("To the second part, dil.NaOH is added",
                    "White precipitate is formed which is soluble in excess NaOH",
                    "Zn(OH){2} is formed followed by Na{2}ZnO{2}. Zn(OH){2} is amphoteric"));


            experimentList.add(new Experiment("H{2}S is passed through the above solution",
                    "White precipitate is formed",
                    "Due to the formation of ZnS"));


            experimentList.add(new Experiment("The precipitate of ZnS is dissolved in few drops of conc.HNO{3}. A filter paper is dipped" +
                    " in Cobalt Nitrate is soaked in the test solution and shown into the flame",
                    "Green edged ash",
                    "Green edged ash due to the formation of CoZnO{2} (Rinman's green)"));


            listRadical.add(new Radical("Zinc", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Cobalt
            formula = "Co{{2+}}";

            experimentList.add(new Experiment("The black precipitate of CoS is dissolved in minimal quantity of conc.HNO{3} and heated." +
                    " NH{4}SCN is added, followed by Amyl alcohol and the test tube is shaken well",
                    "The alcoholic layer turns blue",
                    formula + " is confirmed"));


            listRadical.add(new Radical("Cobalt", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Nickel
            formula = "Ni{{2+}}";

            experimentList.add(new Experiment("The black precipitate of NiS is dissolved in Aqua regia (3 parts conc.HCl is to 1 parts conc.HNO{3}). " +
                    "A portion of it is taken and made distinctly alkaline with NH{4}OH and DMG (Dimethylglyoxime) is added",
                    "Rosy red precipitate is formed",
                    formula + " is confirmed"));


            listRadical.add(new Radical("Nickel", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Manganese
            formula = "Mn{{2+}}";

            experimentList.add(new Experiment("The flesh coloured precipitate of MnS is dissolved in few drops of dil.HCl and boiled to drive off " +
                    "H{2}S. Dil.HNO{3} is added followed by Sodium Bismuthate (NaBiO{3})",
                    "Purple colouration is formed (pink)",
                    formula + " is confirmed"));


            listRadical.add(new Radical("Manganese", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Barium
            formula = "Ba{{2+}}";

            experimentList.add(new Experiment("The white precipitate of BaCO{3} is dissolved in acetic acid, boiled to drive off CO{2}, divided " +
                    "into 3 parts",
                    "",
                    ""));


            experimentList.add(new Experiment("To the 1st part, K{2}CrO{4} (Potassium Chromate) is added",
                    "Yellow precipitate",
                    formula + " is confirmed"));


            experimentList.add(new Experiment("To the 2nd part, (NH{4}){2}SO{4} (Ammonium Sulphate) is added",
                    "White precipitate",
                    formula + " is confirmed"));


            experimentList.add(new Experiment("To the 3rd part, (NH{4}){2}C{2}O{4} (Ammonium Oxalate) is added",
                    "White precipitate",
                    formula + " is confirmed"));


            experimentList.add(new Experiment("Flame test:\nThe salt is made into a paste with conc.HCl. It is taken in a glass rod " +
                    "and shown in the flame",
                    "Apple green colour",
                    formula + " is confirmed"));


            listRadical.add(new Radical("Barium", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Strontium
            formula = "Sr{{2+}}";

            experimentList.add(new Experiment("The white precipitate of SrCO{3} is dissolved in acetic acid, boiled to drive off CO{2}, divided " +
                    "into 3 parts",
                    "",
                    ""));


            experimentList.add(new Experiment("To the 1st part, K{2}CrO{4} (Potassium Chromate) is added",
                    "No precipitate",
                    "Ba{{2+}} is absent"));


            experimentList.add(new Experiment("To the 2nd part, (NH{4}){2}SO{4} (Ammonium Sulphate) is added",
                    "White precipitate",
                    "Presence of " + formula));


            experimentList.add(new Experiment("To the 3rd part, (NH{4}){2}C{2}O{4} (Ammonium Oxalate) is added",
                    "White precipitate",
                    "Presence of " + formula + " confirmed"));


            experimentList.add(new Experiment("Flame test:\nThe salt is made into a paste with conc.HCl. It is taken in a glass rod " +
                    "and shown in the flame",
                    "Crimson red colour",
                    "Presence of " + formula + " confirmed"));


            listRadical.add(new Radical("Strontium", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Calcium
            formula = "Ca{{2+}}";

            experimentList.add(new Experiment("The white precipitate of SrCO{3} is dissolved in acetic acid, boiled to drive off CO{2}, divided " +
                    "into 3 parts",
                    "",
                    ""));


            experimentList.add(new Experiment("To the 1st part, K{2}CrO{4} (Potassium Chromate) is added",
                    "No precipitate",
                    "Absence of Ba{{2+}}"));


            experimentList.add(new Experiment("To the 2nd part, (NH{4}){2}SO{4} (Ammonium Sulphate) is added",
                    "White precipitate",
                    "Absence of Sr{{2+}}"));


            experimentList.add(new Experiment("To the 3rd part, (NH{4}){2}C{2}O{4} (Ammonium Oxalate) is added",
                    "White precipitate",
                    "Presence of " + formula + " confirmed"));


            experimentList.add(new Experiment("Flame test:\nThe salt is made into a paste with conc.HCl. It is taken in a glass rod " +
                    "and shown in the flame",
                    "Brick red colour",
                    "Presence of " + formula + " confirmed"));


            listRadical.add(new Radical("Calcium", formula, false, experimentList));
        }
        {
            ArrayList<Experiment> experimentList = new ArrayList<>();
            // Magnesium
            formula = "Mg{{2+}}";

            experimentList.add(new Experiment("To the filtrate of group 5, Disodium hydrogen phosphate (NaHPO{4}) is added",
                    "White precipitate",
                    formula + " is confirmed"));


            experimentList.add(new Experiment("To the above precipitate, conc.HCl is added, followed by Magneson reagent and NaOH",
                    "Blue precipitate",
                    formula + " is confirmed"));


            listRadical.add(new Radical("Magnesium", formula, false, experimentList));
        }
        return listRadical;

    }

    /*static class Experiment {
        String experiment, observation, conclusion;
        String tag=null;
        String allowedTags[] = {"HCL", "H2SO4", "HEAT", "AGNO3", "SPL", "CONTINUE"};
        boolean isDryTest;

        Experiment(String experiment, String observation, String conclusion, String tag, boolean isDryTest) {
            this.experiment = experiment;
            this.observation = observation;
            this.conclusion = conclusion;
            this.isDryTest = isDryTest;
            boolean isAllowed = false;
            for (String x : allowedTags) {
                if (x.equals(tag)) {
                    isAllowed = true;
                    break;
                }
            }
            if (isAllowed)
                this.tag = tag;
            else throw new IllegalArgumentException("Experiment tag illegal");
        }

        Experiment(String experiment, String observation, String conclusion) {
            this.experiment = experiment;
            this.observation = observation;
            this.conclusion = conclusion;
        }
    }*/

    static class Radical {
        String name, formula;
        boolean isAcidic, isDrySplPresent = false, isWetSplPresent = false;
        ArrayList<Experiment> experiment;

        Radical(String name, String formula, boolean isAcidic, ArrayList<Experiment> experiment) {
            this.name = name;
            this.formula = formula;
            this.isAcidic = isAcidic;
            this.experiment = experiment;

            for (Experiment e : experiment) {
                if (e.getTag() != null && e.getTag().equals("SPL")) {
                    if (e.isDryTest())
                        isDrySplPresent = true;
                    else isWetSplPresent = true;

                    if (isDrySplPresent && isWetSplPresent)
                        break;
                }
            }
        }
    }
}
