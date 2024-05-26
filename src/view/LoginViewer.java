package view;

import java.util.Scanner;

public class LoginViewer {
	
	public static Scanner choice = new Scanner(System.in);
	
	public static void loginMenuView() {
		System.out.println();
		System.out.println("Teamfit 강의 신청 프로그램");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
		System.out.print("번호 선택: ");
	}
}
