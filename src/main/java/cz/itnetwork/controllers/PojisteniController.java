package cz.itnetwork.controllers;

import cz.itnetwork.models.Pojisteni;
import cz.itnetwork.models.SpravcePojisteni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PojisteniController {

    @Autowired
    private SpravcePojisteni spravcePojisteni;

    // READ načtení seznamu všech pojištění
    @GetMapping("/pojisteni")
    public String vypisVsechnaPojisteni(Model model) {
        model.addAttribute("pojisteni", spravcePojisteni.readSeznamPojisteni());
        return "pojisteni";
    }

    // CREATE vytvoření nového pojištění - uložení
    @PostMapping("/pojistenci/detail-pojistence/nove-pojisteni/{osoba_id}/save")
    public String novePojisteni(@ModelAttribute("pojisteni") Pojisteni pojisteni) {
        spravcePojisteni.insertPojisteni(pojisteni);
        return "redirect:/pojistenci/detail-pojistence/" + pojisteni.getOsoba_id();
    }

    // DELETE smazání pojištění
    @PostMapping("pojisteni/delete/{pojisteni_id}")
    public String smazatPojisteni(@PathVariable(name = "pojisteni_id") int pojisteni_id) {
        System.out.println("ID: " + pojisteni_id);
        Pojisteni pojisteni = spravcePojisteni.selectPojisteni(pojisteni_id);
        spravcePojisteni.deletePojisteni(pojisteni_id);
        return "redirect:/pojistenci/detail-pojistence/" + pojisteni.getOsoba_id();
    }

    // UPDATE edit pojištění
    @GetMapping("pojisteni/update/{pojisteni_id}")
    public ModelAndView upravaPojisteni(@PathVariable(name = "pojisteni_id") int pojisteni_id) {
        ModelAndView updateView = new ModelAndView("update-pojisteni");
        Pojisteni pojisteni = spravcePojisteni.selectPojisteni(pojisteni_id);
        updateView.addObject("pojisteni", pojisteni);
        return updateView;
    }

    // UPDATE edit pojištění - uložení
    @PostMapping("pojisteni/update/{pojisteni_id}/save")
    public String upravaPojisteniSave(@ModelAttribute("pojisteni") Pojisteni pojisteni) {
        spravcePojisteni.updatePojisteni(pojisteni);
        return "redirect:/pojistenci/detail-pojistence/detail-pojisteni/" + pojisteni.getPojisteni_id();
    }

}
