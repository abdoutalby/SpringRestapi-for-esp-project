package com.abdou.esp.position;

import com.abdou.esp.device.Device;
import com.abdou.esp.device.DeviceRepo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/position")
public class PositionController {
    private final PositionService service;
    private final DeviceRepo deviceRepo;


    @Autowired
    public PositionController(PositionService service, DeviceRepo deviceRepo) {
        this.service = service;

        this.deviceRepo = deviceRepo;
    }

    @GetMapping
    @ApiOperation("get all positions ")
    public List<Position> getAll() {
        return service.getAll();
    }

    @ApiOperation("save new position of the device")
    @PostMapping("/{id}")
    public ResponseEntity add(@PathVariable("id") Long id, @RequestBody Position position) {
        if(deviceRepo.existsById(id)){
            position.setDevice(deviceRepo.findById(id).get());
            return new ResponseEntity(service.create(position),HttpStatus.CREATED) ;
        }else return new ResponseEntity("{ message: device not found}" , HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/device/{id}")
    @ApiOperation("get positions  of  the specific device")
    public ResponseEntity getByDevice(@PathVariable("id") Long device) {
        if(deviceRepo.existsById(device)){
            return new ResponseEntity( service.findByDevice(device),HttpStatus.OK);
        }
        else return new ResponseEntity<String>("device not found",HttpStatus.NOT_FOUND);
    }

    @PostMapping("/multi/{id}")
    @ApiOperation("save multiple postitoins to one device ")
    public ResponseEntity addMulti(@PathVariable("id") Long id, @RequestBody Position[] positions) {
        if (deviceRepo.existsById(id)){
        for (int i = 0; i < positions.length; i++) {
            positions[i].setDevice(deviceRepo.findById(id).get());
        }
        return new ResponseEntity(service.saveAll(positions) ,HttpStatus.CREATED);
    }
        else {
            return new ResponseEntity("device not found",HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation("get position in time")
    @GetMapping("/device/{device}/time/{time}")
    public ResponseEntity getPositionInTime(@PathVariable("time") String time  , @PathVariable("device") Long device){
        if (deviceRepo.existsById(device)){

            return new ResponseEntity(service.getPositionInTime(device,time),HttpStatus.OK);
        }
        else {
            return new ResponseEntity("not found ",HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation("delete the device and all of his positions")
    @Transactional
    @DeleteMapping("/device/{id}")
    public ResponseEntity<String>  deleteAllPositions(@PathVariable("id") Long id){

       if( deviceRepo.existsById(id)){
           Device d= deviceRepo.findById(id).get();
           service.deleteAllPositions(d.getId());return new ResponseEntity("deleted" , HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>("device not found ",HttpStatus.NOT_FOUND);
       }

    }

}
