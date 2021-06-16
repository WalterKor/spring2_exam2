package com.koreait.spring;

import com.koreait.spring.vo.InsertEntity;
import com.koreait.spring.vo.LocationCodeEntity;
import com.koreait.spring.vo.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChanhoMapper {

    int insApartmentInfoArr(InsertEntity param);
    List<LocationCodeEntity> selLocationCodeList(SearchDTO param);
    List<ApartmentInfoEntity> selApartmentInfoList(SearchDTO param);
    void saveData(SearchDTO param);
}
