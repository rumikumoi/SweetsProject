package com.example.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sample.dto.SweetsData;
import com.example.sample.entity.BackyardForm;
import com.example.sample.entity.SweetsForm;
import com.example.sample.service.BackyardService;
import com.example.sample.service.SweetsService;

@Controller
public class SweetsController {

	@Autowired
	private SweetsService service;
	
	@RequestMapping("/showcase")
	public String showcase(Model model) {
		model.addAttribute("sweets", service.getShowcaseData());
		return "showcase";
	}

	@RequestMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("sweetsForm", service.getShopData());
		return "shop";
	}

	@PostMapping("/buy")
	public String buy(SweetsForm sweetsForm, Model model) {
		List<SweetsData> list = service.updateStock(sweetsForm);
		if(list.size() == 0) {
			model.addAttribute("sweetsForm", sweetsForm);
			model.addAttribute("msg", "商品を選んで下さい");
			return "shop";
		} else {
			model.addAttribute("sweets", service.updateBuy(sweetsForm));
			return "thanks";
		}

	}
	
	
	
	@Autowired
	BackyardService backyardService;
	@RequestMapping("/backyard")
	public String backyard(Model model) {
		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm backyardForm = new BackyardForm();
		model.addAttribute("backyardForm", backyardForm);
		return "backyard";
	}
	
	@PostMapping("/stock-add")
	public String stockAdd(BackyardForm backyardForm, Model model) {
		backyardService.addStock(backyardForm.getId(), backyardForm.getAddStock());

		model.addAttribute("sweetsList", backyardService.getSweetsList());
		model.addAttribute("backyardForm", backyardForm);
		
		return "backyard";
	}
	
	@PostMapping("/item-insert")
	public String itemInsert(BackyardForm backyardForm, Model model) {
		String id = String.format("%03d",Integer.parseInt(backyardService.getMaxID()) + 1);
//		String id = String.valueOf(Integer.parseInt(showcaseData.get(showcaseData.size() - 1).getId()) + 1);
		backyardService.insertItem(id, backyardForm.getItem(), backyardForm.getKind(), backyardForm.getAddStock());

		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		model.addAttribute("backyardForm", form);

		return "backyard";
	}
	
	@PostMapping("/item-delete")
	public String itemDelete(BackyardForm backyardForm, Model model) {
		backyardService.deleatItem(backyardForm.getId());

		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		model.addAttribute("backyardForm", form);

		return "backyard";
	}
}
