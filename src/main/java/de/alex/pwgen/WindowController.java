package de.alex.pwgen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WindowController {

    private static char[] UPPER_ALPHABET;
    private static char[] LOWER_ALPHABET;
    private static char[] NUMBERS;
    private static final char[] SPECIAL_CHARS = {'$','!','?','-','_','§','&'};

    @FXML
    private CheckBox bol_alpha;

    @FXML
    private CheckBox bol_lower_alpha;

    @FXML
    private CheckBox bol_nums;

    @FXML
    private CheckBox bol_special_chars;

    @FXML
    private Button btn_work;

    @FXML
    private Label length_label;

    @FXML
    private Slider sl_length;


    @FXML
    private TextField tb_password;

    @FXML
    public void initialize() {
        bol_alpha.setSelected(true);
        bol_lower_alpha.setSelected(true);
        bol_nums.setSelected(true);

        sl_length.valueProperty().addListener((observable, oldValue, newValue) -> {
            length_label.setText(newValue.intValue() + ")");

        });
    }

    public WindowController() {
        UPPER_ALPHABET = new char[26];
        LOWER_ALPHABET = new char[26];
        NUMBERS = new char[10];
        for(int i = 65; i <= 90; i++) {
            UPPER_ALPHABET[i-65] = (char) i;
        }
        for(int i = 97; i <= 122; i++) {
            LOWER_ALPHABET[i-97] = (char) i;
        }
        for(int i = 48; i <= 57; i++) {
            NUMBERS[i-48] = (char)i;
        }
    }

    public int randomInt(int min, int max) {
        Random random = new Random();
        return random.ints(min,max).findFirst().getAsInt();
    }

    @FXML
    void do_Work(ActionEvent event) {
        StringBuilder pw = new StringBuilder();
        ArrayList<Character> pot = new ArrayList<>();
        if(bol_alpha.isSelected()) for(char c: UPPER_ALPHABET) pot.add(c);
        if(bol_lower_alpha.isSelected()) for(char c: LOWER_ALPHABET) pot.add(c);
        if(bol_nums.isSelected()) {
            for(char c: NUMBERS) {
                for(int i = 0; i < 3; i++) { //increase possibility
                    pot.add(c);
                }
            }
        }
        if(bol_special_chars.isSelected()) {
            for (char c : SPECIAL_CHARS) {
                for(int i = 0; i < 2; i++) { //increase possibility
                    pot.add(c);
                }
            }
        }
        Collections.shuffle(pot);
        if(pot.size() > 0) {
            for(int i = 0; i < (int) sl_length.getValue(); i++) {
                char c = pot.get(randomInt(0,pot.size()-1));
                pw.append(c);
            }
        }
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(pw.toString());
        clipboard.setContent(clipboardContent);
        tb_password.setText(pw.toString());
    }

}
