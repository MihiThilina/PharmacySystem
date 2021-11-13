package Util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import static javafx.scene.paint.Color.rgb;

public class Validation {
    public static Object validate(LinkedHashMap<JFXTextField, Pattern> map, Button btn) {
        btn.setDisable(true);
        for (TextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()){
                if (!textFieldKey.getText().isEmpty()){
                    ((JFXTextField)textFieldKey).setFocusColor(rgb(255, 0, 0));
                    ((JFXTextField)textFieldKey).setUnFocusColor(rgb(255, 0, 0));
                }
                return textFieldKey;
            }
           //textFieldKey.setStyle("-fx-border-color: green");
            ((JFXTextField)textFieldKey).setFocusColor((rgb(0, 128, 0)));
            ((JFXTextField)textFieldKey).setUnFocusColor((rgb(0, 128, 0)));
        }
        btn.setDisable(false);
        return true;
    }

    public static Object validateTextField(LinkedHashMap<TextField, Pattern> map, Button btn) {
        btn.setDisable(true);
        for (TextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()){
                if (!textFieldKey.getText().isEmpty()){
                    textFieldKey.setStyle("-fx-border-color: red");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-border-color: green");
        }
        btn.setDisable(false);
        return true;
    }

}
