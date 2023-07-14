package com.web.webapp;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface that provides methods for accessing and modifying the email data storage.
 * @author hari_rathod
 * @version 2023.06.26
 */
public interface EmailRepository extends JpaRepository<Email, Long>
{
    /**
     * Gets a list of all emails, sorted in descending timestamp order.
     * @return A list of all emails, sorted in descending timestamp order.
     */
    default List<Email> findAllSortedByTimestamp() {
        return this.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }
}
