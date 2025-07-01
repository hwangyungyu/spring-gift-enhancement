package gift.admin;

import gift.Entity.Product;
<<<<<<< HEAD
import gift.dto.ProductDao;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
=======
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
>>>>>>> 26f9270 (step0 (#70))

@Controller
@RequestMapping("/admin/products")
public class AdminController {

<<<<<<< HEAD
    private final ProductDao productDao;
    private final ProductService productservice;

    public AdminController( ProductService productservice, ProductDao productDao) {
        this.productservice = productservice;
        this.productDao = productDao;
=======
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public AdminController() {
        // 샘플 데이터 등록
        Long id = idGenerator.getAndIncrement();
        products.put(id, new Product(id, "아이스 카페 아메리카노 T", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
>>>>>>> 26f9270 (step0 (#70))
    }

    // 상품 목록 페이지
    @GetMapping
    public String list(Model model) {
<<<<<<< HEAD
        List<Product> products = productDao.showProducts();
        model.addAttribute("products", products);
=======
        model.addAttribute("products", products.values());
>>>>>>> 26f9270 (step0 (#70))
        return "admin/list";
    }

    // 상품 등록 폼
<<<<<<< HEAD
    // 메소드 이름 중 첫 글자는 소문자로 시작하도록 통일
    @GetMapping("/add")
    public String createForm(Model model) {
=======
    @GetMapping("/add")
    public String CreateForm(Model model) {
>>>>>>> 26f9270 (step0 (#70))
        model.addAttribute("product", new Product());
        model.addAttribute("formType", "add");
        return "admin/form";
    }

    // 상품 등록 처리 -> 상품 등록에서  method="post" 로 등록 해놓았기 때문에 이곳으로 보내짐
    // 받은 후에 DB에 저장해줌
    @PostMapping
<<<<<<< HEAD
    public String createProduct(@ModelAttribute @Valid Product product,
                                BindingResult bindingResult,
                                Model model) {
        if (!productservice.validateProduct(product, bindingResult)) {
            model.addAttribute("formType", "add");
            return "admin/form";
        }

        productDao.insertProduct(product);
=======
    public String createProduct(@ModelAttribute Product product) {
        long id = idGenerator.getAndIncrement();
        product.setId(id);
        products.put(id, product);
        // update된 사항을 반영하기 위해 "redirect"
>>>>>>> 26f9270 (step0 (#70))
        return "redirect:/admin/products";
    }

    // 상품 수정 폼
<<<<<<< HEAD
    // 메소드 이름 중 첫 글자는 소문자로 시작하도록 통일
    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productDao.selectProduct(id);
=======
    @GetMapping("/{id}/edit")
    public String EditProduct(@PathVariable Long id, Model model) {
        Product product = products.get(id);
>>>>>>> 26f9270 (step0 (#70))
        model.addAttribute("product", product);
        model.addAttribute("formType", "edit");
        return "admin/form";
    }

    // 상품 수정 처리
    @PostMapping("/{id}")
<<<<<<< HEAD
    public String updateProduct(@PathVariable Long id, @ModelAttribute @Valid Product product,
                                BindingResult bindingResult,
                                Model model) {
        if (!productservice.validateProduct(product, bindingResult)) {
            model.addAttribute("formType", "add");
            return "admin/form";
        }

        productDao.updateProduct(id, product);
=======
    public String UpdateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        products.put(id, product);
>>>>>>> 26f9270 (step0 (#70))
        return "redirect:/admin/products";
    }

    // 상품 삭제 처리
<<<<<<< HEAD
    // 메소드 이름 중 첫 글자는 소문자로 시작하도록 통일
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productDao.deleteProduct(id);
        return "redirect:/admin/products";
    }

}
=======
    @PostMapping("/{id}/delete")
    public String DeleteProduct(@PathVariable Long id) {
        products.remove(id);
        return "redirect:/admin/products";
    }

}
>>>>>>> 26f9270 (step0 (#70))
