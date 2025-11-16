package com.emp.view;

import java.util.Scanner;

/**
 * 主菜单界面
 */
public class MainView {

    private Scanner scanner = new Scanner(System.in);
    private DeptView deptView = new DeptView();
    private EmpView empView = new EmpView();

    /**
     * 启动系统
     */
    public void start() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     欢迎使用职工管理系统               ║");
        System.out.println("╚════════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> deptView.showMenu();
                case "2" -> empView.showMenu();
                case "0" -> {
                    running = false;
                    System.out.println("\n感谢使用，再见！");
                }
                default -> System.out.println("\n❌ 无效选项，请重新选择！");
            }
        }

        scanner.close();
    }

    /**
     * 显示主菜单
     */
    private void showMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("【主菜单】");
        System.out.println("=".repeat(40));
        System.out.println("  1. 部门管理");
        System.out.println("  2. 员工管理");
        System.out.println("  0. 退出系统");
        System.out.println("=".repeat(40));
        System.out.print("请选择操作：");
    }

    /**
     * 主方法
     */
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.start();
    }
}