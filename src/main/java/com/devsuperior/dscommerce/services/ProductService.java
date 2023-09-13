package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service /*ja registra ele como componente, pode ser @Component ou @Service*/
public class ProductService {

    @Autowired /*ele vai injetar o Repository aqui, porque ele vai la no Repository pegar a informacao */
    private ProductRepository repository;

    /*vai no banco de dados buscar o produto e converter pra DTO e retornar*/
    @Transactional(readOnly = true)/*como nao to salvando nada no banco, é 'somente leitura', isso é pra nao dar lock */
    public ProductDTO findById(Long id) { /*retorna um DTO e nao um Product. O Service devolve DTO para o Controller*/
        Optional<Product> result = repository.findById(id);/*fui no banco de dados Repository e busquei o produto com esse id */
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);/*instancia um DTO aqui para ele transformar os dados que vieram do Repository */
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) { /*page é um tipo especial de lista*/
        Page<Product> result = repository.findAll(pageable);/*busca todos os products*/
        return result.map(x -> new ProductDTO(x));
    }
}
