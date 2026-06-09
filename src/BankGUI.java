import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BankGUI extends JFrame {
    private SavingsAccount savings;
    private CurrentAccount cheque;
    private ShortTerm shortTerm;
    private LongTerm longTerm;

    private JLabel labelLogo, labelTitle, labelSelector, labelAmount, labelResult, labelLogTitle;
    private JComboBox<String> accountDropdown;
    private JTextField textFieldAmount;
    private JButton buttonDeposit, buttonWithdraw;
    private JTextArea textAreaLog; // History stream component
    private JPanel mainPanel;

    public BankGUI() {
        // 1. Initialize backend accounts with secure tracking PINs
        savings = new SavingsAccount("SAV-4412", "Wisani Mabunda", 5000.0, 6.5, "1234");
        cheque = new CurrentAccount("CHQ-5502", "Wisani Mabunda", 1000.0, 3000.0, "5678");
        shortTerm = new ShortTerm("FIX-9901", "Tresina Mabunda", 10000.0, "1111");
        longTerm = new LongTerm("FIX-7704", "Tresina Mabunda", 50000.0, "2222");

        // 2. Window Frame Geometry Config
        setTitle("Wimab Bank *** Secure Commercial Dashboard");
        setSize(480, 650); // Increased height to match the new history log component cleanly
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 3. Setup Logo
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/logo.jpeg"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon finalLogo = new ImageIcon(scaledImage);
        labelLogo = new JLabel(finalLogo);
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 4. Titles & Row Labels
        labelTitle = new JLabel("Welcome to Wimab Banking App");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelSelector = new JLabel("Select Account Type:");
        labelSelector.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] accountOptions = { "Savings Account", "Current (Cheque) Account", "Short-Term Investment", "Long-Term Investment" };
        accountDropdown = new JComboBox<>(accountOptions);
        accountDropdown.setMaximumSize(new Dimension(250, 30));
        accountDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelAmount = new JLabel("Enter amount ZAR:");
        labelAmount.setFont(new Font("Arial", Font.PLAIN, 14));
        labelAmount.setAlignmentX(Component.CENTER_ALIGNMENT);

        textFieldAmount = new JTextField(15);
        textFieldAmount.setMaximumSize(new Dimension(200, 30));
        textFieldAmount.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 5. Buttons
        buttonDeposit = new JButton("Deposit");
        buttonWithdraw = new JButton("Withdraw");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(buttonDeposit);
        buttonPanel.add(buttonWithdraw);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 6. Current Balance Feedback Text
        labelResult = new JLabel("Current Balance: R" + savings.getBalance());
        labelResult.setFont(new Font("Arial", Font.BOLD, 14));
        labelResult.setForeground(new Color(0, 128, 0));
        labelResult.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 7. Initialize Transaction Log UI Elements
        labelLogTitle = new JLabel("Live Audit & Transaction Log:");
        labelLogTitle.setFont(new Font("Arial", Font.BOLD, 12));
        labelLogTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        textAreaLog = new JTextArea(6, 30);
        textAreaLog.setEditable(false);
        textAreaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textAreaLog.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(textAreaLog);
        scrollPane.setMaximumSize(new Dimension(400, 120));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 8. Vertical Layout Matrix Assembly
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainPanel.add(labelLogo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(labelTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(labelSelector);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(accountDropdown);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(labelAmount);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(textFieldAmount);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(labelResult);

        // Appending the log view system to the bottom section
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(labelLogTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(scrollPane);

        // 9. Startup Logic Engine execution
        logMessage("Wimab Secure Shell initialization complete.");
        logMessage("Default Active profile: Savings Account.");

        attachButtonLogic();
        add(mainPanel);
    }


    public void attachButtonLogic() {

        accountDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
                logMessage("Switched View context to: " + accountDropdown.getSelectedItem().toString());
            }
        });

        // Secured Deposit Call
        buttonDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(textFieldAmount.getText().trim());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter an amount greater than zero.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String inputPin = JOptionPane.showInputDialog(null, "Enter 4-Digit Security PIN for Verification:", "Security Verification", JOptionPane.QUESTION_MESSAGE);

                    if (inputPin == null) return; // User aborted dialog prompt

                    if (getSelectedAccount().verifyPin(inputPin)) {
                        getSelectedAccount().deposit(amount);
                        updateDisplay();
                        logMessage("DEPOSIT: R" + String.format("%.2f", amount) + " -> " + accountDropdown.getSelectedItem().toString());
                        textFieldAmount.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "ACCESS DENIED: Invalid Account Authentication PIN Code.", "Security Violation", JOptionPane.ERROR_MESSAGE);
                        logMessage("SECURITY ALARM: Failed Authentication Attempt on " + accountDropdown.getSelectedItem().toString());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid numeric input. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(textFieldAmount.getText().trim());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter an amount greater than zero.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String inputPin = JOptionPane.showInputDialog(null, "Enter 4-Digit Security PIN for Verification:", "Security Verification", JOptionPane.QUESTION_MESSAGE);

                    if (inputPin == null) return; // User aborted dialog prompt

                    if (getSelectedAccount().verifyPin(inputPin)) {
                        getSelectedAccount().withdraw(amount);
                        updateDisplay();
                        logMessage("WITHDRAWAL: R" + String.format("%.2f", amount) + " <- " + accountDropdown.getSelectedItem().toString());
                        textFieldAmount.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "ACCESS DENIED: Invalid Account Authentication PIN Code.", "Security Violation", JOptionPane.ERROR_MESSAGE);
                        logMessage("SECURITY ALARM: Failed Withdrawal Attempt on " + accountDropdown.getSelectedItem().toString());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid numeric input. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private BankAccount getSelectedAccount() {
        int index = accountDropdown.getSelectedIndex();
        if (index == 1) return cheque;
        if (index == 2) return shortTerm;
        if (index == 3) return longTerm;
        return savings;
    }

    private void updateDisplay() {
        labelResult.setText("Current Balance: R" + String.format("%.2f", getSelectedAccount().getBalance()));
    }

    private void logMessage(String message) {
        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        textAreaLog.append("[" + timestamp + "] " + message + "\n");
        textAreaLog.setCaretPosition(textAreaLog.getDocument().getLength());
    }
}