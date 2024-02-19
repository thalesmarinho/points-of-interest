package org.xy.pointsofinterest.pointsofinterest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xy.pointsofinterest.pointsofinterest.entity.Point;
import org.xy.pointsofinterest.pointsofinterest.request.PointPutRequestBody;
import org.xy.pointsofinterest.pointsofinterest.service.PointService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("points")
public class PointController {

    private final PointService pointService;

    @GetMapping()
    public ResponseEntity<List<Point>> findAll() {
        return ResponseEntity.ok(pointService.findAll());
    }

    @GetMapping("/near")
    public ResponseEntity<List<Point>> findNear(@RequestParam(value="x") int x,
                                               @RequestParam(value="y") int y,
                                               @RequestParam(value="radius") int radius) {
        return ResponseEntity.ok(pointService.findPossiblePoints(x, y, radius));
    }

    @PostMapping
    public ResponseEntity<Point> save(@RequestBody @Valid Point point) {
        return new ResponseEntity<>(pointService.save(point), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        pointService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody PointPutRequestBody requestBody) {
        pointService.replace(requestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}