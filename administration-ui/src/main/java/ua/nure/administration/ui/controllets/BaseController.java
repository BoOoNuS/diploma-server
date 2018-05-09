package ua.nure.administration.ui.controllets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import ua.nure.administration.ui.client.HeatingService;
import ua.nure.administration.ui.client.PermissionService;
import ua.nure.administration.ui.client.UserService;
import ua.nure.administration.ui.model.Heating;
import ua.nure.administration.ui.model.HeatingUsersInfo;
import ua.nure.administration.ui.model.User;
import ua.nure.administration.ui.model.UserRole;

/**
 * @author Stanislav_Vorozhka
 */
@Controller
public class BaseController {

    private HeatingService heatingService;
    private UserService userService;
    private PermissionService permissionService;

    @Autowired
    public BaseController(HeatingService heatingService,
                          UserService userService,
                          PermissionService permissionService) {
        this.heatingService = heatingService;
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView heatings(@SessionAttribute(required = false) User user) {
        ModelAndView mav = new ModelAndView("common/heatings");
        List<HeatingUsersInfo> heatingUsersInfo = Collections.emptyList();
        if (Objects.nonNull(user) && user.getRole() == UserRole.ADMIN && !user.isBlocked()) {
            heatingUsersInfo = heatingService.getAllHeatingUsersInfo();
        } else if (Objects.nonNull(user) && !user.isBlocked()) {
            heatingUsersInfo = heatingService.getHeatingInfoWithoutAssignedUsersByUserId(user.getId());
        }
        mav.addObject("heatingUsersInfo", heatingUsersInfo);
        return mav;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView uesrs() {
        ModelAndView mav = new ModelAndView("admin/users");
        List<User> users = userService.findUsers();
        mav.addObject("users", users);
        return mav;
    }

    @RequestMapping(value = "/unassign/user", method = RequestMethod.POST)
    public String unassignUsers(@RequestParam(required = false) List<Long> userIds, @RequestParam long heatingId) {
        if (!CollectionUtils.isEmpty(userIds)) {
            permissionService.takeAwayHeatingPermissionsFromUsers(heatingId, userIds);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/assign/user", method = RequestMethod.GET)
    public ModelAndView toAssignUsers(@RequestParam long heatingId) {
        ModelAndView mav = new ModelAndView("admin/assign-users");
        mav.addObject("heatingId", heatingId);
        return mav;
    }

    @RequestMapping(value = "/assign/user", method = RequestMethod.POST)
    public String assignUsers(@RequestParam long heatingId, @RequestParam long userId) {
        permissionService.giveHeatingPermissionsToUsers(heatingId, Collections.singletonList(userId));
        return "redirect:/";
    }

    @RequestMapping(value = "/create/heating", method = RequestMethod.GET)
    public String toCreateHeating() {
        return "admin/create-heating";
    }

    @RequestMapping(value = "/create/heating", method = RequestMethod.POST)
    public String createHeating(@RequestParam long heatingId, @RequestParam String host,
                                @RequestParam Integer port, @RequestParam String description) {
        Heating heating = new Heating(heatingId, host, port, description);
        heatingService.createHeating(heating);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/heating", method = RequestMethod.GET)
    public ModelAndView toEditHeating(@RequestParam long heatingId) {
        ModelAndView mav = new ModelAndView("admin/edit-heating");
        Heating heating = heatingService.findHeatingById(heatingId);
        mav.addObject("heating", heating);
        return mav;
    }

    @RequestMapping(value = "/edit/heating", method = RequestMethod.POST)
    public String editHeating(@RequestParam long heatingId, @RequestParam String host,
                              @RequestParam int port, @RequestParam String description) {
        Heating heating = new Heating(heatingId, host, port, description);
        heatingService.updateHeating(heating);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/heating/confirmation", method = RequestMethod.GET)
    public ModelAndView toDeleteHeatingConfirmation(@RequestParam long heatingId) {
        ModelAndView mav = new ModelAndView("common/delete-heating-confirm");
        mav.addObject("heatingId", heatingId);
        return mav;
    }

    @RequestMapping(value = "/delete/heating/confirmation", method = RequestMethod.POST)
    public String deleteHeatingConfirmation(@SessionAttribute(required = false) User user, @RequestParam long heatingId) {
        permissionService.takeAwayHeatingPermissionsFromUsers(heatingId, Collections.singletonList(user.getId()));
        return "redirect:/";
    }

    @RequestMapping(value = "/create/user", method = RequestMethod.GET)
    public String toCreateUser() {
        return "admin/create-user";
    }

    @RequestMapping(value = "/create/user", method = RequestMethod.POST)
    public String createUser(@RequestParam String login, @RequestParam String password,
                             @RequestParam String fullName, @RequestParam UserRole role,
                             @RequestParam(required = false) boolean blocked) {
        User user = new User(login, password, fullName, role, blocked);
        userService.createUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/change/user/permission", method = RequestMethod.POST)
    public String changeUser(@RequestParam long userId, @RequestParam String login,
                             @RequestParam String password, @RequestParam String fullName,
                             @RequestParam UserRole role, @RequestParam(defaultValue = "false") boolean blocked) {
        User user = new User(login, password, fullName, role, blocked);
        user.setId(userId);
        userService.editUserById(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/find/heating", method = RequestMethod.GET)
    public ModelAndView getHeatingsByDescription(@SessionAttribute(required = false) User user,
                                                 @RequestParam("description") String description) {
        ModelAndView mav = new ModelAndView("common/heatings");
        List<HeatingUsersInfo> heatingUsersInfo = Collections.emptyList();
        if (Objects.nonNull(user) && user.getRole() == UserRole.ADMIN && !user.isBlocked()) {
            heatingUsersInfo = heatingService.getHeatingUserInfoByDescription(description);
        } else if (Objects.nonNull(user) && !user.isBlocked()) {
            heatingUsersInfo = heatingService.getHeatingUserInfoByUserIdAndDescription(user.getId(), description);
        }
        mav.addObject("heatingUsersInfo", heatingUsersInfo);
        return mav;
    }

    @RequestMapping(value = "/find/user", method = RequestMethod.GET)
    public ModelAndView getUsersByFullName(String fullName) {
        ModelAndView mav = new ModelAndView("admin/users");
        List<User> users = userService.getUsersByFullName(fullName);
        mav.addObject("users", users);
        return mav;
    }
}
