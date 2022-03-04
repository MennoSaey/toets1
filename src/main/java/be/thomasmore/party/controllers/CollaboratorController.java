package be.thomasmore.party.controllers;


import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Collaborator;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CollaboratorController {
    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @GetMapping("/collaborators")
    public String collaborator(Model model) {
        final Iterable<Collaborator> AllCollaborators = collaboratorRepository.findAll();
        model.addAttribute("collaborators", AllCollaborators);
        return "collaborators";
    }

    @GetMapping({"/collaborator/{id}", "/collaborator"})
    public String collaboratorDetails(Model model,
                                      @PathVariable(required = false) Integer id) {
        if (id == null) return "collaborator";

        Optional<Collaborator> optionalCollaborator = collaboratorRepository.findById(id);
        //noinspection OptionalIsPresent
        if (optionalCollaborator.isPresent()) {
            model.addAttribute("collaborator", optionalCollaborator.get());
        }
        return "collaborator";
    }

    @GetMapping({"/collaborator/role/{role}"})
    public String collaboratorsRole(Model model,
                                  @PathVariable String role) {
        Iterable<Collaborator> collaborators = collaboratorRepository.findAllByRoleEquals(role);
        model.addAttribute("collaborators", collaborators);
        return "collaborators";
    }
}
