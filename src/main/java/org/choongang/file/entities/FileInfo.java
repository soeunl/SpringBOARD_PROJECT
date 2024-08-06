package org.choongang.file.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.global.entities.BaseMemberEntity;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo extends BaseMemberEntity { // 본인이 올린 것만 삭제하기 위해 BaseMemberEntity를 상속 받아 통제한다
    @Id
    @GeneratedValue
    private Long seq; // 서버에 업로드 될 파일 이름 - seq.확장자

    @Column(length = 45, nullable = false)
    private String gid = UUID.randomUUID().toString(); // 그룹 ID

    @Column(length = 45)
    private String location; // 그룹 안에 세부 위치 설정

    @Column(length = 80, nullable = false)
    private String fileName; // 실제 다운은 이걸로 받아야 한다. 실제 파일 이름

    @Column(length = 30)
    private String extension; // 파일 확장자

    @Column(length = 80)
    private String contentType;
    
    private boolean done; // 그룹 작업 완료 여부 (그룹 작업이 완료 되어야 파일이 저장)

    @Transient
    private String fileUrl; // 파일 접근 URL

    @Transient
    private String filePath; // 파일 업로드 경로. DB에는 반영되지 않고 내부에서 사용할 목적
}
