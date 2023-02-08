package com.example.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sample.dto.SweetsData;
import com.example.sample.entity.SweetsForm;
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

//	@RequestMapping("/shop")
//	public String shop(Model model) {
//		model.addAttribute("sweetsForm", service.getShopData());
//		return "shop";
//	}
//
//	@PostMapping("/buy")
//	public String buy(SweetsForm sweetsForm, Model model) {
//		model.addAttribute("sweets", service.updateStock(sweetsForm));
//		return "thanks";
//	}

	@RequestMapping("/shop")
	public String shop2(Model model) {
		model.addAttribute("sweetsForm", service.getShopData2());
		return "shop";
	}

	@PostMapping("/buy")
	public String buy2(SweetsForm sweetsForm, Model model) {
		List<SweetsData> list = service.updateStock2(sweetsForm);
		if (list.size() > 0) {
			model.addAttribute("sweets", list);
			return "thanks";
		} else {
			model.addAttribute("sweetsForm", sweetsForm);
			model.addAttribute("msg", "商品を選んでください");
			return "shop";
		}
	}

}
