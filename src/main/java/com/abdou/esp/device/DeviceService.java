package com.abdou.esp.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DeviceService {

    private  final DeviceRepo repo;


    @Autowired
    public DeviceService(DeviceRepo repo) {
        this.repo = repo;
    }

    public List<Device> getAllDevices(){
      return repo.findAll();
    }
    public  ResponseEntity add(Device device){
        if (repo.existsById(device.getId())){
            return new ResponseEntity ("device already exist" , HttpStatus.CONFLICT);
        }else{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd.HH:mm:ss");  
            device.setStart(dtf.format(LocalDateTime.now()));
            return new ResponseEntity( repo.save(device) ,HttpStatus.CREATED) ;
        }}
}
