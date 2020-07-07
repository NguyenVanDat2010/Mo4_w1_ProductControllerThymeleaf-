package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import product.model.Product;
import product.service.impl.IProductService;

@Controller
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/")
    public ModelAndView showProductList(){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products",productService.findAll());
        return modelAndView;
    }

    /**    Tạo mới      */
    @GetMapping("product/create")
    public ModelAndView createCustomer(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product",new Product());
        return modelAndView;
    }

    //Sử dụng redirect để gửi dữ liệu đi 1 lần và ko save lại khi reload lại trang
    @PostMapping("product/save")
    public ModelAndView saveCustomer(Product product, RedirectAttributes redirectAttributes){
        product.setId((int) (Math.random()*1000));
        productService.save(product);
        redirectAttributes.addFlashAttribute("success","Saved customer successfully!");
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    /**    Sửa product      */
    @GetMapping("product/{id}/edit")
    public ModelAndView editCustomer(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product",productService.findById(id));
        return modelAndView;
    }

    @PostMapping("product/update")
    public ModelAndView updateCustomer(Product product,RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        productService.update(product.getId(),product);
        redirectAttributes.addFlashAttribute("success", "Modified customer successfully!");
        return modelAndView;
    }

    /**    Xoa product      */
    @GetMapping("product/{id}/delete")
    public ModelAndView deleteCustomerForm(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("product/delete");
        modelAndView.addObject("product",productService.findById(id));
        return modelAndView;
    }

    @PostMapping("product/delete")
    public ModelAndView deleteCustomer(Product product, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        productService.remove(product.getId());
        redirectAttributes.addFlashAttribute("success", "Removed customer successfully!");
        return modelAndView;
    }

    @GetMapping("product/{id}/view")
    public ModelAndView viewCustomerForm(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("product/view");
        modelAndView.addObject("product",productService.findById(id));
        return modelAndView;
    }
}
