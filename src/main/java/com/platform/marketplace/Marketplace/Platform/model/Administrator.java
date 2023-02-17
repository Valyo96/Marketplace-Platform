package com.platform.marketplace.Marketplace.Platform.model;

import jakarta.persistence.*;
@Entity
@Table(name = "admins")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
