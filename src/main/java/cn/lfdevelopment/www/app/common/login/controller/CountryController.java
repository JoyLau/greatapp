/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.app.common.login.controller;

import cn.lfdevelopment.www.app.common.login.pojo.Country;
import cn.lfdevelopment.www.app.common.login.service.CountryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping
    public String getAll(Country country,Model model) {
        List<Country> countryList = countryService.getAll(country);
        model.addAttribute("pageInfo", new PageInfo<>(countryList));
        model.addAttribute("queryParam", country);
        model.addAttribute("page", country.getPage());
        model.addAttribute("rows", country.getRows());
        return "index1";
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("country", new Country());
        return "view";
    }

    @RequestMapping(value = "/view/{id}")
    public String view(@PathVariable Integer id,Model model) {
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "view";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        countryService.deleteById(id);
        ra.addFlashAttribute("msg", "删除成功!");
        return "redirect:/countries";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Country country,Model model) {
        String msg = country.getId() == null ? "新增成功!" : "更新成功!";
        countryService.save(country);
        model.addAttribute("country", country);
        model.addAttribute("msg", msg);
        return "view";
    }
}
