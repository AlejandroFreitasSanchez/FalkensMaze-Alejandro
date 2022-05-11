/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;

import javafx.scene.control.Slider;

import javafx.scene.layout.GridPane;

import pedro.ieslaencanta.com.falkensmaze.Size;

/**
 *
 * @author Pedro
 */
public class DialogSize extends Dialog<Size> {

    public DialogSize() {
        super();
    }

    public void init() {
        this.setTitle("Nuevo laberinto");
        ButtonType acceptButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(acceptButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Slider width = new Slider();
        width.setMin(1);
        width.setMax(20);
        width.setBlockIncrement(1);
        width.setShowTickLabels(true);
        width.setShowTickMarks(true);

        Slider height = new Slider();
        height.setMin(1);
        height.setMax(20);
        height.setBlockIncrement(1);
        height.setShowTickLabels(true);
        height.setShowTickMarks(true);

        grid.add(new Label("Ancho:"), 0, 0);
        grid.add(width, 1, 0);
        grid.add(new Label("Alto:"), 0, 1);
        grid.add(height, 1, 1);
        this.getDialogPane().setContent(grid);
        this.setResultConverter(dialogButton -> {
            if (dialogButton == acceptButtonType) {
                int w=(int)width.getValue();
                int h= (int)height.getValue();
                return new Size(w,h);
            }
            return null;
        });
    }
}
