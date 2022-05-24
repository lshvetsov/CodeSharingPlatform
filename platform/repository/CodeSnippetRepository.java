package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.model.dao.CodeSnippetEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeSnippetRepository extends CrudRepository<CodeSnippetEntity, String> {

    List<CodeSnippetEntity> findAllByOrderByTimestampDesc();
    Optional<CodeSnippetEntity> findById(String id);
}
