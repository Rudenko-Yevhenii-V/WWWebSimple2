package ry.rudenko.yevhenii.repository;


import java.util.List;
import java.util.UUID;
import ry.rudenko.yevhenii.entity.BaseEntity;

public interface IRepository {

  public List<BaseEntity> findAll();
  public BaseEntity findById(UUID id);
  public void save(BaseEntity baseEntity);
  public void update(BaseEntity baseEntity);
  public void delete(BaseEntity baseEntity);
}
