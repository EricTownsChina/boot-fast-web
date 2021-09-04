package priv.eric.bc.service;

import priv.eric.bc.entity.Cinema;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-21 10:57
 * <p>
 * desc:
 */
public interface CinemaService {

    /**
     * 根据ID获取数据
     *
     * @param id id
     * @return Cinema
     */
    Cinema getCinemaById(Integer id);

    /**
     * 开放一个电影院
     *
     * @param cinema 电影院信息
     */
    void openAndUpdateCinema(Cinema cinema);

    /**
     * 开放并修改一个电影院
     *
     * @param cinema 电影院信息
     * @return 操作行数
     */
    boolean openAndUpdate(Cinema cinema);

    /**
     * 设置一个电影院的座位数
     *
     * @param cinemaId 电影院ID
     * @param seatNum  座位数
     */
    void setSeats(Integer cinemaId, Integer seatNum);

    /**
     * 减少一个电影院的座位数
     *
     * @param cinemaId 电影院ID
     */
    void reduceOne(Integer cinemaId);

}
