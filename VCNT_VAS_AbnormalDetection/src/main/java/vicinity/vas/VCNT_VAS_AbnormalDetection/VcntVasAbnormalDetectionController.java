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

import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

import vicinity.vas.MySQL.MySQLMethods;
import vicinity.vas.MySQL.ResultSetSerializer;
import vicinity.vas.VCNT_VAS_AbnormalDetection.Requests.MetricsRequest;
import vicinity.vas.VCNT_VAS_AbnormalDetection.Requests.RoomUsageDurationRequest;

@Controller
public class VcntVasAbnormalDetectionController {

	/**
	 * @param oid:
	 *            the device/service id in the infrastructure (not the Vicinity
	 *            oid)
	 * @param request:
	 *            userId and date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/{oid}/properties/activity_level", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String generateActivityLevelResponse(@PathVariable("oid") String oid, @RequestBody MetricsRequest request)
			throws Exception {
		System.out.println("Entered PUT properties");
		// make the query to DB
		String userId = request.getUserId();
		Date date = request.getDate();
		String query = "SELECT * FROM `activity_level` WHERE (userId = \'" + userId + "\' AND date=\'" + date + "\');";
		String response = MySQLMethods.selectQuery(query);

		// Return the response

		return response;
	}

	/**
	 * @param oid:
	 *            the device/service id in the infrastructure (not the Vicinity
	 *            oid)
	 * @param request:
	 *            userId and date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/{oid}/properties/mobility_level", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String generateMobilityLevelResponse(@PathVariable("oid") String oid, @RequestBody MetricsRequest request)
			throws Exception {
		System.out.println("Entered PUT properties");
		// make the query to DB
		String userId = request.getUserId();
		Date date = request.getDate();
		String query = "SELECT * FROM `mobility_level` WHERE (userId = \'" + userId + "\' AND date=\'" + date + "\');";
		String response = MySQLMethods.selectQuery(query);

		// Return the response

		return response;
	}

	/**
	 * @param oid:
	 *            the device/service id in the infrastructure (not the Vicinity
	 *            oid)
	 * @param request:
	 *            userId and date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/{oid}/properties/nri_level", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String generateNRILevelResponse(@PathVariable("oid") String oid, @RequestBody MetricsRequest request)
			throws Exception {
		System.out.println("Entered PUT properties");
		// make the query to DB
		String userId = request.getUserId();
		Date date = request.getDate();
		String query = "SELECT * FROM `nri_level` WHERE (userId = \'" + userId + "\' AND date=\'" + date + "\');";
		String response = MySQLMethods.selectQuery(query);

		// Return the response

		return response;
	}

	/**
	 * @param oid:
	 *            the device/service id in the infrastructure (not the Vicinity
	 *            oid)
	 * @param request:
	 *            userId and date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/{oid}/properties/rooms_duration", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String generateRoomsDurationResponse(@PathVariable("oid") String oid, @RequestBody MetricsRequest request)
			throws Exception {
		System.out.println("Entered PUT properties");
		// make the query to DB
		Utils u = new Utils();
		HashMap<String, String[]> room_map = u.map;
		String userId = request.getUserId();
		Date date = request.getDate();

		String room1 = room_map.get(userId)[0];
		String room2 = room_map.get(userId)[1];
		String room3 = room_map.get(userId)[2];
		String room4 = room_map.get(userId)[3];

		String query = "SELECT id, `Room1` AS " + room1 + ", `Room2` AS " + room2 + ", `Room3` AS " + room3
				+ ", `Room4` AS " + room4 + ", Outside, date, UserId, HouseId FROM `rooms_duration` WHERE (userId = \'"
				+ userId + "\' AND date=\'" + date + "\');";
		String response = MySQLMethods.selectQuery(query);

		// Return the response

		return response;
	}

	/**
	 * @param oid:
	 *            the device/service id in the infrastructure (not the Vicinity
	 *            oid)
	 * @param request:
	 *            userId and date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/{oid}/properties/rooms_usage", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String generateRoomsUsageResponse(@PathVariable("oid") String oid, @RequestBody MetricsRequest request)
			throws Exception {
		System.out.println("Entered PUT properties");
		// make the query to DB
		String userId = request.getUserId();
		Date date = request.getDate();
		String query = "SELECT id, `Room1` AS Bedroom, `Room2` AS Kitchen, `Room3` AS Living_room, `Room4` AS WC, Outside, date, UserId, HouseId FROM `rooms_usage` WHERE (userId = \'"
				+ userId + "\' AND date=\'" + date + "\');";
		String response = MySQLMethods.selectQuery(query);

		// Return the response

		return response;
	}

	/**
	 * @param oid:
	 *            the device/service id in the infrastructure (not the Vicinity
	 *            oid)
	 * @param request:
	 *            userId and date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/{oid}/properties/notifications", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String generateNotificationsResponse(@PathVariable("oid") String oid, @RequestBody MetricsRequest request)
			throws Exception {
		System.out.println("Entered PUT properties");
		// make the query to DB
		String userId = request.getUserId();
		Date date = request.getDate();
		String query = "SELECT * FROM `notifications` WHERE (userId = \'" + userId + "\' AND date=\'" + date + "\');";
		String response = MySQLMethods.selectQuery(query);

		// Return the response

		return response;
	}

	/**
	 * @param oid:
	 *            the device/service id in the infrastructure (not the Vicinity
	 *            oid)
	 * @param request:
	 *            userId and date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/objects/{oid}/properties/rooms_usage_duration_month", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String generateRoomsUsageForMonthResponse(@PathVariable("oid") String oid,
			@RequestBody RoomUsageDurationRequest request) throws Exception {
		System.out.println("Entered PUT properties");
		String response = "";
		// make the query to DB
		String table_name = request.getMetric_name();
		String userId = request.getUserId();
		int month = request.getMonth();
		month -= 1;
		int year = request.getYear();
		Date start = MySQLMethods.getStartOfMonth(month, year);
		Date end = MySQLMethods.getEndOfMonth(month, year);
		String room = request.getRoom();

		Utils u = new Utils();
		HashMap<String, String[]> room_map = u.map;
		int num = 0;
		String[] array = room_map.get(userId);
		for (int i = 0; i < 3; i++) {
			if (array[i].equals(room)) {
				num = i+1;
			}
		}
		if (num != 0) {
			String query = "SELECT `Room" + num + "` AS " + table_name + ", DATE_FORMAT(date,'%Y-%m-%d') AS date FROM `" + table_name
					+ "` WHERE (`userId` = \'" + userId + "\' AND `date`>=\'" + start + "\' AND `date`<=\'" + end
					+ "\');";
			response = MySQLMethods.selectQuery(query);
		} else if (room.equals("Outside")){
			String query ="SELECT `" + room + "` AS " + table_name + ", DATE_FORMAT(date,'%Y-%m-%d') AS date FROM `" + table_name
			+ "` WHERE (`userId` = \'" + userId + "\' AND `date`>=\'" + start + "\' AND `date`<=\'" + end
			+ "\');";
			response = MySQLMethods.selectQuery(query);
		} else {
			throw new Exception();
		}

		// Return the response

		return response;
	}

}
