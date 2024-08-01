package org.choongang.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseMemberEntity extends BaseEntity {
    // 회원 정보를 수정할 때 회원 정보 확인용으로
    // 이 값이 필요 없는 경우에는 쓰지 않아도 됩니다~

    @CreatedBy
    @Column(length = 65, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(length = 65, insertable = false)
    private String modifiedBy;
}
