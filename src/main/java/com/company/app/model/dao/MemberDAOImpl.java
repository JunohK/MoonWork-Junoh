package com.company.app.model.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.company.app.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	// create. 회원등록
	@Override
	public void insert(MemberDTO dto) {
		sqlSession.insert("member.insert", dto);
	}
	
	// read 회원 리스트 조회
	@Override
	public List<MemberDTO> list() {
		return sqlSession.selectList("member.list");
	}
	
	@Override
	public MemberDTO detail(String id) {
		return sqlSession.selectOne("member.detail", id);
	}
	
	@Override
	public void update(MemberDTO dto) {
		sqlSession.update("member.update", dto);
	}
	
	@Override
	public void delete(String id) {
		sqlSession.update("member.delete", id);
	}
	
	@Override
	public boolean check_password(String id, String password) {
		
		boolean result = false;
		
		Map<String, String> map = new HashMap();
		map.put("id", id);
		map.put("password", password);
		
		int count = sqlSession.selectOne("member.check_password",map);
		
		if(count == 1)
			result = true;
		
		return result;
	}
}
