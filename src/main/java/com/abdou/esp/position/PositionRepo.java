package com.abdou.esp.position;

import com.abdou.esp.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepo extends JpaRepository<Position,Long> {
    List<Position> findAllByDevice(Device device);
    void deleteAllByDevice(Device device);
    List<Position> findByDeviceAndTimeIsContaining(Device d,String time );

    boolean existsByTime(Position position);
}
