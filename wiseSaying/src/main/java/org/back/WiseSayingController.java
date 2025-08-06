package org.back;

import java.util.List;

public class WiseSayingController {

    private final WiseSayingService wiseSayingService = new WiseSayingService();

    public void createSaying(String saying, String author){
        System.out.println(wiseSayingService.register(saying, author) + "번 명언이 등록되었습니다.");
    }

    public List<WiseSaying> sayingList() {
        return wiseSayingService.sayingList();
    }

    public void deleteSaying(Long id) {
        if(wiseSayingService.deleteSaying(id) == null){
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    public WiseSaying readWiseSaying(Long id) {
        return wiseSayingService.readSaying(id);
    }
    public void modify_saying(Long id, String saying, String author) {
        wiseSayingService.modifySaying(id, saying, author);
    }

    public Boolean existById(Long id) {
        return wiseSayingService.existById(id);
    }

    public void build() {
        wiseSayingService.build();
    }

}
