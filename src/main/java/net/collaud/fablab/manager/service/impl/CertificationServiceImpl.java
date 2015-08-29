package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import net.collaud.fablab.manager.dao.CertificationRepository;
import net.collaud.fablab.manager.data.CertificationEO;
import net.collaud.fablab.manager.data.TrainingEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.CertificationService;
import net.collaud.fablab.manager.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>Certification</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    private CertificationRepository certificationDAO;

    @Autowired
    private TrainingService trainingService;

    @Override
    @Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
    public List<CertificationEO> findAll() {
        return new ArrayList(new HashSet(certificationDAO.findAll()));
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
    public Optional<CertificationEO> getById(Integer id) {
        return certificationDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public CertificationEO save(CertificationEO certification) {
        if (certification.getId() == null) {
            certification.setId(0);
        }
        if (certification.getId() > 0) {
            CertificationEO old = certificationDAO.findOneDetails(certification.getId()).get();
            old.setName(certification.getName());
            old.setCertificationDate(certification.getCertificationDate());
            old.setCertificationPrice(certification.getCertificationPrice());
            old.setNote(certification.getNote());
            old.setTraining(certification.getTraining());
            old.setUsers(certification.getUsers());
            old.setActive(certification.isActive());
            return certificationDAO.saveAndFlush(old);
        } else {
            return certificationDAO.saveAndFlush(certification);
        }
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public void remove(Integer id) {
        CertificationEO current = certificationDAO.findOne(id);
        current.setActive(false);
        certificationDAO.saveAndFlush(current);
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
    public CertificationEO getId(String name) {
        return certificationDAO.getId(name);
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public boolean canCertify(Integer certificationId, Integer userId) {
        List<CertificationEO> userCertifications = certificationDAO.getCertificationsByUserId(userId);
        TrainingEO trainingOfCurrentCertification = getById(certificationId).get().getTraining();
        List<CertificationEO> trainingOfCurrentCertificationPrerequisitesCertifications = getCertifications(trainingOfCurrentCertification.getPrerequisites());
        /*printforCheck(certificationId, trainingOfCurrentCertification, userId, userCertifications,
         trainingOfCurrentCertificationPrerequisitesCertifications);*/
        if (!trainingOfCurrentCertificationPrerequisitesCertifications.isEmpty()) {
            return userCertifications.containsAll(trainingOfCurrentCertificationPrerequisitesCertifications);
        } else {
            return false;
        }
    }

    private void printforCheck(Integer certificationId, TrainingEO trainingOfCurrentCertification,
            Integer userId, List<CertificationEO> userCertifications,
            List<CertificationEO> trainingOfCurrentCertificationPrerequisitesCertifications) {
        System.out.println("Prerequisite of certification " + getById(certificationId).get().getName() + " : ");
        for (TrainingEO t : trainingOfCurrentCertification.getPrerequisites()) {
            System.out.println(t.getName() + " => " + certificationDAO.getCertification(t.getId()).getName());
        }
        System.out.println("(OU ALORS )");
        for (CertificationEO c : getCertifications(trainingOfCurrentCertification.getPrerequisites())) {
            System.out.println(c.getName());
        }
        System.out.println("Certification of user " + userId + " : ");
        for (CertificationEO c : userCertifications) {
            System.out.println(c.getName());
        }
        System.out.println("RESULT : " + userCertifications.containsAll(trainingOfCurrentCertificationPrerequisitesCertifications));
        //List<Integer> userCertificationsIds = new ArrayList<>();
        for (CertificationEO c : userCertifications) {
            for (CertificationEO c2 : trainingOfCurrentCertificationPrerequisitesCertifications) {
                System.out.println(c.getName() + " " + c2.getName() + " " + c.equals(c2));
            }
        }
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE, Roles.TRAINING_VIEW})
    public List<Integer> failedUser(Integer certificationId, List<Integer> userIds) {
        List<Integer> res = new ArrayList<>();
        CertificationEO c = getById(certificationId).get();
        if (c != null) {
            if (!c.getTraining().getPrerequisites().isEmpty()) {
                for (Integer userId : userIds) {
                    if (!canCertify(certificationId, userId)) {
                        res.add(userId);
                    }
                }
            }
        }
        return res;
    }

    private List<CertificationEO> getCertifications(Set<TrainingEO> prerequisites) {
        List<CertificationEO> res = new ArrayList<>();
        for (TrainingEO p : prerequisites) {
            CertificationEO c = certificationDAO.getCertification(p.getId());
            if (c != null) {
                res.add(c);
            }
        }
        return res;
    }
}
