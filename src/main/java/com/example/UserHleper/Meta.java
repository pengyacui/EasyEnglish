package com.example.UserHleper;

public class Meta {
	
	private String metakey;
	private String metaname;
//	private String metaorderid;
	private int metawordcount;
	private int metaunitcount;
		
	public Meta(String metakey,String metaname,int metawordcount,int metaunitcount) {
		super();
		this.metakey=metakey;
		this.metaname=metaname;
		this.metawordcount=metawordcount;
		this.metaunitcount=metaunitcount;
	}
	public String getMetakey() {
		return metakey;
	}
	public void setMetakey(String metakey) {
		this.metakey = metakey;
	}
	public String getMetaname() {
		return metaname;
	}
	public void setMetaname(String metaname) {
		this.metaname = metaname;
	}
//	public String getMetaorderid() {
//		return metaorderid;
//	}
//	public void setMetaorderid(String metaorderid) {
//		this.metaorderid = metaorderid;
//	}
	public int getMetawordcount() {
		return metawordcount;
	}
	public void setMetawordcount(int metawordcount) {
		this.metawordcount = metawordcount;
	}
	public int getMetaunitcount() {
		return metaunitcount;
	}
	public void setMetaunitcount(int metaunitcount) {
		this.metaunitcount = metaunitcount;
	}
	@Override
	public String toString() {
		return "meta [metakey=" + metakey + ", metaname=" + metaname
				+ ",  metawordcount="+ metawordcount + ", metaunitcount=" 
				+ metaunitcount + "]";
	}
	
	

}
