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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Requests {

	public static class MetricsRequest{
		@JsonProperty("userId")
		private String userId;
		@JsonProperty("date")
		@JsonFormat(pattern="yyyy-MM-dd")
		private Date date;
		
		
		public MetricsRequest(){
			
		}
		public MetricsRequest(String userId, Date date) {
			this.userId = userId;
			this.date = date;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
	}
	
	public static class RoomUsageDurationRequest{
		@JsonProperty("userId")
		private String userId;
		@JsonProperty("month")
		private int month;
		@JsonProperty("year")
		private int year;
		@JsonProperty("room")
		private String room;
		@JsonProperty("metric_name")
		private String metric_name;
		
		
		public RoomUsageDurationRequest(){
			
		}
		public RoomUsageDurationRequest(String userId, int month) {
			this.userId = userId;
			this.month = month;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public int getMonth() {
			return month;
		}
		public void setMonth(int m) {
			this.month = m;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int y) {
			this.year = y;
		}
		public String getRoom() {
			return room;
		}
		public void setRoom(String room) {
			this.room = room;
		}
		public String getMetric_name() {
			return metric_name;
		}
		public void setMetric_name(String metric_name) {
			this.metric_name = metric_name;
		}
	}
}
