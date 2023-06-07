package org.example.HospitalPlanner.ui.form;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.example.HospitalPlanner.model.Schedule;
import org.springframework.stereotype.Component;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.ImageView;

import javax.persistence.Access;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DoctorForm extends Form {
    @FXML
    private AnchorPane doctorForm;
    @FXML
    private Label scheduleLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TableView<Schedule> scheduleTableView;
    @FXML
    private TableColumn<Schedule, String> dayColumn;

    @Override
    public String getFxmlPath() {
        return "/fxml/doctorForm.fxml";
    }

    @Override
    public String getTitle() {
        return "Doctor schedule";
    }

    @FXML
    public void initialize() {
        doctorForm.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                Platform.runLater(() -> {
                    scheduleLabel.setLayoutX((doctorForm.getWidth() - scheduleLabel.getWidth()) / 2);
                    usernameLabel.setText("Logged in as " + name);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDateTime now = LocalDateTime.now();

                    ObservableList<Schedule> data = FXCollections.observableArrayList();


                    List<Schedule> doctorSchedules = scheduleService.getSchedulesByDoctorId(id);

                    Map<String, String[]> schedulesMap = new HashMap<>();
                    for (Schedule schedule : doctorSchedules) {
                        String day = schedule.getDay();
                        String timeSlot = schedule.getTimeSlot();
                        String patientName = schedule.getPatient().getName();

                        String[] times = schedulesMap.getOrDefault(day, new String[9]);
                        int timeSlotIndex = schedule.getTimeSlotIndex(timeSlot);
                        if (timeSlotIndex != -1) {
                            times[timeSlotIndex] = patientName;
                        }

                        schedulesMap.put(day, times);
                    }

                    for (int i = 1; i <= 23; i++) {
                        LocalDateTime nextDay = now.plusDays(i);
                        String[] times = schedulesMap.getOrDefault(dtf.format(nextDay), new String[9]);
                        data.add(new Schedule(dtf.format(nextDay), times));
                    }

                    dayColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Schedule, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<Schedule, String> p) {
                            return new ReadOnlyObjectWrapper(p.getValue().getDay());
                        }
                    });

                    scheduleTableView.setItems(data);
                });
            }
        });
    }

}
