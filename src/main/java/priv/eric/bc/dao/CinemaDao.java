package priv.eric.bc.dao;


import priv.eric.bc.entity.Cinema;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-20 15:21
 * <p>
 * desc:
 */
public interface CinemaDao {

    /**
     * 通过ID获取Cinema
     *
     * @param id ID
     * @return Cinema
     */
    Cinema getCinemaById(Integer id);

    /**
     * 开放一个电影院
     *
     * @param id 电影院ID
     * @return 操作行数
     */
    Integer openCinema(Integer id);

    /**
     * 修改一个电影院信息
     *
     * @param cinema 要修改的信息
     * @return 操作行数
     */
    Integer updateCinema(Cinema cinema);

}
