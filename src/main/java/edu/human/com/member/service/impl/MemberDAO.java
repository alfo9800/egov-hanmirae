package edu.human.com.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.human.com.common.EgovComAbstractMapper;
import edu.human.com.member.service.EmployerInfoVO;
import edu.human.com.util.PageVO;

/**
 * egov에서 DAO는 sqlSession 템플릿을 바로 접근하지 않고,
 * EgovAbstractMapper클래스를 상속받아서 DAO구현메서드를 사용
 * @author 한미래
 *
 */
@Repository
public class MemberDAO extends EgovComAbstractMapper {
	public List<EmployerInfoVO> selectMember(PageVO pageVO) throws Exception {		 
		return selectList("memberMapper.selectMember");
	}
	
	public EmployerInfoVO viewMember(String emplyr_id) throws Exception {
		//selectOne("쿼리ID","쿼리매개변수=파라미터=인자");
		return selectOne("memberMapper.viewMember", emplyr_id);
	}
	
	public int deleteMember(String emplyr_id) throws Exception {
		//affected된 row값이 반환 됨
		return delete("memberMapper.deleteMember", emplyr_id);
	}
	
	public void insertMember(EmployerInfoVO employerInfoVO) throws Exception {
		insert("memberMapper.insertMember", employerInfoVO);
	}
	
	public void updateMember(EmployerInfoVO employerInfoVO) throws Exception {
		update("memberMapper.updateMember", employerInfoVO);
	}
	
	public Map<Object, Object> selectCodeMap(String code_id) throws Exception {
		return selectMap("memberMapper.selectCodeMap", code_id, "CODE");
	}
	
	public Map<Object,Object> selectGroupMap() throws Exception {
		return selectMap("memberMapper.selectGroupMap", "GROUP_ID");
	}
}
