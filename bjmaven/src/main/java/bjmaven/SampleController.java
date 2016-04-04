/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bjmaven;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class SampleController implements Initializable {

	@FXML private Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
