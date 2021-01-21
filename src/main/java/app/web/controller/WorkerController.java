package app.web.controller;

import app.web.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("worker")
public class WorkerController {
    private int count = 1;

    List<Map<String, String>> workers = new ArrayList<>();

    @GetMapping()
    public List<Map<String, String>> list() {
        return workers;
    }

    @GetMapping("{id}")
    public Map<String, String> getWorker(@PathVariable String id) {
        return findWorker(id);
    }

    private Map<String, String> findWorker(@PathVariable String id) {
        return workers.stream()
                .filter(worker -> worker.get("id").equals(id))
                .findFirst().orElseThrow(NotFoundException::new);
    }

    @PostMapping()
    public Map<String, String> create(@RequestBody Map<String, String> worker) {
        count = 1;
        Collections.sort(workers, Comparator.comparingInt(a -> Integer.parseInt(a.get("id"))));
        for (Map<String, String> workerTwo : workers) {
            if (workerTwo.get("id").equals(String.valueOf(count))) {
                count++;
            } else {
                break;
            }
        }
        worker.put("id", String.valueOf(count));

        workers.add(worker);

        return worker;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id,
                                      @RequestBody Map<String, String> worker) {
        Map<String, String> workerFromDb = findWorker(id);

        workerFromDb.putAll(worker);
        workerFromDb.put("id", id);

        return workerFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        workers.remove(findWorker(id));
    }
}