package controller;

import java.util.Scanner;

import model.UserVO;

public class UserRegisterManager {

	public static Scanner sc = new Scanner(System.in);

	// 로그인
	public String userLogin() {
		UserDAO ud = new UserDAO();
		UserVO uv = new UserVO();
		boolean success = false;
		String u_id = null;
		String u_pw = null;

		do {
			System.out.print("아이디: ");
			u_id = sc.nextLine();
			System.out.print("비밀번호: ");
			u_pw = sc.nextLine();

			success = ud.getUserLogin(u_id, u_pw);
			if (!success) {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
			}
		} while (!success);

		System.out.println("로그인 성공!");
		return u_id;
	}

	// 회원가입
	public String userRegister() {
		UserDAO ud = new UserDAO();
		UserVO uv = new UserVO();
		boolean idCheck = false;

		String u_id = null;
		System.out.println("유저 정보 입력");
		do {
			System.out.print("아이디: ");
			u_id = sc.nextLine();
			idCheck = ud.getUserIdCheck(u_id);
			if (idCheck) {
				System.out.println("중복된 아이디입니다.");
			}
		} while (idCheck);
		
		System.out.print("비밀번호: ");
		String u_pw = sc.nextLine();
		System.out.print("이름: ");
		String u_name = sc.nextLine();
		System.out.print("전화번호: ");
		String u_phone = sc.nextLine();
		System.out.print("강사여부(Y/N): ");
		String is_instructor = sc.nextLine().toUpperCase();

		uv.setU_id(u_id);
		uv.setU_pw(u_pw);
		uv.setU_name(u_name);
		uv.setU_phone(u_phone);
		uv.setIs_instructor(is_instructor);

		ud.setUserRegister(uv);
		System.out.println();
		System.out.println("유저 정보 리스트");
		ud.getUser(u_id);
		System.out.println();
		return u_id;
	}
	
	//유저 개인 정보(
	public void userInfo(String u_id) {
		UserDAO ud = new UserDAO();
		ud.getUser(u_id);
	}
	
	public void userList() {
		UserDAO ud = new UserDAO();
		System.out.println("유저 전체 리스트");
		System.out.print("관리자 아이디: ");
		String id = sc.nextLine();
		System.out.print("관리자 비밀번호: ");
		String pw = sc.nextLine();

		if (id.equals("admin") && pw.equals("admin1234")) {
			ud.getUserTotalList();
		} else {
			System.out.println("관리자 아이디 혹은 비밀번호가 틀렸습니다.");
		}
	}

	public void userUpdate(String id) {
		UserDAO ud = new UserDAO();
		UserVO uv = new UserVO();

		String u_no = ud.getUserNO(id);
		System.out.println("수정할 유저");
		System.out.println("일련번호: " + u_no);
		System.out.println("아이디: " + id);
		System.out.println();
		
		System.out.println("수정할 정보 입력");
		System.out.print("비밀번호: ");
		String u_pw = sc.nextLine();
		System.out.print("이름: ");
		String u_name = sc.nextLine();
		System.out.print("전화번호: ");
		String u_phone = sc.nextLine();

		uv.setU_id(id);
		uv.setU_pw(u_pw);
		uv.setU_name(u_name);
		uv.setU_phone(u_phone);

		ud.setUserUpdate(uv);
		System.out.println("유저 정보 수정 결과");
		ud.getUser(id);
		System.out.println();
	}

	public void userDelete(String id) {
		UserDAO ud = new UserDAO();
		String answer = null;
		do {
			System.out.print("계정을 삭제하시겠습니까?(Y/N): ");
			answer = sc.nextLine();
			if (answer.equalsIgnoreCase("Y")) {
				ud.setuserDelete(id);
				break;
			} else if (answer.equalsIgnoreCase("N")){
				System.out.println("이전 페이지로 돌아갑니다.");
				break;
			} else {
				System.out.println("Y 혹은 N을 입력해주세요.");
			}
		} while (true);
		
	}
	
}
