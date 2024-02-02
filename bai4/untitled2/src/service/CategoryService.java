package service;

import entity.entity.Phim;

import java.util.List;

public interface CategoryService {
    List<Phim> findAll();
    Phim insert(Phim phim);
    Phim delete(Phim phim);
    Phim update(Phim phim);
    Phim findById(int id);

}
