package org.xy.pointsofinterest.pointsofinterest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.xy.pointsofinterest.pointsofinterest.entity.Point;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    List<Point> findByName(String name);

    @Query("delete from Point p where p.name = ?1")
    void delete(String name);

    @Query("SELECT p FROM Point p WHERE SQRT(POWER(p.x - ?1, 2) + POWER(p.y - ?2, 2)) <= ?3")
    List<Point> findPossiblePoints(int x,int y, int radius);
}