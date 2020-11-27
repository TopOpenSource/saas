package com.sdstc.pub.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageResult<T> {
	private PageDto pageDto;
	private List<T> results;

	public PageResult(PageDto pageDto, List<T> results) {
		this.pageDto = pageDto;
		this.results = results;
	}

}
