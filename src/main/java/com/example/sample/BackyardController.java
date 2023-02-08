package com.example.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sample.entity.BackyardForm;
import com.example.sample.entity.Sweets;
import com.example.sample.service.BackyardService;
import com.example.sample.service.SweetsService;

@Controller
public class BackyardController {

	@Autowired
	BackyardService backyardService;
	@Autowired
	SweetsService sweetsService;

	@RequestMapping("/backyard")
	public String backyard(Model model) {

		return "backyard";
	}

	/**
	 * 課題5-1
	 */
	@PostMapping("/stock-add")
	public String stockAdd(BackyardForm form, Model model) {
		model.addAttribute("msg1", "在庫を更新しました");
		
		return "backyard";
	}

	/**
	 * 課題5-2
	 */
	@PostMapping("/item-insert")
	public String itemInsert(Sweets sweets, Model model) {
		model.addAttribute("msg2", "商品を追加しました");

		return "backyard";
	}

	/**
	 * 課題5-3
	 */
	@PostMapping("/item-delete")
	public String itemDelete(String id, Model model) {
		model.addAttribute("msg3", "商品を削除しました");

		return "backyard";
	}

}
