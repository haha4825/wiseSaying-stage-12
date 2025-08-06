package org.back;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Path id_path = Paths.get("./db/wiseSaying/lastId.txt");
    private static Long id;
    public static void main(String[] args) throws IOException {
        App.run();
//        if(Files.exists(id_path)){
//            id = Long.parseLong(Files.readString(id_path));
//        }else{
//            id = 1L;
//        }
//
//        System.out.println("== 명언 앱 ==");
//
//        String command = "";
//        Scanner sc = new Scanner(System.in);
//
//        while(!command.equals("종료")){
//            System.out.print("명령) ");
//            command = sc.nextLine().trim();
//
//            if (command.equals("등록")) {
//                register_saying(sc);
//            }else if(command.equals("목록")){
//                saying_list();
//            }else if(command.startsWith("삭제")){
//                delete_saying(command);
//            } else if (command.startsWith("수정")) {
//                modify_saying(command, sc);
//            } else if(command.equals("빌드")){
//                build();
//            }
//        }
//        Files.createDirectories(id_path.getParent());
//        Files.writeString(id_path, String.valueOf(id));
//        System.out.println("명령) 종료\n");
//    }
//    private static void build() throws IOException {
//        String data = "";
//
//        for(int i =1; i<id; i++){
//            Path path = Paths.get("./db/wiseSaying/{" + i + "}.json");
//            if (Files.exists(path)) {
//                String json = Files.readString(path);
//                data +=  "\t"+ json.replace("}", "\t}") + ",\n";
//            }
//        }
//        if (data.endsWith(",\n")) {
//            data = data.substring(0, data.length() - 2);
//        }
//        data = "[\n" + data + "\n]";
//        Path path = Paths.get("./db/wiseSaying/data.json");
//        Files.createDirectories(path.getParent());
//        Files.writeString(path, data);
//        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
//    }
//
//    private static void modify_saying(String command, Scanner sc) throws IOException {
//        String[] split = command.split("=");
//        Long target_id = Long.parseLong(split[1]);
//
//        Path path = Paths.get("./db/wiseSaying/{" + target_id + "}.json");
//
//        if (Files.exists(path)) {
//            String json = Files.readString(path);
//
//            Pattern pattern = Pattern.compile(
//                "\"id\":\\s*(\\d+),\\s*\"content\":\\s*\"([^\"]+)\",\\s*\"author\":\\s*\"([^\"]+)\""
//            );
//            Matcher matcher = pattern.matcher(json);
//
//            if (matcher.find()) {
//                Long id = Long.parseLong(matcher.group(1));
//                String content = matcher.group(2);
//                String author = matcher.group(3);
//                System.out.println("명언(기존) : " + content);
//                System.out.print("명언 : ");
//                String newSaying = sc.nextLine();
//                System.out.println("작가(기존) : " + author);
//                System.out.print("작가 : ");
//                String newAuthor = sc.nextLine();
//                String jsonString = String.format("""
//           {
//               "id": %s,
//               "content": "%s",
//               "author": "%s"
//           }""", id, newSaying, newAuthor);
//                    Files.writeString(path, jsonString, Charset.forName("UTF-8"));
//                } else {
//                    System.out.println("파싱 실패");
//                }
//            } else {
//                System.out.println(target_id + "번 명언은 존재하지 않습니다.");
//            }
//        }
//
//    private static void delete_saying(String command) throws IOException {
//        String[] split = command.split("=");
//        Long id = Long.parseLong(split[1]);
//        Path path = Paths.get("./db/wiseSaying/{" + id + "}.json");
//
//        if(Files.exists(path)) {
//            Files.delete(path);
//            System.out.println(id + "번 명언이 삭제되었습니다.");
//        }else{
//            System.out.println(id + "번 명언은 존재하지 않습니다.");
//        }
//    }
//
//    private static void saying_list() throws IOException {
//        System.out.print("""
//               번호 / 작가 / 명언
//               ----------------------
//                               """);
//        Long copied_id = id - 1;
//        while (copied_id > 0) {
//            Path path = Paths.get("./db/wiseSaying/{" + copied_id + "}.json");
//            if (Files.exists(path)) {
//                String json = Files.readString(path);
//                Pattern pattern = Pattern.compile(
//                    "\"id\":\\s*(\\d+),\\s*\"content\":\\s*\"([^\"]+)\",\\s*\"author\":\\s*\"([^\"]+)\""
//                );
//                Matcher matcher = pattern.matcher(json);
//                if (matcher.find()) {
//                    Long id = Long.parseLong(matcher.group(1));
//                    String content = matcher.group(2);
//                    String author = matcher.group(3);
//                    System.out.println(
//                        copied_id + " / " + author + " / " + content);
//                } else {
//                    System.out.println("파싱 실패");
//                }
//            }
//            copied_id--;
//        }
//    }
//
//    private static void register_saying(Scanner sc) throws IOException {
//        String wiseSaying = "";
//        String author = "";
//
//        System.out.print("명언 : ");
//        wiseSaying = sc.nextLine().trim();
//
//        System.out.print("작가 : ");
//        author = sc.nextLine().trim();
//
//        Path path = Paths.get("./db/wiseSaying/{" + id + "}.json");
//        String jsonString = String.format("""
//           {
//               "id": %s,
//               "content": "%s",
//               "author": "%s"
//           }""", id, wiseSaying, author);
//        Files.createDirectories(path.getParent()); // 디렉토리 없으면 생성 (writeString으로 파일에
//        // 작성시 파일이 없을 땐 생성해서 작성하지만 디렉토리가 없으면 오류)
//        Files.writeString(path, jsonString, Charset.forName("UTF-8"));
//
//
//        System.out.println(id +"번 명언이 등록되었습니다.");
//        id++;
    }
}

