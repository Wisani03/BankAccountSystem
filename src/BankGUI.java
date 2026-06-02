import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankGUI extends JFrame {
    private SavingsAccount savings;
    private CurrentAccount cheque;
    private ShortTerm shortTerm;
    private LongTerm longTerm;


    private JLabel labelLogo, labelTitle, labelSelector, labelAmount, labelResult;
    private JComboBox<String> accountDropdown;
    private JTextField textFieldAmount;
    private JButton buttonDeposit, buttonWithdraw;
    private JPanel mainPanel;

    public BankGUI() {
        // 1. Initialize backend account models with starting cash
        savings = new SavingsAccount("SAV-4412", "Wisani Mabunda", 5000.0, 6.5, "1234");
        cheque = new CurrentAccount("CHQ-5502", "Wisani Mabunda", 1000.0, 3000.0, "5678");
        shortTerm = new ShortTerm("FIX-9901", "Tresina Mabunda", 10000.0, "1111");
        longTerm = new LongTerm("FIX-7704", "Tresina Mabunda", 50000.0, "2222");

        // Window Border Setup
        setTitle("Wimab Bank *** Premium Dashboard");
        setSize(450, 480); // Adjusted height comfortably to fit all elements
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //  Let us Setup Logo Image
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/logo.jpeg"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon finalLogo = new ImageIcon(scaledImage);
        labelLogo = new JLabel(finalLogo);
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //  Setup Welcome Title with official branding
        labelTitle = new JLabel("Welcome to Wimab Banking App");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        //  Setup Account Selector Dropdown Menu
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

        // Let us Setup Interaction Buttons
        buttonDeposit = new JButton("Deposit");
        buttonWithdraw = new JButton("Withdraw");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(buttonDeposit);
        buttonPanel.add(buttonWithdraw);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        labelResult = new JLabel("Current Balance: R" + savings.getBalance());
        labelResult.setFont(new Font("Arial", Font.BOLD, 14));
        labelResult.setForeground(new Color(0, 128, 0));
        labelResult.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Assemble Main Panel Using Vertical Y_AXIS BoxLayout
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
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        mainPanel.add(labelResult);


        attachButtonLogic();
        add(mainPanel);
    }



    public void attachButtonLogic() {

        accountDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }
        });

        // This is for the deposit button action.
        buttonDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(textFieldAmount.getText().trim());
                    if (amount > 0) {
                        getSelectedAccount().deposit(amount);
                        updateDisplay();
                        textFieldAmount.setText("");
                        JOptionPane.showMessageDialog(null, "Successfully deposited R" + amount + " into Wimab Bank Account.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter an amount greater than zero.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid numeric input. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Withdrawal Action button
        buttonWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(textFieldAmount.getText().trim());
                    if (amount > 0) {
                        getSelectedAccount().withdraw(amount);
                        updateDisplay();
                        textFieldAmount.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter an amount greater than zero.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid numeric input. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // This helps to evaluate which account string row is currently picked in the dropdown list
    private BankAccount getSelectedAccount() {
        int index = accountDropdown.getSelectedIndex();
        if (index == 1) return cheque;
        if (index == 2) return shortTerm;
        if (index == 3) return longTerm;
        return savings; // This is index = 0
    }

    // This Refreshes the display text label
    private void updateDisplay() {
        labelResult.setText("Current Balance: R" + String.format("%.2f", getSelectedAccount().getBalance()));
    }
}