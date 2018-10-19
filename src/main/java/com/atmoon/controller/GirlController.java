package com.atmoon.controller;

import com.atmoon.aspect.HttpAspect;
import com.atmoon.repository.GirlRepository;
import com.atmoon.service.GirlService;
import com.atmoon.pojo.Girl;
import com.atmoon.utils.ResultUtil;
import com.atmoon.vo.PageConfig;
import com.atmoon.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        logger.info("girlList");
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * @param girl
     * @return
     */
    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 根据id获取单个女生信息
     * @param id
     * @return
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id")Integer id){
        return girlRepository.findById(id).get();
    }

    /**
     * 更新女生信息
     * @param girl
     * @return
     */
    @PutMapping(value = "/girlUpdate")
    public Girl girlUpdate(Girl girl){
        return girlRepository.save(girl);
    }

    /**
     * 删除女生信息
     * @param id
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id")Integer id){
        girlRepository.deleteById(id);
    }

    /**
     * 根据年龄查询女生信息
     * @param age
     * @return
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age")Integer age){
        return girlRepository.findByAge(age);
    }

    /**
     * 添加两个女生
     */
    @PostMapping(value = "/girls/two")
    public void girlTwo(){
        girlService.insertTwoGirl();
    }


    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id")Integer id) throws Exception{
        girlService.getAge(id);
    }

    /**
     * 带参分页查询女生信息
     * @param girl
     * @param pageConfig
     * @returna
     */
    @PostMapping(value = "/girls/query")
    public List<Girl> girlListByParams(Girl girl, PageConfig pageConfig) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        pageConfig.setSort(sort);
        return girlService.findAllByParams(girl,pageConfig).getContent();
    }

}
