package grain.controller.users;

import grain.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @RequestMapping("login")
    public String login(String username) {
        return usersService.login(username);
    }

    @RequestMapping("transData")
    public String transData(@RequestBody String json) {
        return json;
    }
}
