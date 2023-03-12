package com.today.flower.store;

import org.thymeleaf.util.StringUtils;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {
	
	private JPAQueryFactory queryFactory;
	
	public StoreRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BoolaeanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("storeName", searchBy)) {
			return QStore.store.storeName.like("%" + searchQuery + "%");
		}
	}

}
