package assignment.blog.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import assignment.blog.entity.SearchWord;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SearchWordRepositoryTest {
    @Autowired
    private SearchWordRepository searchWordRepository;

    @DisplayName("검색어를 저장한다.")
    @Test
    void saveSearchWordTest() {
        SearchWord searchWord = new SearchWord("기굥이");

        SearchWord savedSearchWord = searchWordRepository.save(searchWord);
        Optional<SearchWord> findSearchWord = searchWordRepository.findById(savedSearchWord.getId());

        assertThat(savedSearchWord).isEqualTo(findSearchWord.get());
    }
}