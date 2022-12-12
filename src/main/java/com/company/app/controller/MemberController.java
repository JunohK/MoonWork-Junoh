package com.company.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.app.model.dao.MemberDAO;
import com.company.app.model.dto.MemberDTO;

@Controller
public class MemberController {
	@Autowired
	MemberDAO memberDao;
	
	@RequestMapping("member/list")
	public String list(Model model) {
		List<MemberDTO> list = memberDao.list();
		
		model.addAttribute("items", list);
		return "member/list";
	}
	
	@RequestMapping("member/write")
	public String write() {
		return "member/write";
	}
	
	@RequestMapping("member/insert")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberDao.insert(dto);
		return "redirect:/member/list";
	}
	
	@RequestMapping("member/view")
	public String view(@RequestParam String id, Model model) {
		model.addAttribute("dto", memberDao.detail(id));
		return "member/detail";
	}
	
	// 회원 정보 수정
	@RequestMapping("member/update")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		// 비밀번호 체크
		boolean result = memberDao.check_password(dto.getId(), dto.getPassword());
		if (result) {
			memberDao.update(dto);
			return "redirect:/member/list";
		} else {
			model.addAttribute("dto", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/detail";
		}
	}
	
	// 회원 정보 삭제
	@RequestMapping("member/delete")
	public String delete(@RequestParam String id, @RequestParam String password, Model model) {
		// 비밀번호 체크
		boolean result = memberDao.check_password(id, password);
		if (result) {
			memberDao.delete(id);
			return "redirect:/member/list";
		} else {
			model.addAttribute("dto", memberDao.detail(id));
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/detail";
		}
	}
}
