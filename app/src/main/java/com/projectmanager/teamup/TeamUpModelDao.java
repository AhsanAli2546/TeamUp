package com.projectmanager.teamup;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.projectmanager.teamup.Models.ProjectModel;

@Dao
public interface TeamUpModelDao {
    @Insert
    void UserData(TeamUpModel teamUpModel);

    //To check or confirm data from table user
    @Query("SELECT EXISTS (select * from MyTeam where email=:email AND password=:password)")
    boolean checkDetail(String email,String password);



}
