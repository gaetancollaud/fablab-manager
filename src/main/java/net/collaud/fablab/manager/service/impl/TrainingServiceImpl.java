package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.TrainingRepository;
import net.collaud.fablab.manager.data.TrainingEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>Training</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.TRAINING_VIEW})
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingDAO;

    @Override
    @Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
    public List<TrainingEO> findAll() {
        return new ArrayList(new HashSet(trainingDAO.findAll()));
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
    public Optional<TrainingEO> getById(Integer id) {
        return trainingDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public TrainingEO save(TrainingEO training) {
        if (training.getId() == null) {
            training.setId(0);
        }
        if (training.getId() > 0) {
            TrainingEO old = trainingDAO.findOneDetails(training.getId()).get();
            old.setName(training.getName());
            old.setPrice(training.getPrice());
            old.setNote(training.getNote());
            old.setTrainingLevel(training.getTrainingLevel());
            old.setMachineType(training.getMachineType());
            old.setPrerequisites(training.getPrerequisites());
            old.setActive(training.isActive());
            return trainingDAO.saveAndFlush(old);
        } else {
            return trainingDAO.saveAndFlush(training);
        }
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public void remove(Integer id) {
        TrainingEO current = trainingDAO.findOne(id);
        current.setActive(false);
        trainingDAO.saveAndFlush(current);
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
    public TrainingEO getId(String name) {
        return trainingDAO.getId(name);
    }
}
