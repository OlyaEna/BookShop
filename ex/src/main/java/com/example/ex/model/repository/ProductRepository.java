package com.example.ex.model.repository;

import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.security.PermitAll;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);

    @Query("select p from Product p")
    Page<Product> pageProduct(Pageable pageable);

    @Query("select p from Product p where p.description like %?1% or p.title like  %?1%")
    Page<Product> searchProducts(String keyword, Pageable pageable);

    @Query("select p from Product p where p.description like %?1% or p.title like %?1%")
    List<Product> searchProductsList(String keyword);

    List<Product> findProductsByGenre_Id (Long id);

    List<Product> findProductsByAuthor_Id(Long id);

    List<Product> findProductsBySeries_Id(Long id);

    List<Product> findProductsByCategory_Id(Long id);

    @Query(value = "select * from products p where p.bestseller = true and p.is_activated = true order by rand() asc limit 5 ", nativeQuery = true)
    List<Product> listViewProducts();

    @Query(value = "select * from products p where p.novelty = true and p.is_activated = true order by rand() asc limit 5 ", nativeQuery = true)
    List<Product> listNewProducts();

    @Query( "select p from Product p where p.bestseller = true and p.is_activated = true")
    List<Product> bestseller();

    @Query( "select p from Product p where p.novelty = true and p.is_activated = true")
    List<Product> novelty();
    @Query(value = "select * from products p where p.is_activated = true order by rand() asc limit 5 ", nativeQuery = true)
    List<Product> exampleProducts();

    Product findProductsByIdAndAndTitle(Long id, String title);
    @Query("select p from Product p where p.is_activated = true and p.title like  %?1% or p.author.fio like %?1% ")
    List<Product> search(String keyword);

    @Query( "select p from Product p where p.selection = true and p.is_activated = true")
    List<Product> selection();

}