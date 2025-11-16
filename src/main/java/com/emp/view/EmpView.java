package com.emp.view;

import com.emp.entity.Emp;
import com.emp.service.EmpService;
import com.emp.service.impl.EmpServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 员工管理界面
 */
public class EmpView {

    private final Scanner scanner = new Scanner(System.in);
    private final EmpService empService = new EmpServiceImpl();

    /**
     * 显示员工管理菜单
     */
    public void showMenu() {
        boolean running = true;
        while (running) {
            showEmpMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listAllEmps();
                    break;
                case "2":
                    addEmp();
                    break;
                case "3":
                    updateEmp();
                    break;
                case "4":
                    deleteEmp();
                    break;
                case "5":
                    queryEmp();
                    break;
                case "6":
                    searchEmpByName();
                    break;
                case "7":
                    queryEmpsByDept();
                    break;
                case "8":
                    queryEmpsByJob();
                    break;
                case "9":
                    queryEmpsBySalary();
                    break;
                case "10":
                    adjustSalary();
                    break;
                case "11":
                    transferDept();
                    break;
                case "12":
                    showStatistics();
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
     * 显示员工菜单
     */
    private void showEmpMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("【员工管理】");
        System.out.println("=".repeat(40));
        System.out.println("  1. 查看所有员工");
        System.out.println("  2. 添加员工");
        System.out.println("  3. 修改员工");
        System.out.println("  4. 删除员工");
        System.out.println("  5. 查询员工");
        System.out.println("  6. 按姓名搜索员工");
        System.out.println("  7. 按部门查询员工");
        System.out.println("  8. 按职位查询员工");
        System.out.println("  9. 按薪资范围查询");
        System.out.println(" 10. 调整员工薪资");
        System.out.println(" 11. 转移员工部门");
        System.out.println(" 12. 统计信息");
        System.out.println("  0. 返回上级菜单");
        System.out.println("=".repeat(40));
        System.out.print("请选择操作：");
    }

    /**
     * 查看所有员工
     */
    private void listAllEmps() {
        System.out.println("\n【所有员工列表】");
        List<Emp> empList = empService.getAllEmpsWithDept();

        if (empList.isEmpty()) {
            System.out.println("暂无员工数据！");
            return;
        }

        printEmpList(empList);
    }

    /**
     * 打印员工列表
     */
    private void printEmpList(List<Emp> empList) {
        System.out.println("-".repeat(100));
        System.out.printf("%-8s %-12s %-12s %-8s %-12s %-8s %-8s %-8s %-12s%n",
                "编号", "姓名", "职位", "上级", "入职日期", "薪资", "奖金", "部门号", "部门名称");
        System.out.println("-".repeat(100));

        for (Emp emp : empList) {
            System.out.printf("%-8d %-12s %-12s %-8s %-12s %-8d %-8s %-8d %-12s%n",
                    emp.getEmpno(),
                    emp.getEname(),
                    emp.getJob(),
                    emp.getMgrName() != null ? emp.getMgrName() : (emp.getMgr() != null ? emp.getMgr().toString() : "无"),
                    emp.getHiredate(),
                    emp.getSal(),
                    emp.getComm() != null ? emp.getComm().toString() : "0",
                    emp.getDeptno(),
                    emp.getDept() != null ? emp.getDept().getDname() : "未知");
        }
        System.out.println("-".repeat(100));
    }

    /**
     * 添加员工
     */
    private void addEmp() {
        System.out.println("\n【添加员工】");

        try {
            System.out.print("请输入员工编号：");
            int empno = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("请输入员工姓名：");
            String ename = scanner.nextLine().trim();

            System.out.print("请输入职位：");
            String job = scanner.nextLine().trim();

            System.out.print("请输入上级经理编号（无则回车）：");
            String mgrStr = scanner.nextLine().trim();
            Integer mgr = mgrStr.isEmpty() ? null : Integer.parseInt(mgrStr);

            System.out.print("请输入入职日期（格式：2024-01-01）：");
            String hireDateStr = scanner.nextLine().trim();
            Date hiredate = Date.valueOf(hireDateStr);

            System.out.print("请输入薪资（必须>5000）：");
            int sal = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("请输入奖金（无则回车）：");
            String commStr = scanner.nextLine().trim();
            Integer comm = commStr.isEmpty() ? null : Integer.parseInt(commStr);

            System.out.print("请输入部门编号：");
            int deptno = Integer.parseInt(scanner.nextLine().trim());

            Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);

            if (empService.addEmp(emp)) {
                System.out.println("\n✓ 员工添加成功！");
            } else {
                System.out.println("\n✗ 员工添加失败！");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 数字格式错误！");
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ 日期格式错误，请使用 yyyy-MM-dd 格式！");
        }
    }

    /**
     * 修改员工
     */
    private void updateEmp() {
        System.out.println("\n【修改员工】");

        try {
            System.out.print("请输入要修改的员工编号：");
            int empno = Integer.parseInt(scanner.nextLine().trim());

            Emp emp = empService.getEmpByEmpno(empno);
            if (emp == null) {
                System.out.println("\n❌ 员工不存在！");
                return;
            }

            System.out.println("\n当前员工信息：" + emp);

            System.out.print("请输入新的姓名（直接回车保持不变）：");
            String ename = scanner.nextLine().trim();
            if (!ename.isEmpty()) {
                emp.setEname(ename);
            }

            System.out.print("请输入新的职位（直接回车保持不变）：");
            String job = scanner.nextLine().trim();
            if (!job.isEmpty()) {
                emp.setJob(job);
            }

            System.out.print("请输入新的薪资（直接回车保持不变）：");
            String salStr = scanner.nextLine().trim();
            if (!salStr.isEmpty()) {
                emp.setSal(Integer.parseInt(salStr));
            }

            System.out.print("请输入新的奖金（直接回车保持不变）：");
            String commStr = scanner.nextLine().trim();
            if (!commStr.isEmpty()) {
                emp.setComm(Integer.parseInt(commStr));
            }

            if (empService.modifyEmp(emp)) {
                System.out.println("\n✓ 员工修改成功！");
            } else {
                System.out.println("\n✗ 员工修改失败！");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 删除员工
     */
    private void deleteEmp() {
        System.out.println("\n【删除员工】");

        try {
            System.out.print("请输入要删除的员工编号：");
            int empno = Integer.parseInt(scanner.nextLine().trim());

            Emp emp = empService.getEmpByEmpno(empno);
            if (emp == null) {
                System.out.println("\n❌ 员工不存在！");
                return;
            }

            System.out.println("\n确认删除以下员工？");
            System.out.println(emp);
            System.out.print("输入 Y 确认删除：");
            String confirm = scanner.nextLine().trim();

            if (confirm.equalsIgnoreCase("Y")) {
                if (empService.removeEmp(empno)) {
                    System.out.println("\n✓ 员工删除成功！");
                } else {
                    System.out.println("\n✗ 员工删除失败！");
                }
            } else {
                System.out.println("\n已取消删除操作。");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 查询员工
     */
    private void queryEmp() {
        System.out.println("\n【查询员工】");

        try {
            System.out.print("请输入员工编号：");
            int empno = Integer.parseInt(scanner.nextLine().trim());

            Emp emp = empService.getEmpByEmpno(empno);
            if (emp != null) {
                System.out.println("\n查询结果：");
                System.out.println(emp);
            } else {
                System.out.println("\n❌ 未找到该员工！");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 按姓名搜索员工
     */
    private void searchEmpByName() {
        System.out.println("\n【按姓名搜索员工】");

        System.out.print("请输入员工姓名关键字：");
        String keyword = scanner.nextLine().trim();

        List<Emp> empList = empService.searchEmpsByName(keyword);

        if (empList.isEmpty()) {
            System.out.println("\n未找到匹配的员工！");
            return;
        }

        System.out.println("\n搜索结果：");
        printEmpList(empList);
    }

    /**
     * 按部门查询员工
     */
    private void queryEmpsByDept() {
        System.out.println("\n【按部门查询员工】");

        try {
            System.out.print("请输入部门编号：");
            int deptno = Integer.parseInt(scanner.nextLine().trim());

            List<Emp> empList = empService.getEmpsByDeptno(deptno);

            if (empList.isEmpty()) {
                System.out.println("\n该部门暂无员工！");
                return;
            }

            System.out.println("\n查询结果：");
            printEmpList(empList);

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 按职位查询员工
     */
    private void queryEmpsByJob() {
        System.out.println("\n【按职位查询员工】");

        System.out.print("请输入职位：");
        String job = scanner.nextLine().trim();

        List<Emp> empList = empService.getEmpsByJob(job);

        if (empList.isEmpty()) {
            System.out.println("\n未找到该职位的员工！");
            return;
        }

        System.out.println("\n查询结果：");
        printEmpList(empList);
    }

    /**
     * 按薪资范围查询
     */
    private void queryEmpsBySalary() {
        System.out.println("\n【按薪资范围查询】");

        try {
            System.out.print("请输入最低薪资：");
            int minSal = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("请输入最高薪资：");
            int maxSal = Integer.parseInt(scanner.nextLine().trim());

            List<Emp> empList = empService.getEmpsBySalRange(minSal, maxSal);

            if (empList == null || empList.isEmpty()) {
                System.out.println("\n未找到符合条件的员工！");
                return;
            }

            System.out.println("\n查询结果：");
            printEmpList(empList);

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 调整员工薪资
     */
    private void adjustSalary() {
        System.out.println("\n【调整员工薪资】");

        try {
            System.out.print("请输入员工编号：");
            int empno = Integer.parseInt(scanner.nextLine().trim());

            Emp emp = empService.getEmpByEmpno(empno);
            if (emp == null) {
                System.out.println("\n❌ 员工不存在！");
                return;
            }

            System.out.println("当前薪资：" + emp.getSal());

            System.out.print("请输入新薪资（必须>5000）：");
            int newSal = Integer.parseInt(scanner.nextLine().trim());

            if (empService.adjustSalary(empno, newSal)) {
                System.out.println("\n✓ 薪资调整成功！");
            } else {
                System.out.println("\n✗ 薪资调整失败！");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 转移员工部门
     */
    private void transferDept() {
        System.out.println("\n【转移员工部门】");

        try {
            System.out.print("请输入员工编号：");
            int empno = Integer.parseInt(scanner.nextLine().trim());

            Emp emp = empService.getEmpByEmpno(empno);
            if (emp == null) {
                System.out.println("\n❌ 员工不存在！");
                return;
            }

            System.out.println("当前部门：" + emp.getDeptno());

            System.out.print("请输入新部门编号：");
            int newDeptno = Integer.parseInt(scanner.nextLine().trim());

            if (empService.transferDept(empno, newDeptno)) {
                System.out.println("\n✓ 部门转移成功！");
            } else {
                System.out.println("\n✗ 部门转移失败！");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }

    /**
     * 显示统计信息
     */
    private void showStatistics() {
        System.out.println("\n【统计信息】");

        try {
            System.out.print("请输入部门编号：");
            int deptno = Integer.parseInt(scanner.nextLine().trim());

            int count = empService.countEmpsByDept(deptno);
            double avgSal = empService.getAvgSalByDept(deptno);

            System.out.println("\n统计结果：");
            System.out.println("-".repeat(40));
            System.out.println("部门编号：" + deptno);
            System.out.println("员工数量：" + count + " 人");
            System.out.println("平均薪资：" + String.format("%.2f", avgSal) + " 元");
            System.out.println("-".repeat(40));

        } catch (NumberFormatException e) {
            System.out.println("\n❌ 输入格式错误！");
        }
    }
}