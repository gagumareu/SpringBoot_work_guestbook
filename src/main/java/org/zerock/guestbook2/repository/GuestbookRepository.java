package org.zerock.guestbook2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.guestbook2.Entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {
}
