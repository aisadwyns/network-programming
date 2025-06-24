package tugas13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatMulticastGUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private ChatMulticastController controller;

    public ChatMulticastGUI() {
        controller = new ChatMulticastController();

        setTitle("Multicast Chat Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener((ActionEvent e) -> {
            String msg = inputField.getText();
            if (!msg.isEmpty()) {
                controller.sendMessage(msg);
                chatArea.append("Me: " + msg + "\n");
                inputField.setText("");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatMulticastGUI::new);
    }
}

