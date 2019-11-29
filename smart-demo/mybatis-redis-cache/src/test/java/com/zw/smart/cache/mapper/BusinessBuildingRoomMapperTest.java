package com.zw.smart.cache.mapper;

import com.zw.smart.cache.entity.BusinessBuildingRoom;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 分布式锁
 * 集群
 */
@SpringBootTest
@Slf4j
class BusinessBuildingRoomMapperTest {
    @Resource
    BusinessBuildingRoomMapper mapper;

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {

    }

    @Test
    void insertSelective() {
    }

    @Test
    void updateByPrimaryKeySelective() {

    }

    @Test
    void updateByPrimaryKey() {

    }

    @Test
    void selectByPrimaryKey() {
        BusinessBuildingRoom businessBuildingRoom = mapper.selectByPrimaryKey("abad");
        log.info(businessBuildingRoom.toString());
    }
}