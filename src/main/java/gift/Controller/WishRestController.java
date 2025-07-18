package gift.Controller;

import gift.Entity.Member;
import gift.Entity.Product;
import gift.annotation.LoginMember;
import gift.request.WishRequest;
import gift.response.ProductResponse;
import gift.service.ProductService;
import gift.service.WishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishes")
public class WishRestController {

    private final WishService wishService;
    private final ProductService productService;

    public WishRestController(WishService wishService, ProductService productService) {
        this.wishService = wishService;
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductResponse> getWishes(@LoginMember Member member, Pageable pageable) {
        return wishService.getWishedProducts(member, pageable)
                .map(ProductResponse::new);
    }

    @PostMapping
    public void addWish(@RequestBody WishRequest request, @LoginMember Member member) {
        Product product = productService.findById(request.getProductId());
        wishService.addWish(member, product);
    }

    @DeleteMapping
    public void removeWish(@RequestBody WishRequest request, @LoginMember Member member) {
        Product product = productService.findById(request.getProductId());
        wishService.removeWish(member, product);
    }
}
