package com.example.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sample.Service.SweetsService;
import com.example.sample.dto.SweetsData;
import com.example.sample.entity.SweetsForm;

@Controller
public class SweetsController {

	@Autowired
	private SweetsService service;
	
	@RequestMapping("/showcase")
	public String showcase(Model model) {
		model.addAttribute("sweets", service.getShowcaseData());//返されたデータ全件をsweetsとしてshowcase.htmlへ返す
		return "showcase";
	}

	@RequestMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("sweetsForm", service.getShopData());//service.getShopDataから返されたsweetsFormのsweetsListを"sweetsForm"としてshop.htmlへ返す
		return "shop";
	}

	@PostMapping("/buy")
	public String buy(SweetsForm sweetsForm, Model model) {//Postで渡されたデータを、buyメソッド引数sweetsFormで受け取る
		List<SweetsData> list = service.updateBuy(sweetsForm);//updateBuyで作ったリスト（shoppingList?)をlistに写す
		if (list.size() > 0) {//買い物リストが０以上
		model.addAttribute("sweets", list);//listのデータをsweetsとしてthanksへ
		return "thanks";
	} else {
		model.addAttribute("sweetsForm", sweetsForm);
		model.addAttribute("msg", "商品を選んでください");
		return "shop";
	}
	}
	
}
