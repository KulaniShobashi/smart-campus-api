/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.mapper;

import com.smartcampus.exception.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;
import java.util.Map;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable e) {
        if (e instanceof RoomNotEmptyException) {
            return Response.status(409).entity(Map.of("error", e.getMessage())).build();
        }
        if (e instanceof LinkedResourceNotFoundException) {
            return Response.status(422).entity(Map.of("error", e.getMessage())).build();
        }
        if (e instanceof SensorUnavailableException) {
            return Response.status(403).entity(Map.of("error", e.getMessage())).build();
        }
        // Catch-all 500 safety net
        return Response.status(500).entity(Map.of("error", "Internal Server Error")).build();
    }
}