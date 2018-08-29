package com.wandeyun.wuyi.website.controller;

import com.wandeyun.wuyi.website.VO.ResultVO;
import com.wandeyun.wuyi.website.bean.Dynamic;
import com.wandeyun.wuyi.website.enums.ResultEnum;
import com.wandeyun.wuyi.website.exception.WuYiException;
import com.wandeyun.wuyi.website.service.DynamicService;
import com.wandeyun.wuyi.website.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
 *@author 涂梅城
 *@date 2018/7/21 16:22
 * 最新动态接口
 */
@RestController
@RequestMapping("/api/dynamic")
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;
    
    
    /** 查询全部动态
     * @param: []
     * @return: java.util.List<com.wandeyun.wuyi.website.bean.Dynamic> */
    @GetMapping("/findList")
    public ResultVO findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size){
        //倒序
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest request = new PageRequest(page, size, sort);
        Page<Dynamic> bannerPage =  this.dynamicService.findAll(request);

        return ResultVOUtil.success( bannerPage);
    }

    /** 新增动态
     * @param: [dynamic]
     * @return: com.wandeyun.wuyi.website.VO.ResultVO */
    @PostMapping("/addDynamic")
    public ResultVO addDynamic(@Valid Dynamic dynamic){
        Dynamic rest = this.dynamicService.insert(dynamic);
        if (rest == null){
            throw new WuYiException(ResultEnum.ADD_DYNAMIC_ERROR.getCode(),ResultEnum.ADD_DYNAMIC_ERROR.getMessage());
        }
        return  ResultVOUtil.success();
    }

    /** 根据dyn_Id查询
     * @param: [dyn_id]
     * @return: com.wandeyun.wuyi.website.VO.ResultVO */
    @GetMapping("/findByDyn_Id")
    public ResultVO findOne(@RequestParam("id") Integer dyn_id){
        Dynamic optionalDynamic =  this.dynamicService.findByDyn_Id(dyn_id);
        return ResultVOUtil.success(optionalDynamic);
    }
    
    /** 修改动态
     * @param: [dynamic]
     * @return: com.wandeyun.wuyi.website.VO.ResultVO */
    @PostMapping("/updateDynamic")
    public ResultVO updateDynamic(@Valid Dynamic dynamic){
        Dynamic rest = this.dynamicService.update(dynamic);
        if(dynamic == null){
            throw new WuYiException(ResultEnum.DYNAMIC_UPDATE_ERROR.getCode(),ResultEnum.DYNAMIC_UPDATE_ERROR.getMessage());
        }
        return ResultVOUtil.success(rest);
    }

    //删除动态
    @RequestMapping("/delete")
    public ResultVO delete(@RequestParam("id") Integer id){
        try {
            this.dynamicService.delete(id);
            return ResultVOUtil.success();
        }catch (Exception e){
            return ResultVOUtil.error(1001,"删除动态失败！");
        }
    }
}
