/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze;

import jakarta.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pedro.ieslaencanta.com.falkensmaze.components.Block;
import pedro.ieslaencanta.com.falkensmaze.components.BlocksPanel;
import pedro.ieslaencanta.com.falkensmaze.components.DialogSize;
import pedro.ieslaencanta.com.falkensmaze.components.DialogTime;
import pedro.ieslaencanta.com.falkensmaze.components.MazeCanvas;
import pedro.ieslaencanta.com.falkensmaze.model.Maze;

/**
 *
 * @author Pedro
 */
public class Principal extends Application {

    Scene scene;

    private int width = 480;
    private int height = 480;
    final FileChooser fileChooser;
    private MazeCanvas maze;

    public Principal() {
        super();
        fileChooser = new FileChooser();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();
        border.setCenter(this.createMaze());
        border.setLeft(this.createBlockMenu());
        border.setTop(this.createMenu());
        this.scene = new Scene(border, this.width + 120, this.height + 50);

        stage.setTitle("Falken's Maze Editor");
        stage.setResizable(false);
        stage.setScene(scene);
        //para que cierre al pulsar el icono
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        stage.show();
        this.maze.draw();
    }

    private Pane createBlockMenu() {
        BlocksPanel b = new BlocksPanel();
        Block tb;
        String[] nombres = Block.getNamesBlocks();
        for (int i = 0; i < nombres.length; i++) {
            tb = new Block();
            tb.setTipo(nombres[i]);
            b.addBlock(tb);
            tb.addBlocklistener(this.maze);
        }
        b.init();
        return b;
    }

    private MazeCanvas createMaze() {
        this.maze = new MazeCanvas();
        this.maze.setBoard_size(new Size(this.width, this.height));
        this.maze.setRows(10);
        this.maze.setCols(10);
        // this.maze.setCell_size(new Size(this.width / 10, this.height / 10));
        this.maze.init();

        return this.maze;
    }

    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("Archivo");
        MenuItem newMenuItem = new MenuItem("Nuevo");
        newMenuItem.setOnAction(eh -> {
            DialogSize ds = new DialogSize();
            ds.init();
            Optional<Size> result = ds.showAndWait();
            if (result.get() != null) {
                this.maze.reset(result.get());
            }
        });
        MenuItem saveMenuItem = new MenuItem("Guardar");
        saveMenuItem.setOnAction(actionEvent -> {

            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(scene.getWindow());
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML", "*.xml*"),
                new FileChooser.ExtensionFilter("JSon", "*.json"),
                new FileChooser.ExtensionFilter("Bin", "*.bin")
            );
            if (file != null) {
                try {
                    Maze.save(this.maze.getMaze(), file);

                } catch (JAXBException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Infor error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());

                    alert.showAndWait();
                } catch (Exception ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Infor error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());

                    alert.showAndWait();

                }
            }

        });
        MenuItem loadMenuItem = new MenuItem("Abrir");
        loadMenuItem.setOnAction(actionEvent -> {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML", "*.xml*"),
                new FileChooser.ExtensionFilter("JSon", "*.json"),
                new FileChooser.ExtensionFilter("Bin", "*.bin")
            );
            File file = fileChooser.showOpenDialog(scene.getWindow());
            if (file != null) {
                try {
                    Maze m = Maze.load(file);
                    this.maze.reset(new Size(m.getBlocks().length,m.getBlocks()[0].length));
                    this.maze.setMaze(m);
                    this.maze.draw();
                } catch (Exception ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        Menu optionsMenu = new Menu("Options");
        MenuItem soundMenu = new MenuItem("Sound");
        optionsMenu.getItems().add(soundMenu);
        soundMenu.setOnAction(actionEvent -> {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("mp3", "*.mp3*")
             
            );
            File file = fileChooser.showOpenDialog(scene.getWindow());
            if (file != null) {

                this.maze.getMaze().setSound(file.getAbsolutePath());

            }

        });
        
           MenuItem timeMenu = new MenuItem("Time");
           timeMenu.setOnAction(eh -> {
            DialogTime dt = new DialogTime();
            dt.init();
            Optional<Double> result = dt.showAndWait();
            if (result.get() != null) {
                this.maze.getMaze().setTime(result.get());
            }
        });
        optionsMenu.getItems().add(timeMenu);
        timeMenu.setOnAction(actionEvent -> {
           

        });

        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, loadMenuItem,
                new SeparatorMenuItem(), exitMenuItem);

        menuBar.getMenus().addAll(fileMenu, optionsMenu);//, webMenu, sqlMenu);
        return menuBar;
    }

}
