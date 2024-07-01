package com.example.sensor.services;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SerialService {
    private SerialPort comPort;
    private String lastReadValue = "No data";

    public void init() {
        comPort = SerialPort.getCommPorts()[0]; // Select the first available port (modify as needed)
        comPort.setBaudRate(9600);
        comPort.openPort();

        if (comPort.isOpen()) {
            System.out.println("Serial port is open");
        } else {
            System.out.println("Failed to open serial port");
            return;
        }

        new Thread(() -> {
            Scanner scanner = new Scanner(comPort.getInputStream());
            while (scanner.hasNextLine()) {
                lastReadValue = scanner.nextLine();
                System.out.println("Received from Arduino: " + lastReadValue);
            }
            scanner.close();
        }).start();
    }

    public String getLastReadValue() {
        return lastReadValue;
    }
}
