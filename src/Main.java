import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Launches the Wimab Bank GUI window safely
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankGUI myWindow = new BankGUI();
                myWindow.setVisible(true);
            }
        });
    }
}