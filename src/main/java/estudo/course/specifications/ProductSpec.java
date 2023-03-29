package estudo.course.specifications;

import org.springframework.data.jpa.domain.Specification;

import estudo.course.entities.Product;

public class ProductSpec {
	public static Specification<Product> porNome(String name) {
        return (product, query, builder) -> builder.like(builder.lower(product.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> porCategoria(Long categoryId) {
        return (product, query, builder) -> builder.isMember(categoryId, product.get("categories"));
    }	
	
}
