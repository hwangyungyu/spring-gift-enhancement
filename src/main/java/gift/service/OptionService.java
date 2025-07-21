package gift.service;

import gift.Entity.Option;
import gift.repository.OptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    // 특정 상품의 옵션 목록 조회
    public List<Option> findOptionsByProductId(Long productId) {
        return optionRepository.findByProductId(productId);
    }

    // 옵션 수량 차감
    @Transactional
    public void subtractQuantity(Long productId, String optionName, int amount) {
        Option option = optionRepository.findByProductIdAndName(productId, optionName)
                .orElseThrow(() -> new IllegalArgumentException("해당 옵션이 존재하지 않습니다."));

        option.subtract(amount); // 핵심 로직: 재고 차감
        // JPA에서는 @Transactional 내에서 엔티티 변경 시 자동 반영됨 (dirty checking)
    }

    // 옵션 중복 여부 확인
    public boolean isDuplicateOption(Long productId, String optionName) {
        return optionRepository.existsByProductIdAndName(productId, optionName);
    }
}

