package com.canary.beaches.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    private Double latitude;
    private Double longitude;
    private String imageUrl;

    public BeachPreviewDto calculateDistance(double refLat, double refLon) {
        double distance = haversine(this.latitude, this.longitude, refLat, refLon);
        return new BeachPreviewDtoWithDistance(this, distance);
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BeachPreviewDtoWithDistance extends BeachPreviewDto {
        private double distance;

        public BeachPreviewDtoWithDistance(BeachPreviewDto base, double distance) {
            super(base.getId(), base.getName(), base.getIsland(), base.getProvince(),
                    base.getMunicipality(), base.getLatitude(), base.getLongitude(), base.getImageUrl());
            this.distance = distance;
        }
    }
}
