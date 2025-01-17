package org.example;

import org.example.service.Task;
import org.example.models.Rates;
import org.example.service.DbService;
import org.example.service.KryptoService;
import org.example.service.Retriever;
import org.knowm.xchange.dto.marketdata.Ticker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new Task(), 0, 3600000); // 3600000 ms = 60 minutes
    }

}
