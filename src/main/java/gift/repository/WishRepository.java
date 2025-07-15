package gift.repository;

import gift.Entity.Wish;
import gift.Entity.WishId;
import gift.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, WishId> {

    List<Wish> findByMember(Member member);
}

