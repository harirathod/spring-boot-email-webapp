package com.web.webapp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface that provides methods for accessing and modifying the email data storage.
 * @author hari_rathod
 * @version 2023.06.26
 */
public interface EmailRepository extends JpaRepository<Email, Long>
{ }
