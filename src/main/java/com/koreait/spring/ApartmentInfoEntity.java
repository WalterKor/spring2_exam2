package com.koreait.spring;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ApartmentInfoEntity {

    private int i_ai;

    @JsonProperty("거래금액") private String deal_amount; //거래금액
    @JsonProperty("건축년도") private String build_year;
    @JsonProperty("년") private String deal_year;
    @JsonProperty("월") private String deal_month;
    @JsonProperty("일") private String deal_day;
    @JsonProperty("법정동") private String dong;
    @JsonProperty("아파트") private String arpartment_name;
    @JsonProperty("전용면적") private float area_for_exclusive_use;
    @JsonProperty("지번") private String jibun;
    @JsonProperty("층") private int flr;

    public void setDeal_amount(String str){
        this.deal_amount = str.trim().replace(",", "");
    }
    private int location_cd;
}
