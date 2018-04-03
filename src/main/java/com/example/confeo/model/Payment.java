package com.example.confeo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by mstobieniecka on 2018-04-03.
 */
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private Double price;
    private LocalDateTime dateTime;
    private PaymentStatus status;
}
