package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ScoreModel {

public ObservableList<String> score;
	
	public ScoreModel() {
		score = FXCollections.observableArrayList();
	}
	
	public void addScore(String name) {
		score.add(name);
	}
	
	public ObservableList<String> getScoreList() {
		return score;
	}
	
	public int getTotalScores() {
		return score.size();
	}
}
