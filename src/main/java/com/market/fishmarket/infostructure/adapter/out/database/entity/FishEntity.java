package com.market.fishmarket.infostructure.adapter.out.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fish")
public class FishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "catch_date", nullable = false)
    private Date catchDate;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "fish_images", joinColumns = @JoinColumn(name = "fish_id"))
    @Column(name = "image_file_name", nullable = false)
    private List<String> imageFilesNames = new ArrayList<>();
}
