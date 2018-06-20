package vicinity.adapter.services.VCNT_Adapter_AbnormalDetection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CallRestfulService {
	public static String callService(String wsUrl, String crudVerb, ArrayList<Variable> inputs, String entity,
			 ArrayList<Variable> requestHeaderList) {
		String result = "";
		String inputList = "";
		if (!inputs.isEmpty()) {
			for (Variable input : inputs) {
				if (input.value != null && !input.value.isEmpty()) {
					if (!inputList.isEmpty())
						inputList += "&";
					inputList += input.name + "=" + input.value;
				}
			}
		}

		// instead of using URLEncoder
		inputList = inputList.replaceAll("\\{", "%7B");
		inputList = inputList.replaceAll("\\}", "%7D");
		inputList = inputList.replaceAll("\\[", "%5B");
		inputList = inputList.replaceAll("\\]", "%5D");
		inputList = inputList.replaceAll("\\:", "%3A");

		String inputListGet = "";
		if (!inputList.isEmpty()) {
			inputListGet = "?" + inputList;
		}
		if (crudVerb.equalsIgnoreCase("get") || crudVerb.equalsIgnoreCase("post") || crudVerb.equalsIgnoreCase("delete")
				|| crudVerb.equalsIgnoreCase("put")) {
			HttpURLConnection conn = null;
			try {
				URL url = new URL((wsUrl + inputListGet).replaceAll(" ", "%20"));
				System.out.println("Calling " + url.toString());
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(30000);
				conn.setRequestMethod(crudVerb);
				conn.setRequestProperty("Accept", "application/json");
				// if (hasAuth) {
				// Base64 b = new Base64();
				// String encoding = b.encodeAsString(new String(auth).getBytes());
				// conn.setRequestProperty("Authorization", "Basic " + encoding);
				// }
				if (requestHeaderList != null) {
					for (Variable header : requestHeaderList) {
						conn.setRequestProperty(header.name, header.value);
					}
				}
				if (crudVerb.equalsIgnoreCase("post") || crudVerb.equalsIgnoreCase("put")) {

					conn.setRequestProperty("Content-Type", "application/json");
					conn.setDoInput(true);
					conn.setDoOutput(true);
					DataOutputStream os = new DataOutputStream(conn.getOutputStream());
					os.writeBytes(entity);

				}
				if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201 && conn.getResponseCode() != 301) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output;
				System.out.println("Output from Server ....\n");
				while ((output = br.readLine()) != null) {
					result += output;
					System.out.println(output);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}
		} else {
			System.out.println("Operation " + crudVerb + " is not a valid CRUD operation");
		}
		if (result.startsWith("[")) {
			result = "{\"Property\":" + result + "}";
		}
		return result;
	}
	
	public static class Variable {
		public String name;
		public String value;
		public String type;
		public ArrayList<Variable> subtypes = new ArrayList<Variable>();
		public ArrayList<Variable> arrayElements = new ArrayList<Variable>();

		Variable(String name, String value, String type) {
			this.name = name;
			this.value = value;
			this.type = type;
		}

		Variable(Variable prototype) {
			this.name = prototype.name;
			this.value = prototype.value;
			this.type = prototype.type;
			for (Variable sub : prototype.subtypes) {
				Variable arg = new Variable(sub);
				subtypes.add(arg);
			}
			for (Variable el : prototype.arrayElements) {
				Variable arg = new Variable(el);
				arrayElements.add(arg);
			}
		}

		public Variable getSubtype(String name) {
			for (Variable sub : subtypes) {
				if (sub.name.equals(name.replaceAll("[^A-Za-z]", ""))) {
					return sub;
				}
			}
			return null;
		}
	}
}
