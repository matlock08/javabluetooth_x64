package com.roni.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;
import javafx.scene.control.TextField;

public class OneSecTimerTask extends TimerTask{
    private TextField clock;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public OneSecTimerTask(TextField clock) {
        this.clock = clock;
    }
 
    @Override
    public void run() {
        clock.setText( LocalDateTime.now().format(formatter) );             
    }
         
}