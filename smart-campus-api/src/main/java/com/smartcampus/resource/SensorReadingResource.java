/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.*;
import com.smartcampus.store.DataStore;
import com.smartcampus.exception.SensorUnavailableException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

public class SensorReadingResource {
    private String sensorId;
    private DataStore store = DataStore.getInstance();

    public SensorReadingResource(String id) { this.sensorId = id; }

    @POST
    public Response add(SensorReading r) {
        Sensor s = store.sensors.get(sensorId);
        if (s == null || "MAINTENANCE".equals(s.getStatus())) {
            throw new SensorUnavailableException("Sensor is unavailable.");
        }
        store.readings.computeIfAbsent(sensorId, k -> new ArrayList<>()).add(r);
        s.setCurrentValue(r.getValue());
        return Response.status(201).entity(r).build();
    }

    @GET
    public List<SensorReading> getHistory() {
        return store.readings.getOrDefault(sensorId, new ArrayList<>());
    }
}