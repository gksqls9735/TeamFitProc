package model;

import java.util.Date;

public class CartVO {
	private int c_no;
	private String u_id;
	private int e_no;
	private Date c_enrollment_date;
	private String c_payment_status;

	public CartVO() {
		super();
	}

	public CartVO(int c_no, String u_id, int e_no, Date c_enrollment_date, String c_payment_status) {
		super();
		this.c_no = c_no;
		this.u_id = u_id;
		this.e_no = e_no;
		this.c_enrollment_date = c_enrollment_date;
		this.c_payment_status = c_payment_status;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public int getE_no() {
		return e_no;
	}

	public void setE_no(int e_no) {
		this.e_no = e_no;
	}

	public Date getC_enrollment_date() {
		return c_enrollment_date;
	}

	public void setC_enrollment_date(Date c_enrollment_date) {
		this.c_enrollment_date = c_enrollment_date;
	}

	public String getC_payment_status() {
		return c_payment_status;
	}

	public void setC_payment_status(String c_payment_status) {
		this.c_payment_status = c_payment_status;
	}

	@Override
	public String toString() {
		return "일련번호\t|" + c_no + "\n유저ID\t|" + u_id + "\n강의번호\t|" + e_no + "\n신청날짜\t|" + c_enrollment_date
				+ "\n결제여부\t|" + c_payment_status;
	}
}
