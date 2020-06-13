package editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextEditor extends JFrame {
    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle("Swing Text Editor");
        setVisible(true);
        //setLayout(null);
        addComponents();
    }

    public void addComponents() {

        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");
        textArea.setBounds(50, 100, 300, 50);
        textArea.setVisible(true);

        JScrollPane textAreaScrollPane = new JScrollPane();
        textAreaScrollPane.setName("ScrollPane");
        textAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textAreaScrollPane.setVisible(true);

        textAreaScrollPane.add(textArea);
        add(textAreaScrollPane, BorderLayout.SOUTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setName("Panel");
        menuPanel.setBounds(50, 50, 500, 100);
        menuPanel.setVisible(true);

        JTextField filenameTextField = new JTextField();
        filenameTextField.setName("FilenameField");
        filenameTextField.setBounds(50, 30, 200, 40);
        filenameTextField.setVisible(true);

        JButton loadButton = new JButton();
        loadButton.setName("LoadButton");
        loadButton.setText("Load");
        loadButton.setBounds(275, 30, 100, 40);
        loadButton.addActionListener( event -> {
            String filename = filenameTextField.getText();
            Path absolutePath = Paths.get(filename).toAbsolutePath().normalize();
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(absolutePath.toUri())));
                textArea.setText(fileContent);
            } catch (IOException e) {
                textArea.setText("");
            }
        });
        loadButton.setVisible(true);

        JButton saveButton = new JButton();
        saveButton.setName("SaveButton");
        saveButton.setText("Save");
        saveButton.setBounds(400, 30, 100, 40);
        saveButton.addActionListener( event -> {
            String filename = filenameTextField.getText();
            Path absolutePath = Paths.get(filename).toAbsolutePath().normalize();
            try {
                Files.write(absolutePath, textArea.getText().getBytes());
            } catch (IOException e) {

            }
        });
        saveButton.setVisible(true);

        menuPanel.add(filenameTextField);
        menuPanel.add(loadButton);
        menuPanel.add(saveButton);

        add(menuPanel, BorderLayout.NORTH);


    }
}
