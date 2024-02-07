package org.xy.pointsofinterest.pointsofinterest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xy.pointsofinterest.pointsofinterest.entity.Point;
import org.xy.pointsofinterest.pointsofinterest.repository.PointRepository;
import org.xy.pointsofinterest.pointsofinterest.util.PointCreator;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.doNothing;
import static org.mockito.BDDMockito.when;

@ExtendWith(SpringExtension.class)
class PointServiceTest {

    @InjectMocks
    private PointService pointService;

    @Mock
    private PointRepository pointRepository;

    @BeforeEach
    void setUp() {
        when(pointRepository.findAll())
                .thenReturn(List.of(PointCreator.createValidPoint()));

        when(pointRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(PointCreator.createValidPoint()));
        when(pointRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PointCreator.createValidPoint()));
        when(pointRepository.save(ArgumentMatchers.any(Point.class)))
                .thenReturn(PointCreator.createValidPoint());
        doNothing().when(pointRepository).delete(ArgumentMatchers.any(Point.class));
        when(pointRepository.findPossiblePoints(
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt()))
                .thenReturn(List.of(PointCreator.createValidPoint()));
    }
    @Test
    void findAll() {
        String expectedName = PointCreator.createValidPoint().getName();
        List<Point> points = pointService.findAll();

        assertThat(points)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertThat(points.get(0).getName())
                .isEqualTo(expectedName);
    }

    @Test
    void findByName() {
        Point pointToBeSaved = PointCreator.createPointToBeSaved();
        Point pointSaved = pointService.save(pointToBeSaved);

        String expectedName = pointSaved.getName();
        List<Point> points = pointService.findByName(expectedName);

        assertThat(points)
                .isNotEmpty()
                .contains(pointSaved);
    }

    @Test
    void findById() {
        Point pointToBeSaved = PointCreator.createPointToBeSaved();
        Point pointSaved = pointService.save(pointToBeSaved);

        long expectedId = pointSaved.getId();
        Optional<Point> points = pointService.findById(expectedId);

        assertThat(points)
                .isNotNull()
                .isPresent();
    }

    @Test
    void save() {
        Point point = pointService.save(PointCreator.createPointToBeSaved());

        assertThat(point)
                .isNotNull()
                .isEqualTo(PointCreator.createValidPoint());
    }


    @Test
    void delete() {
        assertThatCode(() -> pointService.delete("Test")).doesNotThrowAnyException();
    }

    @Test
    void deleteById() {
        assertThatCode(() -> pointService.deleteById(1L)).doesNotThrowAnyException();
    }

    @Test
    void replace() {
        assertThatCode(() -> pointService.replace(PointCreator.createPointToBeSaved()))
                .doesNotThrowAnyException();
    }

    @Test
    void findPossiblePoints() {
        List<Point> points = pointService.findPossiblePoints(1, 1, 2);

        assertThat(points)
                .isNotNull()
                .isNotEmpty();
    }
}