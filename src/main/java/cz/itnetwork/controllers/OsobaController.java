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

    // READ
    @GetMapping
    public String vypisPojistence(Model model) {
        model.addAttribute("osoby", spravceOsob.readSeznamOsob());
        return "pojistenci";
    }

    // CREATE
    @GetMapping("/novy-pojistenec")
    public String renderNovyPojistenec(Model model) {
        model.addAttribute("osoba", new Osoba());
        return "novy-pojistenec";
    }

    // CREATE
    @PostMapping("/novy-pojistenec/save")
    public String novyPojistenec(@ModelAttribute("osoba") Osoba osoba) {
        spravceOsob.createOsobu(osoba);
        return "redirect:/pojistenci";
    }

    // DELETE
    @PostMapping("/delete/{osoba_id}")
    public String smazatPojisteneho(@PathVariable(name = "osoba_id") int osoba_id) {
        System.out.println("ID: " + osoba_id);
        spravceOsob.deleteOsobu(osoba_id);
        return "redirect:/pojistenci";
    }

    // UPDATE
    @GetMapping("/update/{osoba_id}")
    public ModelAndView upravaPojistence(@PathVariable(name = "osoba_id") int osoba_id) {
        ModelAndView updateView = new ModelAndView("update-pojistence");
        Osoba osoba = spravceOsob.readOsobu(osoba_id);
        updateView.addObject("osoba", osoba);
        return updateView;
    }

    // UPDATE
    @PostMapping("/update/{osoba_id}/save")
    public String upravaPojistenceSave(@ModelAttribute("osoba") Osoba osoba) {
        spravceOsob.updateOsobu(osoba);
        return "redirect:/pojistenci";
    }

}
