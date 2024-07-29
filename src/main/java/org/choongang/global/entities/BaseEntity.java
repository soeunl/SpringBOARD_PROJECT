package org.choongang.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // 테이블로 매핑되지 않고, 하위 엔티티에 상속되어 사용되는 클래스임을 나타냄
@EntityListeners(AuditingEntityListener.class)
// JPA Auditing 기능을 활성화하여 엔티티의 생성 및 수정 시간을 자동으로 관리
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false) // 수정 되지 않도록 설정
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;
}
