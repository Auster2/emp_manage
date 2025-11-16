package com.emp.view;

import com.emp.entity.Dept;
import com.emp.service.DeptService;
import com.emp.service.impl.DeptServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * 部门管理界面
 */
public class DeptView {

    private final Scanner scanner = new Scanner(System.in);
    private final DeptService deptService = new DeptServiceImpl();

    /**
     * 显示部门管理菜单
     */
    public void showMenu() {
        boolean running = true;
        while (running) {
            showDeptMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listAllDepts();
                    break;
                case "2":
                    addDept();
                    break;
                case "3":
                    updateDept();
                    break;
                case "4":
                    deleteDept();
                    break;
                case "5":
                    queryDept();
                    break;
                case "6":
                    searchDeptByName();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("\n❌ 无效选项，请重新选择！");
            }
        }
    }

    /**
     * 显示部门菜单
     */
    private void showDeptMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("【部门管理】");
        System.out.println("=".repeat(40));
        System.out.println("  1. 查看所有部门");
        System.out.println("  2. 添加部门");
        System.out.println("  3. 修改部门");
        System.out.println("  4. 删除部门");
        System.out.println("  5. 查询部门");
        System.out.println("  6. 按名称搜索部门");
        System.out.println("  0. 返回上级菜单");
        System.out.println("=".repeat(40));
        System.out.print("请选择操作：");
    }

    /**
     * 查看所有部门
     */
    private void listAllDepts() {
        System.out.println("\n【所有部门列表】");
        List<Dept> deptList = deptService.getAllDepts();

        if (deptList.isEmpty()) {
            System.out.println("暂无部门数据！");
            return;
        }

        System.out.println("-".repeat(50));
        System.out.printf("%-10s %-15s %-15s%n", "部门编号", "部门名称", "部门位置");
        System.out.println("-".repeat(50));

        for (Dept dept : deptList) {
            System.out.printf("%-10d %-15s %-15s%n",
                    dept.getDeptno(),
                    dept.getDname(),
                    dept.getLoc());
        }
        System.out.println("-".repeat(50));
    }

    /**
     * 添加部门
     */
    private void addDept() {
        System.out.println("\n【添加部门】");

        try {
            System.out.print("请输入部门编号：");
            int deptno = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("请输入部门名称：");
            String dname = scanner.nextLine().trim();

            System.out.print("请输入部门位置：");
            String loc = scanner.nextLine().trim();

            Dept dept = new Dept(deptno, dname, loc);

            if (deptService.addDept(dept)) {
                System.out.println("\n✓ 部门添加成功！");
            } else {
                System.out.println("\n✗ 部门添加失败！");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误，部门编号必须是数字！");
        }
    }

    /**
     * 修改部门
     */
    private void updateDept() {
        System.out.println("\n【修改部门】");

        try {
            System.out.print("请输入要修改的部门编号：");
            int deptno = Integer.parseInt(scanner.nextLine().trim());

            Dept dept = deptService.getDeptByDeptno(deptno);
            if (dept == null) {
                System.out.println("\n❌ 部门不存在！");
                return;
            }

            System.out.println("\n当前部门信息：" + dept);

            System.out.print("请输入新的部门名称（直接回车保持不变）：");
            String dname = scanner.nextLine().trim();
            if (!dname.isEmpty()) {
                dept.setDname(dname);
            }

            System.out.print("请输入新的部门位置（直接回车保持不变）：");
            String loc = scanner.nextLine().trim();
            if (!loc.isEmpty()) {
                dept.setLoc(loc);
            }

            if (deptService.modifyDept(dept)) {
                System.out.println("\n✓ 部门修改成功！");
            } else {
                System.out.println("\n✗ 部门修改失败！");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 删除部门
     */
    private void deleteDept() {
        System.out.println("\n【删除部门】");

        try {
            System.out.print("请输入要删除的部门编号：");
            int deptno = Integer.parseInt(scanner.nextLine().trim());

            Dept dept = deptService.getDeptByDeptno(deptno);
            if (dept == null) {
                System.out.println("\n❌ 部门不存在！");
                return;
            }

            System.out.println("\n确认删除以下部门？");
            System.out.println(dept);
            System.out.print("输入 Y 确认删除：");
            String confirm = scanner.nextLine().trim();

            if (confirm.equalsIgnoreCase("Y")) {
                if (deptService.removeDept(deptno)) {
                    System.out.println("\n✓ 部门删除成功！");
                } else {
                    System.out.println("\n✗ 部门删除失败！");
                }
            } else {
                System.out.println("\n已取消删除操作。");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 查询部门
     */
    private void queryDept() {
        System.out.println("\n【查询部门】");

        try {
            System.out.print("请输入部门编号：");
            int deptno = Integer.parseInt(scanner.nextLine().trim());

            Dept dept = deptService.getDeptByDeptno(deptno);
            if (dept != null) {
                System.out.println("\n查询结果：");
                System.out.println(dept);
            } else {
                System.out.println("\n❌ 未找到该部门！");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 按名称搜索部门
     */
    private void searchDeptByName() {
        System.out.println("\n【按名称搜索部门】");

        System.out.print("请输入部门名称关键字：");
        String keyword = scanner.nextLine().trim();

        List<Dept> deptList = deptService.searchDeptsByName(keyword);

        if (deptList.isEmpty()) {
            System.out.println("\n未找到匹配的部门！");
            return;
        }

        System.out.println("\n搜索结果：");
        System.out.println("-".repeat(50));
        System.out.printf("%-10s %-15s %-15s%n", "部门编号", "部门名称", "部门位置");
        System.out.println("-".repeat(50));

        for (Dept dept : deptList) {
            System.out.printf("%-10d %-15s %-15s%n",
                    dept.getDeptno(),
                    dept.getDname(),
                    dept.getLoc());
        }
        System.out.println("-".repeat(50));
    }
}