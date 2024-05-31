package view;

import java.util.Scanner;

public class MenuViewer {
	public static Scanner choice = new Scanner(System.in);

	public static void mainMenuView() {
		System.out.println("**********************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t         Teamfit  Program");
		System.out.println("**********************************************************************************************************************************");
		System.out.println();
		System.out.println("\t\t\t1. 마이페이지\t\t2. 강의 검색\t\t3. 수강 신청\t\t4. 수강 취소");
		System.out.println("\t\t\t5. 수강 전체 취소\t\t6. 강사 매칭\t\t7. 프로그램 종료");
		System.out.println();
		System.out.println("**********************************************************************************************************************************");
		System.out.print("번호 선택: ");
	}

	public static void myPageView() {
		System.out.println("**********************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t         Teamfit  Program");
		System.out.println("**********************************************************************************************************************************");
		System.out.println();
		System.out.println("\t\t\t1. 개인 정보 확인\t\t2. 정보 수정\t\t3. 수강 신청 목록\t\t4. 계정 삭제");
		System.out.println("\t\t\t5. 매칭된 강사 정보 확인\t6. 강의 개설(강사만 가능)\t7. 강의 수정(강사만 가능)\t8. 강의 취소(강사만 가능)");
		System.out.println("\t\t\t9. 메인페이지로 이동");
		System.out.println();
		System.out.println("**********************************************************************************************************************************");
		System.out.print("번호 선택: ");			
	}
}
