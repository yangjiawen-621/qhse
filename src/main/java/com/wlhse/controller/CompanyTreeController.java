package com.wlhse.controller;

import com.wlhse.cache.JedisUtils;
import com.wlhse.entity.CompanyPojo;
import com.wlhse.service.CompanyService;
import com.wlhse.service.CompanyTreeService;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController("CompanyTreeController")
@RequestMapping("api/v3")
//组织机构
public class CompanyTreeController {

    @Resource
    private CompanyTreeService companyTreeService;

    @Resource
    private CompanyService companyService;

    @Resource
    private JedisUtils jedisUtils;

    @RequestMapping(value = "/company/tree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getCompanyTree(HttpServletRequest request) {
        String token=request.getHeader("Authorization");
        Jedis jedis=jedisUtils.getJedis();
        String id=jedis.hgetAll(token).get("employeeId");
        return companyService.listTreeCompany(Integer.parseInt(id)).toJSONString();
    }

    //qhse新增公司树查询
    @RequestMapping(value = "/qhse_company/tree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getQhseCompanyTree(HttpServletRequest request) {
        return companyService.listQhseTreeCompany(request).toJSONString();
    }

    //增加company
    @RequestMapping(value = "/company_tree", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String addCompany(@RequestBody(required = false) CompanyPojo companyPojo) {
        return companyTreeService.addCompanyTreeNode(companyPojo);
    }

    //删除company
    @RequestMapping(value = "/company_tree/{companyCode}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteCompany(@PathVariable("companyCode") String companyCode) {
        return companyTreeService.deleteCompany(companyCode);
    }

    //更新company
    @RequestMapping(value = "/company_tree", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateCompany(@RequestBody(required = false) CompanyPojo companyPojo) {
        return companyTreeService.updateCompany(companyPojo);
    }

//    @RequestMapping(value = "/contracting/company", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    public R addContractingCompany(@RequestBody(required = false) CompanyPojo companyPojo) {
//        return companyTreeService.addContractingCompany(companyPojo);
//    }
//
//    @RequestMapping(value = "/contracting/company", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
//    public String getContractingCompany(@ModelAttribute BaseGetDto dto,String name) {
//        return companyService.listContractingCompany(dto,name).toJSONString();
//    }
}
