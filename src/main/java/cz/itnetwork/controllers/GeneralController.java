package cz.itnetwork.controllers;

import cz.itnetwork.models.Insurance;
import cz.itnetwork.models.InsuranceManager;
import cz.itnetwork.models.Person;
import cz.itnetwork.models.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralController {

    @Autowired
    private PersonManager personManager;
    @Autowired
    private InsuranceManager insuranceManager;

    // Personal details and insurances
    @GetMapping("insured-persons/personal-details/{personId}")
    public ModelAndView personalDetailsAndInsurances(Model model, @PathVariable(name = "personId") int personId) {
        model.addAttribute("insurances", insuranceManager.selectAllInsurancesOfPerson(personId));
        ModelAndView updateView = new ModelAndView("personal-details");
        Person person = personManager.selectPerson(personId);
        updateView.addObject("person", person);
        return updateView;
    }

    // Insurance details and person name
    @GetMapping("insured-persons/personal-details/insurance-details/{insuranceId}")
    public ModelAndView insuranceDatailsAndPersonalDetails(Model model, @PathVariable(name = "insuranceId") int insuranceId) {
        ModelAndView updateView = new ModelAndView("insurance-details");
        Insurance insurance = insuranceManager.selectInsurance(insuranceId);
        updateView.addObject("insurance", insurance);
        Person person = personManager.selectPerson(insurance.getPersonId());
        updateView.addObject("person", person);
        return updateView;
    }

    // CREATE adds new insurance to a person
    @GetMapping("/insured-persons/personal-details/add-insurance/{personId}")
    public String addInsurance(Model model, @PathVariable(name = "personId") int personId) {
        Person person = personManager.selectPerson(personId);
        model.addAttribute("person", person);
        model.addAttribute("insurance", new Insurance());
        return "add-insurance";
    }

}
