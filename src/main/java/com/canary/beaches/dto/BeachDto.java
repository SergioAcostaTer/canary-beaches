// BeachDto.java
package com.canary.beaches.dto;

import jdk.jshell.Snippet;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class BeachDto {
    private Long id;
    private String name;
    private BeachClassificationDto classification;
    private LocationDto location;
    private DimensionsDto dimensions;
    private CompositionDto composition;
    private ConditionsDto conditions;
    private FacilitiesDto facilities;
    private AccessibilityDto accessibility;
    private SafetyDto safety;
    private StatusDto status;


    // Nested DTOs
    @Data
    public static class BeachClassificationDto {
        private boolean isBeach;
        private boolean isZbm;
        private String protectionLevel;
        private String riskLevel;
    }

    @Data
    public static class LocationDto {
        private String municipality;
        private String province;
        private String island;
        private CoordinatesDto coordinates;
        private AccessDetailsDto access;
    }

    @Data
    public static class CoordinatesDto {
        private Double latitude;
        private Double longitude;
    }

    @Data
    public static class AccessDetailsDto {
        private boolean byCar;
        private boolean byShip;
        private String byFootDescription;
    }

    @Data
    public static class DimensionsDto {
        private Integer length;
        private Integer width;
        private String sizeCategory;
    }

    @Data
    public static class CompositionDto {
        private String primaryMaterial;
        private List<String> secondaryMaterials;
        private String sandColor;
    }

    @Data
    public static class ConditionsDto {
        private String bathingCondition;
        private String environmentCondition;
        private boolean isWindy;
        private String annualMaxOccupancy;
    }

    @Data
    public static class FacilitiesDto {
        private boolean hasBlueFlag;
        private boolean umbrellaRentals;
        private boolean sunbedRentals;
        private boolean waterSportsRentals;
        private boolean kidsArea;
        private boolean sportsArea;
        private SanitationDto sanitation;
    }

    @Data
    public static class SanitationDto {
        private boolean hasShowers;
        private boolean hasAdaptedShowers;
        private boolean hasToilets;
        private boolean hasFootShowers;
    }

    @Data
    public static class AccessibilityDto {
        private boolean wheelchairAccess;
        private boolean adaptedShower;
        private boolean assistedBathing;
        private boolean pmrShade;
    }

    @Data
    public static class SafetyDto {
        private String lifeguardService;
        private String emergencyContacts;
    }

    @Data
    public static class StatusDto {
        private LocalDate lastUpdated;
    }

}

