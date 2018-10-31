package com.atmoon.service;

import com.atmoon.enums.ResultEnum;
import com.atmoon.exception.GirlException;
import com.atmoon.pojo.Girl;
import com.atmoon.repository.GirlRepository;
import com.atmoon.vo.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwoGirl() {
        Girl girlA = new Girl();
        girlA.setAge(16);
        girlA.setCity("xiamen");
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(17);
        girlB.setCity("hangzhou");
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) {
        Girl girl = girlRepository.findById(id).get();
        Integer age = girl.getAge();
        if (age < 18) {
            throw new GirlException(ResultEnum.HIGH_SCHOOL);
        } else if (age > 22) {
            throw new GirlException(ResultEnum.WORK);
        }
    }

    /**
     * 根据id查询一个女生信息
     *
     * @param id
     * @return
     */
    public Girl findOne(Integer id) {
        return girlRepository.findById(id).get();
    }

    /**
     * 带参分页查询所有女生信息
     *
     * @param girl       查询参数
     * @param pageConfig 分页参数
     * @return
     */
    public Page<Girl> findAllByParams(final Girl girl, PageConfig pageConfig) {
        Specification<Girl> specification = (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if (girl.getAge() != null) {
                Predicate _age = criteriaBuilder.equal(root.get("age"), girl.getAge());
                predicates.add(_age);
            }
            if (!girl.getCity().isEmpty()) {
                Predicate _city = criteriaBuilder.like(root.get("city"), "%" + girl.getCity() + "%");
                predicates.add(_city);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
        Pageable pageable = PageRequest.of(pageConfig.getPageNum() - 1, pageConfig.getPageSize(), pageConfig.getSort());
        return girlRepository.findAll(specification, pageable);
    }

}
