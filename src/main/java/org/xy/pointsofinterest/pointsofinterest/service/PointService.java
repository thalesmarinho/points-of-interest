package org.xy.pointsofinterest.pointsofinterest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xy.pointsofinterest.pointsofinterest.entity.Point;
import org.xy.pointsofinterest.pointsofinterest.exception.PointNotFoundException;
import org.xy.pointsofinterest.pointsofinterest.mapper.PointMapper;
import org.xy.pointsofinterest.pointsofinterest.repository.PointRepository;
import org.xy.pointsofinterest.pointsofinterest.request.PointPutRequestBody;

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

    public void replace(PointPutRequestBody requestBody) {
        Optional<Point> savedPoint = findById(requestBody.getId());

        if(savedPoint.isEmpty())
            throw new PointNotFoundException("Point not found");

        Point point = PointMapper.INSTANCE.toPoint(requestBody);
        point.setId(savedPoint.get().getId());

        pointRepository.save(point);
    }

    public List<Point> findPossiblePoints(int x, int y, int radius) {
        return pointRepository.findPossiblePoints(x, y, radius);
    }
}