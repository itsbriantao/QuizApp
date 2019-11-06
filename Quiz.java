import java.util.ArrayList;

import javax.swing.event.ChangeListener;

public class Quiz {
	
	private int streak;
	private ArrayList<String> questions;
	private ArrayList<Boolean> answers;
	private ArrayList<ChangeListener> listeners;
	
	public Quiz() {
		streak = 0;
		questions = new ArrayList<>();
		answers = new ArrayList<>();
	}
	
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	public int getStreak() {
		return streak;
	}
	
	public int setStreak(boolean correct) {
		if (correct)
			streak++;
		else
			streak = 0;
		return streak;
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
