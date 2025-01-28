// BeachMapper.java
package com.canary.beaches.mapper;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.dto.BeachPreviewDto;
import com.canary.beaches.model.Beach;
import com.canary.beaches.model.enums.*;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Builder
public class BeachMapper {

    public static BeachDto toDto(Beach beach) {
        BeachDto dto = new BeachDto();
        dto.setId(beach.getId());
        dto.setName(beach.getName());
        dto.setClassification(mapClassification(beach));
        dto.setLocation(mapLocation(beach));
        dto.setDimensions(mapDimensions(beach));
        dto.setComposition(mapComposition(beach));
        dto.setConditions(mapConditions(beach));
        dto.setFacilities(mapFacilities(beach));
        dto.setAccessibility(mapAccessibility(beach));
        dto.setSafety(mapSafety(beach));
        dto.setStatus(mapStatus(beach));
        return dto;
    }

    public static BeachPreviewDto toPreviewDto(Beach beach) {
        BeachPreviewDto dto = new BeachPreviewDto();
        dto.setId(beach.getId());
        dto.setName(beach.getName());
        dto.setIsland(mapIslandName(beach.getIsland()));
        dto.setProvince(beach.getProvince());
        dto.setMunicipality(beach.getMunicipality());
        return dto;
    }

    private static BeachDto.BeachClassificationDto mapClassification(Beach beach) {
        BeachDto.BeachClassificationDto dto = new BeachDto.BeachClassificationDto();
        dto.setZbm(beach.isZbm());
        dto.setBeach(beach.isBeach());
        dto.setProtectionLevel(enumToString(beach.getProtectionLevel()));
        dto.setRiskLevel(enumToString(beach.getRiskLevel()));
        return dto;
    }

    private static BeachDto.LocationDto mapLocation(Beach beach) {
        BeachDto.LocationDto dto = new BeachDto.LocationDto();
        dto.setMunicipality(beach.getMunicipality());
        dto.setProvince(beach.getProvince());
        dto.setIsland(mapIslandName(beach.getIsland()));
        dto.setCoordinates(mapCoordinates(beach));
        dto.setAccess(mapAccessDetails(beach));
        return dto;
    }

    private static BeachDto.CoordinatesDto mapCoordinates(Beach beach) {
        BeachDto.CoordinatesDto dto = new BeachDto.CoordinatesDto();
        dto.setLatitude(beach.getLatitude());
        dto.setLongitude(beach.getLongitude());
        return dto;
    }

    private static BeachDto.AccessDetailsDto mapAccessDetails(Beach beach) {
        BeachDto.AccessDetailsDto dto = new BeachDto.AccessDetailsDto();
        dto.setByCar(beach.isAccessByCar());
        dto.setByShip(beach.isAccessByShip());
        dto.setByFootDescription(beach.getAccessByFoot());
        return dto;
    }

    private static BeachDto.DimensionsDto mapDimensions(Beach beach) {
        BeachDto.DimensionsDto dto = new BeachDto.DimensionsDto();
        dto.setLength(beach.getLength());
        dto.setWidth(beach.getWidth());
        dto.setSizeCategory(calculateSizeCategory(beach));
        return dto;
    }

    private static BeachDto.CompositionDto mapComposition(Beach beach) {
        BeachDto.CompositionDto dto = new BeachDto.CompositionDto();
        dto.setPrimaryMaterial(determinePrimaryMaterial(beach));
        dto.setSecondaryMaterials(mapSecondaryMaterials(beach));
        dto.setSandColor(enumToString(beach.getSandColor()));
        return dto;
    }

    private static BeachDto.ConditionsDto mapConditions(Beach beach) {
        BeachDto.ConditionsDto dto = new BeachDto.ConditionsDto();
        dto.setBathingCondition(enumToString(beach.getBathingCondition()));
        dto.setEnvironmentCondition(enumToString(beach.getEnvironmentCondition()));
        dto.setWindy(beach.isWindy());
        dto.setAnnualMaxOccupancy(enumToString(beach.getAnnualMaxOccupancy()));
        return dto;
    }

