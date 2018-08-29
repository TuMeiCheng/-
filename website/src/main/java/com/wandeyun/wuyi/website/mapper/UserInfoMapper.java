package com.wandeyun.wuyi.website.mapper;

import com.wandeyun.wuyi.website.bean.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoMapper {

    /** 查询全部
     * @param: []
     * @return: java.util.List<com.wandeyun.wuyi.website.bean.UserInfo> */
    @Select("select * from user_info")
    @Results({
            @Result(property = "userinfoId" ,column = "userinfo_id"),
            @Result(property = "email" ,column = "email")
    })
    List<UserInfo> getAll();

    /** 根据id查询
     * @param: [id]
     * @return: com.wandeyun.wuyi.website.bean.UserInfo */
    @Select("SELECT * FROM user_info WHERE userinfo_id = #{id}")
    @Results({
            @Result(property = "phone" ,column = "phone"),
            @Result(property = "email" ,column = "email")
    })
    UserInfo getById(Integer id);

    /** 添加
     * @param: [user]
     * @return: void */
    @Insert("INSERT INTO user_info(content,create_time,email,modify_by,phone,status,username) VALUES(#{content}, #{createTime}, #{email}, #{modifyBy}, #{phone}, #{status}, #{userName})")
    void insert(UserInfo user);





}
