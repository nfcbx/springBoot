package com.zsx.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zsx.entity.Tuser;

@Repository
public interface TUserDao extends PagingAndSortingRepository<Tuser, Long>, JpaSpecificationExecutor<Tuser> {

    Tuser findByUserName(String userName);

    @Query("from Tuser t where id = :id")
    List<Tuser> queryFamilyList(@Param("id") Long id, Pageable pageable);

}
