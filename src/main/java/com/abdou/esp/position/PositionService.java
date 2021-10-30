package com.abdou.esp.position;

import com.abdou.esp.device.Device;
import com.abdou.esp.device.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepo repo;
    private final DeviceRepo deviceRepo;

    @Autowired
    public PositionService(PositionRepo repo, DeviceRepo deviceRepo) {
        this.repo = repo;
        this.deviceRepo = deviceRepo;
    }

    public List<Position> getAll() {
        return repo.findAll();
    }

    public Position create(Position position){
        return repo.save(position)  ;
    }

    public List<Position> findByDevice(Long device) {

        return repo.findAllByDevice(deviceRepo.findById(device).get());
     }

    public ResponseEntity saveAll(Position[] positions) {
         return new ResponseEntity(repo.saveAll(List.of(positions)),HttpStatus.CREATED);
    }

    public void deleteAllPositions(Long id){

         repo.deleteAllByDevice(deviceRepo.findById(id).get());
    }

    public  List<Position> getPositionInTime(Long d, String time) {

        return repo.findByDeviceAndTimeIsContaining(deviceRepo.findById(d).get(), time);

    }
}
