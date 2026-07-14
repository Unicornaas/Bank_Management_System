package edu.fjut.mybatis.controller;

import edu.fjut.mybatis.entity.Buss;
import edu.fjut.mybatis.entity.UserVip;
import edu.fjut.mybatis.service.BranchService;
import edu.fjut.mybatis.service.BussService;
import edu.fjut.mybatis.service.EmployeeService;
import edu.fjut.mybatis.service.UserService;
import edu.fjut.mybatis.service.UserVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private BussService bussService;

    @Autowired
    private UserVipService userVipService;

    /**
     * 仪表盘统计数据
     */
    @GetMapping("/dashboard/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);

        // 基础统计
        result.put("userCount", userService.count());
        result.put("employeeCount", employeeService.count());
        result.put("branchCount", branchService.count());

        // 计算总存款额
        double totalMoney = userService.findAll().stream()
                .mapToDouble(u -> u.getMoney() != null ? u.getMoney() : 0)
                .sum();
        result.put("totalMoney", totalMoney);

        // 交易统计
        List<Buss> allBills = bussService.findAll();
        result.put("billsCount", allBills.size());

        // 计算今日交易额
        double todayAmount = allBills.stream()
                .filter(b -> {
                    if (b.getCreateTime() == null) return false;
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(b.getCreateTime());
                    Calendar today = Calendar.getInstance();
                    return cal.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                            && cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR);
                })
                .mapToDouble(b -> b.getAmount() != null ? b.getAmount() : 0)
                .sum();
        result.put("todayAmount", todayAmount);

        return result;
    }

    /**
     * 近7日交易趋势（模拟数据 + 真实数据混合）
     */
    @GetMapping("/dashboard/trend")
    public Map<String, Object> getTrend() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);

        List<String> days = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        Random rand = new Random();

        // 获取所有交易流水，按天聚合
        List<Buss> allBills = bussService.findAll();
        Map<String, double[]> dayMap = new LinkedHashMap<>();

        // 初始化最近7天
        for (int i = 6; i >= 0; i--) {
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, -i);
            String key = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH);
            dayMap.put(key, new double[]{0, 0}); // [amount, count]
        }

        // 聚合真实流水数据
        for (Buss b : allBills) {
            if (b.getCreateTime() != null && b.getAmount() != null) {
                cal.setTime(b.getCreateTime());
                String key = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH);
                if (dayMap.containsKey(key)) {
                    double[] arr = dayMap.get(key);
                    arr[0] += b.getAmount();
                    arr[1] += 1;
                }
            }
        }

        // 如果没有真实数据，生成演示数据
        boolean hasRealData = allBills.stream().anyMatch(b -> {
            if (b.getCreateTime() == null) return false;
            Calendar c = Calendar.getInstance();
            c.setTime(b.getCreateTime());
            Calendar now = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 7);
            return c.after(now);
        });

        for (Map.Entry<String, double[]> entry : dayMap.entrySet()) {
            days.add(entry.getKey());
            double amt = entry.getValue()[0];
            int cnt = (int) entry.getValue()[1];

            if (!hasRealData && amt == 0) {
                // 生成演示数据（单位：万元）
                amt = (rand.nextInt(60) + 30) * 10000;
                cnt = rand.nextInt(50) + 10;
            }

            amounts.add(Math.round(amt / 10000.0 * 100.0) / 100.0); // 转为万元
            counts.add(cnt);
        }

        result.put("days", days);
        result.put("amounts", amounts);  // 交易额(万元)
        result.put("counts", counts);    // 交易笔数

        return result;
    }

    /**
     * 存款类型分布
     */
    @GetMapping("/dashboard/depositTypes")
    public Map<String, Object> getDepositTypes() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);

        // 获取VIP等级定义（含阈值和颜色）
        List<UserVip> vipLevels = userVipService.findAll();
        List<edu.fjut.mybatis.entity.User> users = userService.findAll();

        // 按VIP等级阈值分档统计
        double[] amounts = new double[vipLevels.size()];
        int[] counts = new int[vipLevels.size()];

        for (edu.fjut.mybatis.entity.User u : users) {
            double m = u.getMoney() != null ? u.getMoney() : 0;
            UserVip level = userVipService.getLevelByBalance(m);
            if (level != null) {
                int idx = level.getSortOrder() - 1; // sort_order从1开始
                if (idx >= 0 && idx < vipLevels.size()) {
                    amounts[idx] += m;
                    counts[idx]++;
                }
            }
        }

        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < vipLevels.size(); i++) {
            UserVip v = vipLevels.get(i);
            String label = v.getLevelName();
            // 简洁标签
            if (i == 0) label = v.getLevelName() + "(<1万)";
            else label = v.getLevelName() + "(" + (v.getMinBalance().intValue() / 10000) + "万+)";
            data.add(createPieItem(label, amounts[i], counts[i], v.getColor()));
        }

        result.put("data", data);
        return result;
    }

    /**
     * 各类存款指标（进度条）
     */
    @GetMapping("/dashboard/depositProgress")
    public Map<String, Object> getDepositProgress() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);

        List<UserVip> vipLevels = userVipService.findAll();
        List<edu.fjut.mybatis.entity.User> users = userService.findAll();
        double totalMoney = users.stream().mapToDouble(u -> u.getMoney() != null ? u.getMoney() : 0).sum();
        double target = Math.max(totalMoney, 1000000);

        double[] amounts = new double[vipLevels.size()];
        for (edu.fjut.mybatis.entity.User u : users) {
            double m = u.getMoney() != null ? u.getMoney() : 0;
            UserVip level = userVipService.getLevelByBalance(m);
            if (level != null) {
                int idx = level.getSortOrder() - 1;
                if (idx >= 0 && idx < vipLevels.size()) {
                    amounts[idx] += m;
                }
            }
        }

        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < vipLevels.size(); i++) {
            UserVip v = vipLevels.get(i);
            data.add(createProgressItem(v.getLevelName() + "存款", amounts[i], target, v.getColor()));
        }

        result.put("data", data);
        return result;
    }

    private Map<String, Object> createPieItem(String name, double amount, int count, String color) {
        Map<String, Object> item = new HashMap<>();
        item.put("name", name);
        item.put("value", Math.round(amount / 10000.0 * 100.0) / 100.0); // 万元
        item.put("count", count);
        item.put("itemStyle", Collections.singletonMap("color", color));
        return item;
    }

    private Map<String, Object> createProgressItem(String name, double amount, double target, String color) {
        Map<String, Object> item = new HashMap<>();
        item.put("name", name);
        item.put("amount", Math.round(amount / 10000.0 * 100.0) / 100.0);
        item.put("pct", target > 0 ? Math.min(100, Math.round(amount / target * 100)) : 0);
        item.put("color", color);
        return item;
    }
}
