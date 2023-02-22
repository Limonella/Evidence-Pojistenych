package cz.itnetwork.controllers;

import cz.itnetwork.models.Insurance;
import cz.itnetwork.models.InsuranceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsuranceController {

    @Autowired
    private InsuranceManager insuranceManager;

    // READ shows list of all insurances
    @GetMapping("/insurances")
    public String showAllInsurances(Model model) {
        model.addAttribute("insurances", insuranceManager.selectListOfInsurances());
        return "insurances";
    }

    // CREATE adds new insurance to a person - save
    @PostMapping("/insured-persons/personal-details/add-insurance/{personId}/save")
    public String addInsurance(@ModelAttribute("insurance") Insurance insurance) {
        insuranceManager.insertInsurance(insurance);
        return "redirect:/insured-persons/personal-details/" + insurance.getPersonId();
    }

    // DELETE deletes insurance
    @PostMapping("insurances/delete/{insuranceId}")
    public String deleteInsurance(@PathVariable(name = "insuranceId") int insuranceId) {
        System.out.println("ID: " + insuranceId);
        Insurance insurance = insuranceManager.selectInsurance(insuranceId);
        insuranceManager.deleteInsurance(insuranceId);
        return "redirect:/insured-persons/personal-details/" + insurance.getPersonId();
    }

    // UPDATE edits insurance
    @GetMapping("insurances/edit/{insuranceId}")
    public ModelAndView editInsurance(@PathVariable(name = "insuranceId") int insuranceId) {
        ModelAndView updateView = new ModelAndView("edit-insurance");
        Insurance insurance = insuranceManager.selectInsurance(insuranceId);
        updateView.addObject("insurance", insurance);
        return updateView;
    }

    // UPDATE edit insurance - save
    @PostMapping("insurances/edit/{insuranceId}/save")
    public String editInsuranceSave(@ModelAttribute("insurance") Insurance insurance) {
        insuranceManager.updateInsurance(insurance);
        return "redirect:/insured-persons/personal-details/insurance-details/" + insurance.getInsuranceId();
    }

}
