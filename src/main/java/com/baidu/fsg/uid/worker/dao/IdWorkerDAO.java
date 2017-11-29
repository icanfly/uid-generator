package com.baidu.fsg.uid.worker.dao;

import com.baidu.fsg.uid.worker.entity.IdWorkerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author luopeng
 * created at 2017/11/27
 */
@Repository
public interface IdWorkerDAO {

    void saveOrUpdate(IdWorkerEntity worker);

    IdWorkerEntity findWorker(@Param("host") String host, @Param("hostMac") String hostMac);

    void heartbeat(@Param("host") String host, @Param("hostMac") String hostMac);

}
