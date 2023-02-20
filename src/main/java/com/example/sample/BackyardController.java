package com.example.sample;

import java.util.List;

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
	
	public List<String> getKindList() {
		return backyardService.getKindList();
	}
	
	@RequestMapping("/backyard")
	public String backyard(Model model) {
		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm backyardForm = new BackyardForm();
		backyardForm.setKindList(getKindList());
		model.addAttribute("backyardForm", backyardForm);
		return "backyard";
	}
	
	@PostMapping("/stock-add")
	public String stockAdd(BackyardForm backyardForm, Model model) {
		backyardService.addStock(backyardForm.getId(), backyardForm.getAddStock());

		backyardForm.setKindList(getKindList());
		model.addAttribute("sweetsList", backyardService.getSweetsList());
		model.addAttribute("backyardForm", backyardForm);
		
		String msg1 = "在庫補充を行いました！";
		model.addAttribute("msg1", msg1);
		return "backyard";
	}
	
	@PostMapping("/item-insert")
	public String itemInsert(BackyardForm backyardForm, Model model) {
		backyardService.insertItem(backyardForm.getItem(), backyardForm.getKind(), backyardForm.getStock());

		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		form.setKindList(getKindList());
		model.addAttribute("backyardForm", form);
		
		String msg2 = "新規商品の追加を行いました！";
		model.addAttribute("msg2", msg2);
		return "backyard";
	}
	
	@PostMapping("/item-delete")
	public String itemDelete(BackyardForm backyardForm, Model model) {
		backyardService.deleatItem(backyardForm.getId());

		model.addAttribute("sweetsList", backyardService.getSweetsList());
		BackyardForm form = new BackyardForm();
		form.setKindList(getKindList());
		model.addAttribute("backyardForm", form);

		String msg3 = "商品の削除を行いました！";
		model.addAttribute("msg3", msg3);
		return "backyard";
	}
}
