package com.tourcan.quest.model;


import java.sql.Timestamp;
import java.util.List;

import com.google.api.client.util.DateTime;

public class QuestService {

	private QuestDAO_interface dao;

	public QuestService() {
		dao = new QuestDAO();
	}

	public QuestVO insertQuest(Integer quest_catalog, String quest_topic, Integer mem_uid, String quest_quiz,
			Integer admin_id, String quest_reply, Timestamp quest_qtime, Timestamp quest_rtime) {
		
		QuestVO questVO = new QuestVO();
		
		questVO.setQuest_catalog(quest_catalog);
		questVO.setQuest_topic(quest_topic);
		questVO.setMem_uid(mem_uid);
		questVO.setQuest_quiz(quest_quiz);
		questVO.setAdmin_id(admin_id);
		questVO.setQuest_reply(quest_reply);
		questVO.setQuest_qtime(quest_qtime);
		questVO.setQuest_rtime(quest_rtime);
		
        dao.insert(questVO);
		
		return questVO;
	}
	
	public void deleteQuest(Integer quest_id) {
		dao.delete(quest_id);
	}

	public QuestVO findById(Integer quest_id){
		return dao.findById(quest_id);
	}
	
	public List<QuestVO> findByName(String quest_topic){
		return dao.findByName(quest_topic);
	}
	
	public List<QuestVO> getAll(){
		return dao.getAll();
	}
}
