
package org.springframework.samples.petclinic.owner;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SPEK1
 */
public interface ImageRepository extends Repository<Image, Integer>{
     
    @Transactional(readOnly = true)
     Collection<Image> findAll() throws DataAccessException;
     
     void save(Image image) throws DataAccessException;
}

