package org.xy.pointsofinterest.pointsofinterest.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.xy.pointsofinterest.pointsofinterest.entity.Point;
import org.xy.pointsofinterest.pointsofinterest.util.PointCreator;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Tests for Point Repository")
class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    @Test
    void findByName() {
        Point pointToBeSaved = PointCreator.createPointToBeSaved();
        Point pointSaved = pointRepository.save(pointToBeSaved);

        String name = pointSaved.getName();
        List<Point> points = pointRepository.findByName(name);

        assertThat(points)
                .isNotEmpty()
                .contains(pointSaved);
    }

    @Test
    void delete() {
        Point pointToBeSaved = PointCreator.createPointToBeSaved();
        Point pointSaved = pointRepository.save(pointToBeSaved);

        pointRepository.delete(pointSaved);
        Optional<Point> pointOptional = pointRepository.findById(pointSaved.getId());

        assertThat(pointOptional).isEmpty();
    }

    @Test
    void findPossiblePoints() {
        pointRepository.save(PointCreator.createPointToBeSaved());
        List<Point> possiblePoints = pointRepository.findPossiblePoints(1,1,2);

        assertThat(possiblePoints).isNotEmpty();
    }
}