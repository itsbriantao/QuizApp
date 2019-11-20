import java.util.ArrayList;

import javax.swing.event.ChangeListener;

public class Quiz {
	
	private int streak;
	private ArrayList<String> questions;
	private ArrayList<Boolean> answers;
	private int correctCount;
	private int highestStreak;
	private int numSkipped;
	
	public Quiz() {
		streak = 0;
		questions = new ArrayList<>();
		answers = new ArrayList<>();
		correctCount = 0;
		highestStreak = 0;
		numSkipped = 0;
	}
	
	
	public int getStreak() {
		return streak;
	}
	
	public int setStreak(boolean correct) {
		if (correct) {
			streak++;
			if (streak > highestStreak)
				highestStreak = streak;
		}
		else
			streak = 0;
		return streak;
	}
	
	public int getHighestStreak() {
		return highestStreak;
	}
	
	public void answeredCorrectly() {
		correctCount++;
	}
	
	public int getCorrect() {
		return correctCount;
	}
	
	public void skippedQuestion() {
		numSkipped++;
	}
	
	public int getSkipped() {
		return numSkipped;
	}
	
	public void reset() {
		streak = highestStreak = correctCount = numSkipped = 0;
	}
		
	public void addQuestion(String question, boolean answer) {
		questions.add(question);
		answers.add(answer);
	}
	
	public String getQuestion(int q) {
		return questions.get(q);
	}
	
	public boolean getAnswer(int q) {
		return answers.get(q);
	}
	
	public int getNumOfQuestions() {
		return questions.size();
	}
}
