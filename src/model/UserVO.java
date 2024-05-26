package model;

public class UserVO {
	private int u_no;
	private String u_id;
	private String u_pw;
	private String u_name;
	private String u_phone;
	private String is_instructor;

	public UserVO() {
		super();
	}

	public UserVO(int u_no, String u_id, String u_pw, String u_name, String u_phone, String is_instructor) {
		super();
		this.u_no = u_no;
		this.u_id = u_id;
		this.u_pw = u_pw;
		this.u_name = u_name;
		this.u_phone = u_phone;
		this.is_instructor = is_instructor;
	}

	public int getU_no() {
		return u_no;
	}

	public void setU_no(int u_no) {
		this.u_no = u_no;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_pw() {
		return u_pw;
	}

	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public String getIs_instructor() {
		return is_instructor;
	}

	public void setIs_instructor(String is_instructor) {
		this.is_instructor = is_instructor;
	}

	@Override
	public String toString() {
		return "일련번호\t|" + u_no + "\n아이디\t|" + u_id + "\n비밀번호\t|" + u_pw + "\n이름\t|" + u_name + "\n전화번호\t|" + u_phone
				+ "\n강사여부\t|" + is_instructor;

	}
}
