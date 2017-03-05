package com.blckly.kit.service.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * Created by alexnikolayevsky on 5/24/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KitDTO {
    private Integer id;
    private List<KitOptionDTO> kitOptions;
    private String name;
    private String description;
    private Double baseFee;
    private String sku;
    private Integer stock;
    private Date created;
    private Date lastModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<KitOptionDTO> getKitOptions() {
        return kitOptions;
    }

    public void setKitOptions(List<KitOptionDTO> kitOptions) {
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
