package app.web.controller;

import app.web.domain.Worker;
import app.web.repo.WorkerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("worker")
public class WorkerController {

    private final WorkerRepo workerRepo;

    @Autowired
    public WorkerController(WorkerRepo workerRepo) {
        this.workerRepo = workerRepo;
    }

    @GetMapping()
    public List<Worker> list() {
        return (List<Worker>) workerRepo.findAll();
    }

    @GetMapping("{id}")
    public Worker getOne(@PathVariable("id") Worker worker) {
        return worker;
    }

    @PostMapping()
    public Worker create(@RequestBody Worker worker) {
        return workerRepo.save(worker);
    }

    @PutMapping("{id}")
    public Worker update(@PathVariable ("id") Worker workerFromDb,
                         @RequestBody Worker worker) {
        BeanUtils.copyProperties(worker, workerFromDb,"id");

        return workerRepo.save(workerFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable ("id") Worker worker) {
        workerRepo.delete(worker);
    }
}