package net.worldwizard.lasertank.loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataLoader {
    // Private constructor
    private DataLoader() {
	// Do nothing
    }

    public static ArrayList<String> loadCheats() {
	try (InputStream is = DataLoader.class.getResourceAsStream("/net/worldwizard/lasertank/assets/data/cheats.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr)) {
	    ArrayList<String> returnList = new ArrayList<>();
	    String line = "";
	    while (line != null) {
		line = br.readLine();
		if (line != null) {
		    returnList.add(line);
		}
	    }
	    return returnList;
	} catch (IOException ioe) {
	    return new ArrayList<>();
	}
    }
}
