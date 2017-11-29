package com.baidu.fsg.uid.worker;

import com.baidu.fsg.uid.utils.NamingThreadFactory;
import com.baidu.fsg.uid.utils.NetUtils;
import com.baidu.fsg.uid.worker.dao.IdWorkerDAO;
import com.baidu.fsg.uid.worker.entity.IdWorkerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 可重用的WorkerIdAssigner实现<br/>
 * 该WorkerIdAssigner会根据worker的HOST特征（IP+MAC）得到唯一的数据id
 *
 * @author luopeng
 * created at 2017/11/27
 */
public class ReusableWorkerIdAssigner implements WorkerIdAssigner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReusableWorkerIdAssigner.class);

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new NamingThreadFactory
            ("heartbeat", true));

    @Autowired
    private IdWorkerDAO idWorkerDAO;

    //interval for heartbeat
    private long heartbeatInterval = 60L;

    @PreDestroy
    public void close(){
        scheduledExecutorService.shutdown();
    }

    @Override
    public long assignWorkerId() {

        // build worker node entity
        IdWorkerEntity idWorker = buildIdWorker();

        // add worker node for new (update if exist record with same )
        idWorkerDAO.saveOrUpdate(idWorker);

        // query again (avoid id absent when update above)
        idWorker = idWorkerDAO.findWorker(idWorker.getHost(), idWorker.getHostMac());
        LOGGER.info("Add worker node:" + idWorker);

        // start to heartbeat to db for alive
        heartbeat();

        return idWorker.getWorkerId();
    }

    private void heartbeat() {
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                idWorkerDAO.heartbeat(getLocalAddress(), getLocalMacAddress());
            }
        }, heartbeatInterval, heartbeatInterval, TimeUnit.SECONDS);
    }

    private IdWorkerEntity buildIdWorker() {
        IdWorkerEntity entity = new IdWorkerEntity();
        Date currDate = new Date();
        entity.setLaunchTime(currDate);
        entity.setHeartbeatTime(currDate);
        entity.setCreateTime(currDate);
        entity.setUpdateTime(currDate);

        entity.setHost(getLocalAddress());
        entity.setHostMac(getLocalMacAddress());
        return entity;
    }

    private String getLocalMacAddress() {
        return NetUtils.getLocalMacAddress();
    }

    private String getLocalAddress() {
        return NetUtils.getLocalAddress();
    }

    public long getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public void setHeartbeatInterval(long heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }
}
