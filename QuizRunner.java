import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class QuizRunner {
	
	private static int currentQ;
	private static String header;
	private static int[] questionList;
	
	private static int[] pickQuestions() {
		int[] questionList = {-1, -1, -1, -1, -1};
		int i = 0;
		while (i < questionList.length) {
			int questionNumber = (int)(Math.random()*10);
			boolean present = false;
			int j = 0;
			while (!present && j < questionList.length){
				if (questionList[j] == questionNumber)
					present = true;
				++j;
			}
			if (!present)
				questionList[i++] = questionNumber;
		}
		return questionList;
	}
	
	public static void main(String[] args) {
		
		Quiz quiz = new Quiz();
		quiz.addQuestion("Frogs are cold blooded animals.", true);
		quiz.addQuestion("Stretching for over 1,429 miles, The Great Barrier Reef is the largest living structure on Earth.", true);
		quiz.addQuestion("Wolves are the fastest land animals.", false);
		quiz.addQuestion("Albert Einstein was the first to detect radioactivity.", false);
		quiz.addQuestion("Velocity is another name for time.", false);
		quiz.addQuestion("Pumice floats on water.", true);
		quiz.addQuestion("Neutrons are positively charged.", false);
		quiz.addQuestion("Before it has erupted, molten rock is known as lava.", false);
		quiz.addQuestion("Electrons orbit the nucleus of an atom.", true);
		quiz.addQuestion("Issac is the first name of the scientist that gave us Newton's three laws.", true);
		
		QuizFormatter qf = new QuizFormatter();
		
		JTextPane questionPane = new JTextPane();
		questionPane.setPreferredSize(new Dimension(500, 300));
		questionPane.setContentType("text/html");
		questionPane.setEditable(false);
		
		JTextPane responsePane = new JTextPane();
		responsePane.setPreferredSize(new Dimension(500, 150));
		responsePane.setContentType("text/html");
		responsePane.setEditable(false);
		
		questionList = pickQuestions();	
		currentQ = 0;
		
		JButton t = new JButton("True");
		JButton f = new JButton("False");
		JButton skip = new JButton("Skip");
		JButton next = new JButton("Next");
		
		t.addActionListener(event -> {
			t.setEnabled(false);
			f.setEnabled(false);
			skip.setEnabled(false);
			boolean correct = quiz.getAnswer(questionList[currentQ]);
			int streak = quiz.setStreak(correct);
			questionPane.setText(qf.formatStreak(streak, quiz) + header);
			responsePane.setText(qf.formatResponse(correct, quiz));
			next.setEnabled(true);
		});
		
		
		f.addActionListener(event -> {
			t.setEnabled(false);
			f.setEnabled(false);
			skip.setEnabled(false);
			boolean correct = !quiz.getAnswer(questionList[currentQ]);
			int streak = quiz.setStreak(correct);
			questionPane.setText(qf.formatStreak(streak, quiz) + header);
			responsePane.setText(qf.formatResponse(correct, quiz));
			next.setEnabled(true);
		});
		

		skip.addActionListener(event -> {
			t.setEnabled(false);
			f.setEnabled(false);
			skip.setEnabled(false);
			responsePane.setText(qf.formatSkipped(quiz.getAnswer(questionList[currentQ]), quiz));
			next.setEnabled(true);
		});
		
		// Declare quizFrame here, before adding the ActionListener to next
		JFrame quizFrame = new JFrame();
		JFrame resultsFrame = new JFrame();
		JTextPane results = new JTextPane();
		
		next.addActionListener(event -> {
			++currentQ;
			if (currentQ < 5) {
				header = qf.formatHeader(currentQ, quiz) + qf.formatQuestion(quiz.getQuestion(questionList[currentQ]));
				questionPane.setText(qf.formatStreak(quiz.getStreak(), quiz) + header);
				responsePane.setText("");
				t.setEnabled(true);
				f.setEnabled(true);
				skip.setEnabled(true);
				next.setEnabled(false);
			}
			else {
				results.setText(qf.formatResults(quiz));
				resultsFrame.setVisible(true);
				quizFrame.setVisible(false);
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
		
		quizFrame.add(textPanel, BorderLayout.CENTER);
		quizFrame.add(buttonPanel, BorderLayout.SOUTH);
		quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quizFrame.pack();
		quizFrame.setVisible(false);

		////////// HOME PAGE ///////////
		ImageIcon icon = new ImageIcon("src/logo.png");
		Image image = icon.getImage();
		image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		JLabel logo = new JLabel(icon);
		logo.setBorder(new EmptyBorder(50,50,50,50));
		
		JTextPane title = new JTextPane();
		title.setPreferredSize(new Dimension(500, 150));
		title.setContentType("text/html");
		title.setEditable(false);
		title.setText(qf.formatHomepage());
		
		JButton start = new JButton("START");
		
		JFrame homepage = new JFrame();
		homepage.add(title, BorderLayout.NORTH);
		homepage.add(logo, BorderLayout.CENTER);
		homepage.add(start, BorderLayout.SOUTH);
		homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homepage.pack();
		homepage.setVisible(true);
		
		start.addActionListener(event -> {
			header = qf.formatHeader(currentQ, quiz) + qf.formatQuestion(quiz.getQuestion(questionList[currentQ]));
			questionPane.setText(qf.formatStreak(quiz.getStreak(), quiz) + header);
			quizFrame.setVisible(true);
			homepage.setVisible(false);
		});
		
		
		////////// RESULTS PAGE ////////////
		JButton returnHome = new JButton("Return to home page");

		results.setPreferredSize(new Dimension(500, 450));
		results.setContentType("text/html");
		results.setEditable(false);
		
		resultsFrame.add(results);
		resultsFrame.add(returnHome, BorderLayout.SOUTH);
		resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultsFrame.pack();
		resultsFrame.setVisible(false);
		
		returnHome.addActionListener(event -> {
			homepage.setVisible(true);
			resultsFrame.setVisible(false);;
			responsePane.setText("");
			quiz.reset();
			currentQ = 0;
			questionList = pickQuestions();
			t.setEnabled(true);
			f.setEnabled(true);
			skip.setEnabled(true);
		});
	}
}
