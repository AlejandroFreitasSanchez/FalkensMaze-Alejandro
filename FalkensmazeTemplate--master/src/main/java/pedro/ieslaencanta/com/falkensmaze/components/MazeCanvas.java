/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;


import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import pedro.ieslaencanta.com.falkensmaze.Size;
import pedro.ieslaencanta.com.falkensmaze.model.Maze;

/**
 *
 * @author Pedro
 */
public class MazeCanvas extends StackPane implements IBlockListener {

    private GraphicsContext ctx;
    private Size board_size;
    private Size boardcells_size;
    private int rows;
    private int cols;
    private Canvas canvas, bgcanvas;
    private GraphicsContext ctxbg;
    private Block block;
    private Maze maze;

    public MazeCanvas() {
        super();
    }

    public void init() {
        this.canvas = new Canvas(this.board_size.getWidth(), this.board_size.getHeight());
        this.bgcanvas = new Canvas(this.board_size.getWidth(), this.board_size.getHeight());
        this.ctx = canvas.getGraphicsContext2D();
        this.ctxbg = this.bgcanvas.getGraphicsContext2D();
        this.initMaze();
        this.getChildren().add(this.bgcanvas);
        this.getChildren().add(this.canvas);
        this.canvas.setOnMouseClicked(t -> {
            if (this.block != null) {
                int r = (int) (((int) t.getY()) / (this.board_size.getHeight() / this.rows));
                int c = (int) (((int) t.getX()) / (this.board_size.getWidth() / this.cols));
                this.getMaze().setBlockValue(this.block.getType(), r, c);
            }
            this.draw();

        });

    }
    private void initMaze() {
        this.setMaze(new Maze());
        this.getMaze().setSize(new Size(this.getRows(), this.getCols()));
        this.getMaze().init();
    }
    public void reset() {
        this.getMaze().reset();
        this.draw();
    }
    private void clear() {
        this.bgcanvas.getGraphicsContext2D().clearRect(0, 0, this.board_size.getWidth(), this.board_size.getHeight());
        this.canvas.getGraphicsContext2D().clearRect(0, 0, this.board_size.getWidth(), this.board_size.getHeight());
    }
    public void reset(Size s) {
        this.setRows(s.getHeight());
        this.setCols(s.getWidth());
        this.getMaze().reset(s);
        this.clear();
        this.init();
        this.draw();
    }

    private void drawGrid(GraphicsContext gc) {

        gc.clearRect(0, 0, this.board_size.getWidth(), this.board_size.getHeight());
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, this.getBoard_size().getWidth(), this.getBoard_size().getHeight());
        //gc.setFill(new Color((double) Math.random(), (double) Math.random(), (double) Math.random(), 1));
        gc.setStroke(Color.BROWN);//new Color((double) Math.random(), (double) Math.random(), (double) Math.random(), 1));
        int h = this.board_size.getHeight() / this.getRows();
        int w = this.board_size.getWidth() / this.getCols();
        for (int k = 0; k < this.rows; k++) {
            gc.moveTo(0, k * h);
            gc.lineTo(this.getBoard_size().getWidth(), k * h);
            gc.stroke();
        }
        for (int i = 0; i < this.cols; i++) {
            gc.moveTo(i * w, 0);
            gc.lineTo(i * w, this.getBoard_size().getHeight());
            gc.stroke();
        }
    }

    public void draw() {
        this.drawGrid(this.ctxbg);
        this.draw(this.ctx);
    }

    private void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BROWN);
        gc.clearRect(0, 0, this.getBoard_size().getWidth(), this.getBoard_size().getHeight());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                if (this.getMaze().getBlockValue(j, i) != null) {
                    Rectangle2D r = Block.getCoordenadaByName(this.getMaze().getBlockValue(j, i));
                    int h = this.board_size.getHeight() / this.getRows();
                    int w = this.board_size.getWidth() / this.getCols();
                    gc.drawImage(Block.getImage(), r.getMinX(), r.getMinY(), 300, 300, j * w, i * h, w, h);// USE_PREF_SIZE, USE_PREF_SIZE);

                }
            }
        }
    }

    public Size getBoard_size() {
        return board_size;
    }

    public void setBoard_size(Size board_size) {
        this.board_size = board_size;
    }

    public void setBlock(Block block) {
        this.block = block;
        this.draw();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void onClicked(Block b) {
        this.block = b;
    }

    @Override
    public void onDoubleClicked(Block b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
