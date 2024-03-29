package org.xy.pointsofinterest.pointsofinterest.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xy.pointsofinterest.pointsofinterest.entity.Point;
import org.xy.pointsofinterest.pointsofinterest.request.PointPutRequestBody;
import org.xy.pointsofinterest.pointsofinterest.service.PointService;
import org.xy.pointsofinterest.pointsofinterest.util.PointCreator;
import org.xy.pointsofinterest.pointsofinterest.util.PointPutRequestBodyCreator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
class PointControllerTest {

    @InjectMocks
    private PointController pointController;

    @Mock
    private PointService pointService;

    @BeforeEach
    void setup() {
        when(pointService.findAll())
                .thenReturn(List.of(PointCreator.createValidPoint()));

        when(pointService.findPossiblePoints(
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt()))
                .thenReturn(List.of(PointCreator.createValidPoint()));

        when(pointService.save(ArgumentMatchers.any(Point.class)))
                .thenReturn(PointCreator.createPointToBeSaved());

        doNothing().when(pointService).replace(ArgumentMatchers.any(PointPutRequestBody.class));
        doNothing().when(pointService).delete(ArgumentMatchers.anyString());
    }
    @Test
    void findAll() {
        String expectedName = PointCreator.createValidPoint().getName();
        List<Point> points = pointController.findAll().getBody();

        assertThat(points)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertThat(points.get(0).getName())
                .isEqualTo(expectedName);
    }

    @Test
    void findNear() {
        List<Point> points = pointController.findNear(1, 1, 2).getBody();

        System.out.println(points);

        assertThat(points)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void save() {
        Point point = pointController.save(PointCreator.createPointToBeSaved()).getBody();

        assertThat(point)
                .isNotNull()
                .isEqualTo(PointCreator.createPointToBeSaved());
    }

    @Test
    void replace() {
        Assertions.assertThatCode(() -> pointController.replace(PointPutRequestBodyCreator
                .createRequestBody())).doesNotThrowAnyException();
    }

    @Test
    void delete() {
        Assertions.assertThatCode(() -> pointController.delete("test"))
                .doesNotThrowAnyException();
    }
}