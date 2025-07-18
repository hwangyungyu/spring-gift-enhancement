package gift;

import gift.Entity.Member;
import gift.Entity.Product;
import gift.Entity.Wish;
import gift.repository.WishRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class WishRepositoryTest {

    @Autowired
    private WishRepository wishRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveWish() {
        // given
        Member member = entityManager.persist(new Member("test", "test@kakao.com", "123456789", "테스트", "테스트 주소", "USER"));
        Product product = entityManager.persist(new Product(5L ,"아메리카노", 2000, "https://test.com"));
        Wish wish = new Wish(member, product);

        // when
        wishRepository.save(wish);

        // then
        List<Wish> result = wishRepository.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMember().getId()).isEqualTo("testId");
        assertThat(result.get(0).getProduct().getName()).isEqualTo("아메리카노");
    }
}
