package com.example.chessui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Group root = new Group();
        root.getChildren().add(blendModeObjects());



        BorderPane pane = new BorderPane();
        pane.setTop(createMenu());

        Scene scene = new Scene(pane, 800, 800);
//        pane.setTop(addHBox("Top"));
        pane.setLeft(addVBox("Left"));
        pane.setRight(addVBox("Right"));
        pane.setBottom(addHBox("Bottom"));

        GridPane gridPane = new GridPane();
//        gridPane.addColumn(8, addHBox("hbox"));
//        gridPane.addRow(8, addVBox("vbox"));
//        gridPane.setMinSize(100,100);
//        gridPane.setMaxSize(100,100);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Button button = new Button();
                if((i+j)%2==0){
                    button.setId("light-button");
                } else{
                    button.setId("dark-button");
                }
                placePiece(button, i,j);
                button.setMinSize(60,60);
                button.setMaxSize(60,60);
                DropShadow shadow = new DropShadow();
                button.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e)->{
                    button.setEffect(shadow); });


                button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)-> {
                    button.setEffect(null);});


//                gridPane.add(addHBox("hbox"),i,j);
                gridPane.add(button, i,j);
            }
        }
        gridPane.setAlignment(Pos.CENTER);
//        gridPane.setGridLinesVisible(true);

        pane.setCenter(gridPane);


        scene.getStylesheets().add("textStyles.css");


        primaryStage.setTitle("Cirrus Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void placePiece(Button button, int col, int row){
        Image img = null;
        if(row==1){
            img = new Image("bpawn.png");
        }
        if(row==6){
            img = new Image("wpawn.png");
        }
        if(row==7){
            if (col==0 || col == 7){
                img = new Image("wrook.png");
            }
            if(col==1 || col == 6){
                img = new Image("whorse.png");
            }
            if(col== 2 || col == 5){
                img = new Image("wbishop.png");
            }
            if(col == 3){
                img = new Image("wqueen.png");
            }
            if(col == 4){
                img = new Image("wking.png");
            }
        }
        if(row==0){
            if (col==0 || col == 7){
                img = new Image("brook.png");
            }
            if(col==1 || col == 6){
                img = new Image("bhorse.png");
            }
            if(col== 2 || col == 5){
                img = new Image("bbishop.png");
            }
            if(col == 3){
                img = new Image("bqueen.png");
            }
            if(col == 4){
                img = new Image("bking.png");
            }
        }
//        button.setGraphic(new ImageView(new Image("bking.png")));
        button.setGraphic(new ImageView(img));
    }
    public MenuBar createMenu(){
        MenuBar menuBar = new MenuBar();
//        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
//        root.setTop(menuBar);

        // File menu - new, save, print, and exit
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem printMenuItem = new MenuItem("Print");
        MenuItem exitMenuItem = new MenuItem("Exit");



        //add the new menuitems to the fileMenu
        fileMenu.getItems().addAll(newMenuItem, saveMenuItem,printMenuItem,
                new SeparatorMenuItem(), exitMenuItem);

        Menu languageMenu = new Menu("Language");
        CheckMenuItem javaMenuItem = new CheckMenuItem("Java");
        CheckMenuItem pythonMenuItem = new CheckMenuItem("Python");
        CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
        languageMenu.getItems().addAll(javaMenuItem, pythonMenuItem, htmlMenuItem);

        CheckMenuItem FXMenuItem = new CheckMenuItem("JavaFX");
        FXMenuItem.setSelected(true);
        languageMenu.getItems().addAll(new SeparatorMenuItem(),FXMenuItem);


        Menu tutorialMenu = new Menu("Tutorial");
        tutorialMenu.getItems().addAll(
                new MenuItem("Buttons"),
                new MenuItem("Menus"),
                new MenuItem("Images"));

        languageMenu.getItems().add(tutorialMenu);

        exitMenuItem.setOnAction(actionEvent-> Platform.exit());

        menuBar.getMenus().addAll(fileMenu, languageMenu);
        return menuBar;
    }
    public static HBox addHBox(String str)
    {
        HBox hbox = new HBox();
        Text text = new Text(str);
        hbox.setId ("hbox");
        text.setId("textstyle");
        hbox.getChildren().add(text);
        return hbox;
    }
    public static VBox addVBox(String str)
    {
        VBox vbox = new VBox();
        Text text = new Text(str);
        text.setId("textstyle");
        vbox.setId("vbox");
        vbox.getChildren().add(text);
        return vbox;

    }
    static Node blendModeObjects() {
        Group g =new Group();

        Circle c = new Circle(50,50,25);
        c.setFill(Color.DARKGRAY);
        c.setBlendMode(BlendMode.MULTIPLY);

        Rectangle r = new Rectangle(50,50,50,50);
        r.setFill(Color.BLUEVIOLET);

        Rectangle r2 = new Rectangle(170,50,50,50);
        r2.setFill(Color.TEAL);

        Circle c2 = new Circle(170,50,25);
        c2.setFill(Color.CORAL);

        //use rectangle to remove a piece from the circle
        //add circle first, then rectangle on top

        Rectangle r3 = new Rectangle(50,150,50,50);
        r3.setFill(Color.WHITE);

        Circle c3 = new Circle(50,150,25);
        c3.setFill(Color.NAVY);

        g.setBlendMode(BlendMode.SRC_ATOP);
        g.getChildren().addAll(r,c, c2, r2, c3, r3);
        Line seesaw = new Line(60,340,340,140);
        seesaw.setStroke(Color.BLACK);
        seesaw.setStrokeWidth(15);

        Circle cir = new Circle(70,280,40);
        cir.setStroke(Color.RED);
        cir.setStrokeWidth(5);
        cir.setFill(Color.ORANGE);

        Circle sun = new Circle(450,-50,140);
        sun.setStroke(Color.YELLOW);
        sun.setFill(Color.YELLOW);

        Rectangle rec = new Rectangle(240,90,80,70);
        rec.setStroke(Color.GREEN);
        rec.setStrokeWidth(5);
        rec.setFill(Color.YELLOWGREEN);

        Line left = new Line(200,240,160,340);
        left.setStrokeWidth(5);
        Line right = new Line(200, 240, 240, 340);
        right.setStrokeWidth(5);

        g.getChildren().addAll(seesaw,cir,rec,left,right,sun);
        return g;
    }

    public static void main(String[] args) {
        launch();
    }
    @FXML
    Text actiontarget;
    public void handleSubmitButtonAction(ActionEvent actionEvent) {

        actiontarget.setText("Sign in button was pressed");
    }
}

    //        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//    Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
