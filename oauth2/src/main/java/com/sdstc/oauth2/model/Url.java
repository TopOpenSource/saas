package com.sdstc.oauth2.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Url implements Serializable {
    private Long id;
    private String name;
    private String url;
}
