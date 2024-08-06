/**
 * 파일 업로드, 삭제, 조회 공통 기능
 *
 */

const fileManager = {
    /**
     * 파일 업로드
     *
     */
    upload(file, gid, location) {

    },
    /**
     * 파일 삭제
     *
     */
    delete() {

    },
    /**
     * 파일 조회
     *
     */
    search() {

    }
};

window.addEventListener("DOMContentLoaded", function () {
    // 파일 업로드 버튼 이벤트 처리 S
    const fileUploads = document.getElementsByClassName("fileUploads");
    const fileEl = document.createElement("input");
    fileEl.type = 'file';
    fileEl.multiple = true;
    // 동적으로 요소 생성
    for(const el of fileUploads) {
        el.addEventListener("click", function () {
            fileEl.value = ""; // 클릭했을때는 새로 올리는 것이니깐 선택 값 초기화
            delete fileEl.gid;
            delete fileEl.location;
            const dataset = this.dataset;
            fileEl.gid = dataset.gid;
            if(dataset.location) fileEl.location = dataset.location;

            fileEl.click();
        });
    } // 파일 업로드 버튼 이벤트 처리 E

    // 파일 업로드 처리
    fileEl.addEventListener("change", function (e) {
        const files = e.target.files;
        fileManager.upload(files, fileEl.gid, fileEl.location);
    });
});