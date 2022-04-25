package be.intec.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class USConstants {

    public final static String US = "US";

    public final static Map<String, String> mapOfUSStates = new HashMap<String, String>() {
        {
            put("Brussels", "Brussels");
            put("Bruges", "Bruges");
            put("Antwerp", "Antwerp");
            put("Ghent", "Ghent");
            put("Leuven", "Leuven");
            put("Namur", "Namur");
            put("Mechelen", "Mechelen");
            put("Halle", "Halle");

        }
    };

    public final static List<String> listOfUSStatesCode = new ArrayList<>(mapOfUSStates.keySet());
    public final static List<String> listOfUSStatesName = new ArrayList<>(mapOfUSStates.values());

}
