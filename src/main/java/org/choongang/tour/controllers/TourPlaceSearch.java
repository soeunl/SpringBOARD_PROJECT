package org.choongang.tour.controllers;

import lombok.Data;

@Data
public class TourPlaceSearch { // 입력한 좌표의 반경 1km의 정보를 출력하겠다.
    private Double latitude;
    private Double longitude;
    private Integer radius = 1000;
}