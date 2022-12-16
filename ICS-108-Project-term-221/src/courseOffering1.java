
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class courseOffering1 extends Application {
    @Override
    public void start(Stage stage) throws IOException,ClassNotFoundException {
        // create a border pane
        BorderPane borderPane= new BorderPane();

        // create a hbox and set the days on it
        HBox hBox= new HBox();
        hBox.setSpacing(50);
        hBox.setPadding(new Insets(10,10,10,140));
        Label days= new Label("sunday");Label days2= new Label("  monday");
        Label days3= new Label(" tuesday");
        Label days4= new Label("wendesday");Label days5= new Label("thursday");
        hBox.getChildren().addAll(days,days2,days3,days4,days5);

       // adding the hbox to the top borderPne
        borderPane.setTop(hBox);

        ArrayList<Section> basket = MainMenu.basket;
        // basket.add(new Section("ICS 104","-54","23-070","U","1100-1340"));
        // basket.add(new Section("IAS 111","-F50","7-119","U","1100-1340"));
        // basket.add(new Section("ENGL101","-17","24-240","UTR","0800-1050"));

        // create in hbox contains the saveScdule button
        HBox hBox1= new HBox();
        hBox1.setPadding(new Insets(10,10,10,10));
        Button save_schedule = new Button("Save schedule");
        save_schedule.setLayoutX(20);
        hBox1.getChildren().add(save_schedule);

        // setting the hbox in bottom borderPane
        borderPane.setBottom(hBox1);
        save_schedule.setOnAction(e->{

        });

        ListView<Section> listView = new ListView<> (FXCollections.observableArrayList(basket));
        listView.setPrefSize(400,700);
        // listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        borderPane.setRight(new ScrollPane(listView));

        GridPane table = new GridPane();
        ArrayList<Section> sectionNumber= new ArrayList<>();

        ArrayList<Integer> U= new ArrayList<>();
        ArrayList<Integer> M= new ArrayList<>();
        ArrayList<Integer> T= new ArrayList<>();
        ArrayList<Integer> W= new ArrayList<>();
        ArrayList<Integer> R= new ArrayList<>();
        sectionNumber.clear();
        U.clear();
        M.clear();
        T.clear();
        W.clear();
        R.clear();


        listView.getSelectionModel().selectedItemProperty().addListener(ov ->{
            Section item= listView.getSelectionModel().getSelectedItem();
            int time = 0;
            int day;
            int row=1;
            int column=1;
            int columnExpand=0;

            for(int i=0;i<basket.size() ;i++){
                String split;
                String[] splitList;
                int startTime=0;
                int endTime=0;

                if(basket.get(i).getDays().equals("U")){

                    split = basket.get(i).getTime().toString();
                        splitList= split.split("-");
                    startTime = Integer.parseInt(splitList[0]);
                    endTime = Integer.parseInt(splitList[1]);
                    for(int j = startTime;j<=endTime;j=j+100){
                        U.add(j);
                    }
                    }



                System.out.println(basket.get(i).getTime().split("-")[0]);
                if((!sectionNumber.contains(basket.get(i))&& !U.contains(basket.get(i).getTime().split("-")[0]))){
                if(basket.get(i).getCourseName().equals(item.getCourseName())){
                    sectionNumber.add(basket.get(i));
                    Label sectionName= new Label(item.getCourseName());
                    Label day2Label= new Label(item.getCourseName());
                    Label day3Label= new Label(item.getCourseName());
                    sectionName.setPadding(new Insets(1,1,1,1));
                    System.out.println(sectionNumber.contains(basket.get(i).getTime()));



                    if(basket.get(i).getTime().equals("0700-0750")||basket.get(i).getTime().equals("0800-0850")||basket.get(i).getTime().equals("0900-0950")||basket.get(i).getTime().equals("1000-1050")||basket.get(i).getTime().equals("1100-1150")||basket.get(i).getTime().equals("1200-1250")||basket.get(i).getTime().equals("1300-1350")||basket.get(i).getTime().equals("1400-1450")||basket.get(i).getTime().equals("1500-1550")||basket.get(i).getTime().equals("1600-1650")||basket.get(i).getTime().equals("1700-1750")||basket.get(i).getTime().equals("1800-1850")||basket.get(i).getTime().equals("1520-1610")){
                        columnExpand=1;

                        sectionNumber.add(basket.get(i));
                    }
                    else if (basket.get(i).getTime().equals("0800-0915")||basket.get(i).getTime().equals("0830-0945")||basket.get(i).getTime().equals("0930-1045")||basket.get(i).getTime().equals("1000-1115")||basket.get(i).getTime().equals("1100-1215")||basket.get(i).getTime().equals("1130-1245")||basket.get(i).getTime().equals("1230-1345")||basket.get(i).getTime().equals("1400-1515")||basket.get(i).getTime().equals("0930-1045")||basket.get(i).getTime().equals("1530-1645")||basket.get(i).getTime().equals("1720-1835")||basket.get(i).getTime().equals("1100-1250")||basket.get(i).getTime().equals("0900-1050")||basket.get(i).getTime().equals("0700-0850")||basket.get(i).getTime().equals("1000-1150")||basket.get(i).getTime().equals("1400-1550")||basket.get(i).getTime().equals("0800-0950")||basket.get(i).getTime().equals("1200-1350")){
                        columnExpand=2;

                        sectionNumber.add(basket.get(i));

                    }
                    else if(basket.get(i).getTime().equals("0800-1050")||basket.get(i).getTime().equals("1100-1350")||basket.get(i).getTime().equals("1400-1650")||basket.get(i).getTime().equals("0800-1040")||basket.get(i).getTime().equals("1100-1340")||basket.get(i).getTime().equals("1400-1640")){
                        columnExpand=3;

                        sectionNumber.add(basket.get(i));

                    }
                    if(basket.get(i).getTime().equals("0700-0750")) time=0;if(basket.get(i).getTime().equals("0800-0850")) time=1;if(basket.get(i).getTime().equals("0900-0950")) time=2;if(basket.get(i).getTime().equals("1000-1050")) time=3;if(basket.get(i).getTime().equals("1100-1150")) time=4;if(basket.get(i).getTime().equals("1200-1250")) time=5;if(basket.get(i).getTime().equals("1300-1350")) time=6;if(basket.get(i).getTime().equals("1400-1450")) time=7;if(basket.get(i).getTime().equals("1520-1610")) time=8;if(basket.get(i).getTime().equals("1620-1710")) time=9;if(basket.get(i).getTime().equals("1720-1810")) time=10;if(basket.get(i).getTime().equals("1820-1910")) time=11;if(basket.get(i).getTime().equals("1920-2000")) time=12;
                    if(basket.get(i).getTime().equals("0800-1050")||basket.get(i).getTime().equals("0800-1040")||basket.get(i).getTime().equals("0800-0950")||basket.get(i).getTime().equals("0800-0915")||basket.get(i).getTime().equals("0830-0945")) time=1;if(basket.get(i).getTime().equals("1100-1350")||basket.get(i).getTime().equals("1100-1250")||basket.get(i).getTime().equals("1100-1340")||basket.get(i).getTime().equals("1100-1215")||basket.get(i).getTime().equals("1130-1245")) time=4;if(basket.get(i).getTime().equals("1400-1650")||basket.get(i).getTime().equals("1400-1640")||basket.get(i).getTime().equals("1400-1550")||basket.get(i).getTime().equals("1400-1515")) time=7;if(basket.get(i).getTime().equals("1000-1150")||basket.get(i).getTime().equals("1000-1115")) time=3;if(basket.get(i).getTime().equals("1200-1350")||basket.get(i).getTime().equals("1230-1345")) time=5;if(basket.get(i).getTime().equals("0700-0850")) time=0;if(basket.get(i).getTime().equals("0900-1050")||basket.get(i).getTime().equals("0930-1045")) time=2;if(basket.get(i).getTime().equals("1530-1645")) time=8;
                    StackPane stackPane= new StackPane();
                    StackPane day2= new StackPane();
                    StackPane day3= new StackPane();
                    String day2Name= sectionName+" ";
                    String day3Name= sectionName+"  ";



                    stackPane.getChildren().add(sectionName);

                    day3.getChildren().add(day3Label);
                    Button delete= new Button("Del");
                    delete.setOnAction(e->{
                        table.getChildren().remove(stackPane);
                        table.getChildren().remove(day2);
                        table.getChildren().remove(day3);
                        sectionNumber.remove(stackPane.getChildren().get(0));
                        sectionNumber.remove(day2.getChildren().get(0));
                        sectionNumber.remove(day3.getChildren().get(0));

                    });
                    Button delete2= new Button("Del");
                    delete2.setOnAction(e->{
                        table.getChildren().remove(stackPane);
                        table.getChildren().remove(day2);
                        table.getChildren().remove(day3);
                    });
                    Button delete3= new Button("Del");
                    delete3.setOnAction(e->{
                        table.getChildren().remove(stackPane);
                        table.getChildren().remove(day2);
                        table.getChildren().remove(day3);
                    });
                    day2.getChildren().add(day2Label);
                    day2.getChildren().add(delete2);
                    day2.setAlignment( delete2, Pos.BOTTOM_RIGHT);
                    day2.setAlignment(day2Label,Pos.CENTER_LEFT);


                    day3.getChildren().add(delete3);
                    day3.setAlignment( delete3, Pos.BOTTOM_RIGHT);
                    day3.setAlignment(day2Label,Pos.CENTER_LEFT);

                    stackPane.getChildren().add(delete);
                    stackPane.setAlignment( delete, Pos.BOTTOM_RIGHT);
                    stackPane.setAlignment(sectionName,Pos.CENTER_LEFT);







                    stackPane.setBackground(Background.fill(Color.AQUA));
                    day2.setBackground(Background.fill(Color.AQUA));
                    day3.setBackground(Background.fill(Color.AQUA));

                    day= basket.get(i).getDays().length();

                    if(day==3){
                        table.add( stackPane,1,time,1,columnExpand);
                        table.add( day2,3,time,1,columnExpand);
                        table.add( day3,5,time,1,columnExpand);
                    }
                    else if (day==2) {
                        if(basket.get(i).getDays().equals("UT")) {
                            table.add(stackPane, 1, time, 1, columnExpand);
                            table.add(day2, 3, time, 1, columnExpand);
                        }
                        else if (basket.get(i).getDays().equals("MW")){
                            table.add(stackPane, 3, time, 1, columnExpand);
                            table.add(day2, 5, time, 1, columnExpand);
                        }
                         else{
                            table.add(stackPane, 3, time, 1, columnExpand);
                            table.add(day2, 5, time, 1, columnExpand);
                        }
                    }
                    else if (day==1 ) {
                        if(basket.get(i).getDays().equals("U"))
                            table.add(stackPane, 1, time, 1, columnExpand);
                        else if (basket.get(i).getDays().equals("M")) {
                            table.add(stackPane, 2, time, 1, columnExpand);
                        }
                        else if (basket.get(i).getDays().equals("T")) {
                            table.add(stackPane, 3, time, 1, columnExpand);
                        }
                        else if (basket.get(i).getDays().equals("W")) {
                            table.add(stackPane, 4, time, 1, columnExpand);
                        }
                        else {
                            table.add(stackPane, 5, time, 1, columnExpand);

                        }
                    }
                }}
                columnExpand=0;
            }

        });
        borderPane.setLeft(table);
        Label[] time= new Label[14];
        time[0]= new Label("7am");time[1]= new Label("8am");time[2]= new Label("9am");time[3]= new Label("10am");time[4]= new Label("11am");time[5]= new Label("12pm");time[6]= new Label("13pm");time[7]= new Label("14pm");time[8]= new Label("15pm");time[9]= new Label("16pm");time[10]= new Label("17pm");time[11]= new Label("18pm");time[12]= new Label("19pm");time[13]= new Label("20pm");
        table.add(time[0],0,0,1,1);table.add(time[1],0,1,1,1);table.add(time[2],0,2,1,1);table.add(time[3],0,3,1,1);table.add(time[4],0,4,1,1);table.add(time[5],0,5,1,1);table.add(time[6],0,6,1,1);table.add(time[7],0,7,1,1);table.add(time[8],0,8,1,1);table.add(time[9],0,9,1,1);table.add(time[10],0,10,1,1);table.add(time[11],0,11,1,1);table.add(time[12],0,12,1,1);table.add(time[13],0,13,1,1);


        time[0].setPadding(new Insets(10,10,10,10));time[1].setPadding(new Insets(10,10,10,10));time[2].setPadding(new Insets(10,10,10,10));time[3].setPadding(new Insets(10,10,10,10));time[4].setPadding(new Insets(10,10,10,10));time[5].setPadding(new Insets(10,10,10,10));time[6].setPadding(new Insets(10,10,10,10));time[7].setPadding(new Insets(10,10,10,10));time[8].setPadding(new Insets(10,10,10,10));time[9].setPadding(new Insets(10,10,10,10));time[10].setPadding(new Insets(10,10,10,10));time[11].setPadding(new Insets(10,10,10,10));time[12].setPadding(new Insets(10,10,10,10));time[13].setPadding(new Insets(10,10,10,10));

        table.setPadding(new Insets(5,10,10,10));
        ColumnConstraints column1= new ColumnConstraints();ColumnConstraints column2= new ColumnConstraints();ColumnConstraints column3= new ColumnConstraints();ColumnConstraints column4= new ColumnConstraints();ColumnConstraints column5= new ColumnConstraints();ColumnConstraints column6= new ColumnConstraints();


        table.getColumnConstraints().addAll(column1,column2,column3,column4,column5,column6);
        column1.setPrefWidth(100);column2.setPrefWidth(100);column3.setPrefWidth(100);column4.setPrefWidth(100);column5.setPrefWidth(100);column6.setPrefWidth(100);

        GridPane.setHalignment(time[0], HPos.CENTER);GridPane.setHalignment(time[1], HPos.CENTER);GridPane.setHalignment(time[2], HPos.CENTER);GridPane.setHalignment(time[3], HPos.CENTER);GridPane.setHalignment(time[4], HPos.CENTER);GridPane.setHalignment(time[5], HPos.CENTER);GridPane.setHalignment(time[6], HPos.CENTER);GridPane.setHalignment(time[7], HPos.CENTER);GridPane.setHalignment(time[8], HPos.CENTER);GridPane.setHalignment(time[9], HPos.CENTER);GridPane.setHalignment(time[10], HPos.CENTER);GridPane.setHalignment(time[11], HPos.CENTER);GridPane.setHalignment(time[12], HPos.CENTER);GridPane.setHalignment(time[13], HPos.CENTER);
        table.setGridLinesVisible(false);

        Scene scene= new Scene(borderPane,1000,790);
        stage.setTitle("CourseOffering");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
