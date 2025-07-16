package gift.repository;

import gift.Entity.Wish;
import gift.Entity.WishId;
import gift.Entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, WishId> {
    Page<Wish> findByMember(Member member, Pageable pageable);
    List<Wish> findByMember(Member member);
}

