package org.choongang.file.services;


import lombok.RequiredArgsConstructor;
import org.choongang.file.constants.FileStatus;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.repositories.FileInfoRepository;
import org.choongang.global.exceptions.script.UnAuthorizedException;
import org.choongang.member.MemberUtil;
import org.choongang.member.entities.Member;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDeleteService {
    private final FileInfoService infoService;
    private final FileInfoRepository infoRepository;
    private final MemberUtil memberUtil;
    // 비회원인 경우에는 삭제하는 권한도 주기 위해 비밀번호 인증 같은 것을 한다.

    public FileInfo delete(Long seq) {
        FileInfo data = infoService.get(seq);
        String filePath = data.getFilePath();
        String createdBy = data.getCreatedBy(); // 업로드한 회원 이메일

        Member member = memberUtil.getMember();
        if ( !memberUtil.isAdmin() && StringUtils.hasText(createdBy) && memberUtil.isLogin() && !member.getEmail().equals(createdBy)) {
            throw new UnAuthorizedException();
        }

        // 파일 삭제
        File file = new File(filePath);
        if(file.exists()) { // 항상 있는지 확인하고 삭제한다.
            file.delete();
        }

        // 파일 정보 삭제
        infoRepository.delete(data);
        infoRepository.flush();

        return data;
    }

    // 게시글을 삭제할 때 미완료된 파일이 남지 않도록 싹 다 삭제한다.
    public List<FileInfo> delete(String gid, String location, FileStatus status) {
        List<FileInfo> items = infoService.getList(gid, location, status);
        items.forEach(i -> delete(i.getSeq()));

        return items;
    }

    public List<FileInfo> delete(String gid, String location) {
        return delete(gid, location, FileStatus.ALL);
    }

    public List<FileInfo> delete(String gid) {
        return delete(gid, null);
    }
}
