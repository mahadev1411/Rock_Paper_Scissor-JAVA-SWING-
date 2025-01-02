import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissor extends JFrame implements ActionListener
{
    JButton rock, paper, scissor;
    JLabel comp_score, comp_choice, player_score_label;
    int player_score = 0;
    int computer_score = 0;
    String[] choices = {"Rock", "Paper", "Scissor"};
    Random random = new Random();

    public RockPaperScissor() {
        super("Rock Paper Scissor");

        setSize(450, 574);
        setLayout(null); // Use absolute positioning of elements
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setLocationRelativeTo(null); // Center the window on the screen

        addGuiComponents();
    }

    private void addGuiComponents() {
        // Computer score label
        comp_score = new JLabel("Computer: 0");
        comp_score.setBounds(0, 43, 450, 30);
        comp_score.setFont(new Font("Dialog", Font.BOLD, 26));
        comp_score.setHorizontalAlignment(SwingConstants.CENTER);
        add(comp_score);

        // Computer choice display
        comp_choice = new JLabel("--");
        comp_choice.setBounds(175, 118, 98, 81);
        comp_choice.setFont(new Font("Dialog", Font.PLAIN, 18));
        comp_choice.setHorizontalAlignment(SwingConstants.CENTER);
        comp_choice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(comp_choice);

        // Player score label
        player_score_label = new JLabel("Player: 0");
        player_score_label.setBounds(0, 317, 450, 30);
        player_score_label.setFont(new Font("Dialog", Font.BOLD, 26));
        player_score_label.setHorizontalAlignment(SwingConstants.CENTER);
        add(player_score_label);

        // Rock button
        rock = new JButton("Rock");
        rock.setBounds(40, 387, 105, 81);
        rock.setFont(new Font("Dialog", Font.BOLD, 18));
        rock.addActionListener(this);
        add(rock);

        // Paper button
        paper = new JButton("Paper");
        paper.setBounds(165, 387, 105, 81);
        paper.setFont(new Font("Dialog", Font.BOLD, 18));
        paper.addActionListener(this);
        add(paper);

        // Scissors button
        scissor = new JButton("Scissor");
        scissor.setBounds(290, 387, 105, 81);
        scissor.setFont(new Font("Dialog", Font.BOLD, 18));
        scissor.addActionListener(this);
        add(scissor);
    }

    private void showDialog(String message) {
        JDialog resultDialog = new JDialog(this, "Result", true);
        resultDialog.setSize(250, 150);
        resultDialog.setLayout(new BorderLayout());
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        // Label for result message
        JLabel resultLabel = new JLabel(message, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        resultDialog.add(resultLabel, BorderLayout.CENTER);

        // Try Again button
        JButton tryAgain = new JButton("Try Again");
        tryAgain.setFont(new Font("Dialog", Font.PLAIN, 14));
        tryAgain.addActionListener(e -> {
            comp_choice.setText("--"); // Reset the computer's choice
            resultDialog.dispose(); // Close the dialog box
        });
        resultDialog.add(tryAgain, BorderLayout.SOUTH);

        resultDialog.setLocationRelativeTo(this); // Center the dialog relative to the main frame
        resultDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = "";
        if (e.getSource() == rock) {
            playerChoice = "Rock";
        } else if (e.getSource() == paper) {
            playerChoice = "Paper";
        } else if (e.getSource() == scissor) {
            playerChoice = "Scissor";
        }

        String computerChoice = choices[random.nextInt(3)];
        comp_choice.setText(computerChoice);

        String result;
        if (playerChoice.equals(computerChoice)) {
            result = "It's a Tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissor")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissor") && computerChoice.equals("Paper"))) {
            result = "Player Wins!";
            player_score++;
            player_score_label.setText("Player: " + player_score);
        } else {
            result = "Computer Wins!";
            computer_score++;
            comp_score.setText("Computer: " + computer_score);
        }

        showDialog(result);
    }
}