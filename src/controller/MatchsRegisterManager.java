package controller;

import java.util.Scanner;

public class MatchsRegisterManager {

	Scanner sc = new Scanner(System.in);

	//강사 매칭
	public void doMatch(String mem_id) {
		MatchsDAO md = new MatchsDAO();
		UserDAO ud = new UserDAO();

		// 강사가 이미 매칭된 상태라면 돌리기
		boolean check = md.checkMatchs(mem_id); // 0이면 false 0이 아니면 true를 주기
		if (!check) {
			System.out.println("이미 강사가 매칭된 상태입니다.");
			return;
		}
		// 강사의 일련번호를 입력
		ud.getInstList();
		System.out.println("강사의 일련번호를 입력해주세요.");
		System.out.print("일련번호: ");
		int no = Integer.parseInt(sc.nextLine());
		String inst_id = ud.getInstId(no);
		md.setMatchs(inst_id, mem_id);
		System.out.println();
		md.getMyInstInfo(mem_id);
	}

	// 매칭된 정보 확인
	public void myMatchInfo(String mem_id) {
		MatchsDAO md = new MatchsDAO();

		// 강사가 이미 매칭된 상태라면 돌리기
		boolean check = md.checkMatchs(mem_id);
		if (check) {
			System.out.println("매칭된 강사가 존재하지 않습니다.");
			return;
		}

		md.getMyInstInfo(mem_id);
		String answer = null;
		do {
			System.out.print("해당 매칭을 취소하시겠습니까?(Y/N): ");
			answer = sc.nextLine();

			if (answer.equalsIgnoreCase("N")) {
				System.out.println("이전 페이지로 돌아갑니다.");
				break;
			} else if (answer.equalsIgnoreCase("Y")) {
				System.out.println("매칭 취소를 선택하였습니다.");
				md.deleteMatchs(mem_id);
				break;
			} else {
				System.out.println("Y 혹은 N을 입력해주세요.");
			}

		} while (true);

	}
}
