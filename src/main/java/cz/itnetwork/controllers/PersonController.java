package cz.itnetwork.controllers;

import cz.itnetwork.models.Person;
import cz.itnetwork.models.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/insured-persons")
public class PersonController {

    @Autowired
    private PersonManager personManager;

    // READ shows list of all insured persons
    @GetMapping
    public String showAllPersons(Model model) {
        model.addAttribute("persons", personManager.selectListOfPersons());
        return "insured-persons";
    }

    // CREATE adds new insured person
    @GetMapping("/add-person")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }

    // CREATE adds new insured person - save
    @PostMapping("/add-person/save")
    public String addPersonSave(@ModelAttribute("person") Person person) {
        personManager.insertPerson(person);
        return "redirect:/insured-persons";
    }

    // DELETE deletes person and all of his insurances (ON DELETE CASCADE - MySQL)
    @PostMapping("/delete/{personId}")
    public String deletePerson(@PathVariable(name = "personId") int personId) {
        System.out.println("ID: " + personId);
        personManager.deletePerson(personId);
        return "redirect:/insured-persons";
    }

    // UPDATE edits personal details
    @GetMapping("/edit/{personId}")
    public ModelAndView editPerson(@PathVariable(name = "personId") int personId) {
        ModelAndView updateView = new ModelAndView("edit-person");
        Person person = personManager.selectPerson(personId);
        updateView.addObject("person", person);
        return updateView;
    }

    // UPDATE edit personal details - save
    @PostMapping("/edit/{personId}/save")
    public String editPersonSave(@ModelAttribute("person") Person person) {
        personManager.updatePerson(person);
        return "redirect:/insured-persons/personal-details/" + person.getPersonId();
    }

}
