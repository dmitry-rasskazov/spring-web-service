package rasskazov.laba.springwebservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rasskazov.laba.springwebservice.Dto.UserDto;
import rasskazov.laba.springwebservice.Entity.User;
import rasskazov.laba.springwebservice.Service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class SecurityController
{
    private final UserService userService;

    @Autowired
    public SecurityController(@NotNull  UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/intex")
    public String home()
    {
        return "index";
    }

    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model
    ) {
        User findUser = userService.findUserByEmail(userDto.getEmail());

        if(null != findUser && null != findUser.getEmail() && !findUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Адрес электронной почты уже используется");
        }

        if(result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);

        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model)
    {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "/users";
    }
}
