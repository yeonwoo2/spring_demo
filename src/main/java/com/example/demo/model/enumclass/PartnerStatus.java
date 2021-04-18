package com.example.demo.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PartnerStatus {

    REGISTERED(0, "등록", "파트너 등록"),
    UNREGISTERED(1, "해지","파트너 해지"),
    WAITING(2, "검수중", "파트너 검수 대기중")
    ;

    private Integer id;
    private String title;
    private String description;
}
