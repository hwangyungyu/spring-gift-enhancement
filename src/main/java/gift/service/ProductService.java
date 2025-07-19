package gift.service;

import gift.Entity.Product;
import gift.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // if문으로 제어해보기
    public boolean validateProduct(Product product, BindingResult bindingResult) {
        if (product.getName().contains("카카오") && !product.getMDapproved()) {
            bindingResult.rejectValue("name", "forbidden.word", "상품 이름에 '카카오'는 포함할 수 없습니다.");
            return false;
        }
        return true;
    }

    // try catch문으로 제어해보기
    public void validateProductException(Product product) {
        if (product.getName().contains("카카오") && !product.getMDapproved()) {
            throw new IllegalArgumentException("상품 이름에 '카카오'는 포함할 수 없습니다.");
        }
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
