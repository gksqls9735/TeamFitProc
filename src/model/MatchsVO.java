package model;

import java.util.Date;

public class MatchsVO {
	
	private int m_no;
	private String inst_id;
	private String mem_id;
	private Date m_date;
	
	public MatchsVO() {
		super();
	}

	public MatchsVO(int m_no, String inst_id, String mem_id, Date m_date) {
		super();
		this.m_no = m_no;
		this.inst_id = inst_id;
		this.mem_id = mem_id;
		this.m_date = m_date;
	}

	public int getM_no() {
		return m_no;
	}

	public void setM_no(int m_no) {
		this.m_no = m_no;
	}

	public String getInst_id() {
		return inst_id;
	}

	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public Date getM_date() {
		return m_date;
	}

	public void setM_date(Date m_date) {
		this.m_date = m_date;
	}

	@Override
	public String toString() {
		return "일련번호\t|" + m_no + "\n강사ID\t|" + inst_id + "\n유저ID\t|" + mem_id + "\n등록날짜\t" + m_date;
	}
	
	
}
