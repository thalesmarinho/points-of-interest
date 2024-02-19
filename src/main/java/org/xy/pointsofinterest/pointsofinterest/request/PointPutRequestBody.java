package org.xy.pointsofinterest.pointsofinterest.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointPutRequestBody {

    private Long id;
    private String name;

    private int x;
    private int y;

}