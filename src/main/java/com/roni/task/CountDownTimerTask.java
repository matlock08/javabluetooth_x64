package com.roni.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;
import javafx.scene.control.TextField;

public class CountDownTimerTask extends TimerTask{
    private TextField counter;
    private int remaing;

    public CountDownTimerTask(TextField counter) {
        this.counter = counter;
        remaing = 5;
    }
 
    @Override
    public void run() {
        counter.setText( String.valueOf(remaing) );
        if ( remaing-- <= 0 ) {
            cancel();
        }
    }
         
}