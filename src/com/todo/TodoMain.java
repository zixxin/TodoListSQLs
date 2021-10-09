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
		//TodoUtil.loadList(l, "todolist.txt");
		
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
				
			case "comp":
				int id = sc.nextInt();
				TodoUtil.completeItem(l, id);
				break;
				
			case "ls_comp":
				TodoUtil.completeList(l);
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
		TodoUtil.saveList(l, "todolist.txt");
	}
}