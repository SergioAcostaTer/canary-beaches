package com.canary.beaches.model;

import com.canary.beaches.model.enums.Classification;
import com.canary.beaches.model.enums.Environment;
import com.canary.beaches.model.enums.Island;
import com.canary.beaches.model.enums.Level;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "beaches")
@Data
public class Beach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String municipality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Island island;

    @Column(nullable = false)
    private String province;

    @Enumerated(EnumType.STRING)
    private Classification classification;

    @Enumerated(EnumType.STRING)
    private Level protectionLevel;

    @Enumerated(EnumType.STRING)
    private Level annualMaxOccupancy;

    @Enumerated(EnumType.STRING)
    private Level riskLevel;

    @Column(nullable = false)
    private boolean isBeach;

    @Column(nullable = false)
    private boolean isZbm;

    private Integer length;
    private Integer width;
    private String sandComposition;
    private String sandColor;

    @Column(name = "bathing_conditions")
    private String bathingCondition;

    @Column(name = "blue_flag")
    private boolean hasBlueFlag;

    // Accessibility
    private boolean wheelchairAccess;
    private boolean adaptedShower;
    private boolean adaptedRestroom;
    private boolean assistedBathing;
    private boolean pmrShade;

    // Facilities
    private boolean hasParking;
    private boolean hasRestrooms;
    private boolean hasShowers;
    private boolean hasAdaptedShowers;
    private boolean hasToilets;
    private boolean umbrellaRentals;
    private boolean sunbedRentals;
    private boolean waterSportsRentals;
    private boolean kidsArea;
    private boolean sportsArea;

    // Safety
    private String lifeguardService;
    private String swimmingCondition;

    // Coordinates
    private Double latitude;
    private Double longitude;

    @Column(name = "last_update")
    private LocalDate lastUpdated;


    @Enumerated(EnumType.STRING)
    private Environment environmentCondition;

    public void setIsBeach(boolean playa) {
        this.isBeach = playa;
        this.isZbm = !playa;
    }

    private boolean accessByCar;
    private boolean accessByShip;
    private String accessByFoot;




}
