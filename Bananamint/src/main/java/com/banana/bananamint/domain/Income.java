package com.banana.bananamint.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer user;

    private double amount;

    private LocalDate enterDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account moneyTo;

    private String status;

}
