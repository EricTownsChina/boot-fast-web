package priv.eric.bc.entity;

import lombok.Data;

/**
 * @author Eric 840017241@qq.com
 * @date 2021-08-19 0:28
 * <p>
 * desc:
 */
@Data
public class Cinema {

    /**
     * ID
     */
    private Integer seatId;

    /**
     * free
     */
    private Integer free;

    /**
     * 描述
     */
    private String desc;

//    private Cinema(Builder builder) {
//        this.seatId = builder.seatId;
//        this.free = builder.free;
//        this.desc = builder.desc;
//    }
//
//    private static class Builder {
//        private Integer seatId;
//        private Integer free;
//        private String desc;
//
//        public Builder() { }
//
//        public Builder setSeatId(Integer seatId) {
//            this.seatId = seatId;
//            return this;
//        }
//
//        public Builder setFree(Integer free) {
//            this.free = free;
//            return this;
//        }
//
//        public Builder setDesc(String desc) {
//            this.desc = desc;
//            return this;
//        }
//
//        public Cinema build() {
//            return new Cinema(this);
//        }
//    }
//
//    public static Builder n() {
//        return new Builder();
//    }

}
