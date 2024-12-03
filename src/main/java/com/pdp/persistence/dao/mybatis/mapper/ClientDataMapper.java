package com.pdp.persistence.dao.mybatis.mapper;

import com.pdp.persistence.dao.mybatis.model.ClientModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface ClientDataMapper {

    //todo add
    @Select("""
            SELECT ID,
                   IDENTIFICATION_NUMBER
            FROM CLIENT;
            """)
    @Results
    Optional<ClientModel> findById(@Param("id") UUID id);

    @Select("""
            SELECT ID,
                   IDENTIFICATION_NUMBER
            FROM CLIENT
            WHERE ID = #{id};
            """)
    List<ClientModel> findAll();

    @Insert("""
            INSERT INTO CLIENT (ID, CLIENT_INFO_ID, IDENTIFICATION_NUMBER)
            VALUES (#{clientModel.id}, #{clientInfoId}, #{clientModel.identificationNumber});
            """)
    void save(ClientModel clientModel, UUID clientInfoId);

    @Delete("""
            DELETE FROM CLIENT
            WHERE ID = #{id};
            """)
    boolean deleteById(UUID id);

    @Select("""
            UPDATE CLIENT
            SET CLIENT_INFO_ID, IDENTIFICATION_NUMBER
            RETURNING ID, CLIENT_INFO_ID, IDENTIFICATION_NUMBER;
            """)
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    ClientModel update(ClientModel clientModel);
}