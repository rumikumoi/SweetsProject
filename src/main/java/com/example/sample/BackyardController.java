package com.example.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sample.entity.BackyardForm;
import com.example.sample.service.BackyardService;

@Controller
public class BackyardController {
	
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
		
		String msg = "在庫補充を行いました！";
		model.addAttribute("msg", msg);
		return "backyard";
	}
	
	@PostMapping("/item-insert")
	public String itemInsert(BackyardForm backyardForm, Model model) {
		String id = String.format("%03d",Integer.parseInt(backyardService.getMaxID()) + 1);
		backyardService.insertItem(id, backyardForm.getItem(), backyardForm.getKind(), backyardForm.getAddStock());

		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		model.addAttribute("backyardForm", form);
		
		String msg = "新規商品の追加を行いました！";
		model.addAttribute("msg", msg);
		return "backyard";
	}
	
	@PostMapping("/item-delete")
	public String itemDelete(BackyardForm backyardForm, Model model) {
		backyardService.deleatItem(backyardForm.getId());

		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		model.addAttribute("backyardForm", form);

		String msg = "商品の削除を行いました！";
		model.addAttribute("msg", msg);
		return "backyard";
	}
}
