package com.gewuyou.baseforge.dictionary.repository;

import com.gewuyou.baseforge.dictionary.model.DictionaryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 字典类别存储库
 *
 * @author gewuyou
 * @since 2025-01-17 11:56:43
 */
@Repository
public interface DictionaryCategoryRepository extends JpaRepository<DictionaryCategory, Long>, JpaSpecificationExecutor<DictionaryCategory> {
}
