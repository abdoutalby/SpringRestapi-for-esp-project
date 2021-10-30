package com.abdou.esp.position;

import com.abdou.esp.device.Device;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private Float charge;
    private Float xa;
    private Float ya;
    private Float za;
    private Float xg;
    private Float yg;
    private Float zg;
    private Float xm;
    private Float ym;
    private Float zm;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="device" , referencedColumnName = "id")
    @JsonIgnore
    private Device device;

}
