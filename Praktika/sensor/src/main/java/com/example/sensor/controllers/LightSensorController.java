package com.example.sensor.controllers;

import com.example.sensor.services.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class LightSensorController {

    @Autowired
    private SerialService serialService;

    private final Random random = new Random();

    @GetMapping("/sensor")
    public String getSensorData(Model model) {
        String sensorValue = serialService.getLastReadValue();
        model.addAttribute("lightValue", sensorValue);
        return "sensor";
    }

    @GetMapping("/sensor/test")
    public String getFakeSensorData(Model model) {
        float randomLightValue = 10 + (500 - 10) * random.nextFloat(); // Generates random float between 10 and 500
        model.addAttribute("lightValue", randomLightValue);
        return "sensor";
    }

    @GetMapping("/sensor/test/data")
    @ResponseBody
    public Map<String, Object> getFakeSensorData() {
        float randomLightValue = 10 + (500 - 10) * random.nextFloat(); // Generates random float between 10 and 500
        Map<String, Object> data = new HashMap<>();
        data.put("lightValue", randomLightValue);
        return data;
    }
}
