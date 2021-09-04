package priv.eric.bc.controller;

import org.springframework.web.bind.annotation.*;
import priv.eric.bc.entity.Cinema;
import priv.eric.bc.service.CinemaService;
import priv.eric.kit.sys.entity.Resp;

import javax.annotation.Resource;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-21 12:18
 * <p>
 * desc:
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Resource
    private CinemaService cinemaService;

    @GetMapping("/get/{id}")
    public Resp getCinema(@PathVariable Integer id) {
        return Resp.ok(cinemaService.getCinemaById(id));
    }

    @PostMapping("/openAndUpdate")
    public Resp openCinema(Cinema cinema) {
        if (cinemaService.openAndUpdate(cinema)) {
            return Resp.ok();
        }
        return Resp.fail();
    }

    @PostMapping("/setSeats")
    public Resp setSeats(Integer cinemaId, Integer seatNum) {
        cinemaService.setSeats(cinemaId, seatNum);
        return Resp.ok();
    }

    @PostMapping("/bookingOne/{id}")
    public Resp openCinema(@PathVariable Integer id) {
        // 其他业务操作
        // 电影院座位-1
        cinemaService.reduceOne(id);
        return Resp.ok();
    }


}
