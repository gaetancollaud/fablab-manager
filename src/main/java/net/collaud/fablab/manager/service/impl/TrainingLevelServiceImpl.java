package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.TrainingLevelRepository;
import net.collaud.fablab.manager.data.TrainingLevelEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.TrainingLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>TrainingLevel</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.TRAINING_VIEW})
public class TrainingLevelServiceImpl implements TrainingLevelService {

    @Autowired
    private TrainingLevelRepository trainingLevelDAO;

    @Override
    @Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
    public List<TrainingLevelEO> findAll() {
        return new ArrayList(new HashSet(trainingLevelDAO.findAll()));
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE,Roles.TRAINING_VIEW})
    public Optional<TrainingLevelEO> getById(Integer id) {
        return Optional.ofNullable(trainingLevelDAO.findOne(id));
    }

     @Override
    @Secured({Roles.TRAINING_MANAGE})
    public TrainingLevelEO save(TrainingLevelEO trainingLevel) {
        if (trainingLevel.getId() == null) {
            trainingLevel.setId(0);
        }
        if (trainingLevel.getId() > 0) {
            TrainingLevelEO old = trainingLevelDAO.findOne(trainingLevel.getId());
            old.setLabel(trainingLevel.getLabel());
            old.setActive(trainingLevel.isActive());
            return trainingLevelDAO.saveAndFlush(old);
        } else {
            return trainingLevelDAO.saveAndFlush(trainingLevel);
        }
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public void remove(Integer id) {
        TrainingLevelEO current = trainingLevelDAO.findOne(id);
        current.setActive(false);
        trainingLevelDAO.saveAndFlush(current);
    }
}

