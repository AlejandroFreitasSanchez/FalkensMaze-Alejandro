/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

import java.util.ArrayList;
import javafx.geometry.Insets;

import javafx.scene.layout.FlowPane;

import pedro.ieslaencanta.com.falkensmaze.Size;

/**
 *
 * @author Pedro
 */
public class BlocksPanel extends FlowPane implements IBlockListener {

   
    private Size blockSize;
    private ArrayList<Block> blocks;
    private Block selected;

    public BlocksPanel() {
        super();
        this.blocks = new ArrayList<>();
        this.setPrefWrapLength(110); // preferred width allows for two columns
        //this.setStyle("-fx-background-color: FF0000;");
        this.setPadding(new Insets(5, 0, 5, 0));
        this.setBlockSize(new Size(50, 50));
        //b.setNum_cols(2);
    }

   

    public void addBlock(Block b) {
        this.blocks.add(b);
    }

    public void init() {
        int col = 0, row = 0;

        this.setVgap(5);
        this.setHgap(5);

        for (int i = 0; i < this.blocks.size(); i++) {
            final Block b = this.blocks.get(i);
            b.setSize(this.blockSize);
            b.addBlocklistener(this);
            b.init();

            this.getChildren().add(b.getComponent());
           

        }

    }

   

    public Size getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Size blockSize) {
        this.blockSize = blockSize;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public void onClicked(Block b) {
        if(this.selected!=null)
            this.selected.unselect();
        this.selected = b;
        b.select();
     }

    @Override
    public void onDoubleClicked(Block b) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
