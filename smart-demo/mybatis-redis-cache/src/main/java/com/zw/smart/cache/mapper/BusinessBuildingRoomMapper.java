package com.zw.smart.cache.mapper;

import com.zw.smart.cache.entity.BusinessBuildingRoom;

public interface BusinessBuildingRoomMapper {
    int deleteByPrimaryKey(String roomId);

    int insert(BusinessBuildingRoom record);

    int insertSelective(BusinessBuildingRoom record);

    BusinessBuildingRoom selectByPrimaryKey(String roomId);

    int updateByPrimaryKeySelective(BusinessBuildingRoom record);

    int updateByPrimaryKey(BusinessBuildingRoom record);
}