//    Scene scene = new Scene(root, 300, 275);
//        stage.setTitle("Login with FXML");
//                stage.setScene(scene);
//                stage.show();
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25,25,25,25));
//
//        Scene scene = new Scene(grid, 300, 275);
//
//        Text scenetitle = new Text("Please Login: ");
//        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//        Label userName = new Label("User Name:");
//        TextField userTextField = new TextField();
//        Label pw = new Label("Password: ");
//        PasswordField pwBox = new PasswordField();
//
//        grid.add(scenetitle,0,0,2,1);
//        grid.add(userName,0,1);
//        grid.add(userTextField, 1,1);
//        grid.add(pw,0,2);
//        grid.add(pwBox,1,2);
//
//        grid.setGridLinesVisible(false);
//
//        Button btn = new Button("Sign in");
//        HBox hbBtn = new HBox(10);
//        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn.getChildren().add(btn);
//        grid.add(hbBtn,1,4);

//        final Text actiontarget = new Text();
//        grid.add(actiontarget,1,6);
//
//        btn.setOnAction(event ->
//        {
//            actiontarget.setFill(Color.FIREBRICK);
//            actiontarget.setText("Sign in button pressed");
//
//        });
////        scene.getStylesheets().add(getClass().getResource("HelloApplication.css").toExternalForm());
////        scene.getStylesheets().add("HelloApplication.css");
//        scene.getStylesheets().add("HelloApplication.css");
//        stage.setTitle("JavaFX Login Form");
//        stage.setScene(scene);
//        stage.show();
