package com.CS5500.springbootinfrastructure.repos;

import com.CS5500.springbootinfrastructure.dao.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DateLogRepository extends CrudRepository<DateLog, Date> {

    @Query(value = "select d from DateLog d where d.date = ?1")
    DateLog getDateLogByDateIs(Date curr);

    @Query(value = "select d from DateLog d")
    List<DateLog> getJSONDates();

    @Query(value = "select t from Type t")
    List<Type> getJSONTypes();

    @Query(value = "select t from Type t where t.typeID = ?1")
    Type getTypeById(long tid);

    @Query(value = "select a from Activity a")
    List<Activity> getJSONActivities();

    @Query(value = "select m from Move m")
    List<Move> getJSONMoves();

    @Query(value = "select p from Place p")
    List<Place> getJSONPlaces();

    @Modifying
    @Query(value = "delete from Type t where t.typeID = ?1")
    void deleteTypeById(long id);
}
