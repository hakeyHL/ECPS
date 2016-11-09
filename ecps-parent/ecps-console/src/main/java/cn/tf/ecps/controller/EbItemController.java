package cn.tf.ecps.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tf.ecps.po.EbBrand;
import cn.tf.ecps.service.EbBrandService;

@Controller
@RequestMapping("/item")
public class EbItemController {
	
	@Autowired
	private EbBrandService brandService;
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "item/index";
	}
	
	/**
	 * 查询所有的品牌
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectBrandAll.do")
	public String selectBrandAll(Model model){
		List<EbBrand> bList = brandService.selectBrandAll();
		model.addAttribute("bList", bList);
		return "item/listbrand";
	}
	
	/**
	 * 跳转到添加品牌的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddBrand.do")
	public String toAddBrand(Model model){
		return "item/addbrand";
	}
	
	/**
	 * 验证品牌名称的重复性
	 * @param brandName
	 * @param out
	 */
	@RequestMapping("/validBrandName.do")
	public void validBrandName(String brandName, PrintWriter out){
		List<EbBrand> brandList = brandService.validBrandName(brandName);
		String flag = "yes";
		if(brandList.size() > 0){
			flag = "no";
		}
		out.write(flag);
		
	}
	
	@RequestMapping("/addBrand.do")
	public String addBrand(EbBrand brand){
		brandService.saveBrand(brand);
		return "redirect:selectBrandAll.do";
	}
	
	

}
