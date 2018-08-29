package com.wandeyun.wuyi.website.controller;

import com.wandeyun.wuyi.website.VO.ResultVO;
import com.wandeyun.wuyi.website.bean.Partners;
import com.wandeyun.wuyi.website.service.PartnersService;
import com.wandeyun.wuyi.website.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*合作伙伴
 *
 */
@RestController
@Slf4j
@RequestMapping("/api/partners")
public class PartnersController {

    @Autowired
    private PartnersService partnersService;


    //查询所有合作伙伴
    @RequestMapping("/findall")
    public ResultVO findall(@RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest request = new PageRequest(page, size);
        Page<Partners> partnersPage =  this.partnersService.findall(request);

        return ResultVOUtil.success( partnersPage);
    }


    //更新合作伙伴
    @RequestMapping("/update")
    public ResultVO update(Partners partners) {
        if (partners.getId() == null) {
           return  ResultVOUtil.error(1001,"更新合作伙伴失败！id必传");
        }
        try {
            Partners rest = this.partnersService.update(partners);
        }catch (Exception e){
            return ResultVOUtil.error(1001,"更新合作伙伴失败！");
        }
        return ResultVOUtil.success(partners);
    }

    //添加合作伙伴
    @RequestMapping("/insert")
    public ResultVO insert(Partners partners) {
        try {
            Partners rest = this.partnersService.update(partners);
        }catch (Exception e){
            ResultVOUtil.error(1001,"添加合作伙伴失败！");
        }
        return ResultVOUtil.success(partners);
    }

    //删除合作伙伴
    @RequestMapping("/delete")
    public ResultVO delete(@RequestParam("id") Integer id) {
        if (id == null) {
            return  ResultVOUtil.error(1001,"删除合作伙伴失败！id必传");
        }
        try {
             this.partnersService.delete(id);
        }catch (Exception e){
            ResultVOUtil.error(1001,"删除合作伙伴失败！e : "+e.getMessage());
        }
        return ResultVOUtil.success();
    }



}
