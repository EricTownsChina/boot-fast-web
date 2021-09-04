package priv.eric.bc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.eric.bc.dao.CinemaDao;
import priv.eric.bc.entity.Cinema;
import priv.eric.bc.service.CinemaService;
import priv.eric.kit.sys.util.RedisUtil;


import javax.annotation.Resource;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-21 12:17
 * <p>
 * desc: 电影院service实现类
 */
@Slf4j
@Service
public class CinemaServiceImpl implements CinemaService {

    private static final String SEATS_SUFFIX = "_seats";
    private static final String LOCK_KEY_SUFFIX = "_lock";
    @Resource
    private CinemaDao cinemaDao;
    @Resource
    private RedisUtil redisUtil;
    /**
     * 自注入, 解决同类方法事务问题
     */
    @Resource
    private CinemaService cinemaService;

    @Override
    public Cinema getCinemaById(Integer id) {
        return cinemaDao.getCinemaById(id);
    }

    @Override
    public void setSeats(Integer cinemaId, Integer seatNum) {
        redisUtil.set(seatsIndexKey(cinemaId), String.valueOf(seatNum));
    }

    @Override
    public void reduceOne(Integer cinemaId) {

    }

    //////////////////////////// spring事务的测试方法 ////////////////////////////

    @Override
    public boolean openAndUpdate(Cinema cinema) {
        // 通过自注入的bean进行本类事务方法的调用
        cinemaService.openAndUpdateCinema(cinema);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void openAndUpdateCinema(Cinema cinema) {
        // 调用同类的方法
        try {
            update(cinema);
            open(cinema.getSeatId());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void open(Integer id) {
        cinemaDao.openCinema(id);
        throw new RuntimeException();
    }

    public void update(Cinema cinema) {
        cinemaDao.updateCinema(cinema);
    }

    ////////////////////////////////////////////////////////////////////////////

    private String seatsIndexKey(Integer cinemaId) {
        return cinemaId + SEATS_SUFFIX;
    }

    private String seatsLockKey(String key) {
        return key + LOCK_KEY_SUFFIX;
    }


}
