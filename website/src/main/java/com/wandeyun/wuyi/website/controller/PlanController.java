package com.wandeyun.wuyi.website.controller;

import com.wandeyun.wuyi.website.VO.ResultVO;
import com.wandeyun.wuyi.website.bean.Admin;
import com.wandeyun.wuyi.website.bean.Partners;
import com.wandeyun.wuyi.website.bean.Plan;
import com.wandeyun.wuyi.website.bean.PlanType;
import com.wandeyun.wuyi.website.dto.PlanTypeDTO;
import com.wandeyun.wuyi.website.enums.ResultEnum;
import com.wandeyun.wuyi.website.exception.WuYiException;
import com.wandeyun.wuyi.website.service.PlanService;
import com.wandeyun.wuyi.website.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/*解决方案
*
 */
@RestController
@Slf4j
@RequestMapping("/api/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    //查询所有解决方案，根据方案类型分组
    @GetMapping("/findPlanListAll")
   // @Cacheable(value="findPlanListAll")
    public ResultVO findPlanListAll(HttpServletRequest request){
        List<PlanTypeDTO> dtoList = this.planService.findPlanListAll();
        return ResultVOUtil.success(dtoList);
    }

    //根据方案类型id查询解决方案集合
    @GetMapping("/findPlanByTypeId")
   // @Cacheable(value="bytypeid")
    public ResultVO findPlanByPtypeId(@RequestParam("id") Integer typeId,
                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = new PageRequest(page, size);

        try {
            Page<Plan> planPage =  this.planService.findPlanByTypeId(typeId,request);
            return ResultVOUtil.success(planPage);
        } catch (WuYiException e) {
            e.printStackTrace();
            return ResultVOUtil.error(1002, e.getMessage());
        }
    }

    //根据方案id查询解决方案详情
    @GetMapping("/getPlanById")
    public ResultVO findPlanById(@RequestParam("id") Integer id) {
        try {
            Plan plan =  this.planService.getPlanById(id);
            return ResultVOUtil.success(plan);
        } catch (WuYiException e) {
            e.printStackTrace();
            return ResultVOUtil.error(1002, e.getMessage());
        }
    }

    //新增解决方案
    @PostMapping("/addPlan")
    public ResultVO addPlan(@Valid Plan plan){
        Plan rest = this.planService.insert(plan);
        return ResultVOUtil.success(rest);
    }

    //修改解决方案
    @PostMapping("/updatePlan")
    public ResultVO update(@Valid Plan plan){
        Plan rest = this.planService.insert(plan);
        return ResultVOUtil.success(rest);
    }

    //删除解决方案
    @PostMapping("/deletePlan")
    public ResultVO delete(@RequestParam("id") Integer planId){

        try {
            Boolean rest = this.planService.delete(planId);
            return ResultVOUtil.success(rest);
        }catch (Exception e){
            return ResultVOUtil.error(10002,"删除失败"+e.getMessage());
        }
    }
    //获取所有解决方案类型下拉框
    @RequestMapping("/getTypeSelect")
    public ResultVO findTypeSelect(){
        List<PlanType> rest = this.planService.getTypeSelect();
        return ResultVOUtil.success(rest);
    }


}
