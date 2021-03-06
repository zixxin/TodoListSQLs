package com.todo.menu;

public class Menu {

    public static void displaymenu(){
        System.out.println();
        System.out.println("< ToDoList 관리 명령어 사용법 >");
        System.out.println("[add] 항목 추가");
        System.out.println("[del] 항목 삭제");
        System.out.println("[del(3) (항목번호 3개 공백으로 구분)] 3개의 항목 동시 삭제");
        System.out.println("[edit] 항목 수정");
        System.out.println("[ls] 전체 목록");
        System.out.println("[find (키워드)] 항목 검색");
        System.out.println("[find_cate (키워드)] 카테고리 검색");
        System.out.println("[ls_name] 제목순 정렬");
        System.out.println("[ls_name_desc] 제목역순 정렬");
        System.out.println("[ls_date] 날짜순 정렬");
        System.out.println("[ls_date_desc] 날짜역순 정렬");
        System.out.println("[ls_cate] 카테고리 목록 출력");
        System.out.println("[todo_today (항목번호 3개 공백으로 구분)] 오늘 해야할 것들의 목록에 추가");
        System.out.println("[del_todo_today (항목번호)] 오늘 해야할 것들의 목록에서 삭제");
        System.out.println("[ls_todo_today] 오늘 해야할 것들의 목록 출력");
        System.out.println("[doing (항목번호)] 항목 수행 중으로 변경");
        System.out.println("[del_doing (항목번호)] 항목 수행 중 변경 취소");
        System.out.println("[doing(3) (항목번호 3개 공백으로 구분)] 3개의 항목 동시에 수행 중으로 변경");
        System.out.println("[ls_doing] 수행 중인 항목 출력");
        System.out.println("[comp(1) (항목번호)] 1개의 항목 수행완료 여부 변경");
        System.out.println("[del_comp (항목번호)] 항목 수행 완료 변경 취소");
        System.out.println("[comp(3) (항목번호 3개 공백으로 구분)] 3개의 항목 동시 수행완료 여부 변경");
        System.out.println("[ls_comp] 수행완료 항목 출력");
        System.out.println("[ls_start] 수행 시작한 항목만 모아서 출력");
        System.out.println("[exit] 종료");
    }
   
    public static void prompt() {
    	System.out.print("\n명령어 입력 -> ");
    }
}