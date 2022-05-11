/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import pedro.ieslaencanta.com.falkensmaze.Size;

/**
 *
 * @author Pedro
 */
public class Block {

    //private Rectangle r;
    private ImageView imgv;
    private Pane panel;
   
    private boolean selected;
    private Size size;
    private static Image img;
    private static Size img_block_size;
    private static Map<String, Rectangle2D> imgs_coordenadas;
    private String type;
    private ArrayList<IBlockListener> blocklisteners;

    static {
        try {

            int w = (int) (300);
            int h = (int) (300);
            Size img_block_size = new Size(w, h);

            img = new Image(Block.class.getResource("/blocks.png").toURI().toString());

            imgs_coordenadas = new HashMap<>();
            imgs_coordenadas.put("Ladrillo", new Rectangle2D(30, 18, w, h));
            imgs_coordenadas.put("Hielo", new Rectangle2D(516, 18, w, h));
            imgs_coordenadas.put("Submarino", new Rectangle2D(1003, 18, w, h));

            imgs_coordenadas.put("Pasto", new Rectangle2D(30, 448, w, h));
            imgs_coordenadas.put("Sorpresa", new Rectangle2D(516, 448, w, h));
            imgs_coordenadas.put("Cajon", new Rectangle2D(1003, 448, w, h));

            imgs_coordenadas.put("Cesped", new Rectangle2D(30, 870, w, h));
            imgs_coordenadas.put("Tierra", new Rectangle2D(516, 870, w, h));
            imgs_coordenadas.put("Exclamacion", new Rectangle2D(1003, 870, w, h));

        } catch (URISyntaxException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Block() {

        
        this.selected = false;
        this.size = null;
        this.type = null;
        this.imgv = new ImageView(Block.getImage());
        this.panel = new Pane();
        this.panel.getChildren().add(imgv);
       // this.panel.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, null, null)));
        
        this.blocklisteners= new ArrayList();
    }

    public static Image getImage() {
        return Block.img;
    }

    public static String[] getNamesBlocks() {
        

        return Arrays.stream(Block.imgs_coordenadas.keySet().toArray()).toArray(String[]::new);//(String[]) Block.imgs_coordenadas.keySet().toArray();
    }

    public static Rectangle2D getCoordenadaByName(String name) {
        return Block.imgs_coordenadas.get(name);
    }

    public static Size getImgBlockSize() {
        return Block.img_block_size;
    }

    public void init() {

        this.imgv.setViewport(Block.getCoordenadaByName(type));
        this.imgv.setFitWidth(50);
        this.imgv.setFitHeight(50);
        this.imgv.setOnMouseClicked(eh -> {
           this.blocklisteners.forEach(a->{a.onClicked(this);} );        
        });
    }

    

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        this.selected =true;
        
        this.panel.setStyle("-fx-background-color: FF0000;");
    }
    public void unselect(){
        this.selected=false;
        this.panel.setStyle("");
    }
    
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Node getComponent() {
        return this.panel;
    }

    public String getType() {
        if(this.type!="Exclamacion")
            return type;
        else
            return null;
    }

    public void setTipo(String type) {
        this.type = type;
        this.imgv.setViewport(Block.getCoordenadaByName(type));

    }

    public void addBlocklistener(IBlockListener blocklistener) {
        this.blocklisteners.add(blocklistener);
    }
    public void removeBlocklistener(IBlockListener blocklistener){
        this.blocklisteners.remove(blocklistener);
    }

}
