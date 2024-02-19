package org.xy.pointsofinterest.pointsofinterest.util;

import org.xy.pointsofinterest.pointsofinterest.request.PointPutRequestBody;

public class PointPutRequestBodyCreator {

    public static PointPutRequestBody createRequestBody() {
        return PointPutRequestBody.builder()
                .id(PointCreator.createValidPoint().getId())
                .name(PointCreator.createValidPoint().getName())
                .build();
    }
}