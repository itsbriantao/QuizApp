public class QuizFormatter {
	
	public String formatStreak(int streak) {
		return "<html><b><font size=+2>" + streak + "</font></b><br>";
	}
	
	public String formatHeader(int q) {
		 return "<center><h1>CMPE 131 - Q" + (q+1) + " of 15</h1><br>";
	}
	
	public String formatQuestion(String question) {
		return "<font size=+2>" + question + "</center></font></html>";
	}
	
	public String formatResponse(boolean correct) {
		String s = "<html><font size=+2><center>";
		if (correct) {
			s += "Well done! Your streak has increased.<br>";
		}
		else {
			s += "Oops! You got this one wrong and your streak<br>has been reset.";
		}
		return s + " Click Next to move on.</center></font></html>";
	}
	
	public String formatSkipped(boolean answer) {
		return "<html><font size=+2><center>Question skipped. The correct answer is \"" + answer + "\".<br>Click Next to move on.</center></font></html>";
	}
}
