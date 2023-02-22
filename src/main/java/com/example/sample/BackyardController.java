package com.example.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sample.Service.BackyardService;
import com.example.sample.entity.BackyardForm;
import com.example.sample.entity.Sweets;

@Controller
public class BackyardController {

	
	@Autowired
	BackyardService backyardService;
	
	@RequestMapping("/backyard")
	public String backyard(Model model) {
		model.addAttribute("sweetsList",backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		model.addAttribute("backyardForm", form);
		return "backyard";
	}
	
	@PostMapping("/stock-add")
	public String stockAdd(BackyardForm form, Model model) {
		model.addAttribute("msg1", backyardService.addStock(form.getId(), form.getAddStock()));
	
		// 続けて更新ができるよう商品一覧とformを再送
		model.addAttribute("sweetsList", backyardService.getSweetsList());
		model.addAttribute("backyardForm", form);
		
		return "backyard";
	}
	
	@PostMapping("/item-insert")
	public String itemInsert(Sweets sweets, Model model) {
		model.addAttribute("msg2",backyardService.insertItem(sweets));
		
		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		model.addAttribute("backyardForm", form);
		
		return "backyard";
		
	}
	@PostMapping("/item-delete")
	public String itemDelete(String id, Model model) {
		model.addAttribute("msg3", backyardService.itemDelete(id));
		
		// 続けて更新ができるよう商品一覧とformを再送
		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		// 新規商品追加表示用種類一覧
//		form.setKindList(getKindList());
		model.addAttribute("backyardForm", form);

		return "backyard";
		
	}
	
}
