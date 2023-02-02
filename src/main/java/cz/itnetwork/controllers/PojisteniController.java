package cz.itnetwork.controllers;

import cz.itnetwork.models.Osoba;
import cz.itnetwork.models.Pojisteni;
import cz.itnetwork.models.SpravcePojisteni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pojisteni")
public class PojisteniController {

    @Autowired
    private SpravcePojisteni spravcePojisteni;

    // READ
    @GetMapping
    public String vypisPojisteni(Model model) {
        model.addAttribute("druh_pojisteni", spravcePojisteni.readSeznamPojisteni());
        return "pojisteni";
    }

    // CREATE
    @GetMapping("/nove-pojisteni")
    public String renderNovePojisteni(Model model) {
        model.addAttribute("pojisteni", new Pojisteni());
        return "nove-pojisteni";
    }

    // CREATE
    @PostMapping("/nove-pojisteni/save")
    public String novePojisteni(@ModelAttribute("pojisteni") Pojisteni pojisteni) {
        spravcePojisteni.insertPojisteni(pojisteni);
        return "redirect:/pojisteni";
    }

    // DELETE
    @PostMapping("/delete/{pojisteni_id}")
    public String smazatPojisteni(@PathVariable(name = "pojisteni_id") int pojisteni_id) {
        System.out.println("ID: " + pojisteni_id);
        spravcePojisteni.deletePojisteni(pojisteni_id);
        return "redirect:/pojisteni";
    }

    // UPDATE
    @GetMapping("/update/{pojisteni_id}")
    public ModelAndView upravaPojisteni(@PathVariable(name = "pojisteni_id") int pojisteni_id) {
        ModelAndView updateView = new ModelAndView("update-pojisteni");
        Pojisteni pojisteni = spravcePojisteni.selectPojisteni(pojisteni_id);
        updateView.addObject("pojisteni", pojisteni);
        return updateView;
    }

    // UPDATE
    @PostMapping("/update/{pojisteni_id}/save")
    public String upravaPojisteniSave(@ModelAttribute("pojisteni") Pojisteni pojisteni) {
        spravcePojisteni.updatePojisteni(pojisteni);
        return "redirect:/pojisteni";
    }

}
