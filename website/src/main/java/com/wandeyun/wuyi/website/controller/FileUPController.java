package com.wandeyun.wuyi.website.controller;

import com.wandeyun.wuyi.website.VO.ResultVO;
import com.wandeyun.wuyi.website.enums.ResultEnum;
import com.wandeyun.wuyi.website.utils.KeyUtil;
import com.wandeyun.wuyi.website.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 *@author tmc
 *@date 2018/7/24 10:50
 * 文件上传接口
 */
@RequestMapping("/api/upload")
@Controller
public class FileUPController {

    @Value("${file.path}")
    private  String path;   //文件上传位置

    @RequestMapping("/")
    public String upload(){
        return "upload";
    }


    /** 实现文件上传
     * @param: []
     * @return: java.lang.String */
    @RequestMapping(value = "/file",method= RequestMethod.POST)
    @ResponseBody
    public ResultVO fileUpload(@RequestParam("fileName") MultipartFile file){
        if (file.isEmpty()) {
            return ResultVOUtil.error(ResultEnum.FILE_UPLOAD_EXIST.getCode(),ResultEnum.FILE_UPLOAD_EXIST.getMessage());
        }
        String fileName = file.getOriginalFilename();
        String  suffix = fileName.substring(fileName.lastIndexOf("."));
        int size = (int) file.getSize();
        String uploadFileName = KeyUtil.genUniqueKey()+suffix;


        File dest = new File(path + "/" + uploadFileName);
        System.out.println("文件上传地址："+dest.getPath());

        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件  dest.getPath()  /static/upload/3853584110306304.png
           return ResultVOUtil.success(("http://www.5151tech.com" + "/upload/" + uploadFileName));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResultVOUtil.error(500,e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResultVOUtil.error(500,e.getMessage());
        }
    }


    /**
     * 实现多文件上传
     * */
    @RequestMapping(value="/fileList",method= RequestMethod.POST)
    public @ResponseBody ResultVO multifileUpload(HttpServletRequest request){
        System.out.println(path);

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        if(files.isEmpty()){
            return ResultVOUtil.error(ResultEnum.FILE_UPLOAD_EXIST.getCode(),ResultEnum.FILE_UPLOAD_EXIST.getMessage());
        }

        List<String> url_list = new ArrayList<>();
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            String  suffix = fileName.substring(fileName.lastIndexOf("."));
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            String uploadFileName = KeyUtil.genUniqueKey()+suffix;

            if(file.isEmpty()){
                return ResultVOUtil.error(ResultEnum.FILE_UPLOAD_EXIST.getCode(),ResultEnum.FILE_UPLOAD_EXIST.getMessage());
            }else{
                File dest = new File(path + "/" + uploadFileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                    url_list.add(("http://www.5151tech.com" + "/upload/" + uploadFileName));
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return ResultVOUtil.error(500,e.getMessage());
                }
            }
        }
        return ResultVOUtil.success(url_list);
    }


}
