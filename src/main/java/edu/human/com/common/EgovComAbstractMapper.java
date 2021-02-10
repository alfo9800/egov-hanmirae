package edu.human.com.common;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/* 아래 abstract class는 추상클래스.
 * 추상클래스의 특징: 
 * 1) new키워드로 인스턴스 실행클래스를 만들수 없어서, 상속을 통해서 클래스의 메서드를 실행 가능함.
 * 2) 오버라이드해서 전자정부에서 제공한 EgovAbastacMapper 추상클래스에서 정의된 명세를 아래 클래스 재정의(오버라이딩)해서 매서드 구현.
 * 추상클래스 만드는 목적:
 * 1) 멤버변수 또는 멤버메서드를 규격화하기 위해
 * 2) 전자정부표준을 준수하였는지 인증받기 위해서
 */
/**
 * EgovComAbstractMapper클래스는 sqlSession템플릿을 DAO클래스에서 직접 호출하지 않고,
 * 전자정부에서 제공한 EgovAbastacMapper(마이바티스용)클래스를 상속받아서 생성한 개발자(사)클래스를 사용해서 
 * 쿼리템플릿을 재정의 함.
 * @author 한미래
 *
 */
public abstract class EgovComAbstractMapper extends EgovAbstractMapper {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Resource(name="egov.sqlSession")
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}
	
	//오버라이드	
	@Override
	public int delete(String queryId) {
		return getSqlSession().delete(queryId);
	}
	
	@Override
	public int delete(String queryId, Object parameterObject) {
		return getSqlSession().delete(queryId, parameterObject);
	}

	@Override
	public int insert(String queryId) {
		return getSqlSession().insert(queryId);
	}
	
	@Override
	public int insert(String queryId, Object parameterObject) {
		return getSqlSession().insert(queryId, parameterObject);
	}
	
	@Override
	public <E> List<E> selectList(String queryId, Object parameterObject) {
		return getSqlSession().selectList(queryId, parameterObject);
	}

	@Override
	public <E> List<E> selectList(String queryId) {
		return getSqlSession().selectList(queryId);
	}
	
	
	@Override
	public <T> T selectOne(String queryId) {
		return getSqlSession().selectOne(queryId);
	}

	@Override
	public <T> T selectOne(String queryId, Object parameterObject) {
		return getSqlSession().selectOne(queryId, parameterObject);
	}
	
	@Override
	public int update(String queryId) {
		return getSqlSession().update(queryId);
	}
	
	@Override
	public int update(String queryId, Object parameterObject) {
		return getSqlSession().update(queryId, parameterObject);
	}

	
	@Override
	public <E> List<E> selectList(String queryId, Object parameterObject, RowBounds rowBounds) {
		return getSqlSession().selectList(queryId, parameterObject, rowBounds);
	}

	//========================================================================================
	/* 페이징범위 계산: pageIndex(선택한페이지) 외 pageSize(1페이지당 보여줄 개수) 2개 값을 매개변수로 받아서
	 * 1페이지 일 때 시작*쿼리번호 : 10[1페이지당 출력할 개수]x(1[선택한 페이지번호]-1) = 0
	 * 2페이지 일 때 시작*쿼리번호 : 10x(2-1) = 10[계산결과나온 시작페이지 번호] = 10
	 * offset = (pageIndex-1)*pageSize; 
	 */
	@Override
	public List<?> listWithPaging(String queryId, Object parameterObject, int pageIndex, int pageSize) {
		int offset = (pageIndex-1) * pageSize;
		RowBounds rowBounds = new RowBounds(offset, pageSize); //(시작인덱스 번호,꺼내올 개수)
		return getSqlSession().selectList(queryId, parameterObject, rowBounds);
	}
	
	
}
