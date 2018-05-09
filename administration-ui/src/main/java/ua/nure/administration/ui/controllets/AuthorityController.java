package ua.nure.administration.ui.controllets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nure.administration.ui.client.UserService;
import ua.nure.administration.ui.model.User;
import ua.nure.administration.ui.model.UserRole;

import javax.servlet.http.HttpSession;

/**
 * @author Stanislav_Vorozhka
 */
@Controller
public class AuthorityController {

    private UserService userService;

    @Autowired
    public AuthorityController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    public String authentication() {
        return "common/authentication";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "common/registration";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public String signIn(HttpSession session, @RequestParam String login, @RequestParam String password) {
        User foundUser = userService.findUserByLoginAndPassword(login, password);
        session.setAttribute("user", foundUser);
        return "redirect:/";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String signUp(@RequestParam String login, @RequestParam String password, @RequestParam String fullName) {
        User user = new User(login, password, fullName, UserRole.USER, false);
        userService.createUser(user);
        return "redirect:/authentication";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/authentication";
    }
}
