package gift.service;

import gift.Entity.Member;
import gift.Entity.Product;
import gift.Entity.Wish;
import gift.Entity.WishId;
import gift.repository.WishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // 찜 추가
    public void addWish(Member member, Product product) {
        Wish wish = new Wish(member, product);
        wishRepository.save(wish);
    }

    // 찜 삭제
    public void removeWish(Member member, Product product) {
        WishId id = new WishId(member.getId(), product.getId());
        wishRepository.deleteById(id);
    }

    public Page<Product> getWishedProducts(Member member, Pageable pageable) {
        Page<Wish> wishes = wishRepository.findByMemberIdOptimized(member.getId(), pageable);
        return wishes.map(Wish::getProduct); // Wish → Product 매핑
    }
}
