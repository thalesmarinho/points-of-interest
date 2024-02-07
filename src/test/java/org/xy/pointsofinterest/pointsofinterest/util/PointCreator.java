package org.xy.pointsofinterest.pointsofinterest.util;

import org.xy.pointsofinterest.pointsofinterest.entity.Point;

public class PointCreator {

    public static Point createPointToBeSaved() {
        return Point.builder()
                .name("Test")
                .x(1)
                .y(1)
                .build();
    }

    public static Point createValidPoint() {
        return Point.builder()
                .id(1L)
                .name("Test")
                .x(1)
                .y(1)
                .build();
    }
}