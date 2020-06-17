package web.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.*;


@RestController
@RequestMapping("/rest")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers() {
        Gson gson = new Gson();
        String json = gson.toJson(userService.getAllUsers());
        return json;
    }

    @PostMapping("admin/edit")
    public User editUser(String user, String roleIds) {
        User userFromJson = getUser(user, roleIds);
        userService.update(userFromJson);
        return userFromJson;
    }

    @PostMapping("admin/delete")
    public String deleteUser(String id){
        userService.deleteUser(Long.parseLong(id));
        return id;
    }

    @PostMapping("admin/add")
    public User addUser(String user, String roleIds){
        User userFromJson = getUser(user, roleIds);
        userService.insert(userFromJson);
        return userFromJson;
    }

    private User getUser(String user, String roleIds) {
        Gson gson = new Gson();
        Set<String> a;
        User userFromJson = gson.fromJson(user, User.class);
        List<String> roleIdsFromJson = gson.fromJson(roleIds, ArrayList.class);
        String[] roleIdsFromList = new String[roleIdsFromJson.size()];
        for (int i=0; i<roleIdsFromList.length; i++) {
            roleIdsFromList[i] = roleIdsFromJson.get(i);
        }
        userFromJson.setRole(userService.getRoles(roleIdsFromList));
        return userFromJson;
    }
}
