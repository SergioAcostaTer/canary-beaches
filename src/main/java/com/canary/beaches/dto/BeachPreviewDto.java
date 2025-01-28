package com.canary.beaches.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeachPreviewDto {
    private Long id;
    private String name;
    private String island;
    private String province;
    private String municipality;
}
