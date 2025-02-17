package ru.project.quiz.dao;

import org.springframework.stereotype.Repository;
import ru.project.quiz.domain.entity.quiz.Question;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {

    private final EntityManager em;

    public QuestionRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    /* @Query(value = "SELECT * FROM Questions ORDER BY RANDOM() LIMIT 1", nativeQuery = true)*/
    public Optional<Question> getRandomQuestion() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Question> root = query.from(Question.class);
        Long numOfQuestions = em.createQuery(query.select(criteriaBuilder.count(root))).getSingleResult();

        long randomQuestion = (long) (Math.random() * numOfQuestions) + 1;

        CriteriaQuery<Question> questionQuery = criteriaBuilder.createQuery(Question.class);
        Root<Question> questionRoot = questionQuery.from(Question.class);
        questionQuery.select(root).where(criteriaBuilder.equal(questionRoot.get("id"), randomQuestion));
        return Optional.ofNullable(em.createQuery(questionQuery).getSingleResult());
    }

}
