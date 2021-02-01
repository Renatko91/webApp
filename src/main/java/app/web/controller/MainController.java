package app.web.controller;

import app.web.repo.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final WorkerRepo workerRepo;

    @Autowired
    public MainController(WorkerRepo workerRepo) {
        this.workerRepo = workerRepo;
    }

    @GetMapping
    public String main(Model model) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("workers", workerRepo.findAll());

        model.addAttribute("frontendData", data);

        return "index";
    }
}
