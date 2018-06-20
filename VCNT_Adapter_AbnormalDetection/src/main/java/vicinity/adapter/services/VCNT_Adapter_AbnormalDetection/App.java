package vicinity.adapter.services.VCNT_Adapter_AbnormalDetection;

import java.util.ArrayList;

import vicinity.adapter.services.VCNT_Adapter_AbnormalDetection.CallRestfulService.Variable;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        ArrayList<Variable> requestHeaderList = new ArrayList<Variable>();
        ThingDescription th = new ThingDescription();
		String entity = th.readTDFile();
		System.out.println(entity);
		String wsUrl="http://localhost:9997/agent/objects";
		if (args.length!=0){
			wsUrl="http://" + args[0] + ":9997/agent/objects";
		}
		//System.out.println(wsUrl);
		ArrayList<Variable> inputs = new ArrayList<Variable>();
		String result = CallRestfulService.callService(wsUrl, "POST", inputs, entity,requestHeaderList);
		System.out.println(result);
    }
}
