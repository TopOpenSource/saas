package com.sdstc.pub.dto;


import lombok.Data;

@Data
public class PageDto {
	public static Integer DEFAULT_PAGESIZE = 10;
	// 数据总数
	private Integer count;
	// 每页显示数量
	private Integer pageSize;
	// 当前页
	private Integer page;
	// 共几页
	private Integer pages;
	// 起始 limit
	private Integer start;
	
	private String orderBy;
	private String orderType;
	
	
	//计算总页码
	public void setPageCount(Integer count) {
		this.count=count;
		//设置页数
		if (this.pageSize == null||this.pageSize<1) {
			this.pageSize = DEFAULT_PAGESIZE;
		}
		
		if(this.count==0) {
			this.pages=0;
		}else {
			this.pages=this.count % this.pageSize == 0 ? this.count / this.pageSize : this.count / this.pageSize + 1;
		}
		
		//设置limit start
		if(this.pages==0||this.page==null) {
			this.page=1;
			this.start=0;
					
		}else {
			if(this.page>this.pages) {
				this.page=this.getPages();
			}
			
	        if(this.page==null || this.page<1) {
	        	this.page=1;
	        }

	        this.start=this.pageSize*(this.page-1);
		}
		
	}
	
	public String getOrderType() {
		//放置sql注入
		if(this.orderType!=null && (this.orderType.toUpperCase().equals("ASC")|| this.orderType.toUpperCase().equals("DESC"))) {
			return this.orderType;
		}else {
			return "ASC";
		}
	}

}
