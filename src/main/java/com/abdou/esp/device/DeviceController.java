package com.abdou.esp.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/device")
public class DeviceController {

    private  final DeviceService service;

    @Autowired
    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Device> getAllDevices(){
        return service.getAllDevices() ;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Device device){
        return this.service.add(device);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return  this.service.delete(id);
    }
}
