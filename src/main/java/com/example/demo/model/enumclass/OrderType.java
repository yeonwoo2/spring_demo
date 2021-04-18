package com.example.demo.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {

    ALL(0, "묶음", "묶음 배송"),
    EACH(1, "개별", "준비되는데로 배송")
    ;

    private Integer id;
    private String title;
    private String description;
}
