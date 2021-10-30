package com.abdou.esp.device;

import com.abdou.esp.position.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Device {
    @Id
    private Long id;
    private String start;
    @OneToMany(mappedBy = "device" )
     @JsonIgnore
    private Set<Position> positions = new HashSet<>()   ;

}
