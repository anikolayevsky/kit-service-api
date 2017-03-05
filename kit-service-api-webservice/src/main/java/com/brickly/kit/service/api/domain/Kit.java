package com.brickly.kit.service.api.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by alexnikolayevsky on 5/24/16.
 */
@Entity
@Table(name = "kit")
public class Kit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "kit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("category ASC, name ASC")
    private List<KitOption> kitOptions;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="base_fee")
    private Double baseFee;

    @Column(name="sku")
    private String sku;

    @Column(name="stock")
    private Integer stock;

    @Column(name = "created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<KitOption> getKitOptions() {
        return kitOptions;
    }

    public void setKitOptions(List<KitOption> kitOptions) {
        this.kitOptions = kitOptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(Double baseFee) {
        this.baseFee = baseFee;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
