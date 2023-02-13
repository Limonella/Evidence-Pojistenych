package cz.itnetwork.controllers;

import cz.itnetwork.models.Osoba;
import cz.itnetwork.models.Pojisteni;
import cz.itnetwork.models.SpravceOsob;
import cz.itnetwork.models.SpravcePojisteni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralController {

    @Autowired
    private SpravceOsob spravceOsob;
    @Autowired
    private SpravcePojisteni spravcePojisteni;

    // Detail pojištěnce a jeho pojištění
    @GetMapping("pojistenci/detail-pojistence/{osoba_id}")
    public ModelAndView detailPojistence(Model model, @PathVariable(name = "osoba_id")int osoba_id) {
        model.addAttribute("pojisteni", spravcePojisteni.readVsechnaPojisteniOsoby(osoba_id));
        ModelAndView updateView = new ModelAndView("detail-pojistence");
        Osoba osoba = spravceOsob.selectOsobu(osoba_id);
        updateView.addObject("osoba", osoba);
        return updateView;
    }

    // Detail pojištění a jeho pojištěnce
    @GetMapping("pojistenci/detail-pojistence/detail-pojisteni/{pojisteni_id}")
    public ModelAndView detailPojisteni(Model model, @PathVariable(name = "pojisteni_id") int pojisteni_id) {
        ModelAndView updateView = new ModelAndView("detail-pojisteni");
        Pojisteni pojisteni = spravcePojisteni.selectPojisteni(pojisteni_id);
        updateView.addObject("pojisteni", pojisteni);
        Osoba osoba = spravceOsob.selectOsobu(pojisteni.getOsoba_id());
        updateView.addObject("osoba", osoba);
        return updateView;
    }

    // CREATE vytvoření nového pojištění
    @GetMapping("/pojistenci/detail-pojistence/nove-pojisteni/{osoba_id}")
    public String novePojisteni(Model model, @PathVariable(name = "osoba_id") int osoba_id) {
        Osoba osoba = spravceOsob.selectOsobu(osoba_id);
        model.addAttribute("osoba", osoba);
        model.addAttribute("pojisteni", new Pojisteni());
        return "nove-pojisteni";
    }

}
