package view;

import java.util.Scanner;

public class MenuViewer {
	public static Scanner choice = new Scanner(System.in);

	public static void mainMenuView() {
		System.out.println();
		System.out.println("Teamfit");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 마이페이지");
		System.out.println("2. 강의 검색");
		System.out.println("3. 수강 신청");
		System.out.println("4. 수강 취소");
		System.out.println("5. 수강 전체 취소");
		System.out.println("6. 강사 매칭");
		System.out.println("7. 프로그램 종료");
		System.out.print("번호 선택: ");
	}

	public static void myPageView() {
		System.out.println();
		System.out.println("1. 개인 정보 확인");
		System.out.println("2. 정보 수정");
		System.out.println("3. 수강 신청 목록");
		System.out.println("4. 계정 삭제");
		System.out.println("5. 매칭된 강사 정보 확인");			
		System.out.println("6. 강의 개설(강사만 가능)");			
		System.out.println("7. 강의 수정(강사만 가능)");			
		System.out.println("8. 강의 취소(강사만 가능)");		
		System.out.println("9. 메인페이지로 이동");
		System.out.print("번호 선택: ");			
	}
}
