package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.ScoreModel;
import pacman.GameManager;

public class ScoreController {
	@FXML ListView<String> ListView;
	@FXML Text info;
	@FXML ImageView highest;
	@FXML ObservableList<String> strList = FXCollections.observableArrayList();
	private ScoreModel sm;
	
	/**
	 * initialize the score page to load data into ListView.
	 */
	@FXML
	public void initialize() {
		switch(GameManager.difficulty) {
			case 1:
				info.setText("Your score : " + GameManager.score);
				break;
			case 2:
				info.setText("Your score : " + GameManager.score * 1.2);
				break;
			case 3:
				info.setText("Your score : " + GameManager.score * 1.5);
				break;
		}
		sm = new ScoreModel();
		ReadFile();
		
		SortList();
		showHighest();
		AddOrder();
		ListView.setItems(sm.getScoreList());
		ListView.setPrefSize(400, 200);
		sm.getScoreList().addListener((ListChangeListener<? super String>) c -> {
			//label.setText(Integer.toString(sm.getTotalScores()));
		});
	}
	
	/**
	 * Read scores from file and add them into ListView.
	 * Read files from relative location and read line by line to get the score for each time.
	 * Store all the double value into ListView.
	 */
	public void ReadFile() {
		BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader("./src/pacman/Score.csv"));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	sm.addScore(tempString);
	        } 			    
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	}
	
	/**
	 * Sort the ListView from high score to lower one.
	 * Get each double value of score and then use bubble sort to make them in decent order.
	 */
	public void SortList() {
		for(int i = 0; i < sm.score.size(); i++) {
			double max = Double.valueOf(sm.score.get(i)).doubleValue();
			int maxj = i;
			for(int j = i; j < sm.score.size(); j++) {
				if(max < Double.valueOf(sm.score.get(j)).doubleValue()) {
					max = Double.valueOf(sm.score.get(j)).doubleValue();
					maxj = j;
				}
			}
			String temp = sm.score.get(i);
			sm.score.set(i, sm.score.get(maxj));
			sm.score.set(maxj, temp);
		}
	}
	
	/**
	 * Add order to each score.
	 */
	public void AddOrder() {
		for(int i = 0; i < sm.score.size(); i++) {
			sm.score.set(i, (i+1) + ". " + sm.score.get(i));
		}
	}
	
	/**
	 * If this score is the highest score, show the image of the highest.
	 */
	public void showHighest() {
		Image high = new Image("./image/highest.png");
		highest.setImage(high);
		double yscore = 0;
		switch(GameManager.difficulty) {
		case 1:
			yscore = GameManager.score;
			break;
		case 2:
			yscore = GameManager.score*1.2;
			break;
		case 3:
			yscore = GameManager.score*1.5;
			break;
		}
		if(yscore == Double.valueOf(sm.score.get(0)).doubleValue()) {
			highest.setVisible(true);
		}
		else {
			highest.setVisible(false);
		}
		
	}
}
