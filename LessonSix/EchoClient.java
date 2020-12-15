package ru.geekbrains;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class EchoClient extends JFrame {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8222;
    private Socket socket;
    private JTextField msgField;
    private JTextArea chatArea;
    private DataInputStream in;
    private DataOutputStream out;

    public EchoClient() {
        this.msgField = new JTextField();
        this.chatArea = new JTextArea();
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase("/end")) {
                            break;
                        }
                        chatArea.append(strFromServer);
                        chatArea.append("\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        if (!msgField.getText().trim().isEmpty()) {
            try {
                out.writeUTF(msgField.getText());
                chatArea.append(msgField.getText() + "\n");
                msgField.setText("");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

    public void prepareGUI() {

        setTitle("Another new chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 600);
        setResizable(false);

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        //JTextArea chatArea = new JTextArea();
        chatArea.setEnabled(false);
        chatArea.setDisabledTextColor(Color.BLACK);
        JScrollPane chatScroll = new JScrollPane(chatArea);
        chatPanel.add(chatScroll);

        JPanel msgPanel = new JPanel();
        msgPanel.setPreferredSize(new Dimension(0, 50));
        //JTextField msgField = new JTextField();
        msgField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        msgField.setPreferredSize(new Dimension(335, 50));
        JButton sendButton = new JButton();
        sendButton.setPreferredSize(new Dimension(50, 50));
        sendButton.setText(">>");
        sendButton.addActionListener(e -> {
            sendMessage();
        });
        msgPanel.setLayout(new BorderLayout());
        msgPanel.add(msgField, BorderLayout.WEST);
        msgPanel.add(sendButton, BorderLayout.EAST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("/end");
                    closeConnection();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });
        setLayout(new BorderLayout());
        add(chatPanel, BorderLayout.CENTER);
        add(msgPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EchoClient();
            }
        });
    }
}
