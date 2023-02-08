package com.dvaren.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer id;

    private String artist;

    private Integer time;

    private String lrc;

    private String url;

    private String pic;
}
