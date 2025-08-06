package org.back;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {

    private  final WiseSayingController wiseSayingController = new WiseSayingController();
    public  Scanner sc = new Scanner(System.in);

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public  void run() throws IOException {

        System.out.println("== 명언 앱 ==");

        String command = "";

        while (!command.equals("종료")) {
            System.out.print("명령) ");
            command = sc.nextLine().trim();

            if (command.equals("등록")) {
                System.out.print("명언 : ");
                String saying = sc.nextLine().trim();

                System.out.print("작가 : ");
                String author = sc.nextLine().trim();
                wiseSayingController.createSaying(saying, author);
            } else if (command.equals("목록")) {
                List<WiseSaying> wiseSayings = wiseSayingController.sayingList();
                System.out.print("""
               번호 / 작가 / 명언
               ----------------------
                               """);
                for (WiseSaying entity : wiseSayings) {
                    System.out.println(
                        entity.getId() + " / " + entity.getAuthor() + " / " + entity.getSaying());
                }
            } else if (command.startsWith("삭제")) {
                String[] split = command.split("=");
                Long id = Long.parseLong(split[1]);
                wiseSayingController.deleteSaying(id);
            } else if (command.startsWith("수정")) {
                String[] split = command.split("=");
                Long id = Long.parseLong(split[1]);
                WiseSaying wiseSaying = wiseSayingController.readWiseSaying(id);
                if(wiseSaying != null){
                    System.out.println("명언(기존) : " + wiseSaying.getSaying());
                    System.out.print("명언 : ");
                    String newSaying = sc.nextLine();
                    System.out.println("작가(기존) : " + wiseSaying.getAuthor());
                    System.out.print("작가 : ");
                    String newAuthor = sc.nextLine();
                    wiseSayingController.modify_saying(id, newSaying, newAuthor);
                }else{
                    System.out.println(id +"번 명언은 존재하지 않습니다.");
                }
            } else if (command.equals("빌드")) {
                wiseSayingController.build();
                System.out.println("data.json 파일의 내용이 갱신되었습니다.\n");
            }
        }
        close();
    }

    public  void close() {
        WiseSayingRepository.close();
        System.out.println("명령) 종료");
    }
}



