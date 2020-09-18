package com.atguigu.guli.service.edu.controller.admin;


import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author chengfei
 * @since 2020-08-02
 */
@Api("讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {


    @Autowired
    TeacherService teacherService;


    @ApiOperation("分页讲师列表")
    @GetMapping("list/{page}/{limit}")
    public R List(@ApiParam(value = "当前页码",required = true)@PathVariable Long page,
                  @ApiParam(value = "每页记录数",required = true)@PathVariable Long limit,
                  @ApiParam (value = "讲师查询条件") TeacherQueryVo teacherQueryVo){
        Page<Teacher> pageParam = new Page<>(page, limit);
//        IPage<Teacher> pageModel = teacherService.page(pageParam);
        IPage<Teacher> teacherIPage = teacherService.selectPage(page, limit, teacherQueryVo);
        List<Teacher> records = teacherIPage.getRecords();
        long total = teacherIPage.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }




    @ApiOperation(value = "根据Id删除讲师",notes = "根据Id删除讲师")
    @DeleteMapping("delete/{id}")
    public  R deleteById(@PathVariable String id){
        boolean result = teacherService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return  R.error().message("数据不存在");
        }
    }



    @ApiOperation(value = "新增讲师")
    @PostMapping("save")
    public R save(@ApiParam(value = "讲师对象")@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return  R.ok().message("插入成功");
        }else{
            return R.error().message("插入失败");
        }
    }


    @ApiOperation(value = "编辑讲师")
    @PutMapping("update")
    public R updateById(@ApiParam(value = "讲师对象",required = true)@RequestBody Teacher teacher){
        boolean result = teacherService.updateById(teacher);
        if(result){
            return  R.ok().message("编辑成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation(value = "根据Id查询讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam(value = "讲师Id",required = true)@PathVariable String id ){
        Teacher teacher = teacherService.getById(id);
        if(teacher != null){
            return R.ok().data("item",teacher);
        }else{
            return  R.error().message("数据不存在");
    }
    }

}

