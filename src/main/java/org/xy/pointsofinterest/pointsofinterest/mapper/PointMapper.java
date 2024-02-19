package org.xy.pointsofinterest.pointsofinterest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.xy.pointsofinterest.pointsofinterest.entity.Point;
import org.xy.pointsofinterest.pointsofinterest.request.PointPutRequestBody;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PointMapper {

    public static final PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);

    public abstract Point toPoint(PointPutRequestBody requestBody);
}