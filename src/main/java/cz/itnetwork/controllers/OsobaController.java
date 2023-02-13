package cz.itnetwork.controllers;

import cz.itnetwork.models.Osoba;
import cz.itnetwork.models.SpravceOsob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pojistenci")
public class OsobaController {

    @Autowired
    private SpravceOsob spravceOsob;

    // READ načtení seznamu pojištěnců
    @GetMapping
    public String vypisPojistence(Model model) {
        model.addAttribute("osoby", spravceOsob.readSeznamOsob());
        return "pojistenci";
    }

    // CREATE vytvoření nového pojištěnce
    @GetMapping("/novy-pojistenec")
    public String renderNovyPojistenec(Model model) {
        model.addAttribute("osoba", new Osoba());
        return "novy-pojistenec";
    }

    // CREATE vytvoření nového pojištěnce - uložení
    @PostMapping("/novy-pojistenec/save")
    public String novyPojistenec(@ModelAttribute("osoba") Osoba osoba) {
        spravceOsob.insertOsobu(osoba);
        return "redirect:/pojistenci";
    }

    // DELETE smazání pojištěnce, včetně jeho pojištění
    @PostMapping("/delete/{osoba_id}")
    public String smazatPojisteneho(@PathVariable(name = "osoba_id") int osoba_id) {
        System.out.println("ID: " + osoba_id);
        spravceOsob.deleteOsobu(osoba_id);
        return "redirect:/pojistenci";
    }

    // UPDATE edit pojištěnce
    @GetMapping("/update/{osoba_id}")
    public ModelAndView upravaPojistence(@PathVariable(name = "osoba_id") int osoba_id) {
        ModelAndView updateView = new ModelAndView("update-pojistence");
        Osoba osoba = spravceOsob.selectOsobu(osoba_id);
        updateView.addObject("osoba", osoba);
        return updateView;
    }

    // UPDATE edit pojištěnce - uložení
    @PostMapping("/update/{osoba_id}/save")
    public String upravaPojistenceSave(@ModelAttribute("osoba") Osoba osoba) {
        spravceOsob.updateOsobu(osoba);
        return "redirect:/pojistenci/detail-pojistence/" + osoba.getOsoba_id();
    }

}
