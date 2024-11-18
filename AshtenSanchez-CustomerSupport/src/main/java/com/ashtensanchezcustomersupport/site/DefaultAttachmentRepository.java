package com.ashtensanchezcustomersupport.site;

import com.ashtensanchezcustomersupport.entities.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultAttachmentRepository extends GenericJpaRepository<Long, Attachment>
        implements AttachmentRepository{

    @Override
    public Attachment getByTicketId(Long ticketId) {

        try {

            return this.entityManager.createQuery("SELECT i FROM Attachment i " +
                    "WHERE i.ticketId = :id", Attachment.class).setParameter("id", ticketId)
                    .getSingleResult();
        }
        catch(Exception e) {

            return null;
        }
    }
}
