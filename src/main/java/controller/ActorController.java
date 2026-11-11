
package com.craft.actormanagement.controller;

import com.craft.actormanagement.model.Actor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

    private final List<Actor> actors = new ArrayList<>();

    public ActorController() {
        actors.add(new Actor(1, "John Doe", "The Great Adventure"));
        actors.add(new Actor(2, "Jane Smith", "Mystery Island"));
    }

    // READ - Get all actors
    @GetMapping("/actor")
    public List<Actor> getActorDetails() {
        return actors;
    }

    // READ - Get actor by ID
    @GetMapping("/actor/{id}")
    public Actor getActorById(@PathVariable int id) {
        for (Actor actor : actors) {
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;
    }

    // CREATE - Add a new actor
    @PostMapping("/actor")
    public Actor createActor(@RequestBody Actor actor) {
        actors.add(actor);
        return actor;
    }

    // UPDATE - Update an existing actor
    @PutMapping("/actor/{id}")
    public Actor updateActor(@PathVariable int id, @RequestBody Actor updatedActor) {
        for (Actor actor : actors) {
            if (actor.getId() == id) {
                actor.setName(updatedActor.getName());
                actor.setMovieName(updatedActor.getMovieName());
                return actor;
            }
        }
        return null;
    }

    // DELETE - Delete an actor by ID
    @DeleteMapping("/actor/{id}")
    public String deleteActorById(@PathVariable int id) {
        boolean removed = actors.removeIf(actor -> actor.getId() == id);

        if (removed) {
            return "Actor deleted successfully";
        }

        return "Actor not found";
    }
}
