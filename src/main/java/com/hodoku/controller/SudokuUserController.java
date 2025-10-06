package com.hodoku.controller;

import com.hodoku.dto.SudokuUser;
import com.hodoku.dto.SudokuUserRequest;
import com.hodoku.service.SudokuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数独用户控制器
 */
@RestController
@RequestMapping("/api/users")
public class SudokuUserController {

    @Autowired
    private SudokuUserService sudokuUserService;

    /**
     * 根据openId查询用户
     */
    @GetMapping("/openid/{openId}")
    public ResponseEntity<SudokuUser> getUserByOpenId(@PathVariable String openId) {
        SudokuUser user = sudokuUserService.getUserByOpenId(openId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * 根据平台查询用户列表
     */
    @GetMapping("/platform/{platform}")
    public ResponseEntity<List<SudokuUser>> getUsersByPlatform(@PathVariable String platform) {
        List<SudokuUser> users = sudokuUserService.getUsersByPlatform(platform);
        return ResponseEntity.ok(users);
    }

    /**
     * 用户名模糊搜索
     */
    @GetMapping("/search")
    public ResponseEntity<List<SudokuUser>> searchUsers(@RequestParam String userName) {
        List<SudokuUser> users = sudokuUserService.searchUsersByName(userName);
        return ResponseEntity.ok(users);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResponseEntity<SudokuUser> userLogin(@RequestHeader Map<String, String> headers, @RequestBody SudokuUserRequest request) {
        String openId = headers.get("x-wx-openid");
        String platform = headers.get("x-wx-platform");
        String loginIp = headers.get("x-real-ip");

        SudokuUser user = sudokuUserService.registerOrLogin(request,openId, platform, loginIp);
        return ResponseEntity.ok(user);
    }

    /**
     * 获取平台用户统计
     */
    @GetMapping("/stats/platform")
    public ResponseEntity<Map<String, Long>> getPlatformStats() {
        Map<String, Long> stats = new HashMap<>();

        // 这里可以根据实际需要添加更多平台
        String[] platforms = {"微信小程序", "APP", "网页版", "移动端"};

        for (String platform : platforms) {
            long count = sudokuUserService.getUserCountByPlatform(platform);
            if (count > 0) {
                stats.put(platform, count);
            }
        }

        stats.put("总用户数", sudokuUserService.getTotalUserCount());

        return ResponseEntity.ok(stats);
    }
}
