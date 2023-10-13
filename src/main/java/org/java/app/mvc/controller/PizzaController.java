package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pojo.Offerta;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.repo.PizzaRepo;
import org.java.app.db.serv.OffertaService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private PizzaRepo pizzaRepo;

	@Autowired
	private OffertaService offertaService;

	
	
	
	@GetMapping("/")
	public String index(@RequestParam(required=false) String nome, Model model) {
		
		List<Pizza> pizze = null;
		                         
		pizze = nome == null ? pizzaService.findAll() : pizzaService.findByName(nome);
		
		model.addAttribute("nome", nome);
		model.addAttribute("pizze", pizze);
		
		return "index";
	}
	
	
	

	@GetMapping("/{id}")
	public String show(@PathVariable int id, Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		
		model.addAttribute("pizza", pizza);
		
		return "show";
	}	




	@GetMapping("/create-update")
	public String create(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "create-update";
	}
	

	@PostMapping("/create-update")
	public String store(
			@Valid @ModelAttribute("pizza") Pizza formPizza, 
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) 
			return "create-update";
		
		pizzaRepo.save(formPizza);
		
		return "redirect:/";
	}
	



	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable Integer id,
			Model model) {
		
		model.addAttribute("pizza", pizzaService.findById(id));
		
		return "create-update";
	}
	

	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute("pizza") Pizza formPizza,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) 
			return "create-update";
		
		pizzaService.save(formPizza);
		
		return "redirect:/";
	}




	@PostMapping("/delete/{id}")
	public String delete(
		@PathVariable Integer id) {

			pizzaRepo.deleteById(id);

			return "redirect:/";
		}
	
	
	
	
	@GetMapping("/offerta/{pizza_id}")
	public String createOfferta(
			@PathVariable("pizza_id") int id,
			Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		Offerta offerta = new Offerta();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("offerta", offerta);
		
		return "create-update-offerta";
	}
	
	
	@PostMapping("/offerta/{pizza_id}")
	public String storeOfferta(
			@Valid @ModelAttribute Offerta offerta,
			BindingResult bindingResult,
			@PathVariable("pizza_id") int id,
			Model model) {

		Pizza pizza = pizzaService.findById(id);
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("pizza", pizza);
			return "create-update-offerta";
		}
		
		offerta.setPizza(pizza);
		
		offertaService.save(offerta);
		
		return "redirect:/" + id;
	}
}
