import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.back.App;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

    static App app;
    static ByteArrayOutputStream out;


    @BeforeEach
    void beforeEach() {
        Path dirPath = Paths.get("db/wiseSaying");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path file : stream) {
                Files.deleteIfExists(file);
            }
            System.out.println("모든 파일 삭제 완료!");
        } catch (IOException e) {
            System.err.println("삭제 중 오류 발생: " + e.getMessage());
        }
        app = new App();
        out = TestUtil.setOutToByteArray();
    }

    @AfterEach
    void afterEach() {
        TestUtil.clearSetOutToByteArray(out);
    }

    @Test
    void 등록() throws IOException {
        Scanner sc = TestUtil.genScanner("""
            등록
            현재를 사랑하라.
            작자미상
            종료
            """);
        app.setSc(sc);
        app.run();
        String output = out.toString();
        assertThat(output)
            .contains("명언 :")
            .contains("작가 :")
            .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    void 목록() throws IOException {
        Scanner sc = TestUtil.genScanner("""
            등록
            현재를 사랑하라.
            작자미상
            목록
            종료
            """);
        app.setSc(sc);
        app.run();
        String output = out.toString();
        assertThat(output)
            .contains("""
                번호 / 작가 / 명언
                ----------------------
                                """)
            .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    void 삭제() throws IOException {
        Scanner sc = TestUtil.genScanner("""
            등록
            현재를 사랑하라.
            작자미상
            삭제?id=1
            목록
            삭제?id=1
            종료
            """);
        app.setSc(sc);
        app.run();
        String output = out.toString();
        assertThat(output)
            .contains("""
                번호 / 작가 / 명언
                ----------------------
                                """)
            .contains("1번 명언이 삭제되었습니다.")
            .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
            .contains("1번 명언은 존재하지 않습니다.\n");
    }

    @Test
    void 수정() throws IOException {
        Scanner sc = TestUtil.genScanner("""
            등록
            현재를 사랑하라.
            작자미상
            수정?id=1
            현재와 자신을 사랑하라.
            홍길동
            목록
            수정?id=2
            종료
            """);
        app.setSc(sc);
        app.run();
        String output = out.toString();
        assertThat(output)
            .contains("""
                번호 / 작가 / 명언
                ----------------------
                                """)
            .contains("1 / 홍길동 / 현재와 자신을 사랑하라.")
            .contains("2번 명언은 존재하지 않습니다.");
    }
}

