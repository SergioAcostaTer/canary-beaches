
package com.canary.beaches.service.impl;

import com.canary.beaches.dto.CsvBeachDto;
import com.canary.beaches.model.Beach;
import com.canary.beaches.model.enums.Classification;
import com.canary.beaches.model.enums.Environment;
import com.canary.beaches.model.enums.Island;
import com.canary.beaches.model.enums.Level;
import com.canary.beaches.service.BeachService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CsvBeachImporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvBeachImporter.class);

    @Value("${csv.file.path:beaches.csv}")
    private String csvFilePath;

    private final BeachService beachService;

    @Autowired
    public CsvBeachImporter(BeachService beachService) {
        this.beachService = beachService;
    }

    @PostConstruct
    public void importCsvData() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource(csvFilePath).getInputStream()))) {

            CsvToBean<CsvBeachDto> csvToBean = new CsvToBeanBuilder<CsvBeachDto>(reader)
                    .withType(CsvBeachDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            LOGGER.info("CSV data import started...");

            csvToBean.stream().forEach(this::processCsvRecord);

            LOGGER.info("CSV data import completed successfully.");

        } catch (IOException e) {
            LOGGER.error("Error reading CSV file: {}", e.getMessage());
        }
    }

    private void processCsvRecord(CsvBeachDto dto) {
        try {
            Beach beach = convertDtoToEntity(dto);
            beachService.save(beach);
            LOGGER.info("Successfully saved record: {}", dto.getIdDge());
        } catch (Exception e) {
            LOGGER.error("Error processing record: {}", dto.getIdDge(), e);
        }
    }

    private Beach convertDtoToEntity(CsvBeachDto dto) {
        Beach beach = new Beach();
        beach.setName(dto.getName());
        beach.setMunicipality(dto.getMunicipality());
        beach.setIsland(parseIsland(dto.getIsland()));
        beach.setProvince(dto.getProvince());
        beach.setClassification(parseClassification(dto.getClassification()));
        beach.setProtectionLevel(parseLevel(dto.getProtectionLevel()));
        beach.setAnnualMaxOccupancy(parseLevel(dto.getAnnualMaxOccupancy()));
        beach.setRiskLevel(parseLevel(dto.getRiskLevel()));
        beach.setIsBeach(dto.getIsBeach().equalsIgnoreCase("playa"));
        beach.setHasBlueFlag(parseBoolean(dto.getBlueFlag()));
        beach.setWheelchairAccess(parseBoolean(dto.getWheelchairAccess()));
        beach.setAdaptedShower(parseBoolean(dto.getAdaptedShower()));
        beach.setLength(parseInt(dto.getLength()));
        beach.setWidth(parseInt(dto.getWidth()));
        beach.setSandComposition(dto.getSandComposition());
        beach.setSandColor(dto.getSandColor());
        beach.setLifeguardService(dto.getLifeguardService());
        beach.setLatitude(parseDmsToDecimal(dto.getLatitude()));
        beach.setLongitude(parseDmsToDecimal(dto.getLongitude()));
        beach.setLastUpdated(parseDate(dto.getLastUpdated()));
        beach.setBathingCondition(dto.getBathingCondition());
        beach.setEnvironmentCondition(parseEnvironment(dto.getEnvironmentCondition()));
        beach.setAccessByCar(parseByCar(dto.getAccessCondition()));
        beach.setAccessByShip(parseByShip(dto.getAccessCondition()));
        beach.setAccessByFoot(parseByFoot(dto.getAccessCondition()));
        beach.setKidsArea(parseBoolean(dto.getKidsArea()));
        beach.setSportsArea(parseBoolean(dto.getSportsArea()));
        return beach;
    }

    private Environment parseEnvironment(String environment) {
        if (environment == null) return null;
        try {
            return switch (environment.toUpperCase()) {
                case "URBANA" -> Environment.URBAN;
                case "SEMIURBANA" -> Environment.SEMI_URBAN;
                case "AISLADA" -> Environment.ISOLATED;
                default -> null;
            };
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Unknown environment: {}", environment);
            return null;
        }
    }

    private Classification parseClassification(String classification) {
        if (classification == null) return null;
        try {
            return switch (classification.toUpperCase()) {
                case "LIBRE" -> Classification.FREE;
                case "PELIGROSA" -> Classification.DANGEROUS;
                case "USO PROHIBIDO" -> Classification.FORBIDDEN;
                case "ZONA DE SEGURIDAD DE LA BASE AÉREA DE GANDO" -> Classification.SECURITY_ZONE_GANDO;
                default -> null;
            };
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Unknown classification: {}", classification);
            return null;
        }
    }

    private Island parseIsland(String islandName) {
        if (islandName == null) return null;
        try {
            return Island.valueOf(islandName.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Unknown island: {}", islandName);
            return null;
        }
    }

    private Level parseLevel(String levelName) {
        if (levelName == null) return null;
        try {
            char c = levelName.charAt(0);
            return switch (c) {
                case 'A' -> Level.HIGH;
                case 'M' -> Level.MEDIUM;
                case 'B' -> Level.LOW;
                default -> null;
            };
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Unknown level: {}", levelName);
            return null;
        }
    }

    private boolean parseBoolean(String value) {
        if (value == null) return false;
        return value.equalsIgnoreCase("si") || value.equalsIgnoreCase("sí");
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOGGER.warn("Invalid integer value: {}", value);
            return 0;
        }
    }

    private double parseDmsToDecimal(String dms) {
        if (dms == null || dms.isBlank()) return 0.0;

        try {
            String[] parts = dms.replace("\"", "").split("[°']");
            double degrees = Double.parseDouble(parts[0].trim());
            double minutes = parts.length > 1 ? Double.parseDouble(parts[1].trim()) : 0.0;
            double seconds = parts.length > 2 ? Double.parseDouble(parts[2].trim().replaceAll("[A-Za-z]", "")) : 0.0;
            String direction = dms.substring(dms.length() - 1).toUpperCase();

            double decimal = degrees + (minutes / 60) + (seconds / 3600);
            return direction.matches("[SW]") ? -decimal : decimal;
        } catch (Exception e) {
            LOGGER.error("Error parsing coordinates: {}", dms, e);
            return 0.0;
        }
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            LOGGER.warn("Invalid date format: {}", dateStr);
            return null;
        }
    }


    private boolean parseByCar(String byCar) {
        if (byCar == null) return false;
        return byCar.toLowerCase().contains("coche");
    }

    private boolean parseByShip(String byShip) {
        if (byShip == null) return false;
        return byShip.toLowerCase().contains("barco");
    }

    private String parseByFoot(String byFoot) {
        if (byFoot == null) return null;

        if (byFoot.toLowerCase().contains("pie fácil")) {
            return "Easy";
        } else if (byFoot.toLowerCase().contains("pie difícil")) {
            return "Difficult";
        }
        return null;
    }
}
