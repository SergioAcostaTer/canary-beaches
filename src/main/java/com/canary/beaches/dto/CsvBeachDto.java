package com.canary.beaches.dto;

import com.canary.beaches.model.enums.Environment;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvBeachDto {

    @CsvBindByName(column = "ID DGE")
    private String idDge;

    @CsvBindByName(column = "Nombre Playa o ZBM")
    private String name;

    @CsvBindByName(column = "Municipio")
    private String municipality;

    @CsvBindByName(column = "Isla")
    private String island;

    @CsvBindByName(column = "Provincia")
    private String province;

    @CsvBindByName(column = "Clasificación")
    private String classification;

    @CsvBindByName(column = "Grado de Protección")
    private String protectionLevel;

    @CsvBindByName(column = "Afluencia maxima anual")
    private String annualMaxOccupancy;

    @CsvBindByName(column = "Riesgo")
    private String riskLevel;

    @CsvBindByName(column = "Playa o ZBM")
    private String isBeach;

    @CsvBindByName(column = "Bandera azul")
    private String blueFlag;

    @CsvBindByName(column = "Acceso PMR")
    private String wheelchairAccess;

    @CsvBindByName(column = "Ducha adaptada")
    private String adaptedShower;

    @CsvBindByName(column = "Aseo adaptado")
    private String adaptedRestroom;

    @CsvBindByName(column = "Baño asistido")
    private String assistedBathing;

    @CsvBindByName(column = "Sombra PMR")
    private String pmrShade;

    @CsvBindByName(column = "Aparcamientos")
    private String parking;

    @CsvBindByName(column = "Aseo")
    private String restrooms;

    @CsvBindByName(column = "Duchas")
    private String showers;

    @CsvBindByName(column = "Longitud (m)")
    private String length;

    @CsvBindByName(column = "Anchura (m)")
    private String width;

    @CsvBindByName(column = "Composición")
    private String sandComposition;

    @CsvBindByName(column = "Color")
    private String sandColor;

    @CsvBindByName(column = "Nombre Servicio Socorrismo")
    private String lifeguardService;

    @CsvBindByName(column = "Latitud (º)")
    private String latitude;

    @CsvBindByName(column = "Longitud (º)")
    private String longitude;

    @CsvBindByName(column = "Fecha de última actualización")
    private String lastUpdated;

    @CsvBindByName(column = "Condiciones de baño")
    private String bathingCondition;

    @CsvBindByName(column = "Condiciones de acceso")
    private String accessCondition;

    @CsvBindByName(column = "Condiciones de entorno")
    private String environmentCondition;

    @CsvBindByName(column = "Area Infantil")
    private String kidsArea;

    @CsvBindByName(column = "Area Deportiva")
    private String sportsArea;

    @CsvBindByName(column = "Alquiler de hamacas")
    private String sunbedRentals;

    @CsvBindByName(column = "Alquiler de sombrillas")
    private String umbrellaRentals;

    @CsvBindByName(column = "Alquiler nautico")
    private String waterSportsRentals;

    @CsvBindByName(column = "Aseo")
    private String toilets;

    @CsvBindByName(column = "Lavapies")
    private String footShowers;
}
