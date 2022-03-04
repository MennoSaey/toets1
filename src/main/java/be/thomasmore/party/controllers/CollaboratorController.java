package be.thomasmore.party.controllers;


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
    @GetMapping({"/collaborators/{id}", "/collaborators"})
    public String collaborators(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "collaborators";
        Optional<Collaborator> collaboratorFromDb = collaboratorRepository.findById(id);
        if (collaboratorFromDb.isPresent()) {
            model.addAttribute("collaborater", collaboratorFromDb.get());
        }
        return "venuedetails";
    }

    public void addSalary(Model model, Collaborator collaborator){
        final Iterable<Collaborator> AllCollaborators = collaboratorRepository.findAll();
        int totalsalary = collaborator.getSalary();
        int nrcollaborators = collaborator.getId();
        model.addAttribute("totalsalary",totalsalary);
        model.addAttribute("nrcollaborators",nrcollaborators);
    }
    //oefeing 5 (stond al in commit genaamd "oefening 4 versie 2")
    @GetMapping({"/collaborator/role/{role}"})
    public String collaboratorsRole(Model model,
                                  @PathVariable String filter) {
        Iterable<Collaborator> Rolecollaborators = collaboratorRepository.findAllByRoleEquals(filter);
        model.addAttribute("collaborators", Rolecollaborators);
        return "collaborators";
    }
}
