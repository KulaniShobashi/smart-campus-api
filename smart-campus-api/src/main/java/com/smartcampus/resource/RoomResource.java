/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.Room;
import com.smartcampus.store.DataStore;
import com.smartcampus.exception.RoomNotEmptyException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {
    private DataStore store = DataStore.getInstance();

    @GET
    public List<Room> getAll() { return new ArrayList<>(store.rooms.values()); }

    @POST
    public Response create(Room room) {
        store.rooms.put(room.getId(), room);
        return Response.status(201).entity(room).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        Room r = store.rooms.get(id);
        if (r != null && !r.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room has active sensors.");
        }
        store.rooms.remove(id);
        return Response.noContent().build();
    }
}