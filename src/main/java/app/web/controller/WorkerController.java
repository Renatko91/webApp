package app.web.controller;

import app.web.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("worker")
public class WorkerController {
    private int count = 4;

    List<Map<String, String>> workers = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "Andrey");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Sasha");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Sergey");
        }});
    }};

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
                .findAny().orElseThrow(NotFoundException::new);
    }

    @PostMapping()
    public Map<String, String> create(@RequestBody Map<String, String> worker) {
        worker.put("id", String.valueOf(count++));

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