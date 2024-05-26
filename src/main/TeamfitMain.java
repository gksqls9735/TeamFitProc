package main;

import java.util.ArrayList;
import java.util.Map;

import controller.CartDAO;
import controller.CartRegisterManager;
import controller.ExerciseDAO;
import controller.ExerciseRegisterManager;
import controller.MatchsRegisterManager;
import controller.UserRegisterManager;
import view.LOGIN_CHOICE;
import view.LoginViewer;
import view.MENU_CHOICE;
import view.MYPAGE_CHOICE;
import view.MenuViewer;

public class TeamfitMain {

	public static void main(String[] args) {
		String id = loginMenu();
		mainMenu(id);
	}
	//로그인, 회원가입 진행
	private static String loginMenu() {
		UserRegisterManager urm = new UserRegisterManager();
		int choiceNum = 0;
		String id = null;
		while (true) {
			try {
				LoginViewer.loginMenuView();
				choiceNum = Integer.parseInt(LoginViewer.choice.nextLine());

				switch (choiceNum) {
				case LOGIN_CHOICE.LOGIN:	//로그인
					System.out.println();
					id = urm.userLogin();
					return id;
				case LOGIN_CHOICE.SIGNIN:	//회원가입
					System.out.println();
					id = urm.userRegister(); 
					return id;
				case LOGIN_CHOICE.EXIT:
					System.out.println("프로그램 종료");
					System.exit(0);
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n 입력에 오류가 있습니다. \n프로그램을 다시 시작하세요.");
				System.exit(0);
			}
		}

	}
	
	//메인 메뉴
	public static void mainMenu(String id) {
		UserRegisterManager urm = new UserRegisterManager();
		ExerciseRegisterManager erm = new ExerciseRegisterManager();
		CartRegisterManager crm = new CartRegisterManager();
		MatchsRegisterManager mrm = new MatchsRegisterManager();
		int choiceNum = 0;

		while (true) {
			try {
				MenuViewer.mainMenuView();
				choiceNum = Integer.parseInt(MenuViewer.choice.nextLine());

				switch (choiceNum) {
				case MENU_CHOICE.MYPAGE:		//마이페이지 들어가기
					System.out.println();
					myPageMenu(id); 
					break;
				case MENU_CHOICE.SEARCH:		//강의 검색하기
					System.out.println();
					erm.exerciseSearch();;
					break;
				case MENU_CHOICE.APPLICATION:	//수강 신청
					System.out.println();
					crm.cartRegister(id);
					break;
				case MENU_CHOICE.DELETE:		//개별 수강 취소
					System.out.println();
					crm.cartDelete(id);
					break;
				case MENU_CHOICE.ALL_DELETE:	//전체 수강 취소
					System.out.println();
					crm.allCartDelete(id);
					break;
				case MENU_CHOICE.MATCHING:		//강사 매칭
					System.out.println();
					mrm.doMatch(id);
					break;
				case MENU_CHOICE.EXIT:
					System.out.println("프로그램 종료");
					System.exit(0);
					;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n 입력에 오류가 있습니다. \n프로그램을 다시 시작하세요.");
				return;
			}
		}

	}
	
	//마이페이지
	public static void myPageMenu(String id) {
		UserRegisterManager urm = new UserRegisterManager();
		ExerciseRegisterManager erm = new ExerciseRegisterManager();
		CartRegisterManager crm = new CartRegisterManager();
		MatchsRegisterManager mrm = new MatchsRegisterManager();
		int choiceNum = 0;

		while (true) {
			try {
				MenuViewer.myPageView();
				choiceNum = Integer.parseInt(MenuViewer.choice.nextLine());

				switch (choiceNum) {
				case MYPAGE_CHOICE.MYINFO:		//개인 정보 확인
					System.out.println();
					urm.userInfo(id);
					break;
				case MYPAGE_CHOICE.UPDATE:		//개인 정보 수정
					System.out.println();
					urm.userUpdate(id);
					break;
				case MYPAGE_CHOICE.MYLIST:		//수강 신청 목록
					System.out.println();
					crm.cartList(id);
					break;
				case MYPAGE_CHOICE.DELETE:		//계정 삭제
					System.out.println();
					urm.userDelete(id);
					System.exit(0);;
				case MYPAGE_CHOICE.INST_INFO:	//매칭된 강사 정보 확인
					System.out.println();
					mrm.myMatchInfo(id);
					break;
				case MYPAGE_CHOICE.INSERT_EXER:	//강의 개설(강사만)
					System.out.println();
					erm.exerciseRegister(id);
					break;
				case MYPAGE_CHOICE.UPDATE_EXER:	//강의 수정(강사만)
					System.out.println();
					erm.exerciseUpdate(id);
					break;
				case MYPAGE_CHOICE.DELETE_EXER:	//강의 취소(강사만)
					System.out.println();
					erm.exerciseDelete(id);
					break;
				case MYPAGE_CHOICE.EXIT:
					System.out.println("메인페이지로 돌아갑니다.");
					return;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n 입력에 오류가 있습니다. \n프로그램을 다시 시작하세요.");
				return;
			}
		}
	}
	
}
