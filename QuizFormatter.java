public class QuizFormatter {
	
	public String formatHomepage() {
		return "<html><br><center><font size=+5>Quiz App<br><br></font><font size=+2>CMPE 131</font></center></html>";
	}
	
	public String formatStreak(int streak, Quiz quiz) {
		return "<html><b><font size=+2>" + streak + "</font></b><br>";
	}
	
	public String formatHeader(int q, Quiz quiz) {
		 return "<center><h1>CMPE 131 - Q" + (q+1) + " of " + quiz.getNumOfQuestions() + "</h1><br>";
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
		return "<html><font size=+2><center>Question skipped. The correct answer is \"" + answer + "\".<br>Click Next to move on.</center></font></html>";
	}
	
	public String formatResults(Quiz quiz) {
		String correct = "<u>" + quiz.getCorrect() + " / " + quiz.getNumOfQuestions() + "</u> correct";
		String streak = "<u>" + quiz.getHighestStreak() + "</u> longest streak";
		String skipped = "<u>" + quiz.getSkipped() + "</u> skipped";
		return "<html><br><br><br><center><font size=+4><b>RESULTS</b><br><br>" + correct + "<br><br>" + streak + "<br><br>" + skipped + "</font></center></html>";
	}
}
