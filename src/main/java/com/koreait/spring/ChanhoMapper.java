package com.koreait.spring;

import com.koreait.spring.vo.LocationCodeEntity;
import com.koreait.spring.vo.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChanhoMapper {

    List<LocationCodeEntity> selLocationCodeList();

    void saveData(SearchDTO param);
}
