package com.wandeyun.wuyi.website.service.impl;

import com.wandeyun.wuyi.website.bean.UserInfo;
import com.wandeyun.wuyi.website.repository.UserInfoRepository;
import com.wandeyun.wuyi.website.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/*
 *@author tmc
 *@date 2018/7/21 14:53
 * 用户联系我们controller
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    //查询所有
    @Override
    public Page<UserInfo> findAll(Pageable request) {
        return userInfoRepository.findAll(request);
    }

    //新增
    @Override
    public boolean insert(UserInfo userInfo) {
        userInfo.setModifyBy(0);                   //最后修改人id  0:新提交
        userInfo.setCreateTime(new Date());        //创建时间
        userInfo.setStatus(0);                     //状态
        UserInfo rest = userInfoRepository.save(userInfo);
      if (rest != null){
          return true;
      }
      return false;
    }


}
