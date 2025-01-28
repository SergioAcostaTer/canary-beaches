package com.canary.beaches.mapper;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.model.Beach;
import java.util.ArrayList;
import java.util.List;

public class BeachMapper {

    public static BeachDto toDto(Beach beach) {
        BeachDto dto = new BeachDto();
        // Simple field mappings
        dto.setId(beach.getId());
        dto.setName(beach.getName());
        dto.setMunicipality(beach.getMunicipality());
        dto.setIsland(enumToString(beach.getIsland()));
        dto.setProvince(beach.getProvince());
        dto.setClassification(enumToString(beach.getClassification()));
        dto.setProtectionLevel(enumToString(beach.getProtectionLevel()));
        dto.setAnnualMaxOccupancy(enumToString(beach.getAnnualMaxOccupancy()));
        dto.setRiskLevel(enumToString(beach.getRiskLevel()));
        dto.setBeach(beach.isBeach());
        dto.setZbm(beach.isZbm());
        dto.setLength(beach.getLength());
        dto.setWidth(beach.getWidth());
        dto.setSandColor(enumToString(beach.getSandColor()));
        dto.setBathingCondition(enumToString(beach.getBathingCondition()));
        dto.setHasBlueFlag(beach.isHasBlueFlag());
        dto.setLastUpdated(beach.getLastUpdated());
        dto.setEnvironmentCondition(enumToString(beach.getEnvironmentCondition()));
        dto.setAccessByCar(beach.isAccessByCar());
        dto.setAccessByShip(beach.isAccessByShip());
        dto.setAccessByFoot(beach.getAccessByFoot());
        dto.setHasFootShowers(beach.isHasFootShowers());
        dto.setWindy(beach.isWindy());

        // Composition mapping (Spanish terms)
        dto.setComposition(mapComposition(beach));

        // Nested objects
        dto.setAccessibility(mapAccessibility(beach));
        dto.setFacilities(mapFacilities(beach));
        dto.setSafety(mapSafety(beach));
        dto.setCoordinates(mapCoordinates(beach));

        return dto;
    }

    private static String enumToString(Enum<?> enumValue) {
        return enumValue != null ? enumValue.name() : null;
    }

    private static List<String> mapComposition(Beach beach) {
        List<String> composition = new ArrayList<>();
        if (beach.isHasSand()) composition.add("arena");
        if (beach.isHasConcrete()) composition.add("cemento");
        if (beach.isHasGravel()) composition.add("grava");
        if (beach.isHasRock()) composition.add("roca");
        if (beach.isHasPebbles()) composition.add("bolos");
        if (beach.isHasCobbles()) composition.add("callaos");
        if (beach.isHasMixedComposition()) composition.add("mixto");
        return composition;
    }

    private static BeachDto.AccessibilityDto mapAccessibility(Beach beach) {
        BeachDto.AccessibilityDto dto = new BeachDto.AccessibilityDto();
        dto.setWheelchairAccess(beach.isWheelchairAccess());
        dto.setAdaptedShower(beach.isAdaptedShower());
        dto.setAssistedBathing(beach.isAssistedBathing());
        dto.setPmrShade(beach.isPmrShade());
        return dto;
    }

    private static BeachDto.FacilitiesDto mapFacilities(Beach beach) {
        BeachDto.FacilitiesDto dto = new BeachDto.FacilitiesDto();
        dto.setHasShowers(beach.isHasShowers());
        dto.setHasAdaptedShowers(beach.isHasAdaptedShowers());
        dto.setHasToilets(beach.isHasToilets());
        dto.setUmbrellaRentals(beach.isUmbrellaRentals());
        dto.setSunbedRentals(beach.isSunbedRentals());
        dto.setWaterSportsRentals(beach.isWaterSportsRentals());
        dto.setKidsArea(beach.isKidsArea());
        dto.setSportsArea(beach.isSportsArea());
        return dto;
    }

    private static BeachDto.SafetyDto mapSafety(Beach beach) {
        BeachDto.SafetyDto dto = new BeachDto.SafetyDto();
        dto.setLifeguardService(beach.getLifeguardService());
        return dto;
    }

    private static BeachDto.CoordinatesDto mapCoordinates(Beach beach) {
        BeachDto.CoordinatesDto dto = new BeachDto.CoordinatesDto();
        dto.setLatitude(beach.getLatitude());
        dto.setLongitude(beach.getLongitude());
        return dto;
    }
}