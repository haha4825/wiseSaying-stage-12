package org.back;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WiseSayingRepository {

    private static final Path id_path = Paths.get("./db/wiseSaying/lastId.txt");
    private static Long id;

    private static final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public static WiseSayingRepository getInstance(){
        return wiseSayingRepository;
    }


    public Long save(WiseSaying entity) {
        try {
            String jsonString = String.format("""
                {
                    "id": %s,
                    "content": "%s",
                    "author": "%s"
                }""", id, entity.getSaying(), entity.getAuthor());
            Path path = Paths.get("./db/wiseSaying/{" + id + "}.json");
            Files.createDirectories(path.getParent()); // 디렉토리 없으면 생성 (writeString으로 파일에
            // 작성시 파일이 없을 땐 생성해서 작성하지만 디렉토리가 없으면 오류)
            Files.writeString(path, jsonString, Charset.forName("UTF-8"));
            id++;
            return id - 1;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public WiseSaying findById(Long id) {
        Path path = Paths.get("./db/wiseSaying/{" + id + "}.json");
        if (Files.exists(path)) {
            return fileToEntity(id);
        }
        return null;
    }

    public List<WiseSaying> findAll() {
        List<WiseSaying> result = new ArrayList<>();
        for(Long i = 1L; i<id; i++) {
            Path path = Paths.get("./db/wiseSaying/{" + i + "}.json");
            if (Files.exists(path)) {
                WiseSaying entity = fileToEntity(i);
                result.add(entity);
            }
        }
        return result;
    }

    private WiseSaying fileToEntity(Long id) {
        try {
            Path path = Paths.get("./db/wiseSaying/{" + id + "}.json");
            String json = Files.readString(path);
            Pattern pattern = Pattern.compile(
                "\"id\":\\s*(\\d+),\\s*\"content\":\\s*\"([^\"]+)\",\\s*\"author\":\\s*\"([^\"]+)\""
            );
            Matcher matcher = pattern.matcher(json);
            if (matcher.find() && Long.parseLong(matcher.group(1)) == id) {
                String content = matcher.group(2);
                String author = matcher.group(3);
                return new WiseSaying(id, content, author);
            }
            throw new IllegalStateException("파싱 실패");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Long deleteById(Long id) {
        try {
            Path path = Paths.get("./db/wiseSaying/{" + id + "}.json");
            Files.delete(path);

            return id;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean existById(Long id) {
        Path path = Paths.get("./db/wiseSaying/{" + id + "}.json");
        return Files.exists(path);
    }

    public Long update(Long id, WiseSaying entity) {
        try {
            Path path = Paths.get("./db/wiseSaying/{" + id + "}.json");
            String jsonString = String.format("""
                {
                    "id": %s,
                    "content": "%s",
                    "author": "%s"
                }""", id, entity.getSaying(), entity.getAuthor());
            Files.writeString(path, jsonString, Charset.forName("UTF-8"));
            return id;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private WiseSayingRepository() {
        try {
            if (Files.exists(id_path)) {
                id = Long.parseLong(Files.readString(id_path));
            } else {
                id = 1L;
            }
        } catch (IOException e) {
            System.out.println("레포지토리 id 초기화 실패");
            e.printStackTrace();
        }
    }

    public void buildFile(String data) {
        try {
            Path path = Paths.get("./db/wiseSaying/data.json");
            Files.createDirectories(path.getParent());
            Files.writeString(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        try {
            Files.createDirectories(id_path.getParent());
            Files.writeString(id_path, String.valueOf(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
