package com.sdstc.oauth2.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable{
	private static final long serialVersionUID = 1875530964317274753L;
	private Long id;
	private String name;
	private String state;
}
