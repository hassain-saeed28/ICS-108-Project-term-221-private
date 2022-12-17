import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Schedule implements Serializable {

    public static Scene getSecondScene() throws IOException, ClassNotFoundException {
        // create a border pane
        BorderPane borderPane = new BorderPane();

        // create an Hbox of days and set the days on it
        HBox days = new HBox();
        days.setSpacing(50);
        days.setPadding(new Insets(10, 10, 10, 140));
        Label days1 = new Label("                                                 sunday");
        Label days2 = new Label("                                monday");
        Label days3 = new Label("                                tuesday");
        Label days4 = new Label("                          wednesday");
        Label days5 = new Label("                             thursday");
        days.getChildren().addAll(days1, days2, days3, days4, days5);

        // adding the days to the top borderPne
        borderPane.setTop(days);

        ArrayList<Section> basket = MainMenu.basket;
        // basket.add(new Section("ICS 154", "54", "23-070", "M", "0800-0850"));
        // basket.add(new Section("ICS 104", "50", "7-119", "M", "0800-0850"));
        // basket.add(new Section("ICS 104", "-17", "24-240", "UTR", "0800-0950"));

        ArrayList<Section> schedule = new ArrayList<>();

        // create in days contains the saveSchedule button
        HBox saveScheduleHbox = new HBox();
        saveScheduleHbox.setPadding(new Insets(10, 10, 10, 10));

        saveScheduleButton saveSchedule = new saveScheduleButton("Save schedule");
        saveSchedule.setLayoutX(20);
        saveScheduleHbox.getChildren().add(saveSchedule);
        // setting the days in bottom borderPane
        borderPane.setBottom(saveScheduleHbox);

        ListView<Section> lv = new ListView<>(FXCollections.observableArrayList(basket));
        lv.setPrefSize(400, 700);
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        borderPane.setRight(new ScrollPane(lv));

        GridPane table = new GridPane();

        ArrayList<Integer> U = new ArrayList<>();
        ArrayList<Integer> M = new ArrayList<>();
        ArrayList<Integer> T = new ArrayList<>();
        ArrayList<Integer> W = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();

        U.clear();
        M.clear();
        T.clear();
        W.clear();
        R.clear();

        TreeMap<String, ArrayList<Integer>> timeConflict = new TreeMap<>();
        TreeMap<String, ArrayList<Integer>> multiSectionConflict = new TreeMap<>();
        timeConflict.clear();
        multiSectionConflict.clear();
        timeConflict.put("U", U);
        timeConflict.put("M", M);
        timeConflict.put("T", T);
        timeConflict.put("W", W);
        timeConflict.put("R", R);

        lv.getSelectionModel().selectedItemProperty().addListener(ov -> {

            Section item = lv.getSelectionModel().getSelectedItem();
            int time = 0;
            int day;

            for (int i = 0; i < basket.size(); i++) {

                if (basket.get(i).equals(item)) {

                    boolean isMultiSectionConflict = false;
                    boolean isConflict = false;
                    String split = basket.get(i).getTime().toString();
                    String[] splitList = split.split("-");
                    int startTime = Integer.parseInt(splitList[0]);
                    int endTime = Integer.parseInt(splitList[1]);

                    if (multiSectionConflict.containsKey(basket.get(i).getCourseName())) {
                        if (multiSectionConflict.get(basket.get(i).getCourseName()).get(0) != Integer
                                .parseInt(basket.get(i).getSec())) {
                            if (multiSectionConflict.get(basket.get(i).getCourseName()).get(1) == endTime - startTime) {
                                isMultiSectionConflict = true;
                            }
                        }

                    }

                    if (basket.get(i).getDays().length() == 1) {
                        if (timeConflict.get(basket.get(i).getDays()) != null) {
                            if (timeConflict.get(basket.get(i).getDays()).size() > 0) {
                                for (int timeInTheSelectedDay = 0; timeInTheSelectedDay < timeConflict
                                        .get(basket.get(i).getDays()).size(); timeInTheSelectedDay++) {
                                    if (timeConflict.get(basket.get(i).getDays()).get(timeInTheSelectedDay) >= startTime
                                            && timeConflict.get(basket.get(i).getDays())
                                                    .get(timeInTheSelectedDay) <= endTime) {
                                        isConflict = true;
                                    }

                                }
                            }
                        }
                    }

                    if (basket.get(i).getDays().length() == 2) {
                        String daysForTheSelectedSectioString = timeConflict.get(basket.get(i).getDays()).toString();
                        System.out.println(daysForTheSelectedSectioString);
                        String day1 = daysForTheSelectedSectioString.substring(0, 1);
                        String day2 = daysForTheSelectedSectioString.substring(2, 1);
                        int theLongestArray;
                        if ((timeConflict.get(day1) != null) && (timeConflict.get(day2) != null)) {
                            if ((timeConflict.get(day1).size() > 0) && (timeConflict.get(day2).size() > 0)) {

                                if (timeConflict.get(day1).size() >= timeConflict.get(day2).size()) {
                                    theLongestArray = timeConflict.get(day1).size();
                                } else {
                                    theLongestArray = timeConflict.get(day2).size();
                                }

                                for (int timeInTheSelectedDay = 0; timeInTheSelectedDay < theLongestArray; timeInTheSelectedDay++) {
                                    if (timeConflict.get(day1).get(timeInTheSelectedDay) >= startTime
                                            && timeConflict.get(day1)
                                                    .get(timeInTheSelectedDay) <= endTime) {
                                        isConflict = true;
                                    }

                                    if (timeConflict.get(day2).get(timeInTheSelectedDay) >= startTime
                                            && timeConflict.get(day2)
                                                    .get(timeInTheSelectedDay) <= endTime) {
                                        isConflict = true;
                                    }
                                }
                            }
                        }
                    }

                    if (!isConflict && !isMultiSectionConflict) {
                        ArrayList<Integer> secondAndTimeDifference = new ArrayList<>();
                        secondAndTimeDifference.add(Integer.parseInt(basket.get(i).getSec()));
                        secondAndTimeDifference.add(endTime - startTime);

                        multiSectionConflict.put(basket.get(i).getCourseName(), secondAndTimeDifference);
                        Label sectionName = new Label(item.getCourseName()+" ,S: "+item.getSec()+" ,T: "+item.getTime());
                        Label day2Label = new Label(item.getCourseName()+" ,S: "+item.getSec()+" ,T: "+item.getTime());
                        Label day3Label = new Label(item.getCourseName()+" ,S: "+item.getSec()+" ,T: "+item.getTime());
                        sectionName.setPadding(new Insets(1, 1, 1, 1));

                        if (basket.get(i).getTime().equals("0700-0750"))
                            time = 0;
                        if (basket.get(i).getTime().equals("0800-0850"))
                            time = 1;
                        if (basket.get(i).getTime().equals("0900-0950"))
                            time = 2;
                        if (basket.get(i).getTime().equals("1000-1050"))
                            time = 3;
                        if (basket.get(i).getTime().equals("1100-1150"))
                            time = 4;
                        if (basket.get(i).getTime().equals("1200-1250"))
                            time = 5;
                        if (basket.get(i).getTime().equals("1300-1350"))
                            time = 6;
                        if (basket.get(i).getTime().equals("1400-1450"))
                            time = 7;
                        if (basket.get(i).getTime().equals("1520-1610"))
                            time = 8;
                        if (basket.get(i).getTime().equals("1620-1710"))
                            time = 9;
                        if (basket.get(i).getTime().equals("1720-1810"))
                            time = 10;
                        if (basket.get(i).getTime().equals("1820-1910"))
                            time = 11;
                        if (basket.get(i).getTime().equals("1920-2000"))
                            time = 12;
                        if (basket.get(i).getTime().equals("0800-1050") || basket.get(i).getTime().equals("0800-1040")
                                || basket.get(i).getTime().equals("0800-0950")
                                || basket.get(i).getTime().equals("0800-0915")
                                || basket.get(i).getTime().equals("0830-0945"))
                            time = 1;
                        if (basket.get(i).getTime().equals("1100-1350") || basket.get(i).getTime().equals("1100-1250")
                                || basket.get(i).getTime().equals("1100-1340")
                                || basket.get(i).getTime().equals("1100-1215")
                                || basket.get(i).getTime().equals("1130-1245"))
                            time = 4;
                        if (basket.get(i).getTime().equals("1400-1650") || basket.get(i).getTime().equals("1400-1640")
                                || basket.get(i).getTime().equals("1400-1550")
                                || basket.get(i).getTime().equals("1400-1515"))
                            time = 7;
                        if (basket.get(i).getTime().equals("1000-1150") || basket.get(i).getTime().equals("1000-1115"))
                            time = 3;
                        if (basket.get(i).getTime().equals("1200-1350") || basket.get(i).getTime().equals("1230-1345"))
                            time = 5;
                        if (basket.get(i).getTime().equals("0700-0850"))
                            time = 0;
                        if (basket.get(i).getTime().equals("0900-1050") || basket.get(i).getTime().equals("0930-1045"))
                            time = 2;
                        if (basket.get(i).getTime().equals("1530-1645"))
                            time = 8;
                        StackPane stackPane = new StackPane();
                        StackPane day2 = new StackPane();
                        StackPane day3 = new StackPane();
                        String day2Name = sectionName + " ";
                        String day3Name = sectionName + "  ";

                        stackPane.getChildren().add(sectionName);
                        int indexOfTheSectionFromTheBasket = i;
                        String selectedDay = basket.get(indexOfTheSectionFromTheBasket).getDays();
                        Button delete = new Button("Del");
                        delete.setOnAction(e -> {

                            multiSectionConflict.remove(basket.get(indexOfTheSectionFromTheBasket).getCourseName());
                            System.out.println(schedule.remove(basket.get(indexOfTheSectionFromTheBasket)));
                            System.out.println(schedule);
                            schedule.remove(basket.get(indexOfTheSectionFromTheBasket));
                            System.out.println(schedule);
                            table.getChildren().remove(stackPane);
                            table.getChildren().remove(day2);
                            table.getChildren().remove(day3);

                            if (basket.get(indexOfTheSectionFromTheBasket).getDays().length() == 1) {

                                ArrayList<Integer> copyArrayList = timeConflict.get(selectedDay);
                                int start = Integer.parseInt(item.getTime().split("-")[0]);
                                int end = Integer.parseInt(item.getTime().split("-")[1]);
                                for (int z = 0; z < copyArrayList.size(); z++) {
                                    System.out.println(copyArrayList.get(z));
                                    if (copyArrayList.get(z) == start) {

                                        timeConflict.get(basket.get(indexOfTheSectionFromTheBasket).getDays())
                                                .remove(z);
                                    }
                                    if (copyArrayList.get(z) == end) {
                                        System.out.println("we reach the end time");
                                        timeConflict.get(basket.get(indexOfTheSectionFromTheBasket).getDays())
                                                .remove(z);

                                    }
                                }
                            }
                        });
                        Button delete2 = new Button("Del");
                        delete2.setOnAction(e -> {

                            multiSectionConflict.remove(basket.get(indexOfTheSectionFromTheBasket).getCourseName());
                            System.out.println(schedule.remove(basket.get(indexOfTheSectionFromTheBasket)));
                            System.out.println(schedule);
                            schedule.remove(basket.get(indexOfTheSectionFromTheBasket));
                            System.out.println(schedule);
                            table.getChildren().remove(stackPane);
                            table.getChildren().remove(day2);
                            table.getChildren().remove(day3);
                            if (basket.get(indexOfTheSectionFromTheBasket).getDays().length() == 1) {
                                ArrayList<Integer> copyArrayList = timeConflict.get(selectedDay);
                                int start = Integer.parseInt(item.getTime().split("-")[0]);
                                int end = Integer.parseInt(item.getTime().split("-")[1]);
                                for (int z = 0; z < copyArrayList.size(); z++) {
                                    System.out.println(copyArrayList.get(z));
                                    if (copyArrayList.get(z) == start) {
                                        timeConflict.get(basket.get(indexOfTheSectionFromTheBasket).getDays())
                                                .remove(z);
                                    }
                                    if (copyArrayList.get(z) == end) {
                                        System.out.println("we reach the end time");
                                        timeConflict.get(basket.get(indexOfTheSectionFromTheBasket).getDays())
                                                .remove(z);

                                    }
                                }

                            }

                        });
                        Button delete3 = new Button("Del");
                        delete3.setOnAction(e -> {

                            multiSectionConflict.remove(basket.get(indexOfTheSectionFromTheBasket).getCourseName());
                            System.out.println(schedule.remove(basket.get(indexOfTheSectionFromTheBasket)));
                            System.out.println(schedule);
                            schedule.remove(basket.get(indexOfTheSectionFromTheBasket));
                            System.out.println(schedule);
                            table.getChildren().remove(stackPane);
                            table.getChildren().remove(day2);
                            table.getChildren().remove(day3);
                            String firstDay = basket.get(indexOfTheSectionFromTheBasket).getDays();
                            String secondDay = basket.get(indexOfTheSectionFromTheBasket).getDays();
                            String thirdDay = basket.get(indexOfTheSectionFromTheBasket).getDays();
                            if (basket.get(indexOfTheSectionFromTheBasket).getDays().length() == 1) {
                                ArrayList<Integer> copyArrayList = timeConflict.get(selectedDay);
                                int start = Integer.parseInt(item.getTime().split("-")[0]);
                                int end = Integer.parseInt(item.getTime().split("-")[1]);
                                for (int z = 0; z < copyArrayList.size(); z++) {
                                    System.out.println(copyArrayList.get(z));
                                    if (copyArrayList.get(z) == start) {
                                        timeConflict.get(basket.get(indexOfTheSectionFromTheBasket).getDays())
                                                .remove(z);
                                    }
                                    if (copyArrayList.get(z) == end) {
                                        timeConflict.get(basket.get(indexOfTheSectionFromTheBasket).getDays())
                                                .remove(z);

                                    }
                                }

                            }

                        });
                        day2.getChildren().add(day2Label);
                        day2.getChildren().add(delete2);
                        day2.setAlignment(delete2, Pos.BOTTOM_RIGHT);
                        day2.setAlignment(day2Label, Pos.CENTER_LEFT);

                        day3.getChildren().add(delete3);
                        day3.setAlignment(delete3, Pos.BOTTOM_RIGHT);
                        day3.setAlignment(day2Label, Pos.CENTER_LEFT);

                        stackPane.getChildren().add(delete);
                        stackPane.setAlignment(delete, Pos.BOTTOM_RIGHT);
                        stackPane.setAlignment(sectionName, Pos.CENTER_LEFT);
                        day3.getChildren().add(day3Label);

                        stackPane.setBackground(Background.fill(Color.AQUA));
                        day2.setBackground(Background.fill(Color.AQUA));
                        day3.setBackground(Background.fill(Color.AQUA));

                        day = basket.get(i).getDays().length();

                        if (day == 3) {
                            schedule.add(basket.get(i));
                            String firstDay = basket.get(i).getDays();
                            String secondDay = basket.get(i).getDays();
                            String thirdDay = basket.get(i).getDays();

                            table.add(stackPane, 1, time, 1, 1);
                            table.add(day2, 3, time, 1, 1);
                            table.add(day3, 5, time, 1, 1);
                        } else if (day == 2) {
                            schedule.add(basket.get(i));
                            String firstDay = basket.get(i).getDays();
                            String secondDay = basket.get(i).getDays();

                            if (basket.get(i).getDays().equals("UT")) {
                                table.add(stackPane, 1, time, 1, 1);
                                table.add(day2, 3, time, 1, 1);
                            } else if (basket.get(i).getDays().equals("MW")) {
                                table.add(stackPane, 2, time, 1, 1);
                                table.add(day2, 4, time, 1, 1);
                            } else {
                                table.add(stackPane, 3, time, 1, 1);
                                table.add(day2, 5, time, 1, 1);
                            }
                        } else if (day == 1) {
                            schedule.add(basket.get(i));
                            timeConflict.get(basket.get(i).getDays()).add(startTime);
                            timeConflict.get(basket.get(i).getDays()).add(endTime);
                            if (basket.get(i).getDays().equals("U")) {
                                table.add(stackPane, 1, time, 1, 1);

                            } else if (basket.get(i).getDays().equals("M")) {
                                table.add(stackPane, 2, time, 1, 1);
                            } else if (basket.get(i).getDays().equals("T")) {
                                table.add(stackPane, 3, time, 1, 1);
                            } else if (basket.get(i).getDays().equals("W")) {
                                table.add(stackPane, 4, time, 1, 1);
                            } else {
                                table.add(stackPane, 5, time, 1, 1);

                            }

                        }
                    }
                }

            }
        });

        saveSchedule.setOnAction(e -> {

            try (ObjectOutputStream writeObject = new ObjectOutputStream(new FileOutputStream(
                    "ICS-108-Project-term-221/save_schedule.dat"))) {
                writeObject.writeObject(schedule);
                System.out.println("the file has been written");
                writeObject.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        borderPane.setLeft(table);
        Label[] time = new Label[14];
        time[0] = new Label("7am");
        time[1] = new Label("8am");
        time[2] = new Label("9am");
        time[3] = new Label("10am");
        time[4] = new Label("11am");
        time[5] = new Label("12pm");
        time[6] = new Label("13pm");
        time[7] = new Label("14pm");
        time[8] = new Label("15pm");
        time[9] = new Label("16pm");
        time[10] = new Label("17pm");
        time[11] = new Label("18pm");
        time[12] = new Label("19pm");
        time[13] = new Label("20pm");
        table.add(time[0], 0, 0, 1, 1);
        table.add(time[1], 0, 1, 1, 1);
        table.add(time[2], 0, 2, 1, 1);
        table.add(time[3], 0, 3, 1, 1);
        table.add(time[4], 0, 4, 1, 1);
        table.add(time[5], 0, 5, 1, 1);
        table.add(time[6], 0, 6, 1, 1);
        table.add(time[7], 0, 7, 1, 1);
        table.add(time[8], 0, 8, 1, 1);
        table.add(time[9], 0, 9, 1, 1);
        table.add(time[10], 0, 10, 1, 1);
        table.add(time[11], 0, 11, 1, 1);
        table.add(time[12], 0, 12, 1, 1);
        table.add(time[13], 0, 13, 1, 1);

        time[0].setPadding(new Insets(10, 10, 10, 10));
        time[1].setPadding(new Insets(10, 10, 10, 10));
        time[2].setPadding(new Insets(10, 10, 10, 10));
        time[3].setPadding(new Insets(10, 10, 10, 10));
        time[4].setPadding(new Insets(10, 10, 10, 10));
        time[5].setPadding(new Insets(10, 10, 10, 10));
        time[6].setPadding(new Insets(10, 10, 10, 10));
        time[7].setPadding(new Insets(10, 10, 10, 10));
        time[8].setPadding(new Insets(10, 10, 10, 10));
        time[9].setPadding(new Insets(10, 10, 10, 10));
        time[10].setPadding(new Insets(10, 10, 10, 10));
        time[11].setPadding(new Insets(10, 10, 10, 10));
        time[12].setPadding(new Insets(10, 10, 10, 10));
        time[13].setPadding(new Insets(10, 10, 10, 10));

        table.setPadding(new Insets(5, 10, 10, 10));
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();
        ColumnConstraints column4 = new ColumnConstraints();
        ColumnConstraints column5 = new ColumnConstraints();
        ColumnConstraints column6 = new ColumnConstraints();

        table.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6);
        column1.setPrefWidth(200);
        column2.setPrefWidth(200);
        column3.setPrefWidth(200);
        column4.setPrefWidth(200);
        column5.setPrefWidth(200);
        column6.setPrefWidth(200);

        GridPane.setHalignment(time[0], HPos.CENTER);
        GridPane.setHalignment(time[1], HPos.CENTER);
        GridPane.setHalignment(time[2], HPos.CENTER);
        GridPane.setHalignment(time[3], HPos.CENTER);
        GridPane.setHalignment(time[4], HPos.CENTER);
        GridPane.setHalignment(time[5], HPos.CENTER);
        GridPane.setHalignment(time[6], HPos.CENTER);
        GridPane.setHalignment(time[7], HPos.CENTER);
        GridPane.setHalignment(time[8], HPos.CENTER);
        GridPane.setHalignment(time[9], HPos.CENTER);
        GridPane.setHalignment(time[10], HPos.CENTER);
        GridPane.setHalignment(time[11], HPos.CENTER);
        GridPane.setHalignment(time[12], HPos.CENTER);
        GridPane.setHalignment(time[13], HPos.CENTER);
        table.setGridLinesVisible(false);

        Scene scene = new Scene(borderPane, 1600, 790);
        return scene;

    }

    // public static void main(String[] args) {
    // launch(args);
    // }
}
