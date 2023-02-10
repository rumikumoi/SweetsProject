package com.example.sample;

import java.util.List;

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
	
	private List<Sweets> getSweetsList() {
		return sweetsService.getShowcaseData();
	}

	private List<String> getKindList() {
		return backyardService.getKindList();
	}

	@RequestMapping("/backyard")
	public String backyard(Model model) {
		model.addAttribute("sweetsList", getSweetsList());
		BackyardForm form = new BackyardForm();
		// 新規商品追加表示用種類一覧
		form.setKindList(getKindList());
		model.addAttribute("backyardForm", form);

		return "backyard";
	}

	/**
	 * 課題5-1
	 */
	@PostMapping("/stock-add")
	public String stockAdd(BackyardForm form, Model model) {
		model.addAttribute("msg1", backyardService.addStock(form.getId(), form.getAddStock()));

		// 続けて更新ができるよう商品一覧とformを再送
		model.addAttribute("sweetsList", getSweetsList());
		// 新規商品追加表示用種類一覧
		form.setKindList(getKindList());
		model.addAttribute("backyardForm", form);
		
		return "backyard";
	}

	/**
	 * 課題5-2
	 */
	@PostMapping("/item-insert")
	public String itemInsert(Sweets sweets, Model model) {
		model.addAttribute("msg2", backyardService.insertItem(sweets));

		// 続けて更新ができるよう商品一覧とformを再送
		model.addAttribute("sweetsList", getSweetsList());
		BackyardForm form = new BackyardForm();
		// 新規商品追加表示用種類一覧
		form.setKindList(getKindList());
		model.addAttribute("backyardForm", form);

		return "backyard";
	}

	/**
	 * 課題5-3
	 */
	@PostMapping("/item-delete")
	public String itemDelete(String id, Model model) {
		model.addAttribute("msg3", backyardService.deleatItem(id));
		
		// 続けて更新ができるよう商品一覧とformを再送
		model.addAttribute("sweetsList", getSweetsList());
		BackyardForm form = new BackyardForm();
		// 新規商品追加表示用種類一覧
		form.setKindList(getKindList());
		model.addAttribute("backyardForm", form);

		return "backyard";
	}

}
