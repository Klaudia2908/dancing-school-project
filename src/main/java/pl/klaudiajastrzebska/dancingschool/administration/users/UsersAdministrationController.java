package pl.klaudiajastrzebska.dancingschool.administration.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/administration")
class UsersAdministrationController {
    private final CatalogApi catalogApi;

    @GetMapping("/users")
    String manageUsersScreen(Model model) {
        model.addAttribute("users", catalogApi.getAllUsers());

        return "/administration/users/main";
    }

    @GetMapping("/users/delete/{userLogin}")
    String deleteUser(@PathVariable String userLogin) {
        catalogApi.deleteUser(userLogin);

        return "redirect:/administration/users";
    }
}
