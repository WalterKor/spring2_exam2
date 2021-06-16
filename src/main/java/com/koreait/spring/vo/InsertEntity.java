package com.koreait.spring.vo;

import com.koreait.spring.ApartmentInfoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertEntity {

    private int location_cd;
    private ApartmentInfoEntity[] arr;

}
