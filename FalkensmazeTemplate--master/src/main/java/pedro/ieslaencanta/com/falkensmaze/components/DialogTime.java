/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import javafx.scene.control.Slider;

import javafx.scene.layout.GridPane;


/**
 *
 * @author Pedro
 */
public class DialogTime extends Dialog<Double> {

    public DialogTime() {
        super();
    }

    public void init() {
        this.setTitle("Tiempo laberinto");
        ButtonType acceptButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(acceptButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Slider tim = new Slider();
        tim.setMin(1);
        tim.setMax(500);
        tim.setBlockIncrement(1);
        tim.setShowTickLabels(true);
        tim.setShowTickMarks(true);

       

        
        this.getDialogPane().setContent(grid);
        this.setResultConverter(dialogButton -> {
            if (dialogButton == acceptButtonType) {
              
                return  tim.getValue();
            }
            return null;
        });
    }
}