    private static BeachDto.FacilitiesDto mapFacilities(Beach beach) {
        BeachDto.FacilitiesDto dto = new BeachDto.FacilitiesDto();
        dto.setHasBlueFlag(beach.isHasBlueFlag());
        dto.setUmbrellaRentals(beach.isUmbrellaRentals());
        dto.setSunbedRentals(beach.isSunbedRentals());
        dto.setWaterSportsRentals(beach.isWaterSportsRentals());
        dto.setKidsArea(beach.isKidsArea());
        dto.setSportsArea(beach.isSportsArea());
        dto.setSanitation(mapSanitation(beach));
        return dto;
    }

    private static BeachDto.SanitationDto mapSanitation(Beach beach) {
        BeachDto.SanitationDto dto = new BeachDto.SanitationDto();
        dto.setHasShowers(beach.isHasShowers());
        dto.setHasAdaptedShowers(beach.isHasAdaptedShowers());
        dto.setHasToilets(beach.isHasToilets());
        dto.setHasFootShowers(beach.isHasFootShowers());
        return dto;
    }

    private static BeachDto.AccessibilityDto mapAccessibility(Beach beach) {
        BeachDto.AccessibilityDto dto = new BeachDto.AccessibilityDto();
        dto.setWheelchairAccess(beach.isWheelchairAccess());
        dto.setAdaptedShower(beach.isAdaptedShower());
        dto.setAssistedBathing(beach.isAssistedBathing());
        dto.setPmrShade(beach.isPmrShade());
        return dto;
    }

    private static BeachDto.SafetyDto mapSafety(Beach beach) {
        BeachDto.SafetyDto dto = new BeachDto.SafetyDto();
        dto.setLifeguardService(beach.getLifeguardService());
        dto.setEmergencyContacts("+34 112"); // Example static data
        return dto;
    }

    private static BeachDto.StatusDto mapStatus(Beach beach) {
        BeachDto.StatusDto dto = new BeachDto.StatusDto();
        dto.setLastUpdated(beach.getLastUpdated());
        return dto;
    }


    // Helper methods
    private static String enumToString(Enum<?> enumValue) {
        return enumValue != null ? enumValue.name() : null;
    }

    private static String mapIslandName(Island island) {
        return switch (island) {
            case GRAN_CANARIA -> "Gran Canaria";
            case TENERIFE -> "Tenerife";
            case LANZAROTE -> "Lanzarote";
            case FUERTEVENTURA -> "Fuerteventura";
            case LA_PALMA -> "La Palma";
            case LA_GOMERA -> "La Gomera";
            case EL_HIERRO -> "El Hierro";
            case LA_GRACIOSA -> "La Graciosa";
            default -> "Unknown";
        };
    }

    private static String calculateSizeCategory(Beach beach) {
        if (beach.getLength() == null || beach.getWidth() == null) return "UNKNOWN";
        double area = beach.getLength() * beach.getWidth();
        if (area > 100000) return "LARGE";
        if (area > 50000) return "MEDIUM";
        return "SMALL";
    }

    private static List<String> mapSecondaryMaterials(Beach beach) {
        List<String> materials = new ArrayList<>();
        if (beach.isHasConcrete()) materials.add("Concrete");
        if (beach.isHasGravel()) materials.add("Gravel");
        if (beach.isHasRock()) materials.add("Rock");
        if (beach.isHasPebbles()) materials.add("Pebbles");
        if (beach.isHasCobbles()) materials.add("Cobbles");
        return materials;
    }

    private static String determinePrimaryMaterial(Beach beach) {
        if (beach.isHasSand()) return "Sand";
        if (beach.isHasMixedComposition()) return "Mixed";
        return "Unknown";
    }

}