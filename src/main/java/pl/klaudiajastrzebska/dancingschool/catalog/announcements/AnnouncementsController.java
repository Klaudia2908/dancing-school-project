package pl.klaudiajastrzebska.dancingschool.catalog.announcements;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AnnouncementsController {

    @GetMapping("/announcements")
    String getAnnouncementsMainView(){
        return "catalog/announcements/main";
    }
}
