package com.stacs.routemappingapp.service.route;

import org.junit.jupiter.api.Test;

import com.stacs.routemappingapp.model.route.Route;
import com.stacs.routemappingapp.service.RouteService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/*
 * Tests for Route Service.
 */
public class RouteTests {
    private static RouteService routeService;
    private static Map<String, Route> route = new HashMap<>();

    @BeforeAll
    public static void setup() {
        routeService = new RouteService();
    }

    /*
     * Removes all routes from the RouteService
     */
    @AfterEach
    public void wipeRoutes() {
        routeService.wipeRoutes();
    }

    /* 
     * Test checkAlphaNumeric with valid name.
     */
    @Test
    public void shouldCheckAlphaNumericWithValidName() {
        assertDoesNotThrow(() -> {
            routeService.checkAlphaNumeric("route1");
        });
    }

    /*
     * Test checkAlphaNumeric with invalid name.
     */
    @Test
    public void shouldCheckAlphaNumericWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            routeService.checkAlphaNumeric("route-1");
        });
    }

    /*
     * Test checkNamingConvention with valid name.
     */
    @Test
    public void shouldCheckNamingConventionWithValidName() {
        assertDoesNotThrow(() -> {
            routeService.checkNamingConvention("RouteName");
        });
    }

    /*
     * Test checkNamingConvention with invalid name.
     */
    @Test
    public void shouldCheckNamingConventionWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            routeService.checkNamingConvention("RouteName123");
        });
    }

    /*
     * Test checkIfIDValid with valid ID.
     */
    @Test
    public void shouldCheckIfIDValidWithValidID() {
        assertDoesNotThrow(() -> {
            routeService.checkIfIDValid("route1");
        });
    }

    /*
     * Test checkIfIDValid with empty ID.
     */
    @Test
    public void shouldCheckIfIDValidWithEmptyID() {
        assertThrows(IllegalArgumentException.class, () -> {
            routeService.checkIfIDValid("");
        });
    }

    /*
     * Test checkIfNameValid with valid name.
     */
    @Test
    public void shouldCheckIfNameValidWithValidName() {
        assertDoesNotThrow(() -> {
            routeService.checkIfNameValid("RouteName");
        });
    }

    /*
     * Test checkIfNameValid with empty name.
     */
    @Test
    public void shouldCheckIfNameValidWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            routeService.checkIfNameValid("");
        });
    }

    /*
     * Test addRoute with valid parameters.
     */
    @Test
    public void shouldAddRouteWithValidParameters() {
        assertDoesNotThrow(() -> {
            routeService.addRoute("route1", "RouteName", "Destination", "StartingPoint");
        });
    }

    /*
     * Test addRoute with existing route ID.
     */
    @Test
    public void shouldAddRouteWithExistingID() {
        assertThrows(IllegalArgumentException.class, () -> {
            routeService.addRoute("route1", "RouteName", "Destination", "StartingPoint");
            routeService.addRoute("route1", "RouteName2", "Destination2", "StartingPoint2");
        });
    }

    /*
     * Test deleteRoute with existing route ID.
     */
    @Test
    public void shouldDeleteRouteWithExistingID() {
        assertDoesNotThrow(() -> {
            routeService.addRoute("route1", "RouteName", "Destination", "StartingPoint");
            routeService.deleteRoute("route1");
        });

        // Checks if empty.
        assertThrows(IllegalArgumentException.class, () -> {
            routeService.callAllRoutes();
        });
    }

    /*
     * Test saveAppData with single route saved.
     */
    // @Test
    // public void shouldSaveAppData() throws IOException {
    //     // Arrange
    //     route.put("R1", new Route("R1", "Route 1", "Destination", "Starting Point"));
    //     String filePath = "src/test/java/com/stacs/routemappingapp/resources/data/route.ser";

    //     // Act
    //     routeService.saveAppData(filePath, route);

    //     // Assert
    //     FileInputStream fi = new FileInputStream(new File(filePath));
    //     ObjectInputStream oi = new ObjectInputStream(fi);
    //     Map<String, Route> savedRoute = (Map<String, Route>) oi.readObject();
    //     oi.close();
    //     fi.close();

    //    // assertEquals(route, savedRoute);
    // }

    // /*
    //  * Test loadAppData with valid path and data.
    //  */
    // @Test
    // public void shouldLoadAppData() throws IOException, ClassNotFoundException {
    //     // Arrange
    //     String filePath = "src/test/java/com/stacs/routemappingapp/resources/data/route.ser";
    //     route.put("R1", new Route("R1", "Route 1", "Destination", "Starting Point"));
    //     routeService.saveAppData(filePath, route);

    //     // Act
    //     routeService.loadAppData(filePath);

    //     // Assert
    //     assertEquals(route, routeService.getRoute());
    // }

    // /*
    //  * Test loadAppData with invalid path.
    //  */
    // @Test
    // public void shouldLoadAppDataWithInvalidFilePath() {
    //     // Arrange
    //     String filePath = "src/test/java/com/stacs/routemappingapp/resources/data/invalid.ser";

    //     // Act and Assert
    //     assertThrows(FileNotFoundException.class, () -> routeService.loadAppData(filePath));
    // }

    // /*
    //  * Test loadAppData with invalid data.
    //  */
    // @Test
    // public void shouldLoadAppDataWithInvalidData() throws IOException {
    //     // Arrange
    //     String filePath = "src/test/java/com/stacs/routemappingapp/resources/data/route.ser";
    //     FileOutputStream f = new FileOutputStream(new File(filePath));
    //     ObjectOutputStream o = new ObjectOutputStream(f);
    //     o.writeObject("Invalid Data");
    //     o.close();
    //     f.close();

    //     // Act and Assert
    //     assertThrows(ClassCastException.class, () -> routeService.loadAppData(filePath));
    // }

    /*
     * Test callAllRoutes with one entry.
     */
    @Test
    public void shouldCallAllRoutesReturnsNonEmptyHashMap() {
        routeService.addRoute("R1", "Route 1", "Destination 1", "Starting Point 1");

        HashMap<String, Route> actualRoutes = routeService.callAllRoutes();

        assertNotNull(actualRoutes.size());
    }

    /*
     * Tests callAllRoutes with no routes in the system.
     */
    @Test
    public void shouldCallAllRoutesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            System.out.println(routeService.callAllRoutes().size());
            routeService.callAllRoutes();
        });
    }

    /*
     * Tests checkIfRouteNameIDMatch with valid match.
     */
    @Test
    public void shouldCheckIfRouteNameIDMatchSucceeds() {
        routeService.addRoute("R1", "Route 1", "Destination 1", "Starting Point 1");

        assertDoesNotThrow(() -> {
            routeService.checkIfRouteNameIDMatch("R1", "Route 1");
        });
    }

    /*
     * Tests checkIfRouteNameIDMatch with invalid match.
     */
    @Test
    public void shouldCheckIfRouteNameIDMatchThrowsException() {
        routeService.addRoute("R1", "Route 1", "Destination 1", "Starting Point 1");

        assertThrows(IllegalArgumentException.class, () -> {
            routeService.checkIfRouteNameIDMatch("R1", "Invalid Route Name");
        });
    }

    /*
     * Tests getRouteInfoByStopName with valid stop.
     */
    // public void shouldGetRouteInfoByStopNameSucceeds() {
    //     routeService.addRoute("R1", "Route 1", "Destination 1", "Starting Point 1");
    //     String stopName = "Central Station";
    //     route.addStop(stopName, 1);

    //     Map<String, Route> actualRoutes = routeService.getRouteInfoByStopName(stopName);

    //     assertNotNull(actualRoutes.size());
    // }

    /*
     * Tests getRouteInfoByStopName with invalid route name.
     */
    @Test
    public void shouldGetRouteInfoByStopNameThrowsException() {

        //assertEquals(0, routeService.getRouteInfoByStopName("Invalid Route Name").size());

        assertThrows(IllegalArgumentException.class, () -> {
            routeService.getRouteInfoByStopName("Invalid Route Name");
        });
    }
}