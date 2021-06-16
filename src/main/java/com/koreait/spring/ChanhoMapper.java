package com.koreait.spring;

import com.koreait.spring.vo.InsertEntity;
import com.koreait.spring.vo.LocationCodeEntity;
import com.koreait.spring.vo.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChanhoMapper {

    int insApartmentInfoArr(ApartmentInfoEntity param);
    List<LocationCodeEntity> selLocationCodeList();
    List<ApartmentInfoEntity> selApartmentInfoList(SearchDTO param);

}
