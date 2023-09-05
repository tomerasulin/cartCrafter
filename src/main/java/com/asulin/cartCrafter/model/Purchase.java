package com.asulin.cartCrafter.model;

import com.asulin.cartCrafter.util.Dates;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Product> products = new HashSet<>();
    @NotNull
    @Column(nullable = false, updatable = false)
    private Date createdAt = Dates.nowUTC();

    public Date getCreatedAt() {
        return createdAt;
    }

    public Set<Product> getProducts() {
        return products;
    }
}
