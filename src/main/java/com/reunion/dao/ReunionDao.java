package com.reunion.dao;

import com.reunion.dominio.Reunion;

import javax.persistence.Query;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.reunion.utils.EntityManagerUtil.getEntityManager;

public class ReunionDao extends AbstractDao<Reunion>{
    public ReunionDao() {
        setClazz(Reunion.class);
    }

    public Reunion proximaReunion() {
        String qlString = "FROM " + Reunion.class.getName() + " WHERE fecha > now() order by fecha ";
        System.out.println("Test "+qlString);
        Query query = getEntityManager().createQuery(qlString).setMaxResults(1);
        return (Reunion) query.getSingleResult();
    }
    public List<Reunion> reunionesManyana() {
        String qlString = "FROM " + Reunion.class.getName() + " WHERE fecha between ?1 and ?2";
        Query query = getEntityManager().createQuery(qlString);
        LocalDate manyana = LocalDate.now().plus(100, ChronoUnit.DAYS);
        query.setParameter(1, manyana.atStartOfDay());
        query.setParameter(2, manyana.plus(1, ChronoUnit.DAYS).atStartOfDay());
        return query.getResultList();
    }

}
