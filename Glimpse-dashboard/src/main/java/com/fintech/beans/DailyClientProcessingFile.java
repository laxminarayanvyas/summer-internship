package com.fintech.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity

public class DailyClientProcessingFile {
	@Id
	private int sftpparser_statistics_id;
	private String user_name;
	private Date process_timestamp_local;
	private String process_status;
	private String is_notification_sent;
	private String event_type;
	private String status_details;
	private int is_active;
	@JsonFormat(pattern = "HH:mm:ss")
	private Date entry_dt_time;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updated_date;
	private String filename;
	private int is_test;
	
	
	public DailyClientProcessingFile() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DailyClientProcessingFile(int sftpparser_statistics_id, String user_name, Date process_timestamp_local,
			String process_status, String is_notification_sent, String event_type, String status_details, int is_active,
			Date entry_dt_time, Date updated_date, String filename, int is_test) {
		super();
		this.sftpparser_statistics_id = sftpparser_statistics_id;
		this.user_name = user_name;
		this.process_timestamp_local = process_timestamp_local;
		this.process_status = process_status;
		this.is_notification_sent = is_notification_sent;
		this.event_type = event_type;
		this.status_details = status_details;
		this.is_active = is_active;
		this.entry_dt_time = entry_dt_time;
		this.updated_date = updated_date;
		this.filename = filename;
		this.is_test = is_test;
	}


	public int getSftpparser_statistics_id() {
		return sftpparser_statistics_id;
	}


	public void setSftpparser_statistics_id(int sftpparser_statistics_id) {
		this.sftpparser_statistics_id = sftpparser_statistics_id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public Date getProcess_timestamp_local() {
		return process_timestamp_local;
	}


	public void setProcess_timestamp_local(Date process_timestamp_local) {
		this.process_timestamp_local = process_timestamp_local;
	}


	public String getProcess_status() {
		return process_status;
	}


	public void setProcess_status(String process_status) {
		this.process_status = process_status;
	}


	public String getIs_notification_sent() {
		return is_notification_sent;
	}


	public void setIs_notification_sent(String is_notification_sent) {
		this.is_notification_sent = is_notification_sent;
	}


	public String getEvent_type() {
		return event_type;
	}


	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}


	public String getStatus_details() {
		return status_details;
	}


	public void setStatus_details(String status_details) {
		this.status_details = status_details;
	}


	public int getIs_active() {
		return is_active;
	}


	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}


	public Date getEntry_dt_time() {
		return entry_dt_time;
	}


	public void setEntry_dt_time(Date entry_dt_time) {
		this.entry_dt_time = entry_dt_time;
	}


	public Date getUpdated_date() {
		return updated_date;
	}


	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public int getIs_test() {
		return is_test;
	}


	public void setIs_test(int is_test) {
		this.is_test = is_test;
	}


	@Override
	public String toString() {
		return "DailyClientProcessingFile [sftpparser_statistics_id=" + sftpparser_statistics_id + ", user_name="
				+ user_name + ", process_timestamp_local=" + process_timestamp_local + ", process_status="
				+ process_status + ", is_notification_sent=" + is_notification_sent + ", event_type=" + event_type
				+ ", status_details=" + status_details + ", is_active=" + is_active + ", entry_dt_time=" + entry_dt_time
				+ ", updated_date=" + updated_date + ", filename=" + filename + ", is_test=" + is_test + "]";
	}
	
	
	
}
