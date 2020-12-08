package com.sdstc.oauth2.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Perm implements Serializable {
	private Long id;
	private String code;
	private String name;
}
