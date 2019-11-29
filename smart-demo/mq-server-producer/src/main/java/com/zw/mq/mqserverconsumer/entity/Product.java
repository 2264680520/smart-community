package com.zw.mq.mqserverconsumer.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    private String pid;
    private String msg;
}
