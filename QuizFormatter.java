
public class QuizFormatter {
	
	public String formatHomepage() {
		return "<html><head><body style=\"background-color:#46C4FF;\"><br><center><h1 style = color:#FFFFFF;>"
				+ "<font size=+5>Quiz App<br><br></font><h1 style = color:#FFFFFF ;><i><font size=+2>Science Quiz</font>"
				+ "</i></center><br></head></html>";
	}
	
	public String formatStreak(int streak, Quiz quiz) {
		return "<html><b><font size=+2>Streak: "+ streak + "</font></b><br>";
	}
	public String formatHeader(int q, Quiz quiz) {
		 return "<center><h1><h1 style = color:#46C4FF  ;>Science Quiz - Question " + (q+1) + " of 5</h1><br>"
		 		+ "<font size=+2>True or False?</font><br><br>";
	}
	
	public String formatQuestion(String question) {
		return "<font size=+2>" + question + "</center></font></html>";
	}
	
	public String formatResponse(boolean correct, Quiz quiz) {
		String s = "<html><font size=+2><center>";
		if (correct) {
			s += "Well done! Your streak has increased.<br>";
			quiz.answeredCorrectly();
		}
		else {
			s += "Oops! You got this one wrong and your streak<br>has been reset.";
		}
		return s + " Click Next to move on.</center></font></html>";
	}
	
	public String formatSkipped(boolean answer, Quiz quiz) {
		quiz.skippedQuestion();
		return "<html><font size=+2><center>Question skipped. The correct answer is \"" + answer + "\".<br>"
				+ "Click Next to move on.</center></font></html>";
	}
	
	public String formatResults(Quiz quiz) {
		String correct = "<u>" + quiz.getCorrect() + " / 5</u> Correct";
		String streak = "<u>" + quiz.getHighestStreak() + "</u> Longest Streak";
		String skipped = "<u>" + quiz.getSkipped() + "</u> Skipped";
		return "<html><body style=\"background-color:#46C4FF ;\"><br><br><br><center><h1 style = color:#FFFFFF;>"
				+ "<font size=+4><b>RESULTS</b><br><br>" + correct + "<br><br>" + streak + "<br><br>" + skipped + ""
						+ "</font></center></html>";
	}
}