package db.services;

import db.dao.AutoDao;
import db.models.Auto;

import java.util.List;

public class AutoService {
    private final AutoDao autoDao = new AutoDao();

    public AutoService() {
    }

    public Auto findAutoById(int id) {
        return autoDao.findById(id);
    }

    public List<Auto> findAllAutos() {
        return autoDao.findAll();
    }
}
