package com.canary.beaches.model;

import com.canary.beaches.model.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "beaches")
@Data
public class Beach {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Information
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String municipality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Island island;

    @Column(nullable = false)
    private String province;

    // Classification & Protection
    @Enumerated(EnumType.STRING)
    private Classification classification;

    @Enumerated(EnumType.STRING)
    private Level protectionLevel;

    @Enumerated(EnumType.STRING)
    private Level annualMaxOccupancy;

    @Enumerated(EnumType.STRING)
    private Level riskLevel;

    // Beach & ZBM (Zona de Baño Marítima)
    @Column(nullable = false)
    private boolean isBeach;

    @Column(nullable = false)
    private boolean isZbm;

    // Dimensions
    private Integer length;
    private Integer width;

    // Sand & Environment
    @Enumerated(EnumType.STRING)
    private SandColor sandColor;

    @Enumerated(EnumType.STRING)
    private Environment environmentCondition;

    // Bathing Conditions
    @Column(name = "bathing_conditions")
    @Enumerated(EnumType.STRING)
    private Conditions bathingCondition;

    @Column(name = "blue_flag")
    private boolean hasBlueFlag;

    // Accessibility
    private boolean wheelchairAccess;
    private boolean adaptedShower;
    private boolean assistedBathing;
    private boolean pmrShade;

    // Facilities
    private boolean hasShowers;
    private boolean hasAdaptedShowers;
    private boolean hasToilets;
    private boolean umbrellaRentals;
    private boolean sunbedRentals;
    private boolean waterSportsRentals;
    private boolean kidsArea;
    private boolean sportsArea;
    private boolean hasFootShowers;

    // Safety
    private String lifeguardService;

    // Access
    private boolean accessByCar;
    private boolean accessByShip;
    private String accessByFoot;

    // Coordinates
    private Double latitude;
    private Double longitude;

    // Last Update
    @Column(name = "last_update")
    private LocalDate lastUpdated;

    // Wind & Surface Composition
    private boolean isWindy;
    private boolean hasSand;
    private boolean hasConcrete;
    private boolean hasGravel;
    private boolean hasRock;
    private boolean hasPebbles;
    private boolean hasCobbles;
    private boolean hasMixedComposition;

    // Setters
    public void setIsBeach(boolean playa) {
        this.isBeach = playa;
        this.isZbm = !playa;
    }

    public void setIsWindy(boolean b) {
        this.isWindy = b;
    }

    public void setSandComposition(String composition) {
        this.hasSand = composition.toLowerCase().contains("arena");
        this.hasConcrete = composition.toLowerCase().contains("cemento") || composition.toLowerCase().contains("hormigón");
        this.hasGravel = composition.toLowerCase().contains("grava");
        this.hasRock = composition.toLowerCase().contains("roca");
        this.hasPebbles = composition.toLowerCase().contains("bolos");
        this.hasCobbles = composition.toLowerCase().contains("callaos");
        this.hasMixedComposition = composition.toLowerCase().contains("mixto") || composition.toLowerCase().contains("composición");
    }

    public Optional<Beach> orElse(Object o) {
        return Optional.ofNullable((Beach) o);
    }
}