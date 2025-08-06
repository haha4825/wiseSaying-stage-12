package org.back;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository = WiseSayingRepository.getInstance();

    public Long register(String wiseSaying, String author){
        WiseSaying entity = new WiseSaying(wiseSaying, author);
        return wiseSayingRepository.save(entity);
    }

    public List<WiseSaying> sayingList() {
        return wiseSayingRepository.findAll();
    }

    public Long deleteSaying(Long id) {
        if (wiseSayingRepository.existById(id)) {
            return wiseSayingRepository.deleteById(id);
        }
        return null;
    }

    public Boolean existById(Long id) {
        return wiseSayingRepository.existById(id);
    }

    public WiseSaying readSaying(Long id) {
        return wiseSayingRepository.findById(id);
    }

    public Long modifySaying(Long id, String saying, String author) {
        if(wiseSayingRepository.existById(id)){
            WiseSaying wiseSaying = new WiseSaying(id, saying, author);
            return wiseSayingRepository.update(id, wiseSaying);
        }
        return null;
    }

    public void build(){
        List<WiseSaying> wiseSayings = wiseSayingRepository.findAll();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");
        for (int i = 0; i < wiseSayings.size(); i++) {
            WiseSaying ws = wiseSayings.get(i);
            jsonBuilder.append(String.format("""
            {
                "id": %d,
                "content": "%s",
                "author": "%s"
            }""", ws.getId(), ws.getSaying(), ws.getAuthor()));

            if (i < wiseSayings.size() - 1) {
                jsonBuilder.append(",\n");
            } else {
                jsonBuilder.append("\n");
            }
        }

        jsonBuilder.append("]");
        wiseSayingRepository.buildFile(jsonBuilder.toString());
    }

}
