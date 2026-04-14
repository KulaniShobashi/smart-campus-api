/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.store;

import com.smartcampus.model.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    private static final DataStore instance = new DataStore();
    public final Map<String, Room> rooms = new ConcurrentHashMap<>();
    public final Map<String, Sensor> sensors = new ConcurrentHashMap<>();
    public final Map<String, List<SensorReading>> readings = new ConcurrentHashMap<>();

    private DataStore() {}
    public static DataStore getInstance() { return instance; }
}