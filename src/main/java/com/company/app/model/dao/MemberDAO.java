package com.company.app.model.dao;

import java.util.List;

import com.company.app.model.dto.MemberDTO;

public interface MemberDAO {
	
	public void insert(MemberDTO dto);
	public List<MemberDTO> list();
	public MemberDTO detail(String id);
	public void update(MemberDTO dto);
	public void delete(String id);
	
	public boolean check_password(String id, String password);
}
