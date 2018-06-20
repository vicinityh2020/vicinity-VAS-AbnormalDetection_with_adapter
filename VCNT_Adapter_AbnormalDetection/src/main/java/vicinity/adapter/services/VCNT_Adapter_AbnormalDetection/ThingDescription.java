package vicinity.adapter.services.VCNT_Adapter_AbnormalDetection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class ThingDescription {

	final String file_name;

	ThingDescription() {
		file_name = "TD.json";
	}

	protected String readTDFile() {

		String thingsDescription = "";

		// Get file from resources folder
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(file_name);
		String result = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String output;
		System.out.println("Output from Server ....\n");
		try {
			while ((output = br.readLine()) != null) {
				result += output;
				//System.out.println(output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thingsDescription = result;
		return thingsDescription;
	}

}
