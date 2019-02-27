/**
Copyright 2018-2019. Information Technologies Institute (CERTH-ITI)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */


package vicinity.vas.VCNT_VAS_AbnormalDetection;

import java.util.HashMap;

public class Utils {
	String room_types1[] = {"Bedroom", "Kitchen", "Living_room", "WC"};
	String room_types2[] = {"Bedroom", "Corridor", "Living_room", "WC"};
	String room_types3[] = {"Bedroom", "Kitchen", "Living_room", "Bedroom2"};
	String room_types4[] = {"Living_room2", "Kitchen", "Living_room", "Corridor"};
	
	protected HashMap<String, String[]> map = new HashMap<String, String[]>();
	
	Utils(){
		map.put("PX_Home_1", room_types1);
		map.put("PX_Home_2", room_types1);
		map.put("PX_Home_3", room_types2);
		map.put("PX_Home_4", room_types3);
		map.put("PX_Home_5", room_types1);
		map.put("PX_Home_7", room_types1);
		map.put("PX_Home_8", room_types2);
		map.put("PX_Home_10", room_types1);
		map.put("PX_Home_12", room_types4);
		map.put("PX_Home_14", room_types1);
		

	}
}
