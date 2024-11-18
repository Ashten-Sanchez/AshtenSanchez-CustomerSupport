package com.ashtensanchezcustomersupport.site;

import com.ashtensanchezcustomersupport.entities.Attachment;

public interface AttachmentRepository extends GenericRepository<Long, Attachment>{
    Attachment getByTicketId(Long ticketId);
}
