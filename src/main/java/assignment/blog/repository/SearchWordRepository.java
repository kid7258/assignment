package assignment.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import assignment.blog.entity.SearchWord;

public interface SearchWordRepository extends JpaRepository<SearchWord, Long> {

    Optional<SearchWord> findByKeyword(String keyword);

    @Modifying
    @Query("UPDATE SearchWord searchWord SET searchWord.count = searchWord.count + 1 WHERE searchWord.id = :id")
    void updateCount(@Param("id") Long id);

    @Query(value = "SELECT searchWord FROM SearchWord searchWord ORDER BY searchWord.count DESC")
    List<SearchWord> findAllByKeywordOrderByCountDesc();
}
