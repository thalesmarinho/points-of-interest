package org.xy.pointsofinterest.pointsofinterest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xy.pointsofinterest.pointsofinterest.entity.Point;
import org.xy.pointsofinterest.pointsofinterest.exception.PointNotFoundException;
import org.xy.pointsofinterest.pointsofinterest.repository.PointRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PointService {

    private final PointRepository pointRepository;

    public List<Point> findAll() {
        return pointRepository.findAll();
    }

    public List<Point> findByName(String name) {
        return pointRepository.findByName(name);
    }

    public Optional<Point> findById(long id) {
        return pointRepository.findById(id);
    }

    public Point save(Point point) {
        return pointRepository.save(point);
    }

    public void delete(String name) {
        pointRepository.delete(name);
    }

    public void deleteById(long id) {
        pointRepository.deleteById(id);
    }

    public void replace(Point point) {
        List<Point> pointsByName = findByName(point.getName());

        if(pointsByName.isEmpty())
            throw new PointNotFoundException("Point not found");

        point.setId(pointsByName.get(0).getId());

        pointRepository.save(point);
    }

    public List<Point> findPossiblePoints(int x, int y, int radius) {
        return pointRepository.findPossiblePoints(x, y, radius);
    }
}