package gift.Entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wish")
@IdClass(WishId.class)
public class Wish {

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Wish() {}

    public Wish(Member member, Product product) {
        this.member = member;
        this.product = product;
    }

    public Member getMember() { return member; }
    public Product getProduct() { return product; }

    public void setMember(Member member) { this.member = member; }
    public void setProduct(Product product) { this.product = product; }
}
