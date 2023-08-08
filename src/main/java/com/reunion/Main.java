package com.reunion;

import com.reunion.dao.ActaDao;
import com.reunion.dao.ReunionDao;
import com.reunion.dao.SalaDao;
import com.reunion.dominio.Acta;
import com.reunion.dominio.Persona;
import com.reunion.dominio.Reunion;
import com.reunion.dominio.Sala;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ReunionDao dao = new ReunionDao();
        List<Reunion> reuniones = dao.getAll();
        System.out.println("*** " + reuniones);

        Persona marta = new Persona("E001b", "Marta", "García López");
        Persona pedro = new Persona("E002b", "Pedro", "Gómez Fernández");

        Reunion r = new Reunion(LocalDateTime.now(), "Reunión de Test");
        System.out.println(r);

        Reunion r1 = new Reunion(LocalDateTime.now(), "Otra Reunión de Test");
        pedro.addReunion(r1);
        dao.save(r1);

        r.addParticipante(marta);
        r.addParticipante(pedro);
        dao.save(r);
        System.out.println(r);

        ActaDao actaDao = new ActaDao();
        Acta a = new Acta("Reunión anulada", r);
        actaDao.save(a);

        Reunion r2 = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Reunión de pasado mañana");
        dao.save(r2);

        try {
            System.out.println("Tu próxima reunión es: " + dao.proximaReunion());
        } catch (NoResultException nre) {
            System.out.println("No tienes ninguna reunión a la vista");
        }

        Reunion r3 = new Reunion(LocalDateTime.now().plus(1, ChronoUnit.DAYS), "Reunión de mañana");
        dao.save(r3);

        List<Reunion> reunionesManyana = dao.reunionesManyana();
        System.out.println("Para mañana: " + reunionesManyana);


//        System.out.println("Hello World!");
//        ReunionDao dao = new ReunionDao();
//        List<Reunion> reunions = dao.getAll();
//        System.out.println(reunions);
//
//        Reunion reunion = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Reunion paso manana");
//        dao.save(reunion);
//        System.out.println(dao.proximaReunion());
//        System.out.println(reunion);
//
//        ReunionDao dao1 = new ReunionDao();
//        List<Reunion> reuniones = dao1.getAll();
//        System.out.println("*** " + reuniones);
//
//        Reunion r = new Reunion(LocalDateTime.now(), "Reunión de Test");
//        System.out.println(r);
//
//        dao.save(r);
//        System.out.println(r);
//
//        Reunion r2 = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Reunión de pasado mañana");
//        dao.save(r2);
//
//        try {
//            System.out.println("Tu próxima reunión es: " + dao.proximaReunion());
//        } catch (NoResultException nre) {
//            System.out.println("No tienes ninguna reunión a la vista");
//        }
//
//        Reunion r3 = new Reunion(LocalDateTime.now().plus(1, ChronoUnit.DAYS), "Reunión de mañana");
//        dao.save(r3);
//
//        List<Reunion> reunionesManyana = dao.reunionesManyana();
//        System.out.println("Para mañana: " + reunionesManyana);


//        SalaDao salaDao = new SalaDao();
//        Sala s = new Sala("S203", "Sala grande", 25);
//        salaDao.save(s);
//
//        System.out.println("Paso 1 " + salaDao.getAll());
//
//        s.setDescripcion("Sala Grande reformada");
//        salaDao.update(s);
//
//        System.out.println("Paso 2 " + salaDao.getAll());
//
//        Sala s2 = new Sala("99", "Trastero", 1);
//        salaDao.save(s2);
//
//        System.out.println("Paso 3 " + salaDao.getAll());
//
//        salaDao.delete(s2);
//
//        System.out.println("Paso 4 " + salaDao.getAll());

    }
}