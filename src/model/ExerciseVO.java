package model;

import java.util.Date;

public class ExerciseVO {
	private int e_no;
	private String e_name;
	private int e_price;
	private String e_date;
	private String e_time;
	private String e_addr;
	private int e_maxmem;
	private int e_memCount;

	public ExerciseVO() {
		super();
	}

	public ExerciseVO(int e_no, String e_name, int e_price, String e_date, String e_time, String e_addr, int e_maxmem,
			int e_memCount) {
		super();
		this.e_no = e_no;
		this.e_name = e_name;
		this.e_price = e_price;
		this.e_date = e_date;
		this.e_time = e_time;
		this.e_addr = e_addr;
		this.e_maxmem = e_maxmem;
		this.e_memCount = e_memCount;
	}

	public int getE_no() {
		return e_no;
	}

	public void setE_no(int e_no) {
		this.e_no = e_no;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		this.e_name = e_name;
	}

	public int getE_price() {
		return e_price;
	}

	public void setE_price(int e_price) {
		this.e_price = e_price;
	}

	public String getE_date() {
		return e_date;
	}

	public void setE_date(String e_date) {
		this.e_date = e_date;
	}

	public String getE_time() {
		return e_time;
	}

	public void setE_time(String e_time) {
		this.e_time = e_time;
	}

	public String getE_addr() {
		return e_addr;
	}

	public void setE_addr(String e_addr) {
		this.e_addr = e_addr;
	}

	public int getE_maxmem() {
		return e_maxmem;
	}

	public void setE_maxmem(int e_maxmem) {
		this.e_maxmem = e_maxmem;
	}

	public int getE_memCount() {
		return e_memCount;
	}

	public void setE_memCount(int e_memCount) {
		this.e_memCount = e_memCount;
	}

	@Override
	public String toString() {
		return String.format("%-10d %-10s %-10s %-14s %-11s %-16s %-10s",
                e_no, e_name, String.valueOf(e_price) + "원", e_date, e_time, e_addr, e_memCount + "/" + e_maxmem + "명");
	}
}
