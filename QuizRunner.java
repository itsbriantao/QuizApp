import java.awt.*;
import javax.swing.*;

public class QuizRunner {
	
	private static int currentQ;
	private static String header;
	
	public static void main(String[] args) {
		
		Quiz quiz = new Quiz();
		quiz.addQuestion("Software often costs more to maintain than to develop.", true);
		quiz.addQuestion("Functional software engineering activities include specification, development, validation, and evolution.", true);
		quiz.addQuestion("The waterfall model is a software process that interleaves development stages.", false);
		quiz.addQuestion("Stages of software product testing include component testing, system testing, and robustness testing.", false);
		
		QuizFormatter qf = new QuizFormatter();
		
		JTextPane questionPane = new JTextPane();
		questionPane.setPreferredSize(new Dimension(500, 300));
		questionPane.setContentType("text/html");
		questionPane.setEditable(false);
		
		JTextPane responsePane = new JTextPane();
		responsePane.setPreferredSize(new Dimension(500, 150));
		responsePane.setContentType("text/html");
		responsePane.setEditable(false);
		
		currentQ = 0;
		
		
		header = qf.formatHeader(currentQ) + qf.formatQuestion(quiz.getQuestion(currentQ));
		
		questionPane.setText(qf.formatStreak(quiz.getStreak()) + header);
		
		/*quiz.addChangeListener(event -> {
			questionPane.setText(s);
		});
		
		JButton t = new JButton("True");
		t.addActionListener(event -> questionPane.setText(quiz.formatHeader(qf, currentQuestion) + quiz.formatFooterAnswered(qf, quiz.getAnswer(currentQuestion))));
		
		JButton f = new JButton("False");
		f.addActionListener(event -> questionPane.setText(quiz.formatHeader(qf, currentQuestion) + quiz.formatFooterAnswered(qf, !quiz.getAnswer(currentQuestion))));	
		
		JButton skip = new JButton("Skip");
		skip.addActionListener(event -> questionPane.setText(quiz.formatHeader(qf, currentQuestion) + quiz.formatFooterSkipped(qf, currentQuestion)));
		
		JButton next = new JButton("Next");
		next.addActionListener(event -> {
			currentQuestion++;
			header = quiz.formatHeader(qf, currentQuestion) + quiz.formatQuestion(currentQuestion);
			questionPane.setText(header);
		});*/
		
		JButton t = new JButton("True");
		JButton f = new JButton("False");
		JButton skip = new JButton("Skip");
		JButton next = new JButton("Next");
		
		t.addActionListener(event -> {
			t.setEnabled(false);
			f.setEnabled(false);
			skip.setEnabled(false);
			boolean correct = quiz.getAnswer(currentQ);
			int streak = quiz.setStreak(correct);
			questionPane.setText(qf.formatStreak(streak) + header);
			responsePane.setText(qf.formatResponse(correct));
			next.setEnabled(true);
		});
		
		
		f.addActionListener(event -> {
			t.setEnabled(false);
			f.setEnabled(false);
			skip.setEnabled(false);
			boolean correct = !quiz.getAnswer(currentQ);
			int streak = quiz.setStreak(correct);
			questionPane.setText(qf.formatStreak(streak) + header);
			responsePane.setText(qf.formatResponse(correct));
			next.setEnabled(true);
		});
		

		skip.addActionListener(event -> {
			t.setEnabled(false);
			f.setEnabled(false);
			skip.setEnabled(false);
			responsePane.setText(qf.formatSkipped(quiz.getAnswer(currentQ)));
			next.setEnabled(true);
		});
		
		
		next.addActionListener(event -> {
			currentQ++;
			if (currentQ < quiz.getNumOfQuestions()) {
				header = qf.formatHeader(currentQ) + qf.formatQuestion(quiz.getQuestion(currentQ));
				questionPane.setText(qf.formatStreak(quiz.getStreak()) + header);
				responsePane.setText("");
				t.setEnabled(true);
				f.setEnabled(true);
				skip.setEnabled(true);
				next.setEnabled(false);
			}
			else {
				questionPane.setText("<html><br><br><br><center><h1>Sorry, the results page has not yet been implemented.</center></h1></html>");
				responsePane.setText("");
			}
		});
		
		next.setEnabled(false);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(t);
		buttonPanel.add(f);
		buttonPanel.add(skip);
		buttonPanel.add(next);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(questionPane, BorderLayout.NORTH);
		textPanel.add(responsePane, BorderLayout.SOUTH);
		
		JFrame frame = new JFrame();
		frame.add(textPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
