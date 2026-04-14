/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
ppackage com.smartcampus.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.Map;

@Path("/")
public class DiscoveryResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscovery() {
        return Response.ok(Map.of(
            "version", "1.0",
            "links", Map.of("rooms", "/api/v1/rooms", "sensors", "/api/v1/sensors")
        )).build();
    }
}