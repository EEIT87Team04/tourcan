package com.tourcan.quest.model;



import java.sql.Timestamp;


public class QuestVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer quest_catalog;
	private String quest_topic;
	private Integer quest_id;
	private String mem_uid;
	private String quest_quiz;
	private Integer admin_id;	
	private String quest_reply;
	private Timestamp quest_qtime;
	private Timestamp quest_rtime;
	
	public QuestVO() {
	}
	
	public Integer getQuest_catalog() {
		return quest_catalog;
	}
	public void setQuest_catalog(Integer quest_catalog) {
		this.quest_catalog = quest_catalog;
	}
	public String getQuest_topic() {
		return quest_topic;
	}
	public void setQuest_topic(String quest_topic) {
		this.quest_topic = quest_topic;
	}
	public Integer getQuest_id() {
		return quest_id;
	}
	public void setQuest_id(Integer quest_id) {
		this.quest_id = quest_id;
	}
	public String getMem_uid() {
		return mem_uid;
	}
	public void setMem_uid(String mem_uid) {
		this.mem_uid = mem_uid;
	}
	public String getQuest_quiz() {
		return quest_quiz;
	}
	public void setQuest_quiz(String quest_quiz) {
		this.quest_quiz = quest_quiz;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getQuest_reply() {
		return quest_reply;
	}
	public void setQuest_reply(String quest_reply) {
		this.quest_reply = quest_reply;
	}
	public Timestamp getQuest_qtime() {
		return quest_qtime;
	}
	public void setQuest_qtime(Timestamp quest_qtime) {
		this.quest_qtime = quest_qtime;
	}
	public Timestamp getQuest_rtime() {
		return quest_rtime;
	}
	public void setQuest_rtime(Timestamp quest_rtime) {
		this.quest_rtime = quest_rtime;
	}

}
