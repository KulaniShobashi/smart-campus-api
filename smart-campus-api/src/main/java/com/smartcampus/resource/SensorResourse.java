/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.Sensor;
import com.smartcampus.store.DataStore;
import com.smartcampus.exception.LinkedResourceNotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
public class SensorResource {
    private DataStore store = DataStore.getInstance();

    @POST
    public Response add(Sensor s) {
        if (!store.rooms.containsKey(s.getRoomId())) {
            throw new LinkedResourceNotFoundException("Target Room not found.");
        }
        store.sensors.put(s.getId(), s);
        store.rooms.get(s.getRoomId()).getSensorIds().add(s.getId());
        return Response.status(201).entity(s).build();
    }

    @GET
    public List<Sensor> get(@QueryParam("type") String type) {
        List<Sensor> sensors = new ArrayList<>(store.sensors.values());
        if (type != null) {
            sensors.removeIf(s -> !s.getType().equalsIgnoreCase(type));
        }
        return sensors;
    }

    @Path("/{id}/readings")
    public SensorReadingResource getReadings(@PathParam("id") String id) {
        return new SensorReadingResource(id);
    }
}