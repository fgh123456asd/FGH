package com.ruoyi.work.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.Result;
import com.ruoyi.system.service.ISysNoticeService;
import com.ruoyi.work.domain.*;
import com.ruoyi.work.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SJY
 * @description 开发接口
 * @date 2021/11/19
 */
@RestController

@RequestMapping("/open")
@CrossOrigin
@Api(value = "前台接口控制器",tags={"公开访问接口"})
public class OpenController extends BaseController {

    /**
     * =======================================================
     * 接口案例开始
     * =======================================================
     */
    @ApiOperation("GET开发测试接口1")
    @GetMapping("/test1")
    public Result test1(){
        return Result.success("开发接口1");
    }

    @ApiOperation("GET开发测试接口2")
    @ApiImplicitParam(name = "parma1",value = "参数1",required = true)
    @GetMapping("/test2")
    public Result test2(String parma1){
        return Result.success("开发接口2",parma1);
    }

    @ApiOperation("GET开发测试接口3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parma1",value = "参数1",required = true),
            @ApiImplicitParam(name = "parma2",value = "参数2",required = true)
    })
    @GetMapping("/test3")
    public Result test3(String parma1,String parma2){
        Map map = new HashMap();
        map.put("parma1",parma1);
        map.put("parma2",parma2);
        return Result.success("开发接口3",map);
    }

    @ApiOperation("GET开发测试接口4")
    @GetMapping("test4/{id}")
    @ApiImplicitParam(name = "id",value = "ID",paramType = "path",required = true)
    public Result test4(@PathVariable Integer id){
        return Result.success("开发接口4",id);
    }

    @ApiOperation("POST开发测试接口5")
    @PostMapping("test5")
    @ApiImplicitParam(name = "parma1",value = "参数1",required = true)
    //访问的时候必须设置content-type为application/x-www-form-urlencoded
    public Result test5(String parma1){
        return Result.success("开发接口5",parma1);
    }

    @ApiOperation("POST开发测试接口6")
    @PostMapping("test6")
    //访问的时候必须设置content-type为application/json
    public Result test5(@RequestBody Result result){
        return result;
    }

    @ApiOperation("POST开发测试接口7")
    @PostMapping("test7")
    //访问的时候必须设置content-type为application/json
    @ApiImplicitParam(name = "map",value = "参数1")
    public Result test7(@RequestBody Map map){
        return Result.success("开发接口7",map);
    }
    /**
     * =======================================================
     * 接口案例结束
     * =======================================================
     */
    @Autowired
   private IBannerService bannerService;
    @Autowired
    ITeacherService teacherService;
    @Autowired
   private IEmpService empService;

    @ApiOperation("轮播图")
    @GetMapping("/getBanner")
    @CrossOrigin
    public Result getBanner(Integer integer){
        limit(integer);
         QueryWrapper<Banner> queryWrapper= Wrappers.query();
         queryWrapper.lambda()
                 .eq(Banner::getStatus,1)
                 .orderByDesc(Banner::getOrderNum);
         List<Banner> list = bannerService.list(queryWrapper);
            return Result.success(list);
    }

    @ApiOperation("导师")
@GetMapping("/getTeacher")
    public  Result getTeacher(Teacher teacher){

        List<Teacher> list1 = teacherService.selectTeacherList(teacher);
        return Result.success(list1);
    }

//    @ApiOperation("员工")
//    @GetMapping("/getEmp")
//    public  Result getEmp(Emp emp){
//        startPage();
//        QueryWrapper<Emp> queryWrapper2 = Wrappers.query();
//        queryWrapper2.lambda()
//                .eq(Emp::getStatus,1)
//                .eq(Emp::getPotos,emp.getPotos())
//                .like(Emp::getName,emp.getName());
//        List<Emp> list2 = empService.list(queryWrapper2);
//        return Result.success(list2);
//    }

//    @Autowired
//    private INewsService newsService;
//    @ApiOperation("查询新闻")
//    @PostMapping("/newList")
//    public Result newsList(@RequestBody News news){
//        startPage();
//        List<News> news1 = newsService.selectNewsList(news);
//        return Result.success(news1);
//    }

    @Autowired
    private IProductService productService;

    @ApiOperation("产品接口")
    @GetMapping ("/productList")
    public Result productList( Product product){
        List<Product> products = productService.selectProductList(product);
        return Result.success(products);
    }

//    @Autowired
//    private IExanmpleService exanmpleService;
//
//    @ApiOperation("案例接口")
//    @PostMapping("/exanmpleList")
//    public Result exanmpleList(@RequestBody Exanmple exanmple){
//        startPage();
//        List<Exanmple> exanmples = exanmpleService.selectExanmpleList(exanmple);
//        return Result.success(exanmples);
//    }


    @Autowired
    private ILogoService logoService;

    @GetMapping("/getLogo")
    @ApiOperation("商标接口")
    public Result getLogo(String name){
        QueryWrapper<Logo> queryWrapper2 = Wrappers.query();
        queryWrapper2.lambda()
                .like(Logo::getName,name);
        List<Logo> list = logoService.list(queryWrapper2);
        return Result.success(list);
    }

    private static final Integer SIZE = 10;   //分页一页显示几条数据
    @ApiOperation("员工")
    @GetMapping("/getEmp")
    public  Result Emp(  Integer cur,Emp emp ){
        if(cur == null){
            cur = 1;
        }
//        QueryWrapper<Emp> queryWrapper2 = Wrappers.query();
//        queryWrapper2.lambda()
//                .eq(Emp::getStatus,1)
//                .eq(Emp::getPotos,emp.getPotos())
//                .like(Emp::getName,emp.getName());

        Page<Emp> page = new Page<>(cur,SIZE);
         IPage<Emp> empIPage = empService.selectEmpPage(page, emp);

        return Result.success(empIPage);
    }

@Autowired
private INewsService newsService;
    private static final Integer SIZE1 = 6;   //分页一页显示几条数据
    @GetMapping("/newList")
    @ApiOperation("新闻动态")
    public Result New1(News news){
//        QueryWrapper<New1> queryWrapper2 = Wrappers.query();
//        queryWrapper2.lambda()
//                .eq(New1::getStatus,1)
//                .eq(New1::getCreatime,new1.getCreatime())
//                .like(New1::getTitle,new1.getTitle());

        Page<News> page = new Page<>(1,SIZE1);
         IPage<News> newsIPage = newsService.selectNewsPage(page,news);
        return Result.success(newsIPage);
    }


    @Autowired
    private ICasesService casesService;
    @GetMapping("/caseList")
    @ApiOperation("经典案例")
    public Result Cases(Cases cases){
        Page<Cases> page = new Page<>(1,SIZE1);
        IPage<Cases> casesIPageIPage = casesService.selectCasesPage(page,cases);
        return Result.success(casesIPageIPage);
    }


    @Autowired
    private INeedsService needsService;
    @PostMapping("/need")
    @ApiOperation("需求")
    public Result Need ( @RequestBody Needs needs){
        final boolean save = needsService.save(needs);
        if (save){
            return Result.success("添加成功");
        }else
            return Result.error();
    }
}
