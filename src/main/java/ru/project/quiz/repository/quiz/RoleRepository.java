package ru.project.quiz.repository.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.quiz.domain.entity.ituser.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
