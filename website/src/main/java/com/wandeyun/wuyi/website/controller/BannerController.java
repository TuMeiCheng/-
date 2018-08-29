package com.wandeyun.wuyi.website.controller;

import com.wandeyun.wuyi.website.VO.ResultVO;
import com.wandeyun.wuyi.website.bean.Banner;
import com.wandeyun.wuyi.website.service.BannerService;
import com.wandeyun.wuyi.website.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.List;

/*
 *@author tmc
 *@date 2018/7/23 17:12
 * 轮播图控制层接口
 */
@RestController
@RequestMapping("/api/banner")
public class BannerController {

   @Autowired
   private BannerService bannerService;


   //查询全部轮播图
    @GetMapping("/list")
    public ResultVO findList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest request = new PageRequest(page, size);
        Page<Banner> bannerPage =  this.bannerService.findAll(request);
        return ResultVOUtil.success(bannerPage);
    }

    //添加轮播图
    @PostMapping("/add")
    public ResultVO add(@Valid Banner banner){
        Banner rest =  this.bannerService.insert(banner);
        return ResultVOUtil.success(rest);
    }

    //修改轮播图
    @PostMapping("/update")
    public ResultVO update(@Valid Banner banner){
        Banner rest =  this.bannerService.insert(banner);
        return ResultVOUtil.success(rest);
    }

    //删除轮播图
    @RequestMapping("/delete")
    public ResultVO delete(@RequestParam("id") Integer banId){
        try {
            this.bannerService.delete(banId);
            return ResultVOUtil.success();
        }catch (Exception e){
            return ResultVOUtil.error(1001,"删除轮播图失败！");
        }
    }



}
