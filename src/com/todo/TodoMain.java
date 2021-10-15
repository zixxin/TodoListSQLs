package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "del(3)":
				int ids1 = sc.nextInt();
				int ids2 = sc.nextInt();
				int ids3 = sc.nextInt();
				TodoUtil.deleteItem(l, ids1, ids2, ids3);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				System.out.println("[제목순] 정렬 완료!");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("[제목역순] 정렬 완료!");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("[날짜순] 정렬 완료!");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("[날짜역순] 정렬 완료!");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;
				
			case "todo_today":
				int index1 = sc.nextInt();
				int index2 = sc.nextInt();
				int index3 = sc.nextInt();
				TodoUtil.todoTodayItem(l, index1, index2, index3);
				break;
				
			case "del_todo_today":
				int indexx = sc.nextInt();
				TodoUtil.todoTodayDelItem(l, indexx);
				break;
				
			case "ls_todo_today":
				TodoUtil.todoTodayList(l);
				break;
			
			case "comp(1)":
				int id = sc.nextInt();
				TodoUtil.completeItem(l, id);
				break;
				
			case "del_comp":
				int iddels = sc.nextInt();
				TodoUtil.notCompItem(l, iddels);
				break;
				
			case "doing":
				int ids = sc.nextInt();
				TodoUtil.doingItem(l, ids);
				break;
			
			case "del_doing":
				int iddel = sc.nextInt();
				TodoUtil.notDoingItem(l, iddel);
				break;
			
			case "comp(3)":
				int id1 = sc.nextInt();
				int id2 = sc.nextInt();
				int id3 = sc.nextInt();
				TodoUtil.completeItem(l, id1, id2, id3);
				break;
				
			case "doing(3)":
				int idst1 = sc.nextInt();
				int idst2 = sc.nextInt();
				int idst3 = sc.nextInt();
				TodoUtil.doingItem(l, idst1, idst2, idst3);
				break;
				
			case "ls_comp":
				TodoUtil.completeList(l);
				break;
				
			case "ls_doing":
				TodoUtil.doingList(l);
				break;
				
			case "ls_start":
				TodoUtil.startList(l);
				break;
				
			case "help":
				Menu.displaymenu();
				break; 

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("명령어가 올바르지 않아요!");
				System.out.println("도움말(메뉴 보기) -> help");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
	}
}