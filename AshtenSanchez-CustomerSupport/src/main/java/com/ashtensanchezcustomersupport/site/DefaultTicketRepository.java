package com.ashtensanchezcustomersupport.site;

import com.ashtensanchezcustomersupport.entities.TicketEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTicketRepository extends GenericJpaRepository<Long, TicketEntity>
        implements TicketRepository {

}
