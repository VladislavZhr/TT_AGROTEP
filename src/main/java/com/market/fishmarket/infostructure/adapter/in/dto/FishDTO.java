package com.market.fishmarket.infostructure.adapter.in.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FishDTO {
    @NotBlank(message = "Назва рибки обов'язкова")
    @Size(min = 2, max = 100, message = "Назва повинна містити від 2 до 100 символів")
    private String name;

    @NotNull(message = "Ціна обов'язкова")
    @PositiveOrZero(message = "Ціна не може бути від'ємною")
    private BigDecimal price;

    @NotEmpty(message = "Потрібно завантажити хоча б одне фото")
    private List<MultipartFile> imageFiles;
}
