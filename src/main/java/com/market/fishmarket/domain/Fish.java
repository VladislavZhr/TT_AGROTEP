package com.market.fishmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fish {
    private int id;
    private String name;
    private BigDecimal price;
    private Date catchDate;
    private List<String> imageFilesNames;
}
