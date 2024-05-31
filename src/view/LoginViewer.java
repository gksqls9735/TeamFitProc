package view;

import java.util.Scanner;

public class LoginViewer {
	
	public static Scanner choice = new Scanner(System.in);
	
	public static void loginMenuView() {
		System.out.println();
		System.out.println("**********************************************************************************************************************************");
		System.out.println();
		System.out.println("\t\t\t\t\t\t         Teamfit  Program");
		System.out.println("\t\t\t\t\t\t=========Log In / Sign Up=========");
		System.out.println();
		System.out.println("**********************************************************************************************************************************");
		System.out.println("\t\t\t\t\t   1. 로그인\t    2. 회원가입\t      3. 종료");
		System.out.println("**********************************************************************************************************************************");
		System.out.print("번호 선택: ");
	}
}
