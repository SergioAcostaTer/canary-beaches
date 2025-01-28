package com.canary.beaches.dto;

import com.canary.beaches.model.enums.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class BeachDto {
    private Long id;
    private String name;
    private String municipality;
    private String island;
    private String province;
    private String classification;
    private String protectionLevel;
    private String annualMaxOccupancy;
    private String riskLevel;
    private boolean isBeach;
    private boolean isZbm;
    private Integer length;
    private Integer width;
    private String sandColor;
    private String bathingCondition;
    private boolean hasBlueFlag;
    private LocalDate lastUpdated;
    private String environmentCondition;
    private boolean accessByCar;
    private boolean accessByShip;
    private String accessByFoot;
    private boolean hasFootShowers;
    private boolean isWindy;
    private List<String> composition;
    private AccessibilityDto accessibility;
    private FacilitiesDto facilities;
    private SafetyDto safety;
    private CoordinatesDto coordinates;

    @Data
    public static class AccessibilityDto {
        private boolean wheelchairAccess;
        private boolean adaptedShower;
        private boolean assistedBathing;
        private boolean pmrShade;
    }

    @Data
    public static class FacilitiesDto {
        private boolean hasShowers;
        private boolean hasAdaptedShowers;
        private boolean hasToilets;
        private boolean umbrellaRentals;
        private boolean sunbedRentals;
        private boolean waterSportsRentals;
        private boolean kidsArea;
        private boolean sportsArea;
    }

    @Data
    public static class SafetyDto {
        private String lifeguardService;
    }

    @Data
    public static class CoordinatesDto {
        private Double latitude;
        private Double longitude;
    }
}