package com.tourcan.quest.model;

import java.util.List;

public interface QuestDAO_interface {

		public void insert(QuestVO questVO);
		public void update(QuestVO questVO);
		public void delete(Integer quest_id);
		public QuestVO findById(Integer quest_id);
		public List<QuestVO> findByName(String quest_topic);
		public List<QuestVO> getAll();

}
