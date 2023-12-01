package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

import java.util.Set;

@Entity
@Table(name="excursions")
@Getter
@Setter
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="excursion_id")
    private Long id;

    @Column(name="excursion_title")
    private String excursion_title;

    @Column(name="excursion_price")
    private BigDecimal excursion_price;

    @Column(name="image_url")
    private String image_URL;

    @UpdateTimestamp
    @Column(name="last_update")
    private Date last_update;

    @CreationTimestamp
    @Column(name="create_date")
    private Date create_date;

    @ManyToOne
    @JoinColumn(name = "vacation_id",nullable = false)
    private Vacation vacation;


    @ManyToMany(mappedBy = "excursions")
    private Set<CartItem> cartItems;





}
