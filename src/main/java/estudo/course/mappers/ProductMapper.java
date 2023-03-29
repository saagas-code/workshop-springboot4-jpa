package estudo.course.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import estudo.course.entities.Product;
import estudo.course.resources.DTO.ProductDTO;

@Mapper
public interface ProductMapper {
	
	@Mapping(target = "id", ignore = true)
	Product toProduct(ProductDTO productDTO);
	
	ProductDTO toProductDTO(Product product);
}
