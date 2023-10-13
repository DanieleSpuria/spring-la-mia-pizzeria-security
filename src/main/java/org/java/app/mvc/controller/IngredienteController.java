package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pojo.Ingrediente;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.IngredienteService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PizzaService pizzaService;
	
	
	
	
	@GetMapping
	public String indexIngredienti(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingredienti";
	}
	
	
	
	
	@GetMapping("/create")
	public String createIngrediente(Model model) {
		
		List<Pizza> pizze = pizzaService.findAll();
		Ingrediente ingrediente = new Ingrediente();
		
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("pizze", pizze);
		
		return "create-ingrediente";
	}
	
	@PostMapping("/create")
	public String storeIngrediente(
			@Valid @ModelAttribute Ingrediente ingrediente,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors())
			return "create-ingrediente";
		
		ingredienteService.save(ingrediente); 
		
		return "redirect:/ingredienti";
	}
	
	
	
	
	@GetMapping("/edit/{id}")
	public String editIngrediente(
			@PathVariable Integer id,
			Model model) {
		
		List<Pizza> pizze = pizzaService.findAll();
		Ingrediente ingrediente = ingredienteService.findById(id);
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("ingrediente", ingrediente);
		
		return "create-ingrediente";
	}
	
	
	@PostMapping("/edit/{id}")
	public String updateIngrediente(
			@Valid @ModelAttribute Ingrediente ingrediente,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors())
			return "create-ingrediente";	
		
		ingredienteService.save(ingrediente);
		
		return "redirect:/ingredienti";
	}
	
	
	
	
	@PostMapping("/delete/{id}")
	public String deleteIngrediente(
			@PathVariable Integer id) {
		
		ingredienteService.deleteById(id);
		
		return "redirect:/ingredienti";
	}
}
