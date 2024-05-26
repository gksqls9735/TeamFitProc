package controller;

import java.util.Scanner;

import model.ExerciseVO;

public class ExerciseRegisterManager {

	public static Scanner sc = new Scanner(System.in);
	
	//강의 전체 리스트 출력
	public void exerciseList() {
		ExerciseDAO ed = new ExerciseDAO();
		System.out.println("강의 전체 리스트");
		ed.getExerciseTotalList();
		System.out.println();
	}

	//강의 개설
	public void exerciseRegister(String id) {
		UserDAO ud = new UserDAO();

		if (ud.isInstructor(id).equalsIgnoreCase("N")) {
			System.out.println("강사만 사용할 수 있는 메뉴입니다.");
			System.out.println("이전 페이지로 돌아갑니다.");
			return;
		}

		ExerciseDAO ed = new ExerciseDAO();
		ExerciseVO ev = new ExerciseVO();

		System.out.println("강의 정보 입력");
		System.out.print("운동종목: ");
		String e_name = sc.nextLine();
		System.out.print("가격: ");
		int price = Integer.parseInt(sc.nextLine());
		System.out.print("강의날짜: ");
		String e_date = sc.nextLine();
		System.out.print("강의시간: ");
		String e_time = sc.nextLine();
		System.out.print("강의장소: ");
		String e_addr = sc.nextLine();
		System.out.print("최대정원: ");
		int e_maxmem = Integer.parseInt(sc.nextLine());

		ev.setE_name(e_name);
		ev.setE_price(price);
		ev.setE_date(e_date);
		ev.setE_time(e_time);
		ev.setE_addr(e_addr);
		ev.setE_maxmem(e_maxmem);
		ev.setE_memCount(0);

		ed.setExerciseRegister(ev);
		System.out.println();
		System.out.println("강의 전체 리스트");
		ed.getExerciseTotalList();
		System.out.println();
	}

	//강의 수정
	public void exerciseUpdate(String id) {
		UserDAO ud = new UserDAO();

		if (ud.isInstructor(id).equalsIgnoreCase("N")) {
			System.out.println("강사만 사용할 수 있는 메뉴입니다.");
			System.out.println("이전 페이지로 돌아갑니다.");
			return;
		}

		ExerciseDAO ed = new ExerciseDAO();
		ExerciseVO ev = new ExerciseVO();

		System.out.println("강의 전체 리스트");
		ed.getExerciseTotalList();
		System.out.println();
		System.out.println("수정할 강의의 일련번호 입력: ");
		System.out.print("일련번호: ");
		int e_no = Integer.parseInt(sc.nextLine());

		System.out.println();
		System.out.println("수정할 정보 입력");
		System.out.print("가격: ");
		int price = Integer.parseInt(sc.nextLine());
		System.out.print("강의날짜: ");
		String e_date = sc.nextLine();
		System.out.print("강의시간: ");
		String e_time = sc.nextLine();
		System.out.print("강의장소: ");
		String e_addr = sc.nextLine();

		ev.setE_no(e_no);
		ev.setE_price(price);
		ev.setE_date(e_date);
		ev.setE_time(e_time);
		ev.setE_addr(e_addr);

		ed.setExerciseUpdate(ev);
		System.out.println();
		System.out.println("강의 전체 리스트");
		ed.getExerciseTotalList();
		System.out.println();
	}

	//강의 삭제
	public void exerciseDelete(String id) {
		UserDAO ud = new UserDAO();

		if (ud.isInstructor(id).equalsIgnoreCase("N")) {
			System.out.println("강사만 사용할 수 있는 메뉴입니다.");
			System.out.println("이전 페이지로 돌아갑니다.");
			return;
		}

		ExerciseDAO ed = new ExerciseDAO();

		System.out.println("강의 전체 리스트");
		ed.getExerciseTotalList();
		System.out.println();

		System.out.println("삭제할 강의의 일련번호 입력");
		System.out.print("일련번호: ");
		int e_no = Integer.parseInt(sc.nextLine());

		ed.setExerciseDelete(e_no);

		System.out.println();
		System.out.println("강의 전체 리스트보기");
		ed.getExerciseTotalList();
		System.out.println();

	}
	
	//운동 종목명으로 강의 검색
	public void exerciseSearch() {
		ExerciseDAO ed = new ExerciseDAO();
		System.out.println("검색할 운동 종목 입력");
		System.out.print("운동종목: ");
		String e_name = sc.nextLine();
		ed.getExerciseSearch(e_name);
	}

}